package retroscope.rql.syntaxtree.link;

import retroscope.rql.memory.Placeholder;
import retroscope.rql.memory.SimpleSymbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Aleksey on 3/8/2017.
 *
 */
public class MultiPlaceholderNodeLink implements NodeLink {

    private ArrayList<Placeholder> placeholders;
    private int[] version;
    private boolean[] wrapAround;

    private int lockNode;

    public MultiPlaceholderNodeLink(List<Placeholder> placeholders) {
        this.placeholders = new ArrayList<Placeholder>(placeholders);
        reset();

    }


    public boolean nextPlaceholders(HashMap<String, SimpleSymbol> symbolTable) {
        boolean reset = true;
        boolean scannedAll = false;
        boolean matchFound = false;
        if (lockNode == -1) {
            resetPlaceholders(symbolTable);
        }
        while (!scannedAll && !matchFound) {
            for (int i = 0; i < placeholders.size(); i++) {
                Placeholder p = placeholders.get(i);
                SimpleSymbol symbolVal = symbolTable.get(p.getSymbolName());

                if (wrapAround[i + 1]) {
                    p.setItem(symbolVal.get(version[i]));
                }



            }
        }
        for (int i = 0; i < placeholders.size(); i++) {
            Placeholder p = placeholders.get(i);



















            if (reset) {
                SimpleSymbol symbolVal = symbolTable.get(p.getSymbolName());
                if (symbolVal != null) {
                    if (version[i] >= symbolTable.get(p.getSymbolName()).size()) {
                        reset = true;
                        version[i] = 0;
                        if (symbolVal.size() > 0) {
                            p.setItem(symbolVal.get(0));
                        } else {
                            p.setItem(null);
                        }
                    } else {
                        if (lockNode == symbolVal.get(version[i]).getNodeId()) {
                            p.setItem(symbolVal.get(version[i]));
                        }

                        version[i]++;
                        reset = false;
                    }
                } else {
                    //by this time we already threw a warning out in the Variable object trying to get the memory
                }
            }
        }
        return reset;
    }

    private void resetPlaceholders(HashMap<String, SimpleSymbol> symbolTable) {
        for (int i = 0; i < placeholders.size(); i++) {
            Placeholder p = placeholders.get(i);
            SimpleSymbol symbolVal = symbolTable.get(p.getSymbolName());
            if (symbolVal != null && symbolTable.size() > 0) {
                p.setItem(symbolTable.get(p.getSymbolName()).get(0));
                if (i == 0) {
                    lockNode = symbolVal.get(0).getNodeId();
                }
            }
        }

    }

    public void reset() {
        lockNode = -1;
        version = new int[this.placeholders.size()];
        version = new int[this.placeholders.size() + 1];
        for (int i = 0; i < this.placeholders.size(); i++) {
            version[i] = 0;
            wrapAround[i] = false;

        }
        wrapAround[this.placeholders.size() + 1] = true;
    }
}
