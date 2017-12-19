package retroscope.datamodel.datastruct;

import retroscope.datamodel.datastruct.misc.IdList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Aleksey on 11/1/2017.
 *
 */
public abstract class RQLSymbol implements RQLDataStructure {

    private String name = null;
    private Set<Long> nodeIDs = new HashSet<>();

    public RQLSymbol() {}

    public RQLSymbol(String name) {
        this.name = name;
    }

    public void addNodeIDs(IdList list) {
        if (list != null) {
            this.nodeIDs = list.getIds();
        }
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public boolean isComplete() {
        return name != null;
    }

    public abstract void merge(RQLSymbol mergeSymbol);

    public Set<Long> getNodeIDs() {
        return nodeIDs;
    }

    public String getNodeIDsStr() {
        StringBuilder sb = new StringBuilder();
        for (Long l: nodeIDs) {
            sb.append(l);
            sb.append(',');
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public void addNodeID(long nodeID) {
        this.nodeIDs.add(nodeID);
    }

    public void addNodeIDs(Collection<Long> nodeIDs) {
        this.nodeIDs.addAll(nodeIDs);
    }

    public abstract RQLSymbol clone();

    public abstract String toEmitRQLString();

    public abstract String toValueString();

}
