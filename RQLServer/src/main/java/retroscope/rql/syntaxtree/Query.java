package retroscope.rql.syntaxtree;

/**
 * Created by Aleksey on 12/20/2016.
 * This is the main query class from the retroscope.rql syntax tree
 * This class implements pulling data from the remotes,
 *
 */
public class Query {

    private ParamList params;
    private IdentifierList logs;
    private QueryEnd qe;

    private boolean isExplain = false;


    public Query(ParamList pl, IdentifierList logs, QueryEnd qe) {
        params = pl;
        this.logs = logs;
        this.qe = qe;
    }

    public void explain() {
        isExplain = true;
    }

    public ParamList getParams() {
        return params;
    }

    public IdentifierList getLogs() {
        return logs;
    }

    public QueryEnd getQe() {
        return qe;
    }

    public boolean isExplain() {
        return isExplain;
    }
}
