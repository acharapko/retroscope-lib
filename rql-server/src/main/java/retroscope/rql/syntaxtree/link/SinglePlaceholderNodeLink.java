package retroscope.rql.syntaxtree.link;

import retroscope.rql.memory.Placeholder;
import retroscope.rql.memory.SimpleSymbol;

import java.util.HashMap;

/**
 * Created by Aleksey on 3/8/2017.
 *
 */
public class SinglePlaceholderNodeLink implements NodeLink {

    private Placeholder p;
    private int version = 0;

    public SinglePlaceholderNodeLink(Placeholder p) {
        this.p = p;
    }

    public boolean nextPlaceholders(HashMap<String, SimpleSymbol> symbolTable) {
        boolean reset = true;
        SimpleSymbol symbolVal = symbolTable.get(p.getSymbolName());
        if (symbolVal != null) {
            if (version >= symbolTable.get(p.getSymbolName()).size()) {
                reset = true;
                reset();
                if (symbolVal.size() > 0) {
                    p.setItem(symbolVal.get(0));
                } else {
                    p.setItem(null);
                }
            } else {
                p.setItem(symbolVal.get(p.getVersion()));
                version++;
                reset = false;
            }
        } else {
            //by this time we already threw a warning out in the Variable object trying to get the memory
        }
        return reset;
    }

    public void reset() {
        version = 0;
    }
}
