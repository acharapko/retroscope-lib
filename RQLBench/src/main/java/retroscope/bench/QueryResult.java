package retroscope.bench;

public class QueryResult {

    private long queryRuntime;
    private int numCuts;

    public QueryResult(long queryRuntime, int numCuts) {
        this.queryRuntime = queryRuntime;
        this.numCuts = numCuts;
    }

    public long getQueryRuntime() {
        return queryRuntime;
    }

    public int getNumCuts() {
        return numCuts;
    }
}
