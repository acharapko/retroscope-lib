package retroscope.rql.syntaxtree;

/**
 * Created by Aleksey on 12/20/2016.
 */
public class AtNodes {

    private IdentifierList identifierList;
    private boolean allNodes = false;

    public AtNodes(IdentifierList idlist) {
        this.identifierList = idlist;
    }

    public AtNodes() {
        allNodes = true;
    }

    public IdentifierList getIdentifierList() {
        return identifierList;
    }

    public boolean isAllNodes() {
        return allNodes;
    }
}
