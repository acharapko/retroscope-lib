package retroscope.rql.syntaxtree.expression.sets;

import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.datamodel.datastruct.sets.RQLSet;
import retroscope.datamodel.datastruct.variables.LongRQLVariable;
import retroscope.rql.syntaxtree.expression.Expression;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;

public class Union extends SetOperator {

    public Union(Expression ex1, Expression ex2) {
        super(ex1, ex2);
    }

    @Override
    protected RQLSymbol setOperation(RQLSet s1, RQLSet s2) throws IllegalExpressionException {
        RQLSet union = new RQLSet();
        union.addAll(s1);
        union.addAll(s2);
        return union;
    }

    @Override
    public Union clone() {
        return new Union(ex1.clone(), ex2.clone());
    }


}
