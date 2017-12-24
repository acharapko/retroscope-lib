package retroscope.rql.log;

import org.apache.log4j.Logger;
import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.datamodel.datastruct.sets.RQLSet;
import retroscope.datamodel.datastruct.struct.RQLStruct;
import retroscope.datamodel.datastruct.variables.RQLVariable;
import retroscope.datamodel.parser.DataParser;
import retroscope.datamodel.parser.DataScanner;
import retroscope.datamodel.parser.RQLData;
import retroscope.hlc.Timestamp;
import retroscope.net.ignite.StateSequence;
import retroscope.rql.environment.SymbolTable;

import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class RQLStateSequence extends StateSequence {

    private HashMap<Integer, RQLData> currentState; //HashMap<NodeID, Data>
    private long currentHLC;
    private ArrayList<String> params;

    private TreeMap<Long,  Collection<String>> orderedVals;

    private Logger logger = Logger.getLogger(RQLStateSequence.class);

    public RQLStateSequence(Timestamp startingTime) {
        super(startingTime);
        currentState = new HashMap<>();
        currentHLC = getStartingTimestamp().toLong() - 1; //subtract to catch an entry sitting at exact start HLC
    }

    public RQLStateSequence(StateSequence stateSequence) {
        super(stateSequence.getStartingTimestamp());
        this.initState = stateSequence.getInitState();
        this.vals = stateSequence.getVals();
        currentState = new HashMap<>();
        currentHLC = getStartingTimestamp().toLong() - 1; //subtract to catch an entry sitting at exact start HLC
        orderedVals = new TreeMap<>();
        orderedVals.putAll(vals);
    }

    public ArrayList<String> getParams() {
        return params;
    }

    public void setParams(ArrayList<String> params) {
        this.params = params;
    }

    public long getCurrentHLC() {
        return currentHLC;
    }

    public void parseInitialState() {
        DataParser p = new DataParser();
        StringReader sr;
        DataScanner scanner;
        for (String state : initState) {
            //System.out.println("PARSING: " + state);
            sr = new StringReader(state);
            scanner = new DataScanner(sr);
            try {
                scanner.yylex();
            } catch (IOException e) {
                System.err.println("Error parsing data: " + e.getMessage());
            }
            p.setScanner(scanner);
            p.parse();
            RQLData d = p.getData();
            d.restrict(params);
            currentState.put(p.getData().getNodeId(), d);
        }
    }

    private Map.Entry<Long, Collection<String>> higherEntry(long hlc) {
        if (orderedVals == null) {
            orderedVals = new TreeMap<>(vals);
        }
        return orderedVals.higherEntry(hlc);
    }

    public SymbolTable compileSymbolTable() {
        SymbolTable symbolTable = new SymbolTable();
        //go through nodes
        for (Integer nodeID : currentState.keySet()) {
            //go through vars
            RQLData nodeData = currentState.get(nodeID);
            for (RQLSymbol s: nodeData.getDataItems()) {
                String varname = s.getName();
                RQLSymbol symbol = symbolTable.get(varname);
                if (symbol == null) {
                    symbol = new RQLSet(varname);
                    symbolTable.put(varname, symbol);
                }
                if (symbol instanceof RQLSet) {
                    ((RQLSet) symbol).addOrMerge(s);
                }
            }
        }
        return symbolTable;
    }


    /*
     * With getSymbolTableChanges method we do not need tog get the currentState from scratch with next method
     * and compile the symbol table. Instead we stop short of computing currentState and update the symbol
     * table directly
     */
    public ArrayList<RQLData> getSymbolTableChanges(SymbolTable symbolTable) {
        //this.changedVars = new HashSet<>();
        Map.Entry<Long, Collection<String>> entry = higherEntry(currentHLC);
        if (entry != null) {
            Collection<String> changes = entry.getValue();
            currentHLC = entry.getKey();

            DataParser p = new DataParser();
            StringReader sr;
            DataScanner scanner;
            ArrayList<RQLData> changesData = new ArrayList<>();
            for (String state : changes) {
                sr = new StringReader(state);
                scanner = new DataScanner(sr);
                try {
                    scanner.yylex();
                } catch (IOException e) {
                    System.err.println("Error parsing data: " + e.getMessage());
                }
                p.setScanner(scanner);
                p.parse();
                RQLData dt = p.getData();
                if (params != null && params.size() > 0) {
                    dt.restrict(params);
                }
                if (dt.getDataItems().size() > 0) {
                    changesData.add(dt);
                }
            }
            return changesData;
        }
        return null;
    }

    public boolean hasNext() {
        Map.Entry<Long, Collection<String>> entry = higherEntry(currentHLC);
        return entry != null;
    }
}
