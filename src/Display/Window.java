package Display;

import Physics.Phybody;
import Physics.Dimension;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**<h3>Class Window</h3>
 * <p>Extend {@link javax.swing.JPanel}, in packet {@link Display}</p>
 * <p>The Window class is a graphical component that represents and manages the display of multiple {@link Physics.Phybody} objects.
 * Each body is displayed as a dot with fading intensity, controlled by the fainting time and the faintRate (default {@code 0.8}).</p>
 * <p>The display area is defined with minimum and maximum bounds in {@link Physics.Dimension 3D space}, and each body’s position and intensity are recalculated
 * according to these bounds and the maxMass setting. The fainting mechanism gradually dims each body's display, adding a fading effect.</p>
 * <p>This class provides methods for adding bodies {@link Window#addBody(Phybody) individually} or in {@link Window#addBody(Phybody[]) arrays} or {@link Window#addBody(ArrayList) ArrayLists},
 * {@link Window#clearList() clearing} the list of bodies, and adjusting properties such as {@link Window#setSize(int, int) size}, {@link Window#setBounds(Dimension, Dimension) bounds},
 * {@link Window#setMaxMass(long) max mass for display}, and {@link Window#setFaintRate(double) faint rate}. Additionally, it offers methods to {@link Window#clearByAlpha(int) selectively remove} bodies based on their
 * fainting time, avoiding storing bodies that are too dim. Use {@link Window#repaint() repaint} to repaint</p>
 *
 * <pre>
 * Window window = new Window(800, 600, center, min, max);
 * window.addBody(body);
 * window.setFaintRate(0.5);
 * </pre>
 * </p>
 *
 * @see Physics.Phybody
 * @see Display.Grid
 * @see java.util.ArrayList
 * @see javax.swing.JPanel
 * @author William Wu
 * <p>All rights reserved</p>
 * @version 1.4 @Oct 28, 2024
 * <p>Upgrades: modified zero handling, added transformation display. Change to enable out of frame handling</p>
 */
public final class Window extends JPanel {
    private final ArrayList<Phybody> bodies = new ArrayList<>();
    private final ArrayList<Integer> alphas = new ArrayList<>();
    private Physics.Dimension center;
    private Physics.Dimension min;
    private Physics.Dimension max;
    private Physics.Dimension xAxis = Dimension.IDx();
    private Physics.Dimension yAxis = Dimension.IDy();
    private Physics.Dimension zAxis = Dimension.IDz();

    private long maxMass = 0;
    private static double faintRate = 0.8;
    private java.awt.Dimension size;
    /**Constructor
     * <p>Constructs a Window with specified dimensions and spatial bounds.</p>
     * <p>This constructor initializes the display area for the {@link Physics.Phybody} objects, setting the size of the window
     * and defining minimum and maximum bounds for the displayed content in {@link Physics.Dimension 3D space}. </p>
     * <p>Each body will be positioned and displayed within these bounds, relative to the given center.</p>
     *
     * @param width  the width of the Window in pixels; minimum value is 200.
     * @param height the height of the Window in pixels; minimum value is 200.
     * @param center the central position in 3D space relative to which the bodies are displayed.
     * @param min    the minimum bounds in 3D space for the displayed bodies; should be less than {@code max}.
     * @param max    the maximum bounds in 3D space for the displayed bodies; should be greater than {@code min}.
     * @throws IllegalArgumentException if {@code min} is not less than {@code max}.*/
    public Window(int width, int height, Dimension center, Physics.Dimension min, Physics.Dimension max){
        if(min.notlesser(max))throw new IllegalArgumentException("Max must be greater than Min");
        this.min = min; this.max = max;
        if(width < 200)width = 200;
        if(height < 200)height = 200;
        this.size = new java.awt.Dimension(width, height);
        this.center = center;
        setBackground(Color.black);
        setMinimumSize(this.size);
        setPreferredSize(this.size);
    }

    //add dots
    /**Adds a new {@link Physics.Phybody} to the list of bodies with a default fainting time of 0.
     * @param body The body to add, copied into the list.
     * @see #addBody(Phybody, int)*/
    public void addBody(Phybody body){
        bodies.add(body.copy());
        alphas.add(0);
    }
    /**Adds a new {@link Physics.Phybody} to the list of bodies with a specified fainting time.
     * @param body The body to add, copied into the list.
     * @param time The fainting time, where a higher time results in a dimmer display. If time is negative, it will default to 0.
     * @see #addBody(Phybody)*/
    public void addBody(Phybody body, int time) {
        if(time < 0)time = 0;
        bodies.add(body.copy());
        alphas.add(time);
    }
    /**Adds an {@link java.util.ArrayList} of {@link Physics.Phybody} objects to the list with a specified fainting time.
     * @param bodyArray The list of bodies to add, each copied into the list.
     * @param time The fainting time, defaulting to 0 if negative, applied to each body in the list.
     * @see #addBody(Phybody)
     * @see #addBody(ArrayList)*/
    public void addBody(ArrayList<Physics.Phybody> bodyArray, int time){
        if(time < 0)time = 0;
        for(Phybody body : bodyArray){
            bodies.add(body.copy());
            alphas.add(time);
        }
    }
    /**Adds an array of {@link Physics.Phybody} objects to the list with a specified fainting time.
     * @param bodyArray The array of bodies to add, each copied into the list.
     * @param time The fainting time, defaulting to 0 if negative, applied to each body in the array.
     * @see #addBody(Phybody)
     * @see #addBody(Phybody[])*/
    public void addBody(Physics.Phybody[] bodyArray, int time){
        if(time < 0)time = 0;
        for(Phybody body : bodyArray){
            bodies.add(body.copy());
            alphas.add(time);
        }
    }
    /**Adds an {@link java.util.ArrayList} of {@link Physics.Phybody} objects to the list with a default fainting time of 0.
     * @param bodyArray The list of bodies to add, each copied into the list.
     * @see #addBody(Phybody)
     * @see #addBody(ArrayList, int)*/
    public void addBody(ArrayList<Physics.Phybody> bodyArray){
        for(Phybody body : bodyArray){
            bodies.add(body.copy());
            alphas.add(0);
        }
    }
    /**Adds an array of {@link Physics.Phybody} objects to the list with a default fainting time of 0.
     * @param bodyArray The array of bodies to add, each copied into the list.
     * @see #addBody(Phybody)
     * @see #addBody(Phybody[], int)*/
    public void addBody(Physics.Phybody[] bodyArray){
        for(Phybody body : bodyArray){
            bodies.add(body.copy());
            alphas.add(0);
        }
    }

    /**Clears all bodies and fainting times from the list.*/
    public void clearList(){
        if (!bodies.isEmpty()) {
            bodies.subList(0, bodies.size()).clear();
            alphas.subList(0, alphas.size()).clear();
        }
    }
    /**Increases the fainting time for all bodies by the specified amount, dimming each dot.
     * @param time The amount of time to increase fainting, defaulting to 0 if negative.*/
    public void faintList(int time){
        int finalTime = Math.max(time, 0);
        alphas.replaceAll(integer -> integer + finalTime);
    }
    /**Increases the fainting time for all bodies by 1, dimming each dot by one step.*/
    public void faintList(){
        alphas.replaceAll(integer -> integer + 1);
    }
    /**Clears bodies from the list whose fainting time exceeds the specified threshold.
     * <p>This method can be used when dim bodies are not needed</p>
     * @param time The fainting time threshold; bodies with equal or greater fainting time will be removed.*/
    public void clearByAlpha(int time){
        for (int i = bodies.size() - 1; i >= 0; i--){
            if (alphas.get(i) >= time){
                alphas.remove(i);
                bodies.remove(i);
            }
        }
    }
    /**Removes bodies from the list whose fainting time matches the specified value.
     * @param time The fainting time value; bodies with this fainting time will be removed.
     * @see #clearByAlpha(int) */
    public void removeByAlpha(int time){
        for (int i = bodies.size() - 1; i >= 0; i--){
            if (alphas.get(i) == time){
                alphas.remove(i);
                bodies.remove(i);
            }
        }
    }
    /**Sets the display area size.
     * @param width The width of the display area.
     * @param height The height of the display area.*/
    public void setSize(int width, int height){
        size = new java.awt.Dimension(width, height);
    }
    /**Sets the bounds for the display area using minimum and maximum {@link Physics.Dimension} values.
     * @param min The minimum display bounds.
     * @param max The maximum display bounds.*/
    public void setBounds(Physics.Dimension min, Physics.Dimension max){
        this.min = min.copy();
        this.max = max.copy();
    }
    /**Sets the maximum mass for display purposes.
     * <p>Any bodies with mass greater than or equal to the maximum mass will be displayed with 100% intensity, providing it is not fainted</p>
     * @param maxMass The maximum mass value; if negative, it defaults to 0.*/
    public void setMaxMass(long maxMass){
        if(maxMass < 0)maxMass = 0;
        this.maxMass = maxMass;
    }
    /**Sets the fainting rate used to dim displayed bodies.
     * @param faintRate The fainting rate, restricted to a range of 0 to 1. If set above 1 and within 100, it is converted to a percentage.*/
    public void setFaintRate(double faintRate){
        //check rate
        if (faintRate < 0){faintRate = 0;}
        else if (faintRate > 1&&faintRate <= 100){faintRate = faintRate/100;}
        else if(faintRate > 100){faintRate = 1;}
        Window.faintRate = faintRate;
    }
    /**Sets the center for the display area using center {@link Physics.Dimension} values.
     * @param center the display center.*/
    public void setCenter(Physics.Dimension center){
        this.min.addby(center.minus(this.center));
        this.max.addby(center.minus(this.center));
        this.center = center.copy();
    }
    /**Set the rotation to display by three axes if the rotation is not degenerate
     * @param x the x-axis ({@link Physics.Dimension})
     * @param y the y-axis ({@link Physics.Dimension})
     * @param z the z-axis ({@link Physics.Dimension})*/
    public void setRotation(Physics.Dimension x, Physics.Dimension y, Physics.Dimension z){
        double determint = x.cross(y).numDot(z);
        if(determint != 0){
            xAxis = x.copy();
            yAxis = y.copy();
            zAxis = z.copy();
        }
    }
    /** Retrieves the current fainting rate.
     * @return The fainting rate multiplier.*/
    public double getFaintRate(){
        return faintRate;
    }

    /**Paints for this component, displaying bodies as dimmed or highlighted dots based on their fainting time, the
     * center coordinates as digits in the upper left corner, and the transformation in the upper right corner.
     * @param g The {@link Graphics} context used for painting.*/
    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        //for bodies
        ArrayList<Phybody> copy = new ArrayList<>(bodies);//copy
        for (int i = 0; i < copy.size(); i++) {
            Phybody body = null;
            int alpha = 0;
            try {
                body = copy.get(i);
                alpha = alphas.get(i).intValue();
            } catch (IndexOutOfBoundsException | NullPointerException ignored) {
            }
            if (body != null){
                Dot dot = BodyToDot(body, getWidth(), getHeight(), min, max, maxMass, alpha);
                g.setColor(dot.color);
                // Draw a "+" shape using pixels
                g.fillRect(dot.x - 1, dot.y, 3, 1);
                g.fillRect(dot.x, dot.y - 1, 1, 1);
                g.fillRect(dot.x, dot.y + 1, 1, 1);
            }
        }
        //print central coordinate
        int lineDistance = 12;
        paintGrid(g, Grid.LESS , 7, 1);
        paintGrid(g, Grid.CAP_C, 13, 1);
        paintGrid(g, Grid.CAP_E, 19, 1);
        paintGrid(g, Grid.CAP_N, 25, 1);
        paintGrid(g, Grid.CAP_T, 31, 1);
        paintGrid(g, Grid.CAP_E, 37, 1);
        paintGrid(g, Grid.CAP_R, 43, 1);
        paintGrid(g, Grid.GREAT, 49, 1);
        paintGrid(g, Grid.CAP_X, 1, 1+lineDistance);
        paintGrid(g, Grid.COLON, 7, 1+lineDistance);
        paintNumber(g, center.numDot(Dimension.IDx()), 19, 1+lineDistance);
        paintGrid(g, Grid.CAP_Y, 1, 1+lineDistance*2);
        paintGrid(g, Grid.COLON, 7, 1+lineDistance*2);
        paintNumber(g, center.numDot(Dimension.IDy()), 19, 1+lineDistance*2);
        paintGrid(g, Grid.CAP_Z, 1, 1+lineDistance*3);
        paintGrid(g, Grid.COLON, 7, 1+lineDistance*3);
        paintNumber(g, center.numDot(Dimension.IDz()), 19, 1+lineDistance*3);
        //print transformation coordinate
        int leftDistance = this.getSize().width - 137;
        paintGrid(g, Grid.LESS , leftDistance, 1);
        paintGrid(g, Grid.CAP_T, leftDistance+6, 1);
        paintGrid(g, Grid.CAP_R, leftDistance+12, 1);
        paintGrid(g, Grid.CAP_A, leftDistance+18, 1);
        paintGrid(g, Grid.CAP_N, leftDistance+24, 1);
        paintGrid(g, Grid.CAP_S, leftDistance+30, 1);
        paintGrid(g, Grid.CAP_F, leftDistance+36, 1);
        paintGrid(g, Grid.CAP_O, leftDistance+42, 1);
        paintGrid(g, Grid.CAP_R, leftDistance+48, 1);
        paintGrid(g, Grid.CAP_M, leftDistance+54, 1);
        paintGrid(g, Grid.CAP_A, leftDistance+60, 1);
        paintGrid(g, Grid.CAP_T, leftDistance+66, 1);
        paintGrid(g, Grid.CAP_I, leftDistance+72, 1);
        paintGrid(g, Grid.CAP_O, leftDistance+78, 1);
        paintGrid(g, Grid.CAP_N, leftDistance+84, 1);
        paintGrid(g, Grid.CAP_M, leftDistance+96, 1);
        paintGrid(g, Grid.CAP_A, leftDistance+102, 1);
        paintGrid(g, Grid.CAP_T, leftDistance+108, 1);
        paintGrid(g, Grid.CAP_R, leftDistance+114, 1);
        paintGrid(g, Grid.CAP_I, leftDistance+120, 1);
        paintGrid(g, Grid.CAP_X, leftDistance+126, 1);
        paintGrid(g, Grid.GREAT, leftDistance+132, 1);
        paintNumber(g, xAxis.numDot(Dimension.IDx()), leftDistance, 1+lineDistance);
        paintGrid(g, Grid.COMMA, leftDistance+42, 1+lineDistance);
        paintNumber(g, xAxis.numDot(Dimension.IDy()), leftDistance+48, 1+lineDistance);
        paintGrid(g, Grid.COMMA, leftDistance+90, 1+lineDistance);
        paintNumber(g, xAxis.numDot(Dimension.IDz()), leftDistance+96, 1+lineDistance);
        paintNumber(g, yAxis.numDot(Dimension.IDx()), leftDistance, 1+lineDistance*2);
        paintGrid(g, Grid.COMMA, leftDistance+42, 1+lineDistance*2);
        paintNumber(g, yAxis.numDot(Dimension.IDy()), leftDistance+48, 1+lineDistance*2);
        paintGrid(g, Grid.COMMA, leftDistance+90, 1+lineDistance*2);
        paintNumber(g, yAxis.numDot(Dimension.IDz()), leftDistance+96, 1+lineDistance*2);
        paintNumber(g, zAxis.numDot(Dimension.IDx()), leftDistance, 1+lineDistance*3);
        paintGrid(g, Grid.COMMA, leftDistance+42, 1+lineDistance*3);
        paintNumber(g, zAxis.numDot(Dimension.IDy()), leftDistance+48, 1+lineDistance*3);
        paintGrid(g, Grid.COMMA, leftDistance+90, 1+lineDistance*3);
        paintNumber(g, zAxis.numDot(Dimension.IDz()), leftDistance+96, 1+lineDistance*3);
        //paint rotational axis
        Physics.Dimension vector = Dimension.plus(xAxis, yAxis).add(zAxis).unit();
        Dot dot = BodyToDot(new Phybody(maxMass, vector), 31, 31, Dimension.ID().not(), Dimension.ID(), maxMass, 0);
        g.setColor(dot.color);
        g.drawLine(this.getWidth()-16, this.getHeight()-16, this.getWidth()-31 + dot.x, this.getHeight()-31 + dot.y);
        //draw point
        g.fillRect(this.getWidth()-32 + dot.x, this.getHeight()-32 + dot.y, 3, 3);
        //origin and point
        g.setColor(Color.WHITE);
        g.fillRect(this.getWidth()-17, this.getHeight()-19,3, 1);
        g.fillRect(this.getWidth()-19, this.getHeight()-17,1, 3);
        g.fillRect(this.getWidth()-17, this.getHeight()-13,3, 1);
        g.fillRect(this.getWidth()-13, this.getHeight()-17,1, 3);
        g.fillRect(this.getWidth()-18, this.getHeight()-18,5, 5);
    }
    /**Paints a numerical value onto the graphical context as a grid-based representation.
     * The method handles formatting and rendering of numbers up to a certain size, taking into
     * account special cases such as zero, negative numbers, and large numbers that require scientific notation.
     *
     * <p>The number is formatted to fit within 7 characters, including a plus or minus sign, displayed as follows:
     * - Numbers between 0 and 999999 are rendered normally with up to 4 decimal places.
     * - Numbers larger than 1,000,000 are displayed in scientific notation (e.g., 3.8e14).
     * - Special case handling is applied for zero ("0.0000").
     * - For negative numbers, a minus sign is displayed before the number.
     *
     * <p>Examples of formats used:
     * <ul>
     *   <li> 0.0000 (for zero)</li>
     *   <li>+100.00 (for exact hundreds)</li>
     *   <li>-100000 (for large negative numbers)</li>
     *   <li>+6.54e8 (for large numbers in scientific notation)</li>
     *   <li>+3.8e14 (for extremely large numbers)</li>
     * </ul>
     *
     * @param g The {@link Graphics} object to be used for drawing the grid.
     * @param number The numerical value to be displayed. Should be within the range of a {@link Double double}.
     * @param x The x-coordinate where the number will be painted on the grid. (upper left corner)
     * @param y The y-coordinate where the number will be painted on the grid. (upper left corner)
     * @throws IllegalArgumentException if the number exceeds the maximum representable value of {@code double}.
     *
     * @see #paintGrid(Graphics, Grid, int, int)
     * @see #paintInteger(double, int)
     * @see #digitsBeforeDecimal(double)
     */
    private void paintNumber(Graphics g, double number, int x, int y){
        //length = 7: 100.00 or -100000 or 6.54e8 or 3.8e14
        if(number == 0) {
            //special case: 0
            paintGrid(g, Grid.CAP_0, x+6, y);
            paintGrid(g, Grid.PERIOD, x+12, y);
            paintGrid(g, Grid.CAP_0, x+18, y);
            paintGrid(g, Grid.CAP_0, x+24, y);
            paintGrid(g, Grid.CAP_0, x+30, y);
            paintGrid(g, Grid.CAP_0, x+36, y);
            return;
        }else if(number < 0){
            //negative
            paintGrid(g, Grid.MINUS, x, y);
            number = -number;
        }else{
            //positive
            paintGrid(g, Grid.PLUS, x, y);
        }
        //positive
        if(number < 1){
            //0.____
            paintGrid(g, Grid.CAP_0, x+6, y);
            paintGrid(g, Grid.PERIOD, x+12, y);
            paintGrid(g, paintInteger(number, -1), x+18, y);
            paintGrid(g, paintInteger(number, -2), x+24, y);
            paintGrid(g, paintInteger(number, -3), x+30, y);
            paintGrid(g, paintInteger(number, -4), x+36, y);
        }else if(number < 10){
            //_.____
            paintGrid(g, paintInteger(number, 0), x+6, y);
            paintGrid(g, Grid.PERIOD, x+12, y);
            paintGrid(g, paintInteger(number, -1), x+18, y);
            paintGrid(g, paintInteger(number, -2), x+24, y);
            paintGrid(g, paintInteger(number, -3), x+30, y);
            paintGrid(g, paintInteger(number, -4), x+36, y);
        }else if(number < 100){
            //__.___
            paintGrid(g, paintInteger(number, 1), x+6, y);
            paintGrid(g, paintInteger(number, 0), x+12, y);
            paintGrid(g, Grid.PERIOD, x+18, y);
            paintGrid(g, paintInteger(number, -1), x+24, y);
            paintGrid(g, paintInteger(number, -2), x+30, y);
            paintGrid(g, paintInteger(number, -3), x+36, y);
        }else if(number < 1000){
            //___.__
            paintGrid(g, paintInteger(number, 2), x+6, y);
            paintGrid(g, paintInteger(number, 1), x+12, y);
            paintGrid(g, paintInteger(number, 0), x+18, y);
            paintGrid(g, Grid.PERIOD, x+24, y);
            paintGrid(g, paintInteger(number, -1), x+30, y);
            paintGrid(g, paintInteger(number, -2), x+36, y);
        }else if(number < 10000){
            //____._
            paintGrid(g, paintInteger(number, 3), x+6, y);
            paintGrid(g, paintInteger(number, 2), x+12, y);
            paintGrid(g, paintInteger(number, 1), x+18, y);
            paintGrid(g, paintInteger(number, 0), x+24, y);
            paintGrid(g, Grid.PERIOD, x+30, y);
            paintGrid(g, paintInteger(number, -1), x+36, y);
        }else if(number < 1000000){
            //______
            paintGrid(g, paintInteger(number, 5), x+6, y);
            paintGrid(g, paintInteger(number, 4), x+12, y);
            paintGrid(g, paintInteger(number, 3), x+18, y);
            paintGrid(g, paintInteger(number, 2), x+24, y);
            paintGrid(g, paintInteger(number, 1), x+30, y);
            paintGrid(g, paintInteger(number, 0), x+36, y);
        }else if(number < 10000e10){
            //____e_
            int digit = digitsBeforeDecimal(number);
            paintGrid(g, paintInteger(number, digit-1), x+6, y);
            paintGrid(g, paintInteger(number, digit-2), x+12, y);
            paintGrid(g, paintInteger(number, digit-3), x+18, y);
            paintGrid(g, paintInteger(number, digit-4), x+24, y);
            paintGrid(g, Grid.CAP_NL, x+30, y);
            paintGrid(g, Grid.NUM(digit-2), x+36, y);
        }else if(number < 1000e100){
            //___e__
            int digit = digitsBeforeDecimal(number);
            paintGrid(g, paintInteger(number, digit-1), x+6, y);
            paintGrid(g, paintInteger(number, digit-2), x+12, y);
            paintGrid(g, paintInteger(number, digit-3), x+18, y);
            paintGrid(g, Grid.CAP_NL, x+24, y);
            paintGrid(g, Grid.NUM((digit+17)/10), x+30, y);
            paintGrid(g, Grid.NUM((digit-3)%10+2), x+36, y);
        }else{
            //__e___
            int digit = digitsBeforeDecimal(number);
            paintGrid(g, paintInteger(number, digit-1), x+6, y);
            paintGrid(g, paintInteger(number, digit-2), x+12, y);
            paintGrid(g, Grid.CAP_NL, x+18, y);
            paintGrid(g, Grid.NUM((digit+198)/100), x+24, y);
            paintGrid(g, Grid.NUM((digit-2)%100/10+2), x+30, y);
            paintGrid(g, Grid.NUM((digit-2)%10+2), x+36, y);
        }
    }
    /**The grid representation of the digit at a specified position within the number.
     * <p>This method extracts a digit from the number at the position specified by the `digit` parameter.
     * The position can be a positive integer (for digits before the decimal point) or a negative integer
     * (for digits after the decimal point). The resulting digit is mapped to its corresponding grid representation.
     * </p>
     * <p><b>Warning:</b> For excessively large numbers (greater than {@link Double#MAX_VALUE}) or precise decimal points,
     * precision loss may occur, leading to incorrect or unexpected digits being returned.
     * <b>DO NOT try to access decimal points for numbers larger than {@code 1.797693*10^{300}}</b></P>
     * @param number The number from which the digit is extracted.
     * @param digit The position of the digit.<ul>
     *              <li>0 for the digit immediately before the decimal point</li>
     *              <li>Positive values (e.g., 1, 2, 3, etc.) for digits before the decimal point</li>
     *              <li>Negative values (e.g., -1, -2, -3, etc.) for digits after the decimal point</li>
     *              </ul>
     * @return The {@link Grid} representation of the digit at the specified position.
     *         Returns the corresponding grid element from the {@link Grid#NUM(int)} method.
     */
    private Grid paintInteger(double number, int digit){
        if(digit == 0) {
            return Grid.NUM(((int) number)%10 + 2);
        }else if(digit > 0){
            for(int i = 0; i < digit; i++){
                number/=10;
            }
            return Grid.NUM(((int) number)%10 + 2);
        }else{
            for(int i = 0; i < -digit; i++){
                number*=10;
            }
            return Grid.NUM(((int) number)%10 + 2);
        }
    }
    private void paintGrid(Graphics g, Grid grid, int x, int y){
        g.setColor(Color.WHITE);
        for (int i = 0; i < Grid.rows; i++) {
            for (int j = 0; j < Grid.cols; j++) {
                if(grid.getBoolean(i, j)) g.fillRect(x+j,y+i,1,1);
            }
        }
    }
    /**Calculates the number of digits before the decimal point in a given number.
     * <p>Determines how many digits exist before the decimal point by using the logarithmic
     * properties of the number.</p>
     * @param num The number for which the digit count is calculated.
     * @return The number of digits before the decimal point. If the number is between 10 and 0, returns 1.
     */
    private int digitsBeforeDecimal(double num) {
        num = Math.abs(num);
        // If the number is less than 1 but greater than 0, return 1
        if (num < 1 && num > 0) {return 1;}
        // Use logarithm to calculate the number of digits before the decimal point
        return (int) Math.floor(Math.log10(num)) + 1;
    }
    /**Converts a {@link Physics.Phybody physical body} into a {@link Dot dot} for display on a 2D plane.
     * <p>This method maps the body's position in 3D space into a 2D coordinate system for display, with
     * additional considerations for color and transparency based on the body's depth and mass. The display
     * coordinates are scaled relative to the defined width and height, and the color is determined by the
     * body's mass and depth within the specified bounding dimensions. The color will be fainted according
     * to the {@code time} value</p>
     *
     * <p>The resulting {@link Dot} contains a color whose alpha value is based on the body's mass, with an
     * interpolation applied to map the depth dimension to a color gradient ranging between white and a color
     * that shifts from dark blue (for lower depths) to dark red (for higher depths). The alpha value of the
     * color ranges from 255 to 7, depending on the mass of the object and the fainting time and rate.</p>
     *
     * @param body The physical body to be converted.
     * @param width The width of the display in pixels. Must be greater than 1.
     * @param height The height of the display in pixels. Must be greater than 1.
     * @param min The minimum bounding dimensions for scaling the body's position. Must be less than `max`.
     * @param max The maximum bounding dimensions for scaling the body's position. Must be greater than `min`.
     * @param maxM The maximum mass used to normalize the body's mass for display properties, affecting transparency.
     * @param time The time the displayed dot need to be fainted (fainting rate can be changed using {@link Window#setFaintRate(double) setFaintRate}).
     * @return A {@link Dot} object representing the physical body as a 2D display element with appropriate position and color.
     * @author William Wu
     * @throws IllegalArgumentException if width or height is less than 2, or `max` is not greater than `min`.
     */
    public static Dot BodyToDot(Physics.Phybody body, int width, int height, Physics.Dimension min, Physics.Dimension max, double maxM, int time) {
        // Validate inputs
        width = Math.abs(width);
        height = Math.abs(height);
        if (width < 2 || height < 2) throw new IllegalArgumentException("width and height must be greater than 1");
        if (min.notlesser(max)) throw new IllegalArgumentException("max must be greater than min");
        if(maxM < 0)maxM = 0;
        if (time < 0) {time = -time;}//check time

        // Calculate scale factors and screen coordinates
        double scaleX = (body.getPos().numDot(Dimension.IDx()) - min.numDot(Dimension.IDx())) /
                (max.numDot(Dimension.IDx()) - min.numDot(Dimension.IDx()));
        double scaleY = (body.getPos().numDot(Dimension.IDy()) - min.numDot(Dimension.IDy())) /
                (max.numDot(Dimension.IDy()) - min.numDot(Dimension.IDy()));

        int new_x = Math.min(Math.max((int) (2 + scaleX * (width - 4)), 1), width - 1);
        int new_y = Math.min(Math.max((int) (2 + scaleY * (height - 4)), 1), height - 1);

        // Default to black color if mass is zero
        Color color = new Color(0, 0, 0, 238);
        if (new_x == 1 || new_x == width - 1){
            // Special case: between coordinate 1 - n-1
            color = new Color(0,0,0,0);
            new_x = 2;
            if (new_y == 1 || new_y == height - 1) new_y = 2;
        } else if (new_y == 1 || new_y == height - 1){
            // Special case: between coordinate 1 - n-1
            color = new Color(0,0,0,0);
            new_y = 2;
        } else if (body.getMass() != 0) {
            // Calculate alpha based on mass
            double logM = Math.min(Math.max(body.getMass(), 0), maxM);
            int alpha = (int) (Math.log(logM) / Math.log(maxM) * 240 + 15);
            if(alpha > 225){alpha = 225;}
            else if(alpha < 15){alpha = 15;}
            int newAlpha = (int)(alpha*Math.pow(faintRate, time));
            if (newAlpha < 15){alpha = 15;}
            else if(newAlpha == alpha) {alpha --;}
            else{alpha = newAlpha;}

            // Normalize depth for color interpolation
            int minD = (int) min.numDot(Dimension.IDz());
            int maxD = (int) max.numDot(Dimension.IDz());
            double rangeD = maxD - minD;
            double avgD = (maxD + minD) / 2.0;
            if (rangeD <= 0) throw new IllegalArgumentException("max depth must be greater than min depth");

            // Depth normalization for color mapping
            double normalizedDepth = Math.max(minD, Math.min(maxD, body.getPos().numDot(Dimension.IDz())));
            double t = Math.log(Math.abs(normalizedDepth - avgD) + 1) / Math.log(rangeD + 1);
            t = Math.max(0, Math.min(t, 1));

            // Determine color based on depth range
            int red = (normalizedDepth < avgD) ? 0 : 221;
            int blue = (normalizedDepth < avgD) ? 255 : 0;

            // Interpolate between white and chosen color
            int r =  255 + (int)(t * (red - 255));
            int g = (int) ((1 - t) * 255);
            int b =  255 + (int)(t * (blue - 255));
            color = new Color(r, g, b, alpha);
        }
        return new Dot(new_x, new_y, color);
    }
}