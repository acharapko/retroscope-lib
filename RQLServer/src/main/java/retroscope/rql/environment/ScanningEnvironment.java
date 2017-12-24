package retroscope.rql.environment;

import org.apache.log4j.Logger;
import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.datamodel.datastruct.sets.RQLSet;
import retroscope.datamodel.datastruct.struct.RQLStruct;
import retroscope.datamodel.datastruct.variables.LongRQLVariable;
import retroscope.datamodel.datastruct.variables.RQLVariable;
import retroscope.datamodel.parser.RQLData;
import retroscope.rql.log.RQLStateSequence;
import retroscope.rql.syntaxtree.expression.Expression;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;
import retroscope.rql.syntaxtree.expression.literals.Variable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * This class represents the root environment of an RQL query evaluation.
 * It allows scanning through a state sequence and evaluating the
 * query predicates on each of the cuts in the stateSequence.
 *
 * Scanning environment will put other environments on call stack as the query evaluation progresses
 */

public class ScanningEnvironment extends Environment {

    private RQLStateSequence stateSequence;
    private ArrayList<GlobalCut> emittedCuts;
    private ArrayList<String> params;

    private Expression computeExpression;

    private HashMap<String, Variable> mutatingVarsIndex;
    private ArrayList<Variable> mutatingVars; //list of vars

    //private long prevCutHLC = 0;
    private long currentCutHLC = 0;

    private Logger logger = Logger.getLogger(ScanningEnvironment.class);

    public ScanningEnvironment(RQLStateSequence stateSequence, ArrayList<String> params) {
        this.stateSequence = stateSequence;
        this.params = params;
        init();
    }

    public ScanningEnvironment(EnvironmentStack stack, RQLStateSequence stateSequence, ArrayList<String> params) {
        super(stack);
        this.params = params;
        this.stateSequence = stateSequence;
        init();
    }

    private void init() {
        this.mutatingVarsIndex = new HashMap<>();
        this.mutatingVars = new ArrayList<>();
        this.emittedCuts = new ArrayList<>();
        this.stateSequence.setParams(params);
        this.stateSequence.parseInitialState();
        logger.debug(stateSequence);
        this.setSymbolTable(stateSequence.compileSymbolTable());
    }

    public void setComputeExpression(Expression computeExpression) {
        this.computeExpression = computeExpression;
        addToMutatingVarsIndex(computeExpression);
    }

    public void addExpression(Expression ex) {
        super.addExpression(ex);
        addToMutatingVarsIndex(ex);
    }

    public void assignToSymbol(String key, RQLSymbol val) {
        super.assignToSymbol(key, val);
        computeDirty();
    }

    private void addToMutatingVarsIndex(Expression ex) {
        ArrayList<Variable> vars = ex.findVars();
        for (Variable v: vars) {
            //add only new vars to index that are in the list of params
            if (!mutatingVarsIndex.containsKey(v.getName()) && params.contains(v.getName())) {
                mutatingVars.add(v);
                mutatingVarsIndex.put(v.getName(), v);
            }
        }
    }

    public void scan() {
        while (nextCut()) {}
    }

    public synchronized boolean nextCut() {
        if (stateSequence.hasNext()) {
            ArrayList<RQLData> changes = stateSequence.getSymbolTableChanges(this.getSymbolTable());
            if (changes != null) {
                updateSymbolTable(this.getSymbolTable(), changes);
                currentCutHLC = stateSequence.getCurrentHLC();
                try {
                    if (this.expression != null) {
                        evaluate();
                        Expression ex = getExpression();
                        if (ex.getValue() instanceof LongRQLVariable) {
                            if (((LongRQLVariable) ex.getValue()).getValue() != 0) {
                                //we emmit the cut
                                emitCut();
                            }
                        }
                    } else {
                        if (computeExpression != null) {
                            invoke(computeExpression); //doing compute script
                        }
                        emitCut();
                    }
                } catch (IllegalExpressionException e) {
                    System.out.println("Exception:" + e.getMessage());
                    addRunTimeException(e);
                }
            }
            while (stack.size() > 1) {
                stack.pop(); //pop everything form teh stack till we reach global
            }
            return true;
        }
        return false;
    }


    private void updateSymbolTable(SymbolTable symbolTable, Collection<RQLData> changes) {

        if (params != null && params.size() > 0) {
            restrict(changes);
        }
        //clean all the global variables
        /*for (Variable v: mutatingVars) {
            v.clean();
        }*/

        for (String var: symbolTable.keySet()) {
            symbolTable.get(var).clean();
        }

        for (RQLData change : changes) {
            for (RQLSymbol s : change.getDataItems()) {
                String varname = s.getName();

                //this.mutatingVarsIndex.get(varname).stain(); // stain varname

                RQLSymbol symbol = symbolTable.get(varname);
                if (symbol == null) {
                    symbol = new RQLSet(varname);
                    symbolTable.put(varname, symbol);
                }
                if (symbol instanceof RQLSet) {
                    RQLSymbol changeSymb = s;
                    if ((changeSymb instanceof RQLVariable) || (changeSymb instanceof RQLStruct)) {
                        ((RQLSet) symbol).replaceOrAddVariable(changeSymb);
                    } else if (changeSymb instanceof RQLSet) {
                        ((RQLSet) symbol).replaceOrAddSets((RQLSet) changeSymb);
                    }
                    symbol.stain();
                }
            }
        }
        computeDirty();
        logger.debug("Checking cut: " + symbolTable.toString());
    }

    public void restrict(Collection<RQLData> changes) {
        for (RQLData change : changes) {
            change.restrict(params);
        }
    }

    private void computeDirty() {
        if (this.expression != null) {
            this.expression.computeDirty();
        }
        if (this.computeExpression != null) {
            this.computeExpression.computeDirty();
        }
    }



    public void evaluate() throws IllegalExpressionException {
        if (computeExpression != null) {
            Environment computeEnv = invoke(computeExpression); //doing compute script
            computeEnv.invoke(expression).terminate(); // compute expression higher on the stack and pop the stack
            // do not pop computeEnv, as we need this for emit cut
        } else  {
            super.evaluate(); //do flat evaluation right in the global scope
        }
    }

    private void emitCut() {
        String cutVal = getSymbolTable().toString(params);

        GlobalCut gc = new GlobalCut(currentCutHLC, cutVal);
        emittedCuts.add(gc);
    }

    public long getCurrentCutHLC() {
        return currentCutHLC;
    }

    public ArrayList<GlobalCut> getEmittedCuts() {
        return emittedCuts;
    }
}
