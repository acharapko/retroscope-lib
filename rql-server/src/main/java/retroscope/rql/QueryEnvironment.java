package retroscope.rql;

import java.util.*;

import retroscope.log.LogOutTimeBoundsException;
import retroscope.RetroscopeException;
import retroscope.hlc.Timestamp;
import retroscope.log.*;
import retroscope.rql.errors.RQLRunTimeException;
import retroscope.rql.errors.RQLRunTimeWarning;
import retroscope.rql.memory.Placeholder;
import retroscope.rql.memory.SimpleSymbol;
import retroscope.rql.syntaxtree.expression.Expression;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;
import retroscope.rql.syntaxtree.link.ForAllLink;
import retroscope.rql.syntaxtree.link.Link;
import retroscope.rql.syntaxtree.link.LinkLock;
import retroscope.rql.syntaxtree.link.Links;

/**
 * Created by Aleksey on 12/23/2016.
 * This class holds various operational data for RQL syntax tree,
 * such as the symbol table currently in use, logs retrieved and
 * the consistent cuts ready to be emitted.
 */
public abstract class QueryEnvironment extends Environment {

    protected ArrayList<Placeholder> placeholders;
    private ArrayList<Link> linkedNodes;
    private HashMap<Integer, LinkLock> placeholdersToLinkLocks;
    private int forAllLinksCount = 0;
    private int numPlaceholders = 0;
    //log stuff
    protected ArrayList<RQLLog> logs;
    private String defaultLog = "";
    //cut stuff
    protected ArrayList<GlobalCut> tempGlobalCuts;
    protected ArrayList<GlobalCut> emittedGlobalCuts;
    protected ArrayList<String> emittedOut;

    public QueryEnvironment() {
        super();
        logs = new ArrayList<RQLLog>();
        placeholders = new ArrayList<Placeholder>();
        linkedNodes = new ArrayList<Link>();
        placeholdersToLinkLocks = new HashMap<Integer, LinkLock>();
    }

    public Placeholder getPlaceholder(int id) {
        return placeholders.get(id);
    }

    public int addPlaceholder(String name) {
        int id = numPlaceholders++;
        Placeholder p = new Placeholder(id, name);
        placeholders.add(p);
        //placeholders.add(variable.getName());
        //symbolTable.put(name, new HashMap<Integer, Set<DataEntry<RQLItem>>>());
        return id;
    }

    public boolean hasRunTimeWarnings() {
        return warnings.size() >= 1;
    }

    public void  setLinkedNodes(Links linkedNodes) {
        if (linkedNodes != null) {
            ArrayList<Link> links = linkedNodes.getLinks();
            for (Link link : links) {
                link.getLinkedNode().setCardinality(0);
                for (int i = 0; i < link.getPlaceholders().length; i++) {
                    placeholdersToLinkLocks.put(link.getPlaceholders()[i], link.getLinkedNode());
                    link.getLinkedNode().oneUp();
                }
                addLinkedNodes(link);
            }
        }
    }

    public void addLinkedNodes(ArrayList<Link> linkedNodes) {
        this.linkedNodes.addAll(linkedNodes);
        for (Link l : linkedNodes) {
            if (l instanceof ForAllLink) {
                forAllLinksCount++;
            }
        }
    }

    public void addLinkedNodes(Link linkedNodes) {
        this.linkedNodes.add(linkedNodes);
        if (linkedNodes instanceof ForAllLink) {
            forAllLinksCount++;
        }
    }

    public void resetLinkedNodes() {
        linkedNodes = new ArrayList<Link>();
        forAllLinksCount = 0;
    }

    /**
     * This function retrieves the log slices with given names bounded by the start and end times
     * @param retrieveParam RQLRetrieveParam of log names to retrieve
     */
    public abstract void retrieveRemoteLogs(RQLRetrieveParam retrieveParam);

    public abstract void retrieveSingleCut(RQLRetrieveParam retrieveParam);

    public void resetEmits() {
        emittedGlobalCuts = new ArrayList<GlobalCut>();
        tempGlobalCuts = new ArrayList<GlobalCut>();
        emittedOut = new ArrayList<String>();
    }

    private void checkErrors() throws RQLRunTimeException {
        if (logs.size() == 0) {
            throw new RQLRunTimeException("Cannot process query with 0 fetched logs");
        }
    }


    /**
     *
     * @param condition Expression that must be satisfied to emit the cut
     */
    public void findCutsByCondition(Expression condition) {
        /*
        -------------------------------------------------------------------------------------
        (1) get all logs
        (2) add all log tails to priorityQueue by HLC time
        (3) repeat until priorityQueue is empty
        (4)     current = priorityQueue.poll()
        (5)     currentHLC = HLC time of current
        (6)     roll to snapshot at currentHLC on each log
        (7)     evaluate conditions on global snapshot consisting of individual local snapshots
        (8)     if (conditions == TRUE)
        (9)         emit snapshot
                end if
        (10)    add current.getPrevious() to priorityQueue
             end repeat
        ----------------------------------------------------------------------------------------
        */
        // (1) we assume logs have been retrieved by this time
        // (2)

        try {
            checkErrors();
        } catch (RQLRunTimeException re) {
            this.addRunTimeException(re);
            return;
        }



        PriorityQueue<LogEntry<String, RQLItem>> q =
                new PriorityQueue<LogEntry<String, RQLItem>>(logs.size(), new Comparator<LogEntry<String, RQLItem>>() {
                    public int compare(LogEntry<String, RQLItem> o1, LogEntry<String, RQLItem> o2) {
                        return -o1.getTime().compareTo(o2.getTime());
                    }
                });
        int snapshotIDs[] = new int[logs.size()];
        for (RQLLog log : logs) {
            if (log.getLength() > 0) {
                q.add(log.getTail());
            }
        }
        // (3)
        while (q.size() > 0) {
            // (4)
            LogEntry<String, RQLItem> current = q.poll();
            // (5)
            Timestamp currentHLC = current.getTime();

            // (6)
            GlobalCut tempCut = new GlobalCut(currentHLC);
            for (int i = 0; i < logs.size(); i++) {
                try {
                    if (currentHLC.compareTo(logs.get(i).getHead().getTime()) >= 0
                            && currentHLC.compareTo(logs.get(i).getTail().getTime()) <= 0) {
                        if (snapshotIDs[i] > 0) { //we already know a snapshot id, so just roll
                            if (logs.get(i).getSnapshot(snapshotIDs[i]).getAssociatedLogEntry().getTime().compareTo(currentHLC) > 0) {
                                logs.get(i).rollSnapshot(snapshotIDs[i], currentHLC);
                            }
                            tempCut.addLocalSnapshot(logs.get(i).getName(), logs.get(i).getNodeId(), logs.get(i).getSnapshotSetMap(snapshotIDs[i]));
                        } else {
                            //new snapshot for us
                            snapshotIDs[i] = logs.get(i).makeSnapshot(currentHLC);
                            if (snapshotIDs[i] > 0) {
                                //add only valid local snapshots ot the tempCut.
                                tempCut.addLocalSnapshot(logs.get(i).getName(), logs.get(i).getNodeId(), logs.get(i).getSnapshotSetMap(snapshotIDs[i]));
                            }
                        }
                    }
                } catch (LogOutTimeBoundsException lotbe) {
                    RQLRunTimeWarning w = new RQLRunTimeWarning(
                            RQLRunTimeWarning.WarningType.LOG_OUT_OF_TIME_BOUNDS,
                            logs.get(i).getClass().getName() + logs.get(i).hashCode(),
                            "log " + logs.get(i).getName() + " was out of time bounds (first out of time bound cut @ " + currentHLC +")"
                    );
                    addRunTimeWarning(w);
                } catch (RetroscopeException re) {
                    RQLRunTimeWarning w = new RQLRunTimeWarning(
                            RQLRunTimeWarning.WarningType.RETROSCOPE_EXCEPTION,
                            logs.get(i).getClass().getName() + logs.get(i).hashCode(),
                            "Retroscope Exception on log " + logs.get(i).getName() + " @ " + currentHLC
                    );
                    addRunTimeWarning(w);
                }
            }

            // (7), (8) and (9) are here
            if (forAllLinksCount == 0) {
                evalCutOnCondition(tempCut, condition);
            } else {
                evalCutOnConditionForAllNodes(tempCut, condition);
            }
            // (10)
            if (current.getPrev() != null) {
                q.add(current.getPrev());
            }
        }
    }

    public void checkTempCutsOnCondition(Expression condition) {
        for(GlobalCut cut : tempGlobalCuts) {
            if (forAllLinksCount == 0) {
                evalCutOnCondition(cut, condition);
            } else {
                evalCutOnConditionForAllNodes(cut, condition);
            }
        }
    }

    private void evalCutOnCondition(GlobalCut tempCut, Expression condition) {
        // (7) at this time we have a consistent cut at the tempCut object
        // first we update symbol table with new values. The symbol table
        // should be the same as the one used by the vars in the condition expressio
        if (condition == null) { //there is no condition
            addToEmitList(tempCut);
            return;
        }

        boolean shortCircuit = false;
        setSymbolTableAtCut(tempCut);
        //now iterate
        while (!shortCircuit) {
            if (setNextPlaceholders()) {
                shortCircuit = true;
            }
            try {
                condition.evaluate();
            } catch (IllegalExpressionException e) {
                addRunTimeException(e);
            }

            // (8)
            //if any of the results is true, we emit the cut
            if (condition.getValue().getIntVal() != 0) {
                // (9)
                //clone tempCut, since has rolling snapshot data which will be mutated as the snapshot rolls
                addToEmitList(tempCut);
                shortCircuit = true;
            }
        }
    }

    private void evalCutOnConditionForAllNodes(GlobalCut tempCut, Expression condition) {
        // (7) at this time we have a consistent cut at the tempCut object
        // first we update symbol table with new values. The symbol table
        // should be the same as the one used by the vars in the condition expression
        Set<Integer> nodeIds = tempCut.getKnownNodes();
        for (Link l : linkedNodes) {
            if (l instanceof ForAllLink) {
                ((ForAllLink) l).resetSatisfied();
            }
        }
        boolean shortCircuit = false;
        setSymbolTableAtCut(tempCut);
        //now iterate
        while (!shortCircuit) {
            if (setNextPlaceholders()) {
                shortCircuit = true;
            }
            try {
                condition.evaluate();
            } catch (IllegalExpressionException e) {
                addRunTimeException(e);
            }

            // (8)
            //if any of the results is true, we mark them as satisfied for current node in out FALinks
            if (condition.getValue().getIntVal() != 0) {
                int satisfiedCount = 0;
                for (Link l : linkedNodes) {
                    if (l instanceof ForAllLink) {
                        ((ForAllLink) l).markSatisfied();
                        satisfiedCount = ((ForAllLink) l).satisfiedAll(nodeIds) ?  satisfiedCount + 1 : satisfiedCount;
                    }
                }

                // (9)
                //clone tempCut, since has rolling snapshot data which will be mutated as the snapshot rolls
                if (satisfiedCount == forAllLinksCount) {
                    addToEmitList(tempCut);
                    shortCircuit = true;
                }
            }
        }
    }

    public boolean setNextPlaceholders() {
        boolean reset = false;
        for (int i = 0; i < placeholders.size(); i++) {
            Placeholder p = placeholders.get(i);
            SimpleSymbol symbolVal = getSymbol(p.getSymbolName());
            if (symbolVal != null) {

                if (p.getItem() == null && p.getVersion() == 0) {
                    findItemForPlaceholder(p, symbolVal);
                } else {

                //if (p.getVersion() < symbolVal.size()) {
                    //p.setItem(symbolVal.get(p.getVersion()));
                    if (p.isReset()) {
                        boolean reachedEnd = findItemForPlaceholder(p, symbolVal);
                        if (reachedEnd) {
                            if (i > 0) {
                                placeholders.get(i - 1).setReset(true);
                            } else {
                                reset = true;
                            }
                            p.resetVersion();
                        } else {
                            if (i < placeholders.size() - 1) {
                                p.setReset(false);
                            }
                        }
                    }
                }
            } else {
                //by this time we already threw a warning out in the Variable object trying to get the memory
                if (i > 0) {
                    placeholders.get(i - 1).setReset(true);
                } else {
                    reset = true;
                }
                p.resetVersion();
            }
        }
        return reset;
    }

    /**
     *
     * @param p Placeholder placeholder to populate
     * @param symbolVal SimpleSymbol containing set of values a placeholder can take
     * @return returns true if reached the end of values this placeholder can take at this cut and need to reset to the beginning
     */
    private boolean findItemForPlaceholder(Placeholder p, SimpleSymbol symbolVal) {
        LinkLock ll = placeholdersToLinkLocks.get(p.getId());
        if (ll == null) {
            ll = new LinkLock();
        }
        while (p.getVersion() < symbolVal.size()) {
            if (ll.getCardinality() == 1 || ll.getLockedNode() == -1 || symbolVal.get(p.getVersion()).getNodeId() == ll.getLockedNode()) {
                p.setItem(symbolVal.get(p.getVersion()));
                //p.setReset(false);
                if (ll.getCardinality() > 1) {
                    ll.setLockedNode(symbolVal.get(p.getVersion()).getNodeId());
                }
                p.incrementVersion();
                return false;
            }
            p.incrementVersion();
        }
        ll.reset();
        p.setItem(null);
        return true;
    }

    public void setSymbolTableAtCut(GlobalCut tempCut) {
        // (7) at this time we have a consistent cut at the tempCut object
        // first we update symbol table with new values. The symbol table
        // should be the same as the one used by the vars in the condition expression

        ArrayList<RQLSetMap> tempSnaps = tempCut.getLocalSnapshots();
        ArrayList<String> snapNames = tempCut.getLocalSnapshotNames();
        ArrayList<Integer> nodeIds = tempCut.getNodeIds();

        clearSymbolTable(this);

        for (int ls = 0; ls < tempSnaps.size(); ls++) {
            Iterator<Map.Entry<String, Set<DataEntry<RQLItem>>>> it = tempSnaps.get(ls).entrySet().iterator();
            while (it.hasNext()) { //iterating key in the log
                Map.Entry<String, Set<DataEntry<RQLItem>>> pair = it.next();
                String varName = snapNames.get(ls) + "." + pair.getKey();
                SimpleSymbol vals = getSymbol(varName);
                if (vals == null) {
                    vals = new SimpleSymbol();
                    putSymbol(varName, vals);
                }
                if (pair.getValue().size() > 0) {
                    vals.set(nodeIds.get(ls), pair.getValue());
                }
            }
        }

        //reset the placeholders from previous cut
        for (Placeholder p : placeholders) {
            p.clearAll();
        }
        placeholders.get(placeholders.size() - 1).setReset(true);

        for (Link l : linkedNodes) {
            l.resetLink();
        }
    }

    public void updateSymbolTableWithLogName(String[] logIds) throws RQLRunTimeException {

        defaultLog = logIds[0];
        for (Placeholder p : placeholders) {
            if (!p.getSymbolName().contains(".")) {
                //we have no log identifier
                if (logIds.length > 1) {
                    throw new RQLRunTimeException("Parameter '" + p.getSymbolName() + "' is ambiguous: it may exist in more than one log");
                }
                p.setSymbolName(defaultLog + "." + p.getSymbolName());
            }
        }
    }

    private void addToEmitList(GlobalCut gc) {
        if (emittedGlobalCuts.size() == 0
                || emittedGlobalCuts.get(emittedGlobalCuts.size() - 1).getCutTime().compareTo(gc.getCutTime()) > 0) {
            emittedGlobalCuts.add(gc.clone());
        }
    }

    public ArrayList<GlobalCut> getEmittedGlobalCuts() {
        return emittedGlobalCuts;
    }

    public String getDefaultLogName() {
        return defaultLog;
    }

    public ArrayList<RQLLog> getLogs() {
        return logs;
    }


    /*---------------------------
    /* Output stuff
    /*---------------------------*/

    public String cutsToString() {
        StringBuilder sb = new StringBuilder();
        // Send all output to the Appendable object sb
        Formatter formatter = new Formatter(sb, Locale.US);

        if (getEmittedGlobalCuts().size() > 0) {
            for (GlobalCut cut : getEmittedGlobalCuts()) {
                sb.append(String.format("%80s", "").replace(" ", "-"));
                sb.append(System.getProperty("line.separator"));
                formatter.format("%38s %-15s", "Cut @ HLC :", cut.getCutTime().toString());
                sb.append(System.getProperty("line.separator"));

                Set<String> logs = new HashSet<String>();
                logs.addAll(cut.getLocalSnapshotNames());
                List<Integer> nodes = cut.getNodeIds();

                for (String logName : logs) {
                    sb.append(String.format("%80s", "").replace(" ", "-"));
                    sb.append(System.getProperty("line.separator"));
                    formatter.format("%42s %-30s", "Log : ", logName);
                    sb.append(System.getProperty("line.separator"));
                    for (int id : nodes) {
                        RHashMap<String, ?, RQLItem> tsnap = cut.getLocalSnapshot(logName, id);
                        if (tsnap != null) {
                            sb.append(String.format("%80s", "").replace(" ", "*"));
                            sb.append(System.getProperty("line.separator"));
                            formatter.format("%42s %-10d", "Node :", id);
                            sb.append(System.getProperty("line.separator"));
                            if (tsnap instanceof RetroMap) {
                                RetroMap<String, RQLItem> snap = (RetroMap<String, RQLItem>) tsnap;
                                Iterator<Map.Entry<String, DataEntry<RQLItem>>> it = snap.entrySet().iterator();
                                while (it.hasNext()) {
                                    Map.Entry<String, DataEntry<RQLItem>> pair = it.next();
                                    RQLItem item = pair.getValue().getValue();
                                    sb.append(item.toFriendlyString(pair.getKey()));
                                    sb.append(System.getProperty("line.separator"));
                                }
                            } else if (tsnap instanceof RQLSetMap) {
                                RQLSetMap snap = (RQLSetMap) tsnap;
                                Iterator<Map.Entry<String, Set<DataEntry<RQLItem>>>> it = snap.entrySet().iterator();
                                while (it.hasNext()) {
                                    Map.Entry<String, Set<DataEntry<RQLItem>>> pair = it.next();
                                    Set<DataEntry<RQLItem>> items = pair.getValue();
                                    for (DataEntry<RQLItem> item : items) {
                                        sb.append(item.getValue().toFriendlyString(pair.getKey()));
                                        sb.append(System.getProperty("line.separator"));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return sb.toString();
    }

}
