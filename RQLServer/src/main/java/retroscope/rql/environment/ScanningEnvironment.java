package retroscope.rql.environment;

import org.apache.log4j.Logger;
import retroscope.datamodel.datastruct.variables.LongRQLVariable;
import retroscope.rql.log.RQLStateSequence;
import retroscope.rql.syntaxtree.expression.Expression;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;

import java.util.ArrayList;

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

    private long prevCutHLC = 0;
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
        this.emittedCuts = new ArrayList<>();
        this.stateSequence.setParams(params);
        this.stateSequence.parseInitialState();
        logger.debug(stateSequence);
        this.setSymbolTable(stateSequence.compileSymbolTable());
    }

    public void setComputeExpression(Expression computeExpression) {
        this.computeExpression = computeExpression;
    }

    public void scan() {
        while (nextCut()) {}
    }

    public synchronized boolean nextCut() {
        if (stateSequence.hasNext()) {
            SymbolTable upd = stateSequence.nextSymbolTable(this.getSymbolTable());
            if (upd != null) {
                this.setSymbolTable(upd);
                prevCutHLC = currentCutHLC;
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
        /*if (emittedCuts.size() > 0) {
            GlobalCut prevCut = emittedCuts.get(emittedCuts.size() - 1);
            if (prevCut != null && prevCut.getCutTimes().size() > 0
                    && prevCut.getCut().equals(cutVal) && prevCut.getCutTimes().get(prevCut.getCutTimes().size() - 1).equals(prevCutHLC)) {
                prevCut.addCutTime(currentCutHLC);
                return;
            }
        }*/
        GlobalCut gc = new GlobalCut(currentCutHLC, cutVal);
        emittedCuts.add(gc);


    }

    public ArrayList<GlobalCut> getEmittedCuts() {
        return emittedCuts;
    }
}
