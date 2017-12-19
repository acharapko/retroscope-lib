package retroscope.rql.syntaxtree.expression.sets;

import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.datamodel.datastruct.sets.RQLSet;
import retroscope.rql.syntaxtree.expression.Expression;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;

public class Intersect extends SetOperator {

    public Intersect(Expression ex1, Expression ex2) {
        super(ex1, ex2);
    }

    @Override
    protected RQLSymbol setOperation(RQLSet s1, RQLSet s2) throws IllegalExpressionException {
        RQLSet intersect = new RQLSet();
        intersect.getSet().addAll(s1.getSet());
        intersect.getSet().retainAll(s2.getSet());
        return intersect;
    }

    @Override
    public Intersect clone() {
        return new Intersect(ex1.clone(), ex2.clone());
    }


}
