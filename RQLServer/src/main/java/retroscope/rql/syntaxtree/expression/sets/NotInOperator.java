package retroscope.rql.syntaxtree.expression.sets;

import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.datamodel.datastruct.sets.RQLSet;
import retroscope.datamodel.datastruct.variables.LongRQLVariable;
import retroscope.rql.syntaxtree.expression.Expression;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;

public class NotInOperator extends SymbolAndSetOperator {

    public NotInOperator(Expression ex1, Expression ex2) {
        super(ex1, ex2);
    }

    @Override
    protected RQLSymbol symbolAndSetOperation(RQLSymbol s1, RQLSet s2) throws IllegalExpressionException {
        return new LongRQLVariable(s2.getSet().contains(s1) ? 0 : 1);
    }

    @Override
    public NotInOperator clone() {
        return new NotInOperator(ex1.clone(), ex2.clone());
    }


}
