package retroscope.rql.syntaxtree.link;

/**
 * Created by Aleksey on 1/22/2017.
 *
 */
public class Link {

    private String symbolName;
    private int nodeId;

    public Link(String symbolName, int nodeId) {
        this.symbolName = symbolName;
        this.nodeId = nodeId;
    }

    public String getSymbolName() {
        return symbolName;
    }

    public int getNodeId() {
        return nodeId;
    }

    public boolean equals(Object link) {
        if (link instanceof Link) {
            if (this.nodeId ==((Link) link).nodeId && this.symbolName.equals(((Link) link).getSymbolName())) {
                return true;
            }
        }
        return false;
    }
}
