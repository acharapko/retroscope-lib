package retroscope.appviewer;


public class AppData {

    private String appName;
    private int maxId;
    private int heartbeatInterval;
    private int maxMissedHeartbeats;
    private int blockSize;

    private int bufSize;
    private int bufFlushRate;

    private int numNodes = 0;
    private int numBlocks = 0;

    public AppData(String appName) {
        this.appName = appName;
    }

    public String getAppName() {
        return appName;
    }

    public int getMaxId() {
        return maxId;
    }

    public int getHeartbeatInterval() {
        return heartbeatInterval;
    }

    public int getMaxMissedHeartbeats() {
        return maxMissedHeartbeats;
    }

    public int getBlockSize() {
        return blockSize;
    }


    public int getNumNodes() {
        return numNodes;
    }

    public int getNumBlocks() {
        return numBlocks;
    }

    public void setMaxId(int maxId) {
        this.maxId = maxId;
    }

    public void setHeartbeatInterval(int heartbeatInterval) {
        this.heartbeatInterval = heartbeatInterval;
    }

    public void setMaxMissedHeartbeats(int maxMissedHeartbeats) {
        this.maxMissedHeartbeats = maxMissedHeartbeats;
    }

    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }

    public void setNumNodes(int numNodes) {
        this.numNodes = numNodes;
    }

    public void setNumBlocks(int numBlocks) {
        this.numBlocks = numBlocks;
    }

    public int getBufSize() {
        return bufSize;
    }

    public void setBufSize(int bufSize) {
        this.bufSize = bufSize;
    }

    public int getBufFlushRate() {
        return bufFlushRate;
    }

    public void setBufFlushRate(int bufFlushRate) {
        this.bufFlushRate = bufFlushRate;
    }
}
