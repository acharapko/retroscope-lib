package retroscope.rql.syntaxtree.expression.sets;

import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.datamodel.datastruct.sets.RQLSet;
import retroscope.datamodel.datastruct.variables.LongRQLVariable;
import retroscope.rql.syntaxtree.expression.Expression;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;

public class IsSubset extends SetOperator {

    public IsSubset(Expression ex1, Expression ex2) {
        super(ex1, ex2);
    }

    @Override
    protected RQLSymbol setOperation(RQLSet s1, RQLSet s2) throws IllegalExpressionException {
        return new LongRQLVariable(s2.isSubset(s1) ? 1 : 0);
    }

    @Override
    public IsSubset clone() {
        return new IsSubset(ex1.clone(), ex2.clone());
    }


}
