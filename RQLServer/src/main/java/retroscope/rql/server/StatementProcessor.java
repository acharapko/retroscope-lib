package retroscope.rql.server;

import org.apache.ignite.Ignite;
import retroscope.rql.syntaxtree.Query;

import java.util.ArrayList;
import java.util.List;

public class StatementProcessor {

    public static List<Runner> processStatements(List<Query> statements, String appName, String iconf) {
        List<Runner> runners = new ArrayList<>(statements.size());
        for (int i = 0; i < statements.size(); i++) {
            Query s = statements.get(i);
            DistributedQueryRunner qr = new DistributedQueryRunner(s, appName, iconf);
            runners.add(i, qr);
        }

        for (Runner r: runners) {
            if (r.isExplain()) {
                r.explain();
            } else {
                r.execute();
            }
        }

        return runners;
    }

    public static List<Runner> processStatements(List<Query> statements, String appName, Ignite ignite) {
        List<Runner> runners = new ArrayList<>(statements.size());
        for (int i = 0; i < statements.size(); i++) {
            Query s = statements.get(i);
            DistributedQueryRunner qr = new DistributedQueryRunner(s, appName, ignite);
            runners.add(i, qr);

        }

        for (Runner r: runners) {
            if (r.isExplain()) {
                r.explain();
            } else {
                r.execute();
            }
        }

        return runners;
    }
}
