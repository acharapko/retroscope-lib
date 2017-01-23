package retroscope.rql.syntaxtree;

import retroscope.rql.RQLEnvironment;
import retroscope.rql.Types;
import retroscope.rql.syntaxtree.link.Link;

import java.util.ArrayList;
import java.util.List;

public class LinkConditionalExpression extends TwoOperandExpression {

    private boolean isSameNodeLink = false;

    public LinkConditionalExpression(RQLEnvironment rqlEnvironment, Expression ex1, Expression ex2) {
        super(rqlEnvironment, ex1, ex2);
    }

    public LinkConditionalExpression(RQLEnvironment rqlEnvironment, Expression ex1) {
        super(rqlEnvironment, ex1, ex1);
        this.isSameNodeLink = true;
    }

    public void evaluate() throws IllegalExpressionException {
        ArrayList<RQLInterpreterValue> evalVals
                = new ArrayList<RQLInterpreterValue>();
        if (!isSameNodeLink) {
            ex2.evaluate();
            RQLInterpreterValue[] ex2Vals = ex2.getValues();
            for (RQLInterpreterValue linkVal : ex2Vals) {
                if (linkVal.getType() == Types.INT) {
                    if (linkVal.getIntVal() != 0) {
                        //LINK condition met, learn the nodes
                        ArrayList<Link> linkedNodes = linkVal.getLinkChain();
                        ex1.evaluate();
                        RQLInterpreterValue[] ex1Vals = ex1.getValues();
                        //now throw out any matches that have different link chain tham the LINK condition
                        //if ex1 link chain has more link, it is ok, as long as full link chain from LINK condition
                        //is preserved
                        for (RQLInterpreterValue conditionVal : ex1Vals) {
                            ArrayList<Link> cLinkChain = conditionVal.getLinkChain();
                            if (cLinkChain.size() < linkedNodes.size()) {
                                continue; // next conditionval, since this one has smaller linkchain than needed.
                            }
                            if (chainsMatch(cLinkChain, linkedNodes)) {
                                evalVals.add(conditionVal);
                            }

                        }
                    }
                }
            }
        } else {
            ex1.evaluate();
            RQLInterpreterValue[] ex1Vals = ex1.getValues();
            //now throw out any matches that have different link chain tham the LINK condition
            //if ex1 link chain has more link, it is ok, as long as full link chain from LINK condition
            //is preserved
            for (RQLInterpreterValue conditionVal : ex1Vals) {
                ArrayList<Link> cLinkChain = conditionVal.getLinkChain();
                boolean moreThanOneNode = false;
                if (cLinkChain.size() > 1) {
                    for (int i = 1; i < cLinkChain.size(); i++) {
                        if (cLinkChain.get(i-1).getNodeId() != cLinkChain.get(i).getNodeId()) {
                            moreThanOneNode = true;
                        }
                    }
                }

                if (!moreThanOneNode) {
                    evalVals.add(conditionVal);
                }
            }
        }

        if (evalVals.size() == 0) {
            evalVals.add(new RQLInterpreterValue(Types.NULL));
        }
        this.values = evalVals.toArray(new RQLInterpreterValue[evalVals.size()]);
    }


    private boolean chainsMatch(List<Link> conditionChain, List<Link> linkChain) {
        int matchCount = 0;
        for (Link l1 : conditionChain) {
            for (Link l2 : linkChain) {
                if (l1.equals(l2)) {
                    matchCount++;
                }
            }
        }
        return matchCount == linkChain.size();
    }
}
