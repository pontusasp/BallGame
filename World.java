import java.awt.*;
import java.util.Random;

public class World implements Drawable, Physics {

    private Drawable[] drawables;
    private Physics[] physics;
    private double time = 1;

    private final int WIDTH, HEIGHT;

    private Color colorSky = new Color(20, 80, 120);
    private Color colorGrass = new Color(30, 80, 30);

    private Thunder thunder;
    private Ball ball;

    public static final int METER = 200;

    public World(int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;

        // The arrays holding the drawable and physics based objects.
        // It would probably be better to make an iterable Queue to store these in.
        physics = new Physics[202];
        drawables = new Drawable[201];

        // Random number generator (pretty self explanatory lol)
        Random random = new Random(1337);

        // Create 200 raindrops and put them in the physics and drawable arrays.
        for (int i = 0; i < 200; i++) {
            Raindrop raindrop = new Raindrop(random);
            physics[i] = raindrop;
            drawables[i] = raindrop;
        }

        // Create the ball and add it to the arrays.
        ball = new Ball();
        drawables[200] = ball;
        physics[200] = ball;

        // Create thunder and add it to the physics. I did not add it to the drawable because it is
        // important the thunder is drawn on top of everything else, check the drawing methods.
        thunder = new Thunder();
        physics[201] = thunder;
    }


    /**
     * The draw method is the method for foreground drawing (check the Panel class in paintComponent and you can see
     * that this is called AFTER the drawBackground method - it overrides the background.
     * @param g Is the graphics object provided by the window.
     */
    @Override
    public void draw(Graphics g) {
        for (Drawable d : drawables) {
            d.draw(g);
        }
        thunder.draw(g);
    }

    /**
     * The draw method is the method for background drawing (check the Panel class in paintComponent and you can see
     * that this is called BEFORE the draw method - it gets overridden (partially) by the draw method.
     * @param g Is the graphics object provided by the window.
     */
    @Override
    public void drawBackground(Graphics g) {
        g.setColor(colorSky);
        g.fillRect(0, 0, WIDTH, HEIGHT - 100);
        g.setColor(colorGrass);
        g.fillRect(0, HEIGHT - 100, WIDTH, 100);
        for (Drawable d : drawables) {
            d.drawBackground(g);
        }
        thunder.drawBackground(g);
    }

    /**
     * Updates the physics, etc.
     * @param delta
     */
    @Override
    public void update(double delta) {
        // Uses time * delta instead of just delta when updating all the objects in the world.
        // time is basically a modifier for the time. Try to press (hold) the right or left key in-game.
        if (Keys.RIGHT) {
            time += 1 * delta;
        }
        if (Keys.LEFT) {
            time -= 1 * delta;
        }
        if(Keys.UP) {
            time = 1;
        }
        if(Keys.DOWN) {
            time = 0;
        }
        for (Physics p : physics) {
            p.update(delta * time);
        }
    }
}
