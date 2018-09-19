import java.awt.*;
import java.util.Random;

/**
 * An example of what you could add to the game utilizing the base.
 */
public class Thunder implements Drawable, Physics {

    private Random random;
    private double time = 0;
    private boolean active = false;
    private int extra = 0;

    public Thunder() {
        random = new Random(666);
    }

    @Override
    public void draw(Graphics g) {
        // Check update for how this works more specifically. But basically when the thunder should show it draws
        // a white square over the entire screen that is transparent and fades out.
        if (active && time > 1 && time <= 2) {
            g.setColor(new Color(255, 255, 255, (int) (100 * (time - 1))));
            g.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
        }
    }

    @Override
    public void drawBackground(Graphics g) {
    }

    @Override
    public void update(double delta) {
        if (active) { // If it is currently thundering
            //if (time <= 1) active = false;
            time -= delta * 2;
            if (time < 1.4 && extra > 0) { // If there is multiple thunders connected
                extra--;
                time = 2;
            }
        } else if (time < 2) { // If there is less than 2 seconds since last chance of thunder
            time += delta; // add time
        } else {
            // Every 2 seconds there is a 40% chance of thunder if the time is positive (see World.update if you wonder
            // how it could be negative).
            if (random.nextDouble() > 0.60 && delta > 0) {
                time = 2;
                active = true; // Activates the thunder
                double d = random.nextDouble();
                if (d > .7) { // 30% to have an extra thunder
                    extra++;
                    if (d > 0.9) extra++; // 30% to get one more extra thunder
                }
            } else { // If there is no thunder this time, reset the time elapsed to 0
                time = 0;
            }
        }
    }
}
