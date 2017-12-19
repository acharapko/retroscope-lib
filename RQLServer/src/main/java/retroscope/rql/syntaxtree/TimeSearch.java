package retroscope.rql.syntaxtree;

import retroscope.rql.syntaxtree.expression.Expression;

/**
 * Created by Aleksey on 1/23/2017.
 *
 */
public class TimeSearch {

    public static final int BETWEEN = 1;
    public static final int BEFORE = 2;
    public static final int AFTER = 3;

    private Expression ex1, ex2, link;
    private int type;

    public TimeSearch(int timeSearchType, Expression ex1) {
        type = timeSearchType;
        this.ex1 = ex1;
    }

    public TimeSearch(int timeSearchType, Expression ex1, Expression ex2, Expression link) {
        type = timeSearchType;
        this.ex1 = ex1;
        this.ex2 = ex2;
        this.link = link;
    }

    public Expression getEx1() {
        return ex1;
    }

    public Expression getEx2() {
        return ex2;
    }

    public Expression getLink() {
        return link;
    }

    public int getType() {
        return type;
    }
}
