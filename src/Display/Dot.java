package Display;
import java.awt.Color;

/**<h1>Graphics: Class Dot</h1>
 * Represent a dot to be displayed in a 2D surface, with a specific position and color
 * <p>In packet {@link Display}</p>
 * @author William Wu
 * <p>All rights reserved, suggestion welcome</p>
 * @version 1.3 @Oct 28, 2024*/
public final class Dot {
    /**x-coordinate of the dot*/
    int x;

    /**y-coordinate of the dot*/
    int y;

    /**{@link Color color} of the dot*/
    Color color;

    /**Constructor
     * @param x     the x-coordinate of the dot
     * @param y     the y-coordinate of the dot
     * @param color the {@link Color color} of the dot
     */
    Dot(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    /**String representation of the Dot object, including the coordinates and the RGB values of the color.
     * @return a string representation of the dot in the format:
     *         "{x: <x>, y: <y>, r: <r>, g: <g>, b: <b>}"*/
    @Override
    public String toString() {
        return "{x: " + x + ", y: " + y + ", r: " + color.getRed() +
                ", g: " + color.getGreen() + ", b: " + color.getBlue() + '}';
    }
}
