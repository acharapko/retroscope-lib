import retroscope.Retroscope;

import java.util.Random;

/**
 * Created by Aleksey on 11/2/2016.
 */
public class Example {

    private Retroscope<String, String> retro;

    public static void main(String[] args) {
        Example e = new Example();
        try {
            System.in.read();
        } catch (Exception ex) {}
    }

    public Example() {
        retro = new Retroscope<String, String>()
                .setLogCheckpointIntervalMs(1000)
                .connectRetroServer("127.0.0.1", 8007);
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 500; i++) {
            try {
                Thread.sleep(random.nextInt(1000));
                retro.appendToLog("test", "key"+random.nextInt(10), "val" + i);
            } catch (Exception ex) {}
        }
    }

}
