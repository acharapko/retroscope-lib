package retroscope.rql.syntaxtree;

import retroscope.rql.syntaxtree.expression.Expression;
import retroscope.rql.syntaxtree.link.Links;

/**
 * Created by Aleksey on 12/20/2016.
 * this class holds some query parameters and filters
 */
public class QueryEnd {

    private Expression conditions;
    private AtNodes nodeIds;
    private Expression timeEx1, timeEx2;
    private TimeSearch ts;
    private Links links;

    public QueryEnd(When w, Links links, TimeSearch ts, AtNodes nodes, OnTime time)    {
        if (w != null) {
            conditions = w.getConditions();
        }
        nodeIds = nodes;

        this.ts = ts;
        this.links = links;

        if (time != null) {
            timeEx1 = time.getTimeEx1();
            timeEx2 = time.getTimeEx2();
        }
    }

    public Expression getConditions() {
        return conditions;
    }

    public AtNodes getNodeIds() {
        return nodeIds;
    }

    public Expression getTimeEx1() {
        return timeEx1;
    }

    public Expression getTimeEx2() {
        return timeEx2;
    }

    public TimeSearch getTs() {
        return ts;
    }

    public Links getLinks() {
        return links;
    }
}
