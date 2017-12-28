package retroscope.log;

import org.jetbrains.annotations.NotNull;
import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.datamodel.datastruct.sets.AppendRQLSet;
import retroscope.datamodel.datastruct.sets.RQLSet;
import retroscope.datamodel.datastruct.sets.RemoveRQLSet;
import retroscope.datamodel.datastruct.sets.TimerRQLSet;
import retroscope.datamodel.datastruct.struct.RQLStruct;
import retroscope.datamodel.datastruct.variables.DoubleRQLVariable;
import retroscope.datamodel.datastruct.variables.LongRQLVariable;
import retroscope.datamodel.datastruct.variables.RQLVariable;
import retroscope.datamodel.datastruct.variables.StringRQLVariable;
import retroscope.hlc.HLC;
import retroscope.hlc.Timestamp;
import retroscope.net.ignite.EventStreamEntry;
import retroscope.net.ignite.StreamEntry;
import retroscope.net.ignite.IgniteHandler;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.HashMap;

public class RetroscopeLog implements BasicLog<String, String> {

    //state we keep
    private HashMap<String, RQLSymbol> variables;
    private ArrayList<TimerRQLSet> timerSets;

    private HashMap<String, AppendRQLSet> appendSets;
    private HashMap<String, RemoveRQLSet> removeSets;
    private HashMap<String, RQLSymbol> commitVars;

    //private HashMap<String, RQLSymbol> preCommitVars;

    private HashMap<Long, String> snaps;
    private IgniteHandler igniteHandler;
    private String name;
    private HLC hlc;
    private int nodeID;


    public RetroscopeLog(String logName, IgniteHandler igniteHandler, HLC hlc){
        this.igniteHandler = igniteHandler;
        this.variables = new HashMap<>();
        this.commitVars = new HashMap<>();
        this.appendSets = new HashMap<>();
        this.removeSets = new HashMap<>();
        this.snaps = new HashMap<>();
        this.timerSets = new ArrayList<>();
        this.name = logName;
        this.hlc = hlc;
        this.nodeID = igniteHandler.getNodeMeta().getNodeId();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getLength() {
        return 0;
    }

    public HLC getHlc() {
        return hlc;
    }

    public int getNodeID() {
        return nodeID;
    }

   /* private void checkTEvent() {
        if (tEvent == null) {
            tEvent = hlc.now();
            long bt = igniteHandler.getBlockTime(tEvent.getWallTime());
            if (igniteHandler.getCurrentBlockTime() < bt) {
                snaps.put(bt, snapshotToString(new Timestamp(bt, (short)0).toLong()));
            }
        }
    }
    */

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Variables
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    public synchronized RetroscopeLog setVariable(String name, double val) {
        //checkTEvent();
        RQLVariable v = new DoubleRQLVariable(name, val);
        commitVars.put(name, v);
        return this;
    }

    public synchronized RetroscopeLog setVariable(String name, long val) {
        //checkTEvent();
        RQLVariable v = new LongRQLVariable(name, val);
        commitVars.put(name, v);
        return this;
    }

    public synchronized RetroscopeLog setVariable(String name, String val) {
       //checkTEvent();
        RQLVariable v = new StringRQLVariable(name, val);
        commitVars.put(name, v);
        return this;
    }
    public synchronized RetroscopeLog setVariable(RQLStruct struct) {
       //checkTEvent();
        commitVars.put(struct.getName(), struct);
        return this;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Sets
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public RetroscopeLog createSet(String name, long expirationTime) {
        TimerRQLSet set = new TimerRQLSet(name, expirationTime);
        commitVars.put(name, set);
        timerSets.add(set);
        return this;
    }

    private AppendRQLSet getAppendSet(String name) {
        AppendRQLSet set = appendSets.get(name);
        if (set == null) {
            set = new AppendRQLSet(name);
            appendSets.put(name, set);
        }
        return set;
    }

    public synchronized RetroscopeLog addToSet(String name, double val) {
        //checkTEvent();
        AppendRQLSet set = getAppendSet(name);
        set.add(new DoubleRQLVariable(val));
        return this;
    }

    public synchronized RetroscopeLog addToSet(String name, long val) {
        //checkTEvent();
        AppendRQLSet set = getAppendSet(name);
        set.add(new LongRQLVariable(val));
        return this;
    }

    public synchronized RetroscopeLog addToSet(String name, String val) {
        //checkTEvent();
        AppendRQLSet set = getAppendSet(name);
        set.add(new StringRQLVariable(val));
        return this;
    }

    public synchronized RetroscopeLog addToSet(String name, RQLStruct struct) {
        AppendRQLSet set = getAppendSet(name);
        set.add(struct);
        return this;
    }

    private RemoveRQLSet getRemoveSet(String name) {
        //checkTEvent();
        RemoveRQLSet set = removeSets.get(name);
        if (set == null) {
            set = new RemoveRQLSet(name);
            removeSets.put(name, set);
        }
        return set;
    }

    public synchronized RetroscopeLog removeFromSet(String name, double val) {
        //checkTEvent();
        RemoveRQLSet set = getRemoveSet(name);
        set.add(new DoubleRQLVariable(val));
        return this;
    }

    public synchronized RetroscopeLog removeFromSet(String name, long val) {
        //checkTEvent();
        RemoveRQLSet set = getRemoveSet(name);
        set.add(new LongRQLVariable(val));
        return this;
    }

    public synchronized RetroscopeLog removeFromSet(String name, String val) {
        RemoveRQLSet set = getRemoveSet(name);
        set.add(new StringRQLVariable(val));
        return this;
    }

    public synchronized RetroscopeLog removeFromSet(String name, RQLStruct struct) {
        RemoveRQLSet set = getRemoveSet(name);
        set.add(struct);
        return this;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Struct
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    public RQLStruct createStruct(String name) {
        RQLStruct struct = new RQLStruct(name);
        //commitVars.put(name, struct);
        return struct;
    }

    public RQLStruct createStruct() {
        RQLStruct struct = new RQLStruct();
        //commitVars.put(name, struct);
        return struct;
    }

    public synchronized void commit() {
        //checkTEvent();
        Timestamp tnow = hlc.now();
        long bt = igniteHandler.getBlockTime(tnow.getWallTime());
        if (igniteHandler.getCurrentBlockTime() < bt) {
            snaps.put(bt, snapshotToString(new Timestamp(bt, (short)0).toLong()));
        }

        for (TimerRQLSet tset : timerSets) {
            RemoveRQLSet rSet = getRemoveSet(tset.getName());
            rSet.addAll(tset.expireItems());
        }

        variables.putAll(commitVars);
        updateSetsWithAppendSet();
        updateSetsWithRemoveSet();

        StreamEntry streamEntry = new EventStreamEntry(tnow.toLong(), commitToString(tnow.toLong()));
        igniteHandler.stream(streamEntry, this, bt);

        commitVars.clear();
        removeSets.clear();
        appendSets.clear();
    }

    private void updateSetsWithAppendSet() {
        for (String name : appendSets.keySet()) {
            RQLSymbol s = variables.get(name);
            if (s == null) {
                s = new RQLSet();
                variables.put(name, s);
            }
            if (s instanceof RQLSet) {
                ((RQLSet) s).addAll(appendSets.get(name).getSet());
            }
        }
    }

    private void updateSetsWithRemoveSet() {
        for (String name : removeSets.keySet()) {
            RQLSymbol s = variables.get(name);
            if (s == null) {
                s = new RQLSet();
                variables.put(name, s);
            }
            if (s instanceof RQLSet) {
                ((RQLSet) s).removeAll(removeSets.get(name).getSet());
            }
        }
    }

    public String getSnap(long snapWallTime) {
        return snaps.get(snapWallTime);
    }

    @NotNull
    private String snapshotToString(long hlcTime) {
        StringBuilder sb = new StringBuilder();
        buildHeader(sb, hlcTime);
        //variables
        buildVarsAndSets(sb, variables);
        //other stuff
        return sb.toString();
    }

    @NotNull
    private String commitToString(long hlcTime) {
        StringBuilder sb = new StringBuilder();
        buildHeader(sb, hlcTime);
        //variables
        int l1 = sb.length();
        buildVars(sb, commitVars);
        //other stuff
        if (l1 != sb.length()) {
            sb.append(',');
        }
        l1 = sb.length();
        buildSetAppends(sb);
        if (l1 != sb.length()) {
            sb.append(',');
        }
        buildSetRemoves(sb);
        return sb.toString();
    }



    private void buildHeader(StringBuilder sb, long hlcTime) {
        sb.append('<');
        //sb.append(name);
        sb.append("t" +(new Timestamp(hlcTime)).getWallTime());
        sb.append(',');
        sb.append(hlcTime);
        sb.append(',');
        sb.append(nodeID);
        sb.append('>');
        sb.append(':');
    }

    private void buildVars(StringBuilder sb, HashMap<String, RQLSymbol> vars) {
        //iterate through vars
        int l = sb.length();
        for (String key : vars.keySet()){
            //iterate over key
            if (!(vars.get(key) instanceof RQLSet)) {
                sb.append(vars.get(key).toRQLString());
                sb.append(',');
            }
        }
        if (l != sb.length()) {
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private void buildVarsAndSets(StringBuilder sb, HashMap<String, RQLSymbol> vars) {
        //iterate through vars
        for (String key : vars.keySet()){
            //iterate over key
            sb.append(vars.get(key).toRQLString());
            sb.append(',');
        }
        sb.deleteCharAt(sb.length() - 1);
    }

    private void buildSetAppends(StringBuilder sb) {
        for (String key : appendSets.keySet()){
            //iterate over key
            if (appendSets.get(key).size() > 0) {
                sb.append(appendSets.get(key).toRQLString());
                sb.append(',');
            }
        }
        sb.deleteCharAt(sb.length() - 1);
    }

    private void buildSetRemoves(StringBuilder sb) {
        for (String key : removeSets.keySet()){
            //iterate over key
            if (removeSets.get(key).size() > 0) {
                sb.append(removeSets.get(key).toRQLString());
                sb.append(',');
            }
        }
        sb.deleteCharAt(sb.length() - 1);
    }
}
