package retroscope.rql.syntaxtree.expression.compare;


import retroscope.datamodel.datastruct.sets.RQLSet;
import retroscope.rql.syntaxtree.expression.Expression;

public class NEQExpression extends CompareExpression
{

	public NEQExpression(Expression ex1, Expression ex2)
	{ 
		super(ex1, ex2);
	}

    @Override
    protected boolean compareNums(Number n1, Number n2) {
	    if (n1 instanceof Long && n2 instanceof Long) {
	        return ((Long) n1).compareTo((Long)n2) != 0;
        }
        return n1.doubleValue() != n2.doubleValue();
    }

    @Override
    protected boolean compareStrings(String s1, String s2) {
        return !s1.equals(s2);
    }

    @Override
    protected boolean compareSets(RQLSet s1, RQLSet s2) {
        return !s1.equals(s2);
    }

    public NEQExpression clone() {
        return new NEQExpression(ex1.clone(), ex2.clone());
    }

}
