package retroscope.rql.syntaxtree.expression.compare;


import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.datamodel.datastruct.sets.RQLSet;
import retroscope.datamodel.datastruct.variables.LongRQLVariable;
import retroscope.datamodel.datastruct.variables.RQLVariable;
import retroscope.rql.syntaxtree.expression.Expression;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;
import retroscope.rql.syntaxtree.expression.TwoOperandExpression;

public abstract class CompareExpression extends TwoOperandExpression
{
	public CompareExpression(Expression ex1, Expression ex2)
	{ 
		super(ex1, ex2);
	}

	public void evaluate() throws IllegalExpressionException {
		evaluateOperands();
		RQLSymbol ex1Val = ex1.getValue();
		RQLSymbol ex2Val = ex2.getValue();

		if (ex1Val instanceof RQLVariable && ex2Val instanceof RQLVariable) {
			Number n1 = null;
			Number n2 = null;
			boolean cmp = false;
			if (((RQLVariable) ex1Val).getValue() instanceof Number) {
				n1 = (Number) ((RQLVariable) ex1Val).getValue();
			}
			if (((RQLVariable) ex2Val).getValue() instanceof Number) {
				n2 = (Number) ((RQLVariable) ex2Val).getValue();
			}
			if (n1 != null && n2 != null) {
				cmp = compareNums(n1, n2);
			} else if (n1 == null && n2 == null) {
				//dealing with strings
				String str1 = (String) ((RQLVariable) ex1Val).getValue();
				String str2 = (String) ((RQLVariable) ex2Val).getValue();
				cmp = compareStrings(str1, str2);
			}

			value = new LongRQLVariable(cmp ? 1 : 0);
		} else if (ex1Val instanceof RQLSet && ex2Val instanceof RQLSet) {
			boolean cmp = false;
			cmp = compareSets((RQLSet) ex1Val, (RQLSet) ex1Val);
			value = new LongRQLVariable(cmp ? 1 : 0);
		} else {
			String ex1Type = "NULL";
			String ex2Type = "NULL";
			if (ex1Val != null) {
				ex1Type = ex1Val.getClass().getName();
			}
			if (ex2Val != null) {
				ex2Type = ex2Val.getClass().getName();
			}
			System.out.println("Cannot compare " +ex1Type + " and " + ex2Type);
			throw new IllegalExpressionException("Cannot compare " +ex1Type	+ " and " + ex2Type);
		}
	}

	protected abstract boolean compareNums(Number n1, Number n2);

	protected abstract boolean compareStrings(String s1, String s2);

	protected abstract boolean compareSets(RQLSet s1, RQLSet s2) throws IllegalExpressionException;



}
