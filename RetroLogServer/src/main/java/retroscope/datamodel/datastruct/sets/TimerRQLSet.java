package retroscope.datamodel.datastruct.sets;

import retroscope.datamodel.datastruct.RQLSymbol;

import java.util.*;

public class TimerRQLSet extends RQLSet {

    private TreeMap<Long, ExpiryBucket> expiryMap;
    private long expireTime;

    public TimerRQLSet() {
        this.expiryMap = new TreeMap<>();
        this.expireTime = 100;
    }

    public TimerRQLSet(String name, long expireTime) {
        super(name);
        this.expiryMap = new TreeMap<>();
        this.expireTime = expireTime;
    }

    public void add(RQLSymbol s) {
        super.add(s);
        //use system time instead of HLC to make sure we properly keep track of expiration time
        long t = System.currentTimeMillis();
        ExpiryBucket b = expiryMap.get(t);
        if (b == null) {
            b = new ExpiryBucket();
            expiryMap.put(t, b);
        }
        b.addSymbol(s);
    }

    public void addAll(HashSet<RQLSymbol> set) {
        for (RQLSymbol symb: set) {
            add(symb);
        }
    }

    public void addAll(RQLSet set) {
        for (RQLSymbol symb: set.getSet()) {
            add(symb);
        }
    }

    public RemoveRQLSet expireItems() {
        RemoveRQLSet rset = new RemoveRQLSet(this.getName());

        long t = System.currentTimeMillis();
        Iterator<Map.Entry<Long, ExpiryBucket>> iterator = expiryMap.entrySet().iterator();
        boolean old = true;
        while (iterator.hasNext() && old) {
            Map.Entry<Long, ExpiryBucket> entry = iterator.next();
            if (entry.getKey() + expireTime < t) {
                for (RQLSymbol s: entry.getValue().getSymbols()) {
                    rset.add(s);
                }
                iterator.remove();
            } else {
                old = false;
            }
        }

        return rset;
    }

    class ExpiryBucket {
        ArrayList<RQLSymbol> symbols;

        public ExpiryBucket() {
            symbols = new ArrayList<>();
        }

        public void addSymbol(RQLSymbol s) {
            symbols.add(s);
        }

        public ArrayList<RQLSymbol> getSymbols() {
            return symbols;
        }
    }

}
