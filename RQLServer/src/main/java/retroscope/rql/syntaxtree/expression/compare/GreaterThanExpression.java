package retroscope.rql.syntaxtree.expression.compare;


import retroscope.datamodel.datastruct.sets.RQLSet;
import retroscope.rql.syntaxtree.expression.Expression;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;

public class GreaterThanExpression extends CompareExpression
{
	public GreaterThanExpression(Expression ex1, Expression ex2)
	{
		super(ex1, ex2);
	}

    @Override
    protected boolean compareNums(Number n1, Number n2) {
        if (n1 instanceof Long && n2 instanceof Long) {
            return n1.longValue() > n2.longValue();
        }
        return n1.doubleValue() > n2.doubleValue();
    }

    @Override
    protected boolean compareStrings(String s1, String s2) {
        return s1.compareTo(s2) > 0;
    }

    @Override
    protected boolean compareSets(RQLSet s1, RQLSet s2) throws IllegalExpressionException {
        throw new IllegalExpressionException("Greater than is undefined for sets");
    }

    public GreaterThanExpression clone() {
		return new GreaterThanExpression(ex1.clone(), ex2.clone());
	}
}
