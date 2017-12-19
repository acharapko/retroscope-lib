package retroscope.rql.syntaxtree.expression.stackedexpression;

import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.datamodel.datastruct.sets.RQLSet;
import retroscope.datamodel.datastruct.struct.RQLStruct;
import retroscope.datamodel.datastruct.variables.LongRQLVariable;
import retroscope.rql.environment.Environment;
import retroscope.rql.environment.EnvironmentStack;
import retroscope.rql.syntaxtree.IdentifierList;
import retroscope.rql.syntaxtree.expression.Expression;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class SetIteratingExpression extends Expression {

    private IdentifierList ids;
    private Expression ex1, ex2;
    private ArrayList<RQLIterator> iterators;
    private RQLSet iteratingSet;

    private long maxCombinations;
    private long completedCombinations;




    public SetIteratingExpression(IdentifierList ids, Expression ex1, Expression ex2) {
        this.ids = ids;
        this.ex2 = ex2;
        this.ex1 = ex1;
        iterators = new ArrayList<>();

    }

    public SetIteratingExpression(IteratingFunc aggregate) {
        this(aggregate.getIds(), aggregate.getEx1(), aggregate.getEx2());
    }

    @Override
    public void evaluate() throws IllegalExpressionException {
        //first we evaluate the set expression;
        this.value = null;
        ex1.evaluate();
        if (ex1.getValue() instanceof RQLSet) {
            if (((RQLSet) ex1.getValue()).size() > 0) {
                iteratingSet = (RQLSet) ex1.getValue();
                Environment funcEnv = new Environment(this.stack);
                ex2.setEnvironmentStack(this.stack);
                this.initVars();
                boolean interrupted = false;
                boolean hasNext = true;
                while (!interrupted && hasNext) {
                    completedCombinations++;
                    ex2.evaluate();
                    interrupted = checkExpressionVal(ex2.getValue());
                    hasNext = nextVarValue();
                }
                funcEnv.terminate();
            }
        }
        if (this.value == null) {
            this.value = new LongRQLVariable(0);
        }
    }

    private void initVars() {
        maxCombinations = 1;
        iterators = new ArrayList<>();
        for (String id: ids.getIdentifiers()) {
            maxCombinations *= iteratingSet.getSet().size();
            RQLIterator it = new RQLIterator(id);
            iterators.add(it);
            if (it.hasNext()) {
                RQLSymbol s = it.next();
                if (s instanceof RQLStruct) {
                    if (((RQLStruct) s).getElement("mid") instanceof LongRQLVariable) {
                        if (((LongRQLVariable) ((RQLStruct) s).getElement("mid")).getValue() == 216172782113783880l) {
                            System.out.println("LOOKHERE1");
                        }
                    }
                }
                s.setName(id);
                stack.peek().putSymbol(id, s);
            }
        }
    }

    private boolean nextVarValue() {
        boolean notDone = true;
        for (int i = iterators.size() - 1; i >= 0; i--) {
            RQLIterator it = iterators.get(i);

            if (it.hasNext()) {
                RQLSymbol s = it.next();
                if (s instanceof RQLStruct) {
                    if (((RQLStruct) s).getElement("mid") instanceof LongRQLVariable) {
                        if (((LongRQLVariable) ((RQLStruct) s).getElement("mid")).getValue() == 216172782113783880l) {
                            System.out.println("LOOKHERE2");
                        }
                    }
                }
                stack.peek().putSymbol(it.getName(), s);
                break;
            } else {
                RQLSymbol s = it.reset();
                if (s instanceof RQLStruct) {
                    if (((RQLStruct) s).getElement("mid") instanceof LongRQLVariable) {
                        if (((LongRQLVariable) ((RQLStruct) s).getElement("mid")).getValue() == 216172782113783880l) {
                            System.out.println("LOOKHERE3");
                        }
                    }
                }
                stack.peek().putSymbol(it.getName(), s);
                if (i == 0) {
                    notDone = false;
                }
            }
        }
        return notDone;
    }

    protected abstract boolean checkExpressionVal(RQLSymbol exVal);

    public IdentifierList getIds() {
        return ids;
    }

    public Expression getEx1() {
        return ex1;
    }

    public Expression getEx2() {
        return ex2;
    }

    public long getMaxCombinations() {
        return maxCombinations;
    }

    public long getCompletedCombinations() {
        return completedCombinations;
    }

    class RQLIterator {
        private Iterator<RQLSymbol> iterator;
        private String name;

        public RQLIterator(String name) {
            this.iterator = iteratingSet.getSet().iterator();
            this.name = name;
        }

        public Iterator<RQLSymbol> getIterator() {
            return iterator;
        }

        public String getName() {
            return name;
        }

        public boolean hasNext() {
            return iterator.hasNext();
        }

        public RQLSymbol next() {
            return iterator.next();
        }

        public RQLSymbol reset() {
            this.iterator = iteratingSet.getSet().iterator();
            return iterator.next();
        }
    }

    public void setEnvironmentStack(EnvironmentStack stack) {
        this.stack = stack;
        ex1.setEnvironmentStack(stack);
    }
}
