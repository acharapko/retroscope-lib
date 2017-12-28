package retroscope.datamodel.datastruct.sets;

import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.datamodel.datastruct.RQLType;
import retroscope.datamodel.datastruct.variables.RQLVariable;
import retroscope.datamodel.parser.SetItems;

import java.util.HashSet;
import java.util.Iterator;

public class RQLSet extends RQLSymbol {

    private HashSet<RQLSymbol> set;

    public RQLSet() {
        super();
        set = new HashSet<>();
    }

    public RQLSet(String name) {
        super(name);
        set = new HashSet<>();
    }

    public RQLSet(SetItems items) {
        super();
        set = new HashSet<>();
        set.addAll(items.getSetItems());
    }

    public void add(RQLSymbol s) {
        set.add(s);
    }

    public void addOrMerge(RQLSymbol s) {
        if (set.contains(s)) {
            for(RQLSymbol symbol : set) {
                if (symbol.equals(s)) {
                    symbol.addNodeIDs(s.getNodeIDs());
                    break;
                }
            }
        } else {
            set.add(s);
        }
    }


    public void replaceOrAddVariable(RQLSymbol s) {
        //loop 1: remove old values/nodeIDs
        Iterator<RQLSymbol> iterator = set.iterator();
        while (iterator.hasNext()) {
            RQLSymbol symbol = iterator.next();
            //TODO: this only handles variable changes
            //TODO: this only handles cases when symbol has only a single nodeID
            if (symbol.getNodeIDs().containsAll(s.getNodeIDs()) && symbol.getNodeIDs().size() == s.getNodeIDs().size()) {
                //set.remove(symbol);
                iterator.remove();
            }  else {
                symbol.getNodeIDs().removeAll(s.getNodeIDs());
                if (symbol.getNodeIDs().size() == 0) {
                    iterator.remove();
                }
            }
        }
        //loop 2 add new values/nodeIDs
        if (!set.contains(s)) {
            set.add(s);
            return;
        }
        iterator = set.iterator();
        while (iterator.hasNext()) {
            RQLSymbol symbol = iterator.next();
            if (symbol.equals(s)) {
                symbol.addNodeIDs(s.getNodeIDs());
                return;
            }
        }
    }

    public void replaceOrAddSets(RQLSet s) {
        //System.out.println("---------------------");
        Iterator<RQLSymbol> iterator = set.iterator();
        boolean updated = false;
        while (iterator.hasNext() && !updated) {
            RQLSymbol symbol = iterator.next();
            if (symbol instanceof RQLSet) {
                if (symbol.getNodeIDs().containsAll(s.getNodeIDs())) {
                    //we have the change from the same nodes
                    if (s instanceof AppendRQLSet) {
                        //System.out.println("append " + s.toEmitRQLString() + " to symbol " + symbol.toEmitRQLString());
                        ((RQLSet) symbol).addAll(s);
                        updated = true;
                    } else if (s instanceof RemoveRQLSet) {
                        ((RQLSet) symbol).removeAll(s);
                        updated = true;
                        //System.out.println("RemoveRQLSet");
                    } else {
                        ((RQLSet) symbol).getSet().clear();
                        ((RQLSet) symbol).addAll(s);
                        updated = true;
                    }
                }
            }
        }
        if (!updated) {
            if (!(s instanceof RemoveRQLSet)) {
                s.getNodeIDs().size();
                set.add(s);
            }
        }

    }

    public void remove(RQLSymbol s) {
        set.remove(s);
    }

    public void addAll(HashSet<RQLSymbol> set) {
        this.set.addAll(set);
    }

    public void addAll(RQLSet set) {
        this.set.addAll(set.set);
    }


    public void removeAll(HashSet<RQLSymbol> set) {
        this.set.removeAll(set);
    }

    public void removeAll(RQLSet set) {
        removeAll(set.set);
    }

    public int size() {
        return set.size();
    }


    /**
     * Checks whether this set is a subset on another set
     * @param s RQLSet another set
     * @return boolean whether this set is a subset on another set
     */
    public boolean isSubset(RQLSet s) {
        return s.getSet().containsAll(this.set);
    }

    public boolean equals(Object s1) {
        if (s1 instanceof RQLSet) {
            RQLSet s = (RQLSet) s1;
            if (size() != s.size()) {
                return false;
            } else {
                for (RQLSymbol symbol: set) {
                    if (!s.set.contains(symbol)) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public HashSet<RQLSymbol> getSet() {
        return set;
    }

    @Override
    public void merge(RQLSymbol mergeSymbol) {

    }

    @Override
    public String toRQLString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getName());
        sb.append(':');
        sb.append('{');
        int l = sb.length();
        for (RQLSymbol s: set) {
            sb.append(s.toRQLString());
            sb.append(',');
        }
        if (l != sb.length()) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append('}');
        return sb.toString();
    }

    public String toEmitRQLString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getName());
        sb.append('|');
        for (Long n : getNodeIDs()) {
            sb.append(n);
            sb.append(',');
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(':');
        sb.append('{');
        int l = sb.length();
        for (RQLSymbol s: set) {
            sb.append(s.toEmitRQLString());
            sb.append(',');
        }
        if (l != sb.length()) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append('}');
        return sb.toString();
    }

    @Override
    public String toValueString() {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        int l = sb.length();
        for (RQLSymbol s: set) {
            sb.append(s.toValueString());
            //sb.append(s.toValueString() + " - " + s.hashCode());
            sb.append(',');
        }
        if (l != sb.length()) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append('}');
        return sb.toString();
    }

    public RQLSet clone() {
        RQLSet clone = new RQLSet(getName());
        clone.set = (HashSet<RQLSymbol>) this.set.clone();
        return clone;
    }
}
