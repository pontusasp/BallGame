import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    private int WIDTH, HEIGHT;
    private World world;
    public static boolean running = false;

    public Panel(int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;

        // Sets the size of the panel, this also dictates the size of the window with the
        // settings applied in Window.class
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        // Creates a new world with the specified dimensions.
        world = new World(WIDTH, HEIGHT);
    }

    /**
     * The game loop.
     */
    public void run() {
        running = true;
        long start, end; // Used to calculate delta.

        // Delta is the execution time for the last iteration of the loop in seconds.
        // E.g. if it takes 100 ms to run one iteration delta is 0.1, this is useful for physics (i.e. speed = m/s)
        double delta = 0;

        while (running) {
            start = System.nanoTime();

            // Update world.
            world.update(delta);

            end = System.nanoTime();
            delta = (end - start) / 1000000000.0;
        }
        System.exit(0);
    }

    /**
     * This is the drawing method, it is called the
     * first time automatically by the created window.
     * @param g Is the graphics object we call our drawing instructions to.
     */
    @Override
    public void paintComponent(Graphics g) {
        // First draw a black background. (Not visible in-game with provided code since the world overrides it)
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Draw the background and foreground in world.
        world.drawBackground(g);
        world.draw(g);

        repaint(); // This tells java we want to draw again, use this call and NOT paintComponent(g) to redraw.
    }


}
