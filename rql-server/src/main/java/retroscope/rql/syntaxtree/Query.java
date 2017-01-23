package retroscope.rql.syntaxtree;

import retroscope.rql.RQLEnvironment;
import retroscope.rql.RQLItemValue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Aleksey on 12/20/2016.
 * This is the main query class from the rql syntax tree
 * This class implements pulling data from the remotes,
 *
 */
public class Query {

    private ParamList params;
    private IdentifierList logs;
    private QueryEnd qe;
    private RQLEnvironment rqlScope;


    public Query(ParamList pl, IdentifierList logs, QueryEnd qe, RQLEnvironment rqlScope) {
        params = pl;
        this.logs = logs;
        this.qe = qe;
        this.rqlScope = rqlScope;

        //System.out.println(explain());

    }

    public String explain() {
        StringBuilder sb = new StringBuilder();
        sb.append("---------QUERY EXPLAIN---------");
        sb.append(System.getProperty("line.separator"));
        sb.append("Retrieves ");
        sb.append(params.getParams().length);
        sb.append(" parameter(s):");
        sb.append(System.getProperty("line.separator"));
        for (int i = 0; i < params.getParams().length; i++) {
            sb.append(params.getParams()[i].toString());
            if (i + 1 < params.getParams().length) {
                sb.append(", ");
            }
        }
        sb.append(System.getProperty("line.separator"));
        sb.append("From ");
        sb.append(logs.getIdentifiers().length);
        sb.append(" log(s):");
        sb.append(System.getProperty("line.separator"));
        for (int i = 0; i < logs.getIdentifiers().length; i++) {
            sb.append(logs.getIdentifiers()[i]);
            if (i + 1 < logs.getIdentifiers().length) {
                sb.append(", ");
            }
        }
        sb.append(System.getProperty("line.separator"));
        sb.append("-------QUERY EXPLAIN END-------");

        return sb.toString();
    }

    public void execute() {
        try {
            rqlScope.updateSymbolTableWithLogName(logs.getIdentifiers());
        } catch (RQLRunTimeException rqlrte) {
            rqlScope.addRunTimeException(rqlrte);
        }
        if (!rqlScope.hasRunTimeErrors()) {
            rqlScope.retrieveRemoteLogs(logs.getIdentifiers());
            rqlScope.findCutsByCondition(qe.getConditions());
        }
    }



}
