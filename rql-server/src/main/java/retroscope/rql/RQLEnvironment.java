package retroscope.rql;

import java.util.*;
import retroscope.RetroscopeException;
import retroscope.hlc.Timestamp;
import retroscope.log.*;
import retroscope.rql.syntaxtree.*;
import retroscope.rql.syntaxtree.link.Link;

/**
 * Created by Aleksey on 12/23/2016.
 * This class holds various operational data for RQL syntax tree,
 * such as the symbol table currently in use, logs retrieved and
 * the consistent cuts ready to be emitted.
 */
public abstract class RQLEnvironment {

    protected HashMap<String, Valuable> symbolTable;
    protected ArrayList<RQLDataMapLog> logs; // RQL only works with String Retroscopes
    protected ArrayList<GlobalCut> globalCuts;
    private ArrayList<RQLRunTimeException> exceptions;
    private ArrayList<RQLRunTimeWarning> warnings;
    private String defaultLog = "";

    public RQLEnvironment() {
        symbolTable = new HashMap<String, Valuable>();
        logs = new ArrayList<RQLDataMapLog>();
        globalCuts = new ArrayList<GlobalCut>();
        exceptions = new ArrayList<RQLRunTimeException>();
        warnings = new ArrayList<RQLRunTimeWarning>();

    }

    public HashMap<String, Valuable> getSymbolTable() {
        return symbolTable;
    }

    /*
    public void setSymbolTable(HashMap<String, RQLInterpreterValue> symbolTable) {
        this.symbolTable = symbolTable;
    }
    */

    public ArrayList<RQLRunTimeException> getExceptions() {
        return exceptions;
    }

    public void addRunTimeException(RQLRunTimeException e) {
        exceptions.add(e);
    }

    public boolean hasRunTimeErrors() {
        return exceptions.size() >= 1;
    }

    public ArrayList<RQLRunTimeWarning> getWarnings() {
        return warnings;
    }

    public void addRunTimeWarning(RQLRunTimeWarning e) {
        boolean exists = false;
        for (RQLRunTimeWarning w : warnings) {
            if (w.equals(e)) {
                exists = true;
            }
        }
        if (!exists) {
            warnings.add(e);
        }
    }

    public boolean hasRunTimeWarnings() {
        return warnings.size() >= 1;
    }

    /**
     * This function retrieves the log slices with given names bounded by the start and end times
     * @param logs String[] array of log names to retrieve
     */
    public abstract void retrieveRemoteLogs(String logs[]);

    /**
     * This function retrieves the log slices with given names bounded by the start and end times
     * @param logs String[] array of log names to retrieve
     * @param startTime Timestamp HLC timestamp for log slice start
     * @param endTime Timestamp HLC timestamp for log slice end
     */
    public abstract void retrieveRemoteLogs(String logs[], Timestamp startTime, Timestamp endTime);

    /**
     * This function retrieves the log slices with given names bounded by the start and end times
     * @param logs String[] array of log names to retrieve
     * @param startTime Timestamp HLC timestamp for log slice start
     * @param endTime Timestamp HLC timestamp for log slice end
     * @param nodeList int[] array of node ids to retrieve logs from
     */
    public abstract void retrieveRemoteLogs(String logs[], Timestamp startTime, Timestamp endTime, int nodeList[]);

    /**
     * Retrieves a consistent cuts on specified logs at a given time
     * @param logs String[] array of log names to retrieve
     * @param cutTime Timestamp HLC timestamp for log consistent cut on the logs
     */
    public abstract void retrieveSingleCut(String logs[], Timestamp cutTime);

    /**
     *
     * @param condition
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
        PriorityQueue<LogEntry<String, RQLItem>> q =
                new PriorityQueue<LogEntry<String, RQLItem>>(logs.size(), new Comparator<LogEntry<String, RQLItem>>() {
                    public int compare(LogEntry<String, RQLItem> o1, LogEntry<String, RQLItem> o2) {
                        return -o1.getTime().compareTo(o2.getTime());
                    }
                });
        int snapshotIDs[] = new int[logs.size()];
        for (DataMapLog<String, RQLItem> log : logs) {
            q.add(log.getTail());
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

                            tempCut.addLocalSnapshot(logs.get(i).getName(), logs.get(i).getNodeId(), logs.get(i).getSnapshot(snapshotIDs[i]));
                        } else {
                            //new snapshot for us
                            snapshotIDs[i] = logs.get(i).makeSnapshot(currentHLC);
                            if (snapshotIDs[i] > 0) {
                                //add only valid local snapshots ot the tempCut.
                                tempCut.addLocalSnapshot(logs.get(i).getName(), logs.get(i).getNodeId(), logs.get(i).getSnapshot(snapshotIDs[i]));
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
            // (7) at this time we have a consistent cut at the tempCut object
            // first we update symbol table with new values. The symbol table
            // should be the same as the one used by the vars in the condition expression
            Iterator<Map.Entry<String, Valuable>> it = symbolTable.entrySet().iterator();
            while (it.hasNext()) { //iterating through the symbols in the symbol table
                Map.Entry<String, Valuable> pair = it.next();
                String symbolName = pair.getKey();
                ArrayList<RQLInterpreterValue> expVals = new ArrayList<RQLInterpreterValue>();
                //for each symbols, we try to fetch it from each local snapshot in tempCut
                ArrayList<RetroMap<String, RQLItem>> tempSnaps = tempCut.getLocalSnapshots();
                ArrayList<String> snapNames = tempCut.getLocalSnapshotNames();
                ArrayList<Integer> nodeIds = tempCut.getNodeIds();
                for (int ls = 0; ls < tempSnaps.size(); ls++) {
                    String[] temp = symbolName.split("\\.");
                    String lName = temp[0];
                    temp = temp[1].split(":");
                    String pName = temp[0];
                    String fName = "";
                    if (temp.length > 1) {
                        fName = temp[1];
                    }
                    if (lName.equals(snapNames.get(ls))) {
                        DataEntry<RQLItem> tempV = tempSnaps.get(ls).get(pName);
                        if (tempV != null) {
                            RQLItemValue val = tempV.getValue().getField(fName);
                            if (val != null) {
                                RQLInterpreterValue expressionValue = new RQLInterpreterValue(val).addLink(new Link(lName+"."+pName, nodeIds.get(ls)));
                                expVals.add(expressionValue);
                            }
                        }
                    }
                }
                //System.out.println(symbolName + " - " + expVals.get(0).getIntVal());
                Valuable stemp = symbolTable.get(symbolName);
                if (stemp instanceof SimpleSymbol) {
                    ((SimpleSymbol) stemp).setAll(expVals);
                }
            }

            // (8)
            try {
                condition.evaluate();
            } catch (IllegalExpressionException e) {System.err.println(e.getMessage());}
            for (int i = 0; i < condition.getValues().length; i++) {
                //if any of the results is true, we emit the cut
                if (condition.getValues()[i].getIntVal() != 0) {
                    // (9)
                    //clone tempCut, since has rolling snapshot data which will be mutated as the snapshot rolls
                    addToEmitList(tempCut);
                    break; //done with for loop
                }
            }
            // (10)
            if (current.getPrev() != null) {
                q.add(current.getPrev());
            }
        }

    }

    public void updateSymbolTableWithLogName(String[] logIds) throws RQLRunTimeException {

        defaultLog = logIds[0];
        HashMap<String, Valuable> mutatedIds = new HashMap<String, Valuable>();
        Iterator<Map.Entry<String, Valuable>> it = getSymbolTable().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Valuable> pair = it.next();
            if (!pair.getKey().contains(".")) {
                //we have no log identifier
                if (logIds.length == 1) {
                    mutatedIds.put(defaultLog + "." + pair.getKey(), pair.getValue());
                    //getSymbolTable().remove(pair.getKey());

                    it.remove();
                } else {
                    throw new RQLRunTimeException("Parameter " + pair.getKey() + " is ambiguous: it may exist in more than one log");
                }
            } else {
                boolean specified = false;
                String logName = pair.getKey().split("\\.")[0];
                for (String logId : logIds) {
                    if (logId.equals(logName)) {
                        specified = true;
                        break;
                    }
                }
                if (!specified) {
                    throw new RQLRunTimeException("log " + logName + "in parameter " + pair.getKey() + " is missing from the log list");
                }
            }
        }
        getSymbolTable().putAll(mutatedIds);
    }

    private void addToEmitList(GlobalCut gc) {
        if (globalCuts.size() == 0
                || globalCuts.get(globalCuts.size() - 1).getCutTime().compareTo(gc.getCutTime()) > 0) {
            globalCuts.add(gc.clone());
        }
    }


    public ArrayList<GlobalCut> getGlobalCuts() {
        return globalCuts;
    }

    public String getDefaultLogName() {
        return defaultLog;
    }

    public ArrayList<RQLDataMapLog> getLogs() {
        return logs;
    }
}
