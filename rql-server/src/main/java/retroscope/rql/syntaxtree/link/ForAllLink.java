package retroscope.rql.syntaxtree.link;

import retroscope.rql.syntaxtree.IdentifierList;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Aleksey on 3/14/2017.
 * 
 */
public class ForAllLink extends Link {

    private Set<Integer> satisfiedOnNodes;

    public ForAllLink(IdentifierList placeholders) {
        super(placeholders);
        resetSatisfied();
    }

    public void markSatisfied() {
        satisfiedOnNodes.add(linkedNode.getLockedNode());
    }

    public void resetSatisfied() {
        satisfiedOnNodes = new HashSet<Integer>();
    }

    public boolean satisfiedAll(Set<Integer> all) {
        return all.equals(satisfiedOnNodes);
    }

    public Set<Integer> getSatisfiedOnNodes() {
        return satisfiedOnNodes;
    }
}
