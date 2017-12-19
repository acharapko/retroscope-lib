package retroscope.rql.environment;

import org.junit.Before;
import org.junit.Test;
import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.datamodel.datastruct.sets.RQLSet;
import retroscope.datamodel.datastruct.variables.LongRQLVariable;
import retroscope.hlc.Timestamp;
import retroscope.net.ignite.EventStreamEntry;
import retroscope.rql.log.RQLStateSequence;
import retroscope.rql.syntaxtree.IdentifierList;
import retroscope.rql.syntaxtree.expression.*;
import retroscope.rql.syntaxtree.expression.arithmetic.Plus;
import retroscope.rql.syntaxtree.expression.compare.GreaterThanExpression;
import retroscope.rql.syntaxtree.expression.literals.IntegerLiteral;
import retroscope.rql.syntaxtree.expression.literals.Variable;
import retroscope.rql.syntaxtree.expression.stackedexpression.Exists;
import retroscope.rql.syntaxtree.expression.stackedexpression.ForAll;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.Assert.*;

public class ScanningEnvironmentTest {

    private RQLStateSequence stateSequence;
    private static final int SEQ_LEN = 150;
    private ArrayList<String> params;

    @Before
    public void setUp() throws Exception {
        Timestamp t = new Timestamp();
        stateSequence = new RQLStateSequence(t);

        for (int i = 0; i < SEQ_LEN; i++) {
            t = t.add(1, (short) 0);
            LongRQLVariable v = new LongRQLVariable("a", i*i);
            String var = "<test," + t.toLong()+",1>:" + v.toRQLString();
            EventStreamEntry entry = new EventStreamEntry(t.toLong(), var);
            stateSequence.appendItem(entry);

            v = new LongRQLVariable("a", i+i);
            var = "<test," + t.toLong()+",2>:" + v.toRQLString();
            entry = new EventStreamEntry(t.toLong(), var);
            stateSequence.appendItem(entry);
        }

        params = new ArrayList<>();
        params.add("a");
    }

    @Test
    public void nextCut() throws Exception {
        ScanningEnvironment env = new ScanningEnvironment(stateSequence, params);
        FuncCall card = new FuncCall("Cardinality", new ExpressionList(new Variable("a")));
        Plus p = new Plus(new IntegerLiteral(-1), new IntegerLiteral(2));
        GreaterThanExpression ex = new GreaterThanExpression(card, p);
        env.addExpression(ex);
        int i = 0;
        while (env.nextCut()) {
            //System.out.println("nextCut");
            assertTrue(env.getSymbolTable().size() == 1);
            RQLSymbol s = env.getSymbol("a");
            assertTrue(s instanceof RQLSet);
            RQLSet symbol = (RQLSet)s;
            /* System.out.println(symbol.size());*/
            assertTrue(symbol.size() == ((i == 0 || i == 2) ? 1 : 2));
            i++;
        }

        ArrayList<GlobalCut> cuts = env.getEmittedCuts();
        System.out.println(cuts.size());
        assertTrue(cuts.size() + 2 == SEQ_LEN);
    }

    @Test
    public void testExists() throws Exception {
        ScanningEnvironment env = new ScanningEnvironment(stateSequence, params);
        GreaterThanExpression gte = new GreaterThanExpression(new Variable("c"), new IntegerLiteral(1));

        Exists exists = new Exists(new IdentifierList("c"), new Variable("a"), gte);


        env.addExpression(exists);
        while (env.nextCut()) {
        }

        ArrayList<GlobalCut> cuts = env.getEmittedCuts();
        System.out.println(cuts.size());
        assertTrue(cuts.size() + 1 == SEQ_LEN);
    }

    @Test
    public void testForAll() throws Exception {
        ScanningEnvironment env = new ScanningEnvironment(stateSequence, params);
        GreaterThanExpression gte = new GreaterThanExpression(new Variable("c"), new IntegerLiteral(1));

        ForAll forAll = new ForAll(new IdentifierList("c"), new Variable("a"), gte);


        env.addExpression(forAll);
        while (env.nextCut()) {
        }
        ArrayList<GlobalCut> cuts = env.getEmittedCuts();
        System.out.println(cuts.size());
        assertTrue(cuts.size() + 2 == SEQ_LEN);
    }

}