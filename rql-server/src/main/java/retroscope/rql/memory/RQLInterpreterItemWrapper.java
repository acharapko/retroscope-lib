package retroscope.rql.memory;

import retroscope.rql.RQLItem;
import retroscope.rql.TypedValue;
import retroscope.rql.Types;
import retroscope.rql.syntaxtree.link.Link;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksey on 2/25/2017.
 * Wrapper for RQLItem that has node ID
 * */

public class RQLInterpreterItemWrapper  {

    private RQLItem item;
    private int nodeId;

    public RQLInterpreterItemWrapper(RQLItem item, int nodeId) {
        this.item = item;
        this.nodeId = nodeId;
    }

    public RQLItem getItem() {
        return item;
    }

    public int getNodeId() {
        return nodeId;
    }
}
