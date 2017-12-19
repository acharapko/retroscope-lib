package retroscope.rql.parser;

import org.apache.ignite.internal.util.typedef.PA;
import retroscope.hlc.Timestamp;
import retroscope.rql.syntaxtree.Param;
import retroscope.rql.syntaxtree.expression.Expression;

import java.util.*;

/**
 * Created by Aleksey on 1/26/2017.
 *
 */
public class RQLSearchParam implements Cloneable {

    private Timestamp startTime, endTime;
    private List<String> logs;
    private HashMap<String, List<Param>> keys;
    private List<Integer> nodeIds;
    private Expression condition;
    private Expression computeExpression;
    private ArrayList<String> params;

    public RQLSearchParam() {
        this.keys = new HashMap<>();
    }

    public RQLSearchParam setStartTime(Timestamp startTime) {
        this.startTime = startTime;
        return this;
    }

    public RQLSearchParam setEndTime(Timestamp endTime) {
        this.endTime = endTime;
        return this;
    }

    public RQLSearchParam setLogs(List<String> logs) {
        this.logs = logs;
        return this;
    }

    public RQLSearchParam setKeys(String logName, List<Param> keys) {
        this.keys.put(logName, keys);
        return this;
    }

    public RQLSearchParam setAllKeys(HashMap<String, List<Param>> keys) {
        this.keys = keys;
        return this;
    }

    public RQLSearchParam setNodeIds(List<Integer> nodeIds) {
        this.nodeIds = nodeIds;
        return this;
    }

    public ArrayList<String> getParams() {
        return params;
    }

    public void setParams(ArrayList<String> params) {
        this.params = params;
    }

    public void setCondition(Expression condition) {
        this.condition = condition;
    }

    public void setComputeExpression(Expression computeExpression) {
        this.computeExpression = computeExpression;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public List<String> getLogs() {
        return logs;
    }

    public List<Param> getKeys(String logName) {
        return keys.get(logName);
    }

    public int countKeys() {
        int count = 0;
        Iterator<Map.Entry<String, List<Param>>> iterator = keys.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, List<Param>> pair = iterator.next();
            count += pair.getValue().size();
        }
        return count;
    }

    public List<String> getKeysAsString(String logName) {
        List<Param> list = keys.get(logName);
        ArrayList<String> keys = new ArrayList<>(list.size());
        for (Param p : list) {
            keys.add(p.getIdentifier());
        }
        return keys;
    }

    public List<Integer> getNodeIds() {
        return nodeIds;
    }

    public Expression getCondition() {
        return condition;
    }

    public Expression getComputeExpression() {
        return computeExpression;
    }

    public RQLSearchParam clone() {
        RQLSearchParam cloned = new RQLSearchParam();
        if (startTime != null) {
            cloned.startTime = startTime.clone();
        }
        if (endTime != null) {
            cloned.endTime = endTime.clone();
        }
        cloned.keys = new HashMap<>(keys);
        cloned.logs = new ArrayList<>(logs);
        if (nodeIds != null) {
            cloned.nodeIds = new ArrayList<>(nodeIds);
        }
        if (condition != null) {
            cloned.condition = condition.clone();
        }
        if (computeExpression != null) {
            cloned.computeExpression = computeExpression.clone();
        }
        cloned.params = new ArrayList<>(params);

        return cloned;
    }

}