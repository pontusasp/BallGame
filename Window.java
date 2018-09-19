import javax.swing.*;

public class Window extends JFrame {

    public static int WIDTH = 800;
    public static int HEIGHT = 600;
    private Panel panel;
    private Keys keys;

    public Window() {
        super("A random ass game"); // Call the constructor of the super class (JFrame)

        // Create a new instance of keys. Will be used as KeyListener for the window.
        keys = new Keys();

        // Create a new panel, this is where we draw our graphics. (and update the physics
        panel = new Panel(WIDTH, HEIGHT);
        add(panel);

        // Makes the window use the keys object as handler for the keyboard
        addKeyListener(keys);

        // Assigns the X button to end the program
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Makes the window non-resizable
        setResizable(false);

        // Adapts the size of the window to the contents inside of it. (I.e. the panel that we draw to)
        pack();

        // A little trick to make the window centered.
        setLocationRelativeTo(null);

        // The window is by default invisible, set it visible only after changing and adding everything you need.
        setVisible(true);

        // Start the game loop.
        panel.run();
    }

    public static void main(String[] args) {
        new Window(); // Creates and runs the Window constructor.
    }
}
