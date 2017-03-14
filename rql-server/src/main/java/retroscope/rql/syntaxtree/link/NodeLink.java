package retroscope.rql.syntaxtree.link;

import retroscope.rql.memory.SimpleSymbol;

import java.util.HashMap;

/**
 * Created by Aleksey on 3/8/2017.
 *
 */
public interface NodeLink {

    boolean nextPlaceholders(HashMap<String, SimpleSymbol> symbolTable);

    void reset();
}
