package retroscope.datamodel.parser;

import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.hlc.Timestamp;

import java.util.*;

public class RQLData {

    private ArrayList<RQLSymbol> dataItems;
    private Timestamp timestamp;
    private String logName;
    private int nodeId;

    public RQLData() {
        dataItems = new ArrayList<>();
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public ArrayList<RQLSymbol> getDataItems() {
        return dataItems;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setTimestamp(long hlc) {
        setTimestamp(new Timestamp(hlc));
    }


    public void appendDataItem(RQLSymbol dataItem) {
        dataItems.add(dataItem);
    }

    public void setDataItems(ArrayList<RQLSymbol> dataItems) {
        this.dataItems = dataItems;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(long nodeId) {
        setNodeId((int) nodeId);
    }

    public void setNodeId(int nodeId) {
        //if we set node it for this data object then all symbols in this RQLData are form the same node
        this.nodeId = nodeId;
        for (RQLSymbol s : dataItems) {
            s.addNodeID(nodeId);
        }
    }

    public void restrict(ArrayList<String> params) {
        Iterator<RQLSymbol> iterator = dataItems.iterator();
        while (iterator.hasNext()) {
            RQLSymbol entry = iterator.next();
            if (!params.contains(entry.getName())) {
                //System.out.println("Restricting: removing key " + entry.getKey());
                iterator.remove();
            }
        }
    }
}
