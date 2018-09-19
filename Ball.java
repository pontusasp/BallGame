import java.awt.*;

/**
 * Example class of what you can easily add with the base stuff.
 */
public class Ball implements Drawable, Physics {

    private double x, y;
    private double vx, vy; // Velocities in x and y
    private int radius = 25;
    private Color color = new Color(130, 120, 0); // Color of the ball

    public Ball() {
        // Starting positions
        x = Window.WIDTH / 2;
        y = Window.HEIGHT - 50;
    }

    /**
     * Pretty obvious, but this method draws a ball.
     * @param g This is the graphic object obtained from the Window.
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval((int) x - radius, (int) y - radius, radius * 2, radius * 2);
    }

    /**
     * The ball doesn't draw a background, but if I would add something here it would be a shadow.
     * @param g This is the graphic object obtained from the Window.
     */
    @Override
    public void drawBackground(Graphics g) {

    }

    /**
     * Updates the physics and the controls for the ball.
     * @param delta
     */
    @Override
    public void update(double delta) {
        if (Keys.D) {
            if (vx < 500)
                vx += 1000 * Math.abs(delta);
        }

        if (Keys.A) {
            if (vx > -500)
                vx -= 1000 * Math.abs(delta);
        }

        if (y >= Window.HEIGHT - 50) { // If the ball is on the ground
            vy *= -0.6; // This makes the ball bounce but loose 40% of its momentum each time.
            y = Window.HEIGHT - 50;
            if (vx < 0 && !Keys.A) {
                vx += 500 * Math.abs(delta);
            } else if (vx > 0 && !Keys.A) {
                vx -= 500 * Math.abs(delta);
            }
            if (Math.abs(vy) < 0.01) { // Added this because otherwise the ball never stops bouncing fully.
                vy = 0;
            }
        } else if (vx < 0) { // This is slowing the ball down a little bit (friction with the air)
            vx += 50 * delta;
        } else if (vx > 0) { // Same, but other direction.
            vx -= 50 * delta;
        }

        if (x < -radius) x = Window.WIDTH + radius; // If the ball exits to the left, spawn to the right.
        if (x > Window.WIDTH + radius) x = -radius; // The other way around.

        // Make it jump if it is on the ground
        if (Keys.W && y >= Window.HEIGHT - 50) {
            vy -= 500; // Note I do not use time here since the jump is instantaneous and not accelerating.
        } else if (y < Window.HEIGHT - 50) {
            // If the ball is not on the ground add earths gravitation.
            // World.METER is how many pixels corresponds to 1 meter.
            vy += 9.82 * delta * World.METER;
        }

        // Finally, update the position with the speed.
        x += vx * delta;
        y += vy * delta;
    }
}
