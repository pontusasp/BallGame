import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The Keys class works as a KeyListener for the main window.
 * It also provides static boolean variables for the game logic to use.
 */
public class Keys implements KeyListener {

    public static boolean W, A, S, D, RIGHT, LEFT, UP, DOWN;

    /**
     * Checks if a key is pressed and sets the corresponding variable to be true.
     * To bind a new key simply press the key in-game to see the key code and create a new boolean variable
     * for it.
     * @param e Is the KeyEvent provided by the window when a key is pressed.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 87:
                W = true;
                break;
            case 65:
                A = true;
                break;
            case 83:
                S = true;
                break;
            case 68:
                D = true;
                break;
            case 39:
                RIGHT = true;
                break;
            case 37:
                LEFT = true;
                break;
            case 38:
                UP = true;
                break;
            case 40:
                DOWN = true;
                break;
            case 27: // 27 is the ESCAPE key.
                System.exit(0);
                break;
            default:
                System.out.println("Key " + e.getKeyCode() + " is not bound yet.");
        }
    }

    /**
     * Checks if a key is pressed and sets the corresponding variable to be false.
     * @param e Is the KeyEvent provided by the window when a key is pressed.
     */
    @Override
    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {
            case 87:
                W = false;
                break;
            case 65:
                A = false;
                break;
            case 83:
                S = false;
                break;
            case 68:
                D = false;
                break;
            case 39:
                RIGHT = false;
                break;
            case 37:
                LEFT = false;
                break;
            case 38:
                UP = false;
                break;
            case 40:
                DOWN = false;
                break;
        }
    }

    /**
     * Don't use this method for games, it doesn't work very well except for writing, it is a combined version of the
     * two ones above.
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }
}
