package retroscope.datamodel.parser;

import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.hlc.Timestamp;

import java.util.*;

public class SetItems {

    private ArrayList<RQLSymbol> setItems;

    public SetItems() {
        setItems = new ArrayList<>();
    }

    public SetItems(RQLSymbol s) {
        this();
        setItems.add(s);
    }

    public SetItems addItem(RQLSymbol s) {
        setItems.add(s);
        return this;
    }

    public ArrayList<RQLSymbol> getSetItems() {
        return setItems;
    }
}
