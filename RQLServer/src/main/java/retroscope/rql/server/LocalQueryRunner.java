package retroscope.rql.server;

import org.apache.log4j.Logger;
import retroscope.datamodel.datastruct.variables.LongRQLVariable;
import retroscope.hlc.Timestamp;
import retroscope.rql.environment.Environment;
import retroscope.rql.environment.GlobalCut;
import retroscope.rql.environment.ScanningEnvironment;
import retroscope.rql.errors.RQLRunTimeException;
import retroscope.rql.errors.RQLRunTimeWarning;
import retroscope.rql.log.RQLStateSequence;
import retroscope.rql.parser.RQLSearchParam;
import retroscope.rql.syntaxtree.Param;
import retroscope.rql.syntaxtree.Query;
import retroscope.rql.syntaxtree.QueryEnd;
import retroscope.rql.syntaxtree.expression.IllegalExpressionException;

import java.util.*;

public class LocalQueryRunner implements Runner {

    private RQLStateSequence stateSequence;
    private Query q;
    private ScanningEnvironment env;

    private Logger logger = Logger.getLogger(LocalQueryRunner.class);

    public LocalQueryRunner(Query q, RQLStateSequence stateSequence) {
        this.q = q;
        this.stateSequence = stateSequence;
    }

    public ArrayList<RQLRunTimeException> getExceptions() {
        if (env != null) {
            return env.getExceptions();
        }
        return null;
    }
    public ArrayList<RQLRunTimeWarning> getWarnings() {
        if (env != null) {
            return env.getWarnings();
        }
        return null;
    }

    public boolean hasRunTimeErrors() {
        return env != null && env.getExceptions().size() >= 1;
    }

    public boolean hasRunTimeWarnings() {
        return env != null && env.getWarnings().size() >= 1;

    }


    @Override
    public boolean isExplain() {
        return q.isExplain();
    }

    public String explain() {
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }

    public void execute() {


        QueryEnd qe = q.getQe();
        //set fetchedLogs we retrieve
        RQLSearchParam rqlSearchParam = new RQLSearchParam().setLogs(Arrays.asList(q.getLogs().getIdentifiers()));
        //set keys to be retrieved
        /*List<Param> defaultLogsParams = q.getParams().getParams().get("");
        q.getParams().getParams().put(q.getLogs().getIdentifiers()[0], defaultLogsParams);

        rqlSearchParam.setAllKeys(q.getParams().getParams());*/
        //set nodes we use
        if (qe.getNodeIds() != null && !qe.getNodeIds().isAllNodes()) {
            String nodeIds[] = qe.getNodeIds().getIdentifierList().getIdentifiers();
            ArrayList<Integer> nodes = new ArrayList<>();
            for (String node : nodeIds) {
                nodes.add(Integer.parseInt(node));
            }
            rqlSearchParam.setNodeIds(nodes);
        }
        ArrayList<String> paramList = new ArrayList<>();

        for (Param p : q.getParams().getParams()) {
            paramList.add(p.toString());
        }
        rqlSearchParam.setParams(paramList);

        //set times we retrieve
        setTimeConstraints(rqlSearchParam);

        //now if we decided whether we retrieve a log slice or just a single cut
        //single cut is retrieved only when an exact time is given
        if (rqlSearchParam.getStartTime() != null && rqlSearchParam.getEndTime() == null) {
           //single cut query
        } else {



            env = new ScanningEnvironment(stateSequence, paramList);
            if (qe.getComputeStatements() != null) {
                env.setComputeExpression(qe.getComputeStatements());
            }
            if (qe.getConditions() != null) {
                env.addExpression(qe.getConditions());
            }
            env.scan();

            //now schedule workers

            /*queryEnv.resetEmitted();
            queryEnv.setLinkedNodes(qe.getLinks());

            if (qe.getConditions() != null) {
                qe.getConditions().setQueryEnvironment(queryEnv);
                //set condition for search
                rqlSearchParam.setCondition(qe.getConditions());
            }
            try {
                queryEnv.updateSymbolTableWithLogName(q.getLogs().getIdentifiers());
            } catch (RQLRunTimeException rqlrte) {
                queryEnv.addRunTimeException(rqlrte);
            }


            queryEnv.retrieveStateSeq();

            //and now we evaluate WHEN conditions
            queryEnv.findCuts(rqlSearchParam);

            emittedGlobalCuts = queryEnv.getEmittedGlobalCuts();
            warnings = queryEnv.getWarnings();
            exceptions = queryEnv.getExceptions();*/
        }
    }


    private void setTimeConstraints(RQLSearchParam rqlSearchParam) {
        Environment env = new Environment();
        QueryEnd qe = q.getQe();
        if (qe.getTimeEx1() != null) {
            try {
                qe.getTimeEx1().evaluate();
            } catch (IllegalExpressionException iee) {
                env.addRunTimeException(iee);}
            if (qe.getTimeEx1().getValue() instanceof LongRQLVariable) {
                rqlSearchParam.setStartTime(new Timestamp(((LongRQLVariable) qe.getTimeEx1().getValue()).getValue(), (short)0));
            }
        }

        if (qe.getTimeEx2() != null) {
            try {
                qe.getTimeEx2().evaluate();
            } catch (IllegalExpressionException iee) {
                env.addRunTimeException(iee);}
            if (qe.getTimeEx2().getValue() instanceof LongRQLVariable) {
                rqlSearchParam.setEndTime(new Timestamp(((LongRQLVariable) qe.getTimeEx1().getValue()).getValue(), (short)0));
            }
        }
    }

    /*---------------------------
    /* Output stuff
    /*---------------------------*/

    public ArrayList<GlobalCut> getEmittedCuts() {
        if (env != null) {
            return env.getEmittedCuts();
        }
        return new ArrayList<>();
    }
}
