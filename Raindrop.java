import java.awt.*;
import java.util.Random;

public class Raindrop implements Drawable, Physics {

    public static Color color = new Color(150, 150, 255, 180);
    private double x, y, speed;
    private Random random;

    public Raindrop(Random random) {
        this.random = random;
        this.x = random.nextDouble() * Window.WIDTH;
        this.y = random.nextDouble() * (Window.HEIGHT + 100) - 100;
        this.speed = random.nextDouble() * 200 + 500;
    }

    /**
     * Draws the rain
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        if (y < Window.HEIGHT - 100 + (speed - 500) / 2) { // If the rain is in the air
            g.fillRect((int) x, (int) y, 3, 9);
        } else { // If the rain has hit the floor, draw a puddle (makes a nice effect of them hitting the ground)
            g.fillRect((int) x - 1, Window.HEIGHT - 100 + 3 + (int) (speed - 500) / 2, 4, 4);
        }
    }

    @Override
    public void drawBackground(Graphics g) {

    }


    /**
     * Updates the rain
     * @param delta
     */
    @Override
    public void update(double delta) {
        y += speed * delta; // Makes the rain fall with the specified speed

        x -= 80 * delta; // Makes the rain always go a bit to the left, as if it's windy.

        // If the rain goes outside the screen in the x-axis spawn it on the other side.
        if (x < 0) x = Window.WIDTH;
        if (x > Window.WIDTH) x = 0;

        // If the rain hits the ground, respawn with new values
        if (y > Window.HEIGHT - 80 + (speed - 500) / 2) {
            this.y = -(speed - 500) / 2;
            this.x = random.nextDouble() * Window.WIDTH;
            this.speed = random.nextDouble() * 200 + 500;
        } else if (y < -(speed - 500) / 2) { // If the rain is too high spawn on the ground (it can go higher with negative time, see
            // World.update if you wonder how this is possible.)
            y = Window.HEIGHT - 80 + (speed - 500) / 2;
            this.x = random.nextDouble() * Window.WIDTH;
            this.speed = random.nextDouble() * 200 + 500;
        }
    }
}
