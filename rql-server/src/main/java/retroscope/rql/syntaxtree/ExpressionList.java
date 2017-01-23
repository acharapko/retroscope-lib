package retroscope.rql.syntaxtree;

import java.util.ArrayList;

/**
 * Created by ALEKS on 1/18/2017.
 * this class represents the list of RQL expressions
 */
public class ExpressionList {

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

    public ArrayList<Expression> getList() {
        return list;
    }
}

