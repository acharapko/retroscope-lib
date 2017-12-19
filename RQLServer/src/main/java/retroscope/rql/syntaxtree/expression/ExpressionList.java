package retroscope.rql.syntaxtree.expression;


import java.util.ArrayList;

/**
 * Created by ALEKS on 1/18/2017.
 * this class represents the list of RQL expressions
 */
public class ExpressionList implements Cloneable {

    private ArrayList<Expression> list;

    public ExpressionList() {
        list = new ArrayList<Expression>();
    }

    public ExpressionList(Expression ex) {
        list = new ArrayList<Expression>();
        list.add(ex);
    }

    public ExpressionList(Expression ex, ExpressionList lst) {
        list = new ArrayList<Expression>();
        list.add(ex);
        list.addAll(lst.getList());
    }

    public ExpressionList(ArrayList<Expression> list) {
        this.list = list;
    }

    public ArrayList<Expression> getList() {
        return list;
    }

    public ExpressionList clone() {
        ArrayList<Expression> clonedEx = new ArrayList<>();
        for (Expression ex: list) {
            clonedEx.add(ex.clone());
        }
        return new ExpressionList(clonedEx);
    }

}

