package retroscope.net.server;


import io.netty.channel.Channel;

/**
 * Created by Aleksey on 8/14/2016.
 */
public class SyncRunner implements Runnable
{
    private Thread t;
    private int stepsBetweenSync = 2;
    private float stepDelay = 0;
    private boolean isRunning = true;
    private long previousTimems;
    /**
     * starts nes sync runner with a specified delay between syncs
     * @param stepDelay
     */
    public SyncRunner(float stepDelay, int stepsBetweenSync)
    {
        this.stepDelay = stepDelay;
        this.stepsBetweenSync = stepsBetweenSync;
        t = new Thread(this);
        previousTimems = System.currentTimeMillis();
        t.start();
    }

    @Override
    public void run() {
        int syncStepCounter = 0;
        while (isRunning) {
            long t1 = System.currentTimeMillis();
            float delta = (t1 - previousTimems) / 1000.0f;
            previousTimems = t1;
            //System.out.println(t1 + " Render, delta = " + delta + " seconds");

            if (++syncStepCounter == stepsBetweenSync) {
                //System.out.println("Sync");
                syncClients();
                syncStepCounter = 0;
            }

            long t2 = System.currentTimeMillis();
            if (t2 - t1 < (int) (stepDelay * 1000)) {
                try {
                    Thread.sleep((int) (stepDelay * 1000) - t2 + t1);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }

    private void syncClients()
    {

    }

    public void stop()
    {
        isRunning = false;
    }
}