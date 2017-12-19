package retroscope.rql.environment;

import org.jetbrains.annotations.NotNull;
import retroscope.datamodel.datastruct.variables.RQLVariable;

import java.util.ArrayList;
import java.util.Collection;

public class GlobalCut implements Comparable<GlobalCut> {

    private ArrayList<Long> cutTimes;
    private String cut;

    public GlobalCut(long cutTime, String cut) {
        cutTimes = new ArrayList<>();
        this.cutTimes.add(cutTime);
        this.cut = cut;
    }

    public void addCutTimes(Collection<Long> times) {
        cutTimes.addAll(times);
    }

    public void addCutTime(long time) {
        cutTimes.add(time);
    }

    public ArrayList<Long> getCutTimes() {
        return cutTimes;
    }

    public String getCut() {
        return cut;
    }

    public String toOneString() {
        StringBuilder sb = new StringBuilder();

        if (cutTimes.size() > 0) {
            sb.append('<');
            sb.append(cutTimes.get(0));
            sb.append('>');
            sb.append(':');
            sb.append(cut);
            sb.append(System.lineSeparator());
        }

        return sb.toString();

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (long time: cutTimes) {
            sb.append('<');
            sb.append(time);
            sb.append('>');
            sb.append(':');
            sb.append(cut);
            sb.append(System.lineSeparator());
        }

        return sb.toString();

    }

    /*
    * Equality is based on the value only and not on additional meta-data, such as name or nodeID
    */
    public boolean equals(Object obj) {
        return obj != null
                && obj instanceof RQLVariable
                && cut.equals(((GlobalCut)obj).getCut());
    }

    public int hashCode(){
        return cut.hashCode();
    }


    @Override
    public int compareTo(@NotNull GlobalCut o) {

        if (this.getCutTimes().size() > 0 && o.getCutTimes().size() > 0) {
            int c1 = this.cutTimes.get(this.getCutTimes().size() - 1).compareTo(o.getCutTimes().get(0));
            if (c1 < 0) {
                return - 1;
            } else {
                int c2 = this.cutTimes.get(0).compareTo(o.getCutTimes().get(o.getCutTimes().size() - 1));
                if (c2 > 0) {
                    return 1;
                }
            }
        }
        return 0;

    }
}
