package retroscope.rql.syntaxtree.expression.sets;

import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.datamodel.datastruct.sets.RQLSet;
import retroscope.rql.syntaxtree.expression.Expression;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;

public class SetDifference extends SetOperator {

    public SetDifference(Expression ex1, Expression ex2) {
        super(ex1, ex2);
    }

    @Override
    protected RQLSymbol setOperation(RQLSet s1, RQLSet s2) throws IllegalExpressionException {
        RQLSet diff = new RQLSet();
        diff.addAll(s1);
        diff.removeAll(s2);
        return diff;
    }

    @Override
    public SetDifference clone() {
        return new SetDifference(ex1.clone(), ex2.clone());
    }


}
