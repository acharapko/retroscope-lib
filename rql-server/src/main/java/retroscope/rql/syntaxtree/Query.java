package retroscope.rql.syntaxtree;

import retroscope.hlc.Timestamp;
import retroscope.rql.QueryEnvironment;
import retroscope.rql.RQLRetrieveParam;
import retroscope.rql.Types;
import retroscope.rql.errors.RQLRunTimeException;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;

import java.util.ArrayList;
import java.util.Arrays;

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
    private QueryEnvironment rqlScope;


    public Query(ParamList pl, IdentifierList logs, QueryEnd qe, QueryEnvironment rqlScope) {
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
        rqlScope.resetEnvironment();
        try {
            rqlScope.updateSymbolTableWithLogName(logs.getIdentifiers());
        } catch (RQLRunTimeException rqlrte) {
            rqlScope.addRunTimeException(rqlrte);
        }

        rqlScope.setLinkedNodes(qe.getLinks());


        //set logs we retrieve
        RQLRetrieveParam rqlRetrieveParam = new RQLRetrieveParam().setLogs(Arrays.asList(logs.getIdentifiers()));
        //set keys to be retrieved
        rqlRetrieveParam.setKeys(params.getParamIds());
        //set nodes we use
        if (qe.getNodeIds() != null && !qe.getNodeIds().isAllNodes()) {
            String nodeIds[] = qe.getNodeIds().getIdentifierList().getIdentifiers();
            ArrayList<Integer> nodes = new ArrayList<Integer>();
            for (String node : nodeIds) {
                nodes.add(Integer.parseInt(node));
            }
            rqlRetrieveParam.setNodeIds(nodes);
        }

        //set times we retrieve
        setTimeConstraints(rqlRetrieveParam);

        if (!rqlScope.hasRunTimeErrors()) {

            //now if we decided whether we retrieve a log slice or just a single cut
            //single cut is retrieved only when an exact time is given
            if (rqlRetrieveParam.getStartTime() != null && rqlRetrieveParam.getEndTime() == null) {
                rqlScope.retrieveSingleCut(rqlRetrieveParam);

                rqlScope.checkTempCutsOnCondition(qe.getConditions());

            } else {
                rqlScope.retrieveRemoteLogs(rqlRetrieveParam);

                //at this point we evaluate Time Search

                //and now we evaluate WHEN conditions
                rqlScope.findCutsByCondition(qe.getConditions());
            }
        }
    }

    private void setTimeConstraints(RQLRetrieveParam rqlRetrieveParam) {
        if (qe.getTimeEx1() != null) {
            try {
                qe.getTimeEx1().evaluate();
            } catch (IllegalExpressionException iee) {rqlScope.addRunTimeException(iee);}
            if (qe.getTimeEx1().getValue().getType() != Types.NULL) {
                rqlRetrieveParam.setStartTime(new Timestamp(qe.getTimeEx1().getValue().getIntVal()));
                /*if (qe.getTimeEx1().getValues().length > 1) {
                    rqlScope.addRunTimeWarning(new RQLRunTimeWarning(
                            RQLRunTimeWarning.WarningType.AMBIGUOUS_TIME_EXPRESSION,
                            "timeex1",
                            "Time From expression is ambiguous as it evaluates to more than one value. Only 1st value is used"
                    ));
                }*/
            }
        }
        if (qe.getTimeEx2() != null) {
            try {
                qe.getTimeEx2().evaluate();
            } catch (IllegalExpressionException iee) {rqlScope.addRunTimeException(iee);}
            if (qe.getTimeEx2().getValue().getType() != Types.NULL) {
                rqlRetrieveParam.setStartTime(new Timestamp(qe.getTimeEx2().getValue().getIntVal()));
                /*if (qe.getTimeEx2().getValues().length > 1) {
                    rqlScope.addRunTimeWarning(new RQLRunTimeWarning(
                            RQLRunTimeWarning.WarningType.AMBIGUOUS_TIME_EXPRESSION,
                            "timeex2",
                            "Time TO expression is ambiguous as it evaluates to more than one value. Only 1st value is used"
                    ));
                }*/
            }
        }
    }
}
