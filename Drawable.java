import java.awt.*;

/**
 * This interface is useful for objects that should be drawn to the screen as they can all be saven in a shared
 * collection because they share the same interface.
 */
public interface Drawable {

    void draw(Graphics g);
    void drawBackground(Graphics g);

}
