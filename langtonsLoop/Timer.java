package langtonsLoop;
import javafx.animation.AnimationTimer;
import java.util.concurrent.TimeUnit;
public class Timer {
    public static void run(LGrid grid) {
        AnimationTimer timer = new AnimationTimer() {
            private long prevUpdate = 0;
            @Override
            public void handle(long now) {
                if (now - prevUpdate >= TimeUnit.SECONDS.toNanos(1)) {
                   // grid.nextGeneration();
                    prevUpdate = now;
                }
            }
        };
        timer.start();
    }
}
