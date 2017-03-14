package retroscope.rql.syntaxtree.link;

/**
 * Created by Aleksey on 3/9/2017.
 *
 */
public class LinkLock {

    private int lockedNode;
    private int cardinality = 1;

    public LinkLock() {
        this.lockedNode = -1;
    }

    public int getLockedNode() {
        return lockedNode;
    }

    public void setLockedNode(int lockedNode) {
        this.lockedNode = lockedNode;
    }

    public void reset() {
        this.lockedNode = -1;
    }

    public int getCardinality() {
        return cardinality;
    }

    public void setCardinality(int cardinality) {
        this.cardinality = cardinality;
    }

    public void oneUp() {
        cardinality++;
    }
}
