package retroscope.hlc;

/**
 * Created by aleksey on 10/15/16.
 * Timestamp is an HLC time with physical (wallTime) and logical components
 */

public class Timestamp implements Comparable<Timestamp>, Cloneable {

    private long wallTime;
    private short logical;

    public Timestamp() {
        this(System.currentTimeMillis(), (short)0);
    }

    public Timestamp(long wallTime, short logical) {
        this.wallTime = wallTime;
        this.logical = logical;
    }


    /**
     * creates a timestamp from 64 bit representation of HLC
     * @param t - long representation of an HLC
     * @return Timestamp object that has been reconstructed from a 64 bit integer
     *
     */
    public Timestamp(long t)
    {
        wallTime = t >> 16;
        logical = (short)(t & 0x00000000000000FFFFL);
    }

    public Timestamp clone() {
        return new Timestamp(this.wallTime, this.logical);
    }

    public Timestamp add(Timestamp t) {
        return new Timestamp(this.wallTime + t.wallTime, (short)(this.logical + t.logical));
    }

    public Timestamp add(long wallTime, short logical) {
        return new Timestamp(this.wallTime + wallTime, (short)(this.logical + logical));
    }


    public long getWallTime() {
        return wallTime;
    }

    public void setWallTime(long wallTime) {
        this.wallTime = wallTime;
    }

    public short getLogical() {
        return logical;
    }

    public void setLogical(short logical) {
        this.logical = logical;
    }

    public void incrementLogical()
    {
        this.logical++;
    }

    public void resetLogical()
    {
        this.logical = 0;
    }

    /**
     * toLong packs an HLC into a 64 bit integer
     * First 48 bits are for physical time
     * last 16 bits are for logical component
     */
    public long toLong()
    {
        long longRepresentation = this.wallTime;
        longRepresentation = longRepresentation << 16;
        //longRepresentation = (longRepresentation & 0xFFFFFFFFFFFF0000L);
        longRepresentation = (longRepresentation | (long)this.logical);
        return longRepresentation;
    }

    public int compareTo(Timestamp o) {
        if (this.wallTime < o.wallTime
                || (this.wallTime == o.wallTime && this.logical < o.logical)) {
            return -1;
        } else if (this.wallTime > o.wallTime
                || (this.wallTime == o.wallTime && this.logical > o.logical)) {
            return 1;
        }
        return 0;
    }

    public String toString()
    {
        return this.wallTime + ", " + this.logical;
    }

}

