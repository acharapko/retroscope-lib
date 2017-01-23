package retroscope.rql.syntaxtree;

/**
 * Created by Aleksey on 1/23/2017.
 *
 */
public class TimeSearch {

    public final int BETWEEN = 1;
    public final int BEFORE = 2;
    public final int AFTER = 3;

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

}
