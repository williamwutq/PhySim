//class PointDepth
//William Wu
//Oct 7

package Display;
import Physics.Dimension;
import Physics.Phybody;
import java.awt.Color;

/**<h3>Class PointDepth</h3>
 * <p>A static helper class in {@link Display}</p>
 * Provides methods to create and manipulate a {@link Color} object that represents a {@link Physics.Dimension 3D point}'s mass and depth value.
 * Also provide {@link PointDepth#BodyToDot method} to convert a {@link Physics.Dimension 3D point} to its 2D display representation.
 * <p>Contains</p>
 * <pre>
 * {@link PointDepth#BodyToDot(Phybody, int, int, Dimension, Dimension, double)} convert {@link Physics.Dimension Dimension object} to {@link Display.Dot}
 * {@link PointDepth#faint(Color, double, double) fainting}
 * {@link PointDepth#create(double, double, double, double, double, double) coloring based on mass and depth}
 * </pre>
 * @author William Wu
 * <p>All rights reserved</p>
 * @version 1.4 @Oct 28, 2024 {@link Deprecated Final Version}
 * <p>Upgrades: fixed print outside of frame bug, modified fainting, etc.</p>
 * <p>This class is migrated into the {@link Display.Window#BodyToDot(Phybody, int, int, Dimension, Dimension, double, int) BodyToDot method}
 * of the {@link Display.Window} class</p>
 */
@Deprecated
public final class PointDepth {
    /**Creates a {@link Color} object based on the given depth, reference, min, max,
     * mass, and maximum mass. The resulting color reflects the depth relative to a
     * reference point, with additional consideration for mass to modify the alpha
     * channel. Pay attention that max and min are not normalized by reference.
     *
     * @param depth     The depth value of the point.
     * @param reference The reference depth from which to normalize the depth value.
     * @param min      The minimum depth value.
     * @param max      The maximum depth value.
     * @param mass     The mass of the point, which affects the alpha channel of the color.
     * @param maxM     The maximum mass value to scale the alpha channel.
     * @return A {@link Color} object representing the color for the given depth and mass.
     * @throws IllegalArgumentException if max is not greater than min.
     */
    public static Color create(double depth, double reference, double min, double max, double mass, double maxM) {
        //no mass
        if (mass == 0) return new Color(0, 0, 0, 238);  // Black with alpha 238

        //------calculating alpha------
        //handle mass and maxMass for alpha channel (A)
        double logMaxM = Math.log(maxM);
        double logM = Math.min(maxM, mass);
        if(logM > logMaxM){logM = logMaxM;}
        if(logM < 0){logM = 0;}

        //interpolate alpha on a logarithmic scale for m
        int alpha = (int) (logM / logMaxM * 240 + 15);  // Alpha is between 0 and 255
        if(alpha >= 255)alpha = 255;

        //------Calculating color------
        //difference
        double difference = max - min;
        if (difference <= 0)throw new IllegalArgumentException("Max must be greater than Min");

        //normalized depth
        double normalizedDepth = depth - reference;
        if (normalizedDepth > max) {normalizedDepth = max;}
        if (normalizedDepth < min) {normalizedDepth = min;}

        // Use average to determine color
        double average = (max + min) / 2;
        double logMax = Math.log(difference + 1);
        if (normalizedDepth == average) {
            //is average
            return new Color(255, 255, 255, alpha);  // White
        } else if (normalizedDepth < average) {
            //between min and average
            normalizedDepth = average - normalizedDepth;  // Invert for interpolation
            double logDepth = Math.log(normalizedDepth + 1);
            if(logDepth > logMax){logDepth = logMax;}
            if(logDepth < 0){logDepth = 0;}

            //between dark blue and white
            return interpolateColor(Color.white, new Color(0, 0, 255), logDepth / logMax, alpha);  // Dark blue to white
        } else if (normalizedDepth > average) {
            //between min and average
            normalizedDepth -= average;
            double logDepth = Math.log(normalizedDepth + 1);
            if(logDepth > Math.log(difference + 1)){logDepth = Math.log(difference + 1);}
            if(logDepth < 0){logDepth = 0;}

            //between dark blue and white
            return interpolateColor(Color.white, new Color(221, 0, 0), logDepth / logMax, alpha);  // Dark red to white
        }
        //should not reach this
        return new Color(0, 0, 0, 238);  // Black with alpha 238
    }
    /**
     * Faints the given {@link Color} by decreasing its alpha channel based on the specified
     * time and rate. The rate should be between 0 and 1. The resulting alpha will not be
     * less than 15.
     *
     * @param color The original color to be modified.
     * @param time  The time factor affecting the alpha decrease, should be positive.
     * @param rate  The rate of decrease, should be between 0 and 1. 0-100 also works as a safeguard.
     * @return A new {@link Color} object with the modified alpha.
     */
    public static Color faint(Color color, double time, double rate) {
        //check rate
        if (rate < 0){rate = 0;}
        else if (rate > 1&&rate <= 100){rate = rate/100;}
        else if(rate > 100){rate = 1;}
        
        if (time < 0) {time = -time;}//check time

        // Calculate the new alpha value
        int newAlpha = (int) (color.getAlpha() * Math.pow(rate, time));
        if (newAlpha < 15) newAlpha = 15;

        // Create a new Color with the modified alpha and the same RGB values
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), newAlpha);
    }
    // Helper method to interpolate between two colors
    private static Color interpolateColor(Color color1, Color color2, double t, int a) {
        int r = (int) (color1.getRed() + t * (color2.getRed() - color1.getRed()));
        int g = (int) (color1.getGreen() + t * (color2.getGreen() - color1.getGreen()));
        int b = (int) (color1.getBlue() + t * (color2.getBlue() - color1.getBlue()));
        return new Color(r, g, b, a);
    }

    /**Converts a {@link Physics.Phybody physical body} into a {@link Dot dot} for display on a 2D plane.
     * <p>This method takes into account the position of the body, the dimensions of the display,
     * and the minimum and maximum bounding {@link Physics.Dimension dimensions}, mapping the body's position and depth
     * into screen coordinates and color properties.</p>
     *
     * @param body The physical body to be converted.
     * @param width The width of the display. Must be greater than 1.
     * @param height The height of the display. Must be greater than 1.
     * @param min The minimum dimensions of the bounding box for scaling. Must be lesser than `max`.
     * @param max The maximum dimensions of the bounding box for scaling. Must be greater than `min`.
     * @param maxM The maximum mass to normalize the body's mass for display properties.
     * @return {@link Dot Dot object} object representing the physical body for display on a 2D plane.
     * @throws IllegalArgumentException if width or height is less than 2, or if `max` is not greater than `min`.
     */
    @Deprecated
    public static Dot BodyToDot(Phybody body, int width, int height, Dimension min, Dimension max, double maxM){
        // Check inputs
        if(width < 0){width = - width;}
        if (width < 2)throw new IllegalArgumentException("width must be bigger than 1");
        if(height < 0){height = - height;}
        if (height < 2)throw new IllegalArgumentException("height must be bigger than 1");
        if(min.notlesser(max))throw new IllegalArgumentException("max must be greater than min");

        // Calculate scale factors and position on the display
        double scaleX = (body.getPos().numDot(Dimension.IDx()) - min.numDot(Dimension.IDx())) / (max.numDot(Dimension.IDx()) - min.numDot(Dimension.IDx()));
        double scaleY = (body.getPos().numDot(Dimension.IDy()) - min.numDot(Dimension.IDy())) / (max.numDot(Dimension.IDy()) - min.numDot(Dimension.IDy()));

        // Map to screen coordinates, ensuring they remain within bounds
        int new_x = Math.min(Math.max((int) (2+ scaleX * (width - 4)), 2), width - 2);
        int new_y = Math.min(Math.max((int) (2+ scaleY * (height - 4)), 2), height - 2);

        int minD = (int) min.numDot(Dimension.IDz());
        int maxD = (int) max.numDot(Dimension.IDz());
        return new Dot(new_x, new_y, create(body.getPos().numDot(Dimension.IDz()), 0, minD, maxD, body.getMass(), maxM));
    }
}