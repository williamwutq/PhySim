//class Physics.Dimension
//William Wu
//Oct 10
package Physics;

/**
 * <h1>3D Vector Class Dimension</h1>
 * Floating points calculation & storage
 * <p>In packet {@link Physics}</p>
 * <h3>Functions: </h3> Mathematical functions, Vector math, comparison functions, Array functions, print and String functions, etc.
 * <h3>Applications: </h3> Suitable for physics simulation, vector calculation, or other 3d spacial application
 * <h3>Contains: </h3>Class Dimension ©2024 William Wu
 * @author William Wu
 * <p>All rights reserved, suggestion welcome</p>
 * @version 1.4.1 @Oct 10, 2024
 * <p>UPGRADES: added Perspective</p>
 * @Functions
 * <pre>
 *     Static
 *     {@link Dimension#zero() .zero() static -> Dimension}
 *     {@link Dimension#unit(Dimension) .unit() static -> Dimension}
 *     {@link Dimension#normalized(Dimension) .normalized() static -> Dimension}
 *     {@link Dimension#scale(Dimension, double) .scale() static -> Dimension}
 *     {@link Dimension#positive(Dimension) .positive(Dimension) static -> Dimension}
 *     {@link Dimension#negative(Dimension) .negative(Dimension) static -> Dimension}
 *     {@link Dimension#perspective(Dimension, double) .perspective(Dimension, double) static -> Dimension 2D}
 *     {@link Dimension#plus(Dimension, Dimension) .plus(Dimension, Dimension) static -> Dimension}
 *     {@link Dimension#minus(Dimension, Dimension) .minus(Dimension, Dimension) static -> Dimension}
 *     {@link Dimension#multiply(Dimension, Dimension) .multiply(Dimension, Dimension) static -> Dimension}
 *     {@link Dimension#divide(Dimension, Dimension) .divide(Dimension, Dimension) static -> Dimension}
 *     {@link Dimension#cross(Dimension, Dimension) .cross(Dimension, Dimension) static -> Dimension}
 *     {@link Dimension#numCross(Dimension, Dimension) .numCross(Dimension, Dimension) static -> double}
 *     {@link Dimension#dot(Dimension, Dimension) .dot(Dimension, Dimension) static -> Dimension}
 *     {@link Dimension#numDot(Dimension, Dimension) .numDot(Dimension, Dimension) static -> double}
 *     {@link Dimension#triple(Dimension, Dimension, Dimension) .triple(Dimension, Dimension, Dimension) static -> Dimension}
 *     {@link Dimension#numTriple(Dimension, Dimension, Dimension) .numTriple(Dimension, Dimension, Dimension) static -> double}
 *     {@link Dimension#vector(Dimension, Dimension) .vector(Dimension, Dimension) static -> Dimension}
 *     {@link Dimension#plane(Dimension, Dimension) .plane(Dimension, Dimension) static -> Dimension}
 *     {@link Dimension#mirror(Dimension, Dimension) .mirror(Dimension, Dimension) static -> Dimension}
 *     {@link Dimension#reflect(Dimension, Dimension) .reflect(Dimension, Dimension) static -> Dimension}
 *     {@link Dimension#angleBetween(Dimension, Dimension) .angleBetween(Dimension, Dimension) static -> double}
 *     {@link Dimension#rotate(Dimension, Dimension, double) .rotateAround(Dimension, Dimension, double) static -> Dimension}
 *     {@link Dimension#scaleAlong(Dimension, Dimension, double) .scaleAlong(Dimension, Dimension, double) static -> Dimension}
 *     {@link Dimension#scaleAround(Dimension, Dimension, double) .scaleAround(Dimension, Dimension, double) static -> Dimension}
 *     {@link Dimension#sum(Dimension[]) .sum(Dimension[]) static -> Dimension}
 *     {@link Dimension#product(Dimension[]) .product(Dimension[]) static -> Dimension}
 *     {@link Dimension#volume(Dimension) .volume(Dimension) static -> double}
 *     {@link Dimension#volume(Dimension, Dimension) .volume(Dimension, Dimension) static -> double}
 *     {@link Dimension#min(Dimension, Dimension, Dimension) .min(Dimension, Dimension, Dimension) static -> Dimension}
 *     {@link Dimension#min(Dimension, Dimension) .min(Dimension, Dimension) static -> Dimension}
 *     {@link Dimension#min(Dimension[]) .min(Dimension[]) static -> Dimension}
 *     {@link Dimension#max(Dimension, Dimension, Dimension) .max(Dimension, Dimension, Dimension) static -> Dimension}
 *     {@link Dimension#max(Dimension, Dimension) .max(Dimension, Dimension) static -> Dimension}
 *     {@link Dimension#max(Dimension[]) .max(Dimension[]) static -> Dimension}
 *     {@link Dimension#midpoint(Dimension, Dimension) .midpoint(Dimension, Dimension) static -> Dimension}
 *     {@link Dimension#midpoint(Dimension[]) .midpoint(Dimension[]) static -> Dimension}
 *     {@link Dimension#interpolate(Dimension, Dimension, double) .interpolate(Dimension, Dimension, double) static -> Dimension}
 *     {@link Dimension#distance(Dimension, Dimension) .distance(Dimension, Dimension) static -> Dimension}
 *
 *     {@link Dimension#trim(Dimension[], Dimension min, Dimension max) .trim(Dimension[], Dimension min, Dimension max) static -> Dimension[]}
 *     {@link Dimension#trim(Dimension[], Dimension min, Dimension max, boolean, boolean) .trim(Dimension[], Dimension min, Dimension max, boolean, boolean) static -> Dimension[]}
 *
 *     Constructors
 *     {@link Dimension#Dimension(double, double, double) 3 double input}
 *     {@link Dimension#Dimension(double, double) 2 double input}
 *     {@link Dimension#Dimension(double) 1 double input}
 *     {@link Dimension#Dimension() no input}
 *
 *     Constants
 *     {@link Dimension#IDx() .IDx() static -> Dimension}
 *     {@link Dimension#IDy() .IDy() static -> Dimension}
 *     {@link Dimension#IDz() .IDz() static -> Dimension}
 *     {@link Dimension#ID() .ID() static -> Dimension}
 *     {@link Dimension#ID_2d() .ID_2d() static -> Dimension 2D}
 *     {@link Dimension#x() .x() -> Dimension}
 *     {@link Dimension#y() .y() -> Dimension}
 *     {@link Dimension#z() .z() -> Dimension}
 *     {@link Dimension#obj() .obj() -> Dimension}
 *     {@link Dimension#obj_2d() .obj_2d() -> Dimension 2D}
 *
 *     String
 *     {@link Dimension#toString() .toString() -> String}
 *     {@link Dimension#print() .print() -> String}
 *     {@link Dimension#print(boolean) .print(boolean) -> String}
 *     {@link Dimension#println() .println() -> String}
 *     {@link Dimension#println(boolean) .println(boolean) -> String}
 *
 *     Properties
 *     {@link Dimension#copy() .copy() -> Dimension}
 *     {@link Dimension#magnitude() .magnitude() -> double}
 *     {@link Dimension#mag() .mag() -> double}
 *     {@link Dimension#sqmag() .sqmag() -> double}
 *     {@link Dimension#abs() .abs() -> double}
 *     {@link Dimension#volume() .volume() -> double}
 *     {@link Dimension#volume(Dimension) .volume(Dimension) static -> double}
 *     {@link Dimension#volume(Dimension, Dimension) .volume(Dimension, Dimension) static -> double}
 *     {@link Dimension#unit() .unit() -> Dimension}
 *     {@link Dimension#positive() .positive() -> Dimension}
 *     {@link Dimension#negative() .positive() -> Dimension}
 *     {@link Dimension#perspective(double) .perspective(double) -> Dimension 2D}
 *     {@link Dimension#perspective(Dimension, double) .perspective(Dimension, double) static -> Dimension 2D}
 *
 *     Modification
 *     {@link Dimension#setzero() .setzero() -> modify}
 *     {@link Dimension#initialize() .initialize() -> modify}
 *     {@link Dimension#not() .not() -> Dimension}
 *     {@link Dimension#negate() .negate() -> modify}
 *     {@link Dimension#unit() .unit() -> Dimension}
 *     {@link Dimension#tounit() .tounit() -> modify}
 *     {@link Dimension#normalized() .normalized() -> Dimension}
 *     {@link Dimension#normalize() .normalize() -> modify}
 *     {@link Dimension#scale(double) .scale() -> Dimension}
 *     {@link Dimension#referto(Dimension) .referto(Dimension) -> Dimension}
 *     {@link Dimension#refer(Dimension) .refer(Dimension) -> Dimension, modify}
 *     {@link Dimension#positive() .positive() -> Dimension}
 *     {@link Dimension#negative() .positive() -> Dimension}
 *
 *     Boolean
 *     {@link Dimension#equals(Object) .equals(Object)}
 *     {@link Dimension#equal(Dimension) .equal(Dimension) -> boolean}
 *     {@link Dimension#notequal(Dimension) .notequal(Dimension) -> boolean}
 *     {@link Dimension#overlap(Dimension) .overlap(Dimension) -> boolean}
 *     {@link Dimension#lesser(Dimension) .lesser(Dimension) -> boolean}
 *     {@link Dimension#greater(Dimension) .greater(Dimension) -> boolean}
 *     {@link Dimension#lesserEqual(Dimension) .lesserEqual(Dimension) -> boolean}
 *     {@link Dimension#greaterEqual(Dimension) .greaterEqual(Dimension) -> boolean}
 *     {@link Dimension#notlesser(Dimension) .notlesser(Dimension) -> boolean}
 *     {@link Dimension#notgreater(Dimension) .notgreater(Dimension) -> boolean}
 *     {@link Dimension#between(Dimension, Dimension) .between(Dimension, Dimension) -> boolean}
 *     {@link Dimension#isZero() .isZero() -> boolean}
 *     {@link Dimension#isUnit() .isUnit() -> boolean}
 *     {@link Dimension#isParallel(Dimension) .isParallel(Dimension) -> boolean}
 *     {@link Dimension#isPerpendicular(Dimension) .isPerpendicular(Dimension) -> boolean}
 *
 *     Math Operations
 *     {@link Dimension#not() .not() -> Dimension}
 *     {@link Dimension#unit() .unit() -> Dimension}
 *     {@link Dimension#plus(Dimension) .plus(Dimension) -> Dimension}
 *     {@link Dimension#minus(Dimension) .minus(Dimension) -> Dimension}
 *     {@link Dimension#add(Dimension) .add(Dimension) -> Dimension}
 *     {@link Dimension#subtract(Dimension) .subtract(Dimension) -> Dimension}
 *     {@link Dimension#multiply(Dimension) .multiply(Dimension) -> Dimension}
 *     {@link Dimension#multiply(double) .multiply(double) -> Dimension}
 *     {@link Dimension#multiply() .multiply() -> Dimension}
 *     {@link Dimension#divide(Dimension) .divide(Dimension) -> Dimension}
 *     {@link Dimension#divide(double) .divide(double) -> Dimension}
 *     {@link Dimension#cross(Dimension) .cross(Dimension) -> Dimension}
 *     {@link Dimension#numCross(Dimension) .numCross(Dimension) -> double}
 *     {@link Dimension#dot(Dimension) .dot(Dimension) -> Dimension}
 *     {@link Dimension#dot() .dot() -> Dimension}
 *     {@link Dimension#numDot(Dimension) .numDot(Dimension) -> double}
 *     {@link Dimension#numDot() .numDot() -> double}
 *     {@link Dimension#referto(Dimension) .referto(Dimension) -> Dimension}
 *
 *     {@link Dimension#negate() .negate() -> modify}
 *     {@link Dimension#tounit() .tounit() -> modify}
 *     {@link Dimension#plusby(Dimension) .plusby(Dimension) -> Dimension, modify}
 *     {@link Dimension#minusby(Dimension) .minusby(Dimension) -> Dimension, modify}
 *     {@link Dimension#addby(Dimension) .addby(Dimension) -> Dimension, modify}
 *     {@link Dimension#subtractby(Dimension) .subtractby(Dimension) -> Dimension, modify}
 *     {@link Dimension#multiplyby(Dimension) .multiplyby(Dimension) -> Dimension, modify}
 *     {@link Dimension#multiplyby(double) .multiplyby(double) -> Dimension, modify}
 *     {@link Dimension#divideby(Dimension) .divideby(Dimension) -> Dimension, modify}
 *     {@link Dimension#divideby(double) .divideby(double) -> Dimension, modify}
 *     {@link Dimension#crossby(Dimension) .crossby(Dimension) -> Dimension, modify}
 *     {@link Dimension#dotby(Dimension) .dotby(Dimension) -> Dimension, modify}
 *     {@link Dimension#refer(Dimension) .refer(Dimension) -> Dimension, modify}
 *
 *     Vector Math
 *     {@link Dimension#cross(Dimension) .cross(Dimension) -> Dimension}
 *     {@link Dimension#dot(Dimension) .dot(Dimension) -> Dimension}
 *     {@link Dimension#triple(Dimension, Dimension, Dimension) .triple(Dimension, Dimension, Dimension) static -> Dimension}
 *     {@link Dimension#numCross(Dimension) .numCross(Dimension) -> double}
 *     {@link Dimension#numDot(Dimension) .numDot(Dimension) -> double}
 *     {@link Dimension#numTriple(Dimension, Dimension, Dimension) .numTriple(Dimension, Dimension, Dimension) static -> double}
 *     {@link Dimension#projectVector(Dimension) .projectVector(Dimension) -> Dimension}
 *     {@link Dimension#projectPlane(Dimension) .projectPlane(Dimension) -> Dimension}
 *     {@link Dimension#mirror(Dimension) .mirror(Dimension) -> Dimension}
 *     {@link Dimension#reflect(Dimension) .reflect(Dimension) -> Dimension}
 *     {@link Dimension#angleBetween(Dimension, Dimension) .angleBetween(Dimension, Dimension) static -> double}
 *     {@link Dimension#rotateAround(Dimension, double) .rotateAround(Dimension, double) -> Dimension}
 *     {@link Dimension#rotate(Dimension, Dimension, double) .rotateAround(Dimension, Dimension, double) static -> Dimension}
 *     {@link Dimension#scaleAlong(Dimension, double) .scaleAlong(Dimension, double) -> Dimension}
 *     {@link Dimension#scaleAlong(Dimension, Dimension, double) .scaleAlong(Dimension, Dimension, double) static -> Dimension}
 *     {@link Dimension#scaleAround(Dimension, double) .scaleAround(Dimension, double) -> Dimension}
 *     {@link Dimension#scaleAround(Dimension, Dimension, double) .scaleAround(Dimension, Dimension, double) static -> Dimension}
 *     {@link Dimension#threePointPlane(Dimension, Dimension, Dimension) .threePointPlane(Dimension, Dimension, Dimension) static -> Dimension}
 *
 *     View
 *     {@link Dimension#perspective(double) .perspective(double) -> Dimension 2D}
 *     {@link Dimension#perspective(Dimension, double) .perspective(Dimension, double) static -> Dimension 2D}
 * </pre>*/
public final class Dimension {
    private double x;
    private double y;
    private double z;

    //constructor
    /**<p>Constructor</p>
     * <p>Three input: Creator a new Physics.Dimension vector holding value {x: x, y: y, z: z}</p>
     * <p>related {@link Dimension#copy()}, see {@link Dimension}</p>
     * @param dim_x input x value
     * @param dim_y input y value
     * @param dim_z input z value
     * @value {x: x, y: y, z: z}*/
    public Dimension(double dim_x, double dim_y, double dim_z){
        x = dim_x;
        y = dim_y;
        z = dim_z;
    }
    /**<p>Constructor</p>
     * <p>Two input: Creator a 2D Physics.Dimension vector holding value {x: x, y: y, z: 0}</p>
     * <p>related {@link Dimension#ID_2d()}, see {@link Dimension}</p>
     * @param dim_x input x value
     * @param dim_y input y value
     * @value {x: x, y: y, z: 0}*/
    public Dimension(double dim_x, double dim_y){
        x = dim_x;
        y = dim_y;
        z = 0;
    }
    /**<p>Constructor</p>
     * <p>One input: Creator a new Physics.Dimension vector that has magnitude of mag and point toward {x: 1, y: 1, z: 1}</p>
     * <p>see {@link Dimension#ID()}, {@link Dimension#magnitude()} and {@link Dimension}. Implemented by {@link Math#cbrt(double) the cuberoot method}</p>
     * @param mag the magnitude of the vector*/
    public Dimension(double mag){
        double result = Math.cbrt(mag);
        x = result;
        y = result;
        z = result;
    }
    /**<p>Constructor</p>
     * <p>No input: Creator a new Physics.Dimension vector and set it to zero</p>
     * <p>see {@link Dimension#zero()} and {@link Dimension}</p>
     * @value {x: 0, y: 0, z: 0}*/
    public Dimension(){
        x = 0;
        y = 0;
        z = 0;
    }

    //identity
    /**<p>The X identity vector</p>
     * <p>see {@link Dimension#ID()}</p>
     * @value {x: 1, y: 0, z: 0}
     * @return vector (Physics.Dimension)*/
    public static Dimension IDx(){
        return new Dimension(1,0);
    }
    /**<p>The Y identity vector</p>
     * <p>see {@link Dimension#ID()}</p>
     * @value {x: 0, y: 1, z: 0}
     * @return vector (Physics.Dimension)*/
    public static Dimension IDy(){
        return new Dimension(0,1);
    }
    /**<p>The Z identity vector</p>
     * <p>see {@link Dimension#ID()}</p>
     * @value {x: 0, y: 0, z: 1}
     * @return vector (Physics.Dimension)*/
    public static Dimension IDz(){
        return new Dimension(0,0,1);
    }
    /**<p>The 2D identity vector</p>
     * <p>see {@link Dimension#ID()}</p>
     * @value {x: 1, y: 1, z: 0}
     * @return vector (Physics.Dimension)*/
    public static Dimension ID_2d(){
        return new Dimension(1,1);
    }
    /**<p>The identity vector</p>
     * <p>see {@link Dimension}</p>
     * @value {x: 1, y: 1, z: 1}
     * @return vector (Physics.Dimension)*/
    public static Dimension ID(){
        return new Dimension(1);
    }
    /**<p>A copy of the vector, but only x value is preserved</p>
     * <p>see {@link Dimension#IDx()}</p>
     * @return vector (Physics.Dimension)*/
    public Dimension x(){
        return new Dimension(x,0);
    }
    /**<p>A copy of the vector, but only y value is preserved</p>
     * <p>see {@link Dimension#IDy()}</p>
     * @return vector (Physics.Dimension)*/
    public Dimension y(){
        return new Dimension(0,y);
    }
    /**<p>A copy of the vector, but only z value is preserved</p>
     * <p>see {@link Dimension#IDz()}</p>
     * @return vector (Physics.Dimension)*/
    public Dimension z(){
        return new Dimension(0,0,z);
    }
    /**<p>A 2D copy of the vector with no z value</p>
     * <p>see {@link Dimension#ID_2d()}</p>
     * @return vector (Physics.Dimension)*/
    public Dimension obj_2d(){
        return new Dimension(x,y);
    }
    /**<p>A copy of the vector</p>
     * <p>see {@link Dimension#copy()}</p>
     * @return vector (Physics.Dimension)*/
    public Dimension obj(){
        return new Dimension(x,y,z);
    }

    private static String num_to_string(double num){
       return String.format("%.8f",num);
    }

    /**<p>A string representation of the values of the vector in the form: {x: num, y: num, z: num}</p>
     * <p>see {@link Dimension#print(boolean)}</p>
     * @return vector (String)*/
    @Override
    public String toString(){
        return print();
    }
    /**<p>A string representation of the values of the vector in the form: {x: num, y: num, z: num}</p>
     * <p>see {@link Dimension#print(boolean)}</p>
     * @return vector (String)*/
    public String print(){//return a string for printing
        return "{x: " + num_to_string(x)
                + ", y: " + num_to_string(y)
                + ", z: " + num_to_string(z)
                + '}';

    }
    /**<p>A string representation of the values of the vector in the form: {x: num, y: num, z: num}</p>
     * <p>Without space, it would be {x:num,y:num,z:num}</p>
     * <pre>Examples:
     *     {x: 1, y: -3.5, z: 4} (have space)
     *     {x:1,y:-3.5,z:4} (have no space)
     * </pre>
     * <p>it is used in {@link Dimension#toString()} to help debugging your code or print values</p>
     * @param space whether the output should include any spaces
     * @return vector (String)*/
    public String print(boolean space){//return a string for printing
        if (space) {
            return "{x: " + num_to_string(x)
                    + ", y: " + num_to_string(y)
                    + ", z: " + num_to_string(z)
                    + '}';
        }else{
            return "{x:" + num_to_string(x)
                    +",y:" + num_to_string(y)
                    +",z:" + num_to_string(z)
                    +'}';
        }
    }
    /**<p>A string representation of the values of the vector with a newline character in the form: {x: num, y: num, z: num}</p>
     * <p>see {@link Dimension#print()}</p>
     * @return vector (String)*/
    public String println(){//return a string for printing
        return "{x: " + num_to_string(x)
                + ", y: " + num_to_string(y)
                + ", z: " + num_to_string(z)
                + '}' + '\n';
    }
    /**<p>A string representation of the values of the vector with a newline character in the form: {x: num, y: num, z: num}</p>
     * <p>see {@link Dimension#print(boolean)}</p>
     * @param space whether the output should include any spaces
     * @return vector (String)*/
    public String println(boolean space){//return a string for printing
        if (space) {
            return "{x: " + num_to_string(x)
                    + ", y: " + num_to_string(y)
                    + ", z: " + num_to_string(z)
                    + '}' + '\n';
        }else{
            return "{x:" + num_to_string(x)
                    +",y:" + num_to_string(y)
                    +",z:" + num_to_string(z)
                    +'}' + '\n';
        }
    }
    /**<p>The magnitude of the vector</p>
     * <p>to obtain magnitude squared, use {@link Dimension#sqmag()}</p>
     * @return magnitude (double)*/
    public double magnitude(){
        return Math.sqrt(x*x + y*y + z*z);
    }
    /**<p>The square of the magnitude of the vector</p>
     * <p>to obtain magnitude, use {@link Dimension#magnitude()}</p>
     * @return magnitude^2 (double)*/
    public double sqmag(){
        return this.x*this.x + this.y*this.y + this.z*this.z;
    }
    /**<p>The magnitude of the vector</p>
     * <p>see {@link Dimension#magnitude()}</p>
     * @return magnitude (double)*/
    public double mag(){
        return Math.sqrt(x*x + y*y + z*z);
    }
    /**<p>The absolute value, aka magnitude of the vector</p>
     * <p>see {@link Dimension#magnitude()}</p>
     * @return magnitude (double)*/
    public double abs(){
        return Math.sqrt(x*x + y*y + z*z);
    }

    //self functions
    /**<p>Set this vector to zero</p>*/
    public void setzero(){//set all values to zero
        x = 0; y = 0; z = 0;
    }
    /**@value {x: 0, y: 0, z: 0}
     * @return zero (Physics.Dimension)*/
    public static Dimension zero(){
        return new Dimension();
    }
    /**<p>Set this vector to zero</p>
     * <p>see {@link Dimension#zero()}</p>*/
    public void initialize(){//set all values to zero
        x = 0; y = 0; z = 0;
    }
    /**<p>Flip the direction of the vector, which means all values gets negated</p>
     * <p>see {@link Dimension#not()} or {@link Dimension#negate()}</p>
     * @param dimension the vector to negate
     * @return reversed vector (Physics.Dimension)*/
    public static Dimension negate(Dimension dimension){
        return dimension.not();
    }
    /**<p>Flip the direction of the vector, which means all values gets negated</p>
     * <p>see {@link Dimension#not()}</p>
     * @param dimension the vector to negate
     * @return reversed vector (Physics.Dimension)*/
    public static Dimension not(Dimension dimension){
        return dimension.not();
    }
    /**<p>Flip the direction of the vector, which means all values gets negated</p>
     * <p>see {@link Dimension#not()}</p>*/
    public void negate(){//flip signs
        x = -x; y = -y; z = -z;
    }
    /**<p>Return a numerical copy of the vector. This will be a different object</p>
     * {@code return new Physics.Dimension(this.x, this.y, this.z);}
     * @return copy (Physics.Dimension)*/
    public Dimension copy(){
        return new Dimension(x, y, z);
    }
    /**<p>Convert the vector to an unit vector</p>
     * <p>use {@link Dimension#unit()} if no modification is needed</p>
     * @throws ArithmeticException prohibit vector of magnitude zero*/
    public void tounit(){
        double mag = magnitude();
        if (mag == 0){throw new ArithmeticException("Division by Zero");}
        x /= mag; y /= mag; z /= mag;
    }
    /**<p>Return an unit vector pointing the same direction as this</p>
     * @return unit vector (Physics.Dimension)
     * @throws ArithmeticException prohibit vector of magnitude zero*/
    public Dimension unit(){
        double mag = magnitude();
        if (mag == 0){throw new ArithmeticException("Division by Zero");}
        return new Dimension(x / mag, y / mag, z / mag);
    }
    /**<p>Return an unit vector pointing the same direction as this</p>
     * <p>see {@link Dimension#unit()}</p>
     * @return unit vector (Physics.Dimension)
     * @param dimension the directional vector
     * @throws ArithmeticException prohibit vector of magnitude zero*/
    public static Dimension unit(Dimension dimension){
        double mag = dimension.magnitude();
        if (mag == 0){throw new ArithmeticException("Division by Zero");}
        return new Dimension(dimension.x / mag, dimension.y / mag, dimension.z / mag);
    }
    /**<p>Normalize this vector</p>
     * <p>see {@link Dimension#tounit()}</p>
     * <p>use {@link Dimension#normalized()} if no modification is needed</p>
     * @throws ArithmeticException prohibit vector of magnitude zero*/
    public void normalize(){
        double mag = magnitude();
        if (mag == 0){throw new ArithmeticException("Division by Zero");}
        x /= mag; y /= mag; z /= mag;
    }
    /**<p>Normalized unit vector</p>
     * <p>see {@link Dimension#unit()}</p>
     * @return unit vector (Physics.Dimension)
     * @throws ArithmeticException prohibit vector of magnitude zero*/
    public Dimension normalized(){
        double mag = magnitude();
        if (mag == 0){throw new ArithmeticException("Division by Zero");}
        return new Dimension(x / mag, y / mag, z / mag);
    }
    /**<p>Normalized unit vector</p>
     * <p>see {@link Dimension#unit()}</p>
     * @return unit vector (Physics.Dimension)
     * @param dimension the directional vector
     * @throws ArithmeticException prohibit vector of magnitude zero*/
    public static Dimension normalized(Dimension dimension){
        double mag = dimension.magnitude();
        if (mag == 0){throw new ArithmeticException("Division by Zero");}
        return new Dimension(dimension.x / mag, dimension.y / mag, dimension.z / mag);
    }
    /**<p>The volume occupied by the cuboid created by the vector</p>
     * @return volume (double)*/
    public double volume(){
        return Math.abs(this.x * this.y * this.z);
    }
    /**<p>The volume occupied by the cuboid created by the vector</p>
     * <p>see {@link Dimension#volume()}</p>
     * @return volume (double)
     * @param dimension the vector*/
    public static double volume(Dimension dimension){
        return dimension.volume();
    }
    /**<p>The volume occupied by the cuboid between vector a and b</p>
     * <p>see {@link Dimension#volume()}</p>
     * @return volume (double)
     * @param a a Physics.Dimension vector
     * @param b a Physics.Dimension vector*/
    public static double volume(Dimension a, Dimension b){
        Dimension dis = a.minus(b);
        return dis.volume();
    }
    /**<p>Return vector with certain length pointing the same direction as this</p>
     * @return scaled vector (Physics.Dimension)
     * @param scale length of new vector
     * @throws ArithmeticException prohibit vector of magnitude zero*/
    public Dimension scale(double scale){
        double mag = magnitude();
        if (mag == 0){throw new ArithmeticException("Division by Zero");}else{mag*=scale;}
        return new Dimension(x / mag, y / mag, z / mag);
    }
    /**<p>Return vector with certain length pointing the same direction as this</p>
     * <p>see {@link Dimension#scale(double)}</p>
     * @return unit vector (Physics.Dimension)
     * @param dimension the directional vector
     * @throws ArithmeticException prohibit vector of magnitude zero*/
    public static Dimension scale(Dimension dimension, double scale){
        double mag = dimension.magnitude();
        if (mag == 0){throw new ArithmeticException("Division by Zero");}else{mag*=scale;}
        return new Dimension(dimension.x / mag, dimension.y / mag, dimension.z / mag);
    }
    /**<p>Return vector but values in all dimensions are positive</p>
     * @return positive vector (Physics.Dimension)*/
    public Dimension positive(){
        return new Dimension(Math.abs(x), Math.abs(y), Math.abs(z));
    }
    /**<p>Return vector but values in all dimensions are positive</p>
     * <p>see {@link Dimension#positive()}</p>
     * @param dimension input vector
     * @return positive vector (Physics.Dimension)*/
    public static Dimension positive(Dimension dimension){
        return dimension.positive();
    }
    /**<p>Return vector but values in all dimensions are negative</p>
     * @return negative vector (Physics.Dimension)*/
    public Dimension negative(){
        return new Dimension(-Math.abs(x), -Math.abs(y), -Math.abs(z));
    }
    /**<p>Return vector but values in all dimensions are negative</p>
     * <p>see {@link Dimension#negative()}</p>
     * @param dimension input vector
     * @return negative vector (Physics.Dimension)*/
    public static Dimension negative(Dimension dimension){
        return dimension.negative();
    }
    //operators
    /**<p>Sum of two vectors</p>
     * <p>see {@link Dimension#add(Dimension)} or {@link Dimension#plus(Dimension)}</p>
     * @param a Physics.Dimension vector
     * @param b Physics.Dimension vector
     * @return sum (Physics.Dimension)
     */
    public static Dimension plus(Dimension a, Dimension b){
        return a.plus(b);
    }
    /**<p>Difference of two vectors</p>
     * <p>see {@link Dimension#subtract(Dimension)} or {@link Dimension#minus(Dimension)}</p>
     * @param a Physics.Dimension vector
     * @param b Physics.Dimension vector
     * @return difference (Physics.Dimension)
     */
    public static Dimension minus(Dimension a, Dimension b){
        return a.minus(b);
    }
    /**<p>Product of two vectors</p>
     * <p>see {@link Dimension#multiply(Dimension)}</p>
     * @param a Physics.Dimension vector
     * @param b Physics.Dimension vector
     * @return product (Physics.Dimension)
     */
    public static Dimension multiply(Dimension a, Dimension b){
        return a.multiply(b);
    }
    /**<p>Quotient of two vectors</p>
     * <p>see {@link Dimension#divide(Dimension)}</p>
     * @param a Physics.Dimension vector
     * @param b Physics.Dimension vector
     * @return quotient (Physics.Dimension)
     */
    public static Dimension divide(Dimension a, Dimension b){
        return a.divide(b);
    }
    /**<p>Cross product of two vectors</p>
     * <p>see {@link Dimension#cross(Dimension)}</p>
     * @param a Physics.Dimension vector
     * @param b Physics.Dimension vector
     * @return cross product (Physics.Dimension)*/
    public static Dimension cross(Dimension a, Dimension b){
        return a.cross(b);
    }
    /**<p>Scalar cross product with v</p>
     * <p>see {@link Dimension#numCross(Dimension)}</p>
     * @param a Physics.Dimension vector
     * @param b Physics.Dimension vector
     * @return cross product (double)*/
    public static double numCross(Dimension a, Dimension b){
        return (a.y * b.z - a.z * b.y - a.z * b.x + a.x * b.z + a.x * b.y - a.y * b.x);
    }
    /**<p>Dot product of two vectors</p>
     * <p>see {@link Dimension#dot(Dimension)}</p>
     * @param a Physics.Dimension vector
     * @param b Physics.Dimension vector
     * @return dot product (Physics.Dimension)*/
    public static Dimension dot(Dimension a, Dimension b){
        return a.dot(b);
    }
    /**<p>Scalar dot product with v</p>
     * <p>see {@link Dimension#numDot(Dimension)}</p>
     * @param a Physics.Dimension vector
     * @param b Physics.Dimension vector
     * @return dot product (double)*/
    public static double numDot(Dimension a, Dimension b){
        return (a.x * b.x + a.y * b.y + a.z * b.z);
    }
    /**<p>see {@link Dimension#projectVector(Dimension)}</p>*/
    public static Dimension vector(Dimension a, Dimension b){
        return a.projectVector(b);
    }
    /**<p>see {@link Dimension#projectPlane(Dimension)}</p>*/
    public static Dimension plane(Dimension a, Dimension b){
        return a.projectPlane(b);
    }
    /**<p>see {@link Dimension#mirror(Dimension)}</p>*/
    public static Dimension mirror(Dimension a, Dimension b){
        return a.mirror(b);
    }
    /**<p>see {@link Dimension#reflect(Dimension)}</p>*/
    public static Dimension reflect(Dimension a, Dimension b){
        return a.reflect(b);
    }
    /**<p>Find the sum of vectors in an array</p>
     * @param dimensions Physics.Dimension vector array
     * @throws IllegalArgumentException array length cannot be 0
     * @return sum of all vectors (Physics.Dimension)
     */
    public static Dimension sum(Dimension[] dimensions) {//find the sum of an array of Physics.Dimension objects
        if (dimensions.length == 0){throw new IllegalArgumentException("Physics.Dimension Array length 0 prohibited");}
        else if (dimensions.length == 1){return dimensions[0];}
        else if (dimensions.length == 2){return plus(dimensions[0], dimensions[1]);}

        Dimension sum = new Dimension();
        // Sum up the x, y, and z components of all dimensions
        for (Dimension dim : dimensions) {
            sum.plusby(dim);
        }
        return sum;
    }
    /**<p>Find the product of vectors in an array</p>
     * @param dimensions Physics.Dimension vector array
     * @throws IllegalArgumentException array length cannot be 0
     * @return product of all vectors (Physics.Dimension)
     */
    public static Dimension product (Dimension[] dimensions) {//find the product of an array of Physics.Dimension objects
        if (dimensions.length == 0){throw new IllegalArgumentException("Physics.Dimension Array length 0 prohibited");}
        else if (dimensions.length == 1){return dimensions[0];}
        else if (dimensions.length == 2){return multiply(dimensions[0], dimensions[1]);}

        Dimension product = new Dimension(1);
        // Sum up the x, y, and z components of all dimensions
        for (Dimension dim : dimensions) {
            product.multiplyby(dim);
        }
        return product;
    }

    /**<p>A negation of the vector, which means all value gets inverted</p>
     * {@code return new Physics.Dimension(-this.x, -this.y, -this.z);}*/
    public Dimension not(){//overload operator!: negate
        return new Dimension(-x, -y, -z);
    }
    /**<p>Add this vector to the other</p>
     * <p>see {@link Dimension#add(Dimension)}</p>
     * @param other addend
     * @return sum (Physics.Dimension)
     */
    public Dimension plus(Dimension other){//overload operator+
        return new Dimension(x + other.x, y + other.y, z + other.z);
    }
    /**<p>Subtract this vector by the other</p>
     * <p>see {@link Dimension#subtract(Dimension)}</p>
     * @param other subtrahend
     * @return difference (Physics.Dimension)
     */
    public Dimension minus(Dimension other){//overload operator-
        return new Dimension(x - other.x, y - other.y, z - other.z);
    }
    /**<p>Create reference relative to origin, modify this vector</p>
     * <p>see {@link Dimension#refer(Dimension)}</p>
     * @param origin origin
     * @return reference (Physics.Dimension)
     */
    public Dimension referto(Dimension origin){//create reference relative to origin
        return new Dimension(x - origin.x, y - origin.y, z - origin.z);
    }
    /**<p>Create reference relative to origin</p>
     * @param origin origin
     * @return reference (Physics.Dimension)
     */
    public Dimension refer(Dimension origin){//create reference relative to origin
        x -= origin.x; y -= origin.y; z -= origin.z;
        return this;
    }
    /**<p>Add this vector to the other</p>
     * @param other addend
     * @return sum (Physics.Dimension)
     */
    public Dimension add(Dimension other){//overload operator+=
        return new Dimension(x + other.x, y + other.y, z + other.z);
    }
    /**<p>Subtract this vector to the other</p>
     * @param other subtrahend
     * @return difference (Physics.Dimension)
     */
    public Dimension subtract(Dimension other){//overload operator-=
        return new Dimension(x - other.x, y - other.y, z - other.z);
    }
    /**<p>Multiply this vector with the other</p>
     * @param other multiplier
     * @return product (Physics.Dimension)
     */
    public Dimension multiply(Dimension other){//overload operator*: dot product
        return new Dimension(x * other.x, y * other.y, z * other.z);
    }
    /**<p>Divide this vector by the other</p>
     * @param other divisor
     * @throws ArithmeticException division by zero
     * @return quotient (Physics.Dimension)
     */
    public Dimension divide(Dimension other){//overload operator/
        if (other.x == 0 || other.y == 0 || other.z == 0){throw new ArithmeticException("Division by Zero");}
        return new Dimension(x / other.x, y / other.y, z / other.z);
    }
    /**<p>Add this vector by the other, modify this</p>
     * @param other addend
     * @return sum (Physics.Dimension)
     */
    public Dimension plusby(Dimension other){//overload operator+=
        x += other.x; y += other.y; z += other.z;
        return this;
    }
    /**<p>Subtract this vector by the other, modify this</p>
     * @param other subtrahend
     * @return difference (Physics.Dimension)
     */
    public Dimension minusby(Dimension other){//overload operator-=
        x -= other.x; y -= other.y; z -= other.z;
        return this;
    }
    /**<p>Add this vector by the other, modify this</p>
     * @param other addend
     * @return sum (Physics.Dimension)
     */
    public Dimension addby(Dimension other){//overload operator+=
        x += other.x; y += other.y; z += other.z;
        return this;
    }
    /**<p>Subtract this vector by the other, modify this</p>
     * @param other subtrahend
     * @return difference (Physics.Dimension)
     */
    public Dimension subtractby(Dimension other){//overload operator-=
        x -= other.x; y -= other.y; z -= other.z;
        return this;
    }
    /**<p>Multiply this vector by the other, modify this</p>
     * @param other multiplier
     * @return product (Physics.Dimension)
     */
    public Dimension multiplyby(Dimension other){//overload operator*=: dot product
        x *= other.x; y *= other.y; z *= other.z;
        return this;
    }
    /**<p>Divide this vector by the other, modify this</p>
     * @param other divisor
     * @throws ArithmeticException division by zero
     * @return quotient (Physics.Dimension)
     */
    public Dimension divideby(Dimension other){//overload operator/=
        if (other.x == 0 || other.y == 0 || other.z == 0){throw new ArithmeticException("Division by Zero");}
        x /= other.x; y /= other.y; z /= other.z;
        return this;
    }
    /**<p>Cross this vector by the other, modify this</p>
     * <p>see {@link Dimension#cross(Dimension)}</p>
     * @param other vector
     * @return result (Physics.Dimension)
     */
    public Dimension crossby(Dimension other){//overload operator%=: cross product
        double a = y * other.z - z * other.y;
        double b = z * other.x - x * other.z;
        double c = x * other.y - y * other.x;
        x = a; y = b; z = c;
        return this;
    }
    /**<p>Dot this vector by the other, modify this</p>
     * <p>see {@link Dimension#dot(Dimension)}</p>
     * @param other vector
     * @return result (Physics.Dimension)
     */
    public Dimension dotby(Dimension other){//overload operator*=: dot product
        x *= other.x; y *= other.y; z *= other.z;
        return this;
    }
    /**<p>Multiply this vector with a multiplier, modify this</p>
     * @param k multiplier
     * @return result (Physics.Dimension)
     */
    public Dimension multiply(double k){//overload operator Dim*num
        return new Dimension(x * k, y * k, z * k);
    }
    /**<p>Divide this vector by a divisor</p>
     * @param k divisor
     * @throws ArithmeticException division by zero
     * @return result (Physics.Dimension)
     */
    public Dimension divide(double k){//overload operator Dim/num
        if (k == 0){throw new ArithmeticException("Division by Zero");}
        return new Dimension(x / k, y / k, z / k);
    }
    /**<p>Multiply this vector by a multiplier, modify this</p>
     * @param k multiplier
     * @return result (Physics.Dimension)
     */
    public Dimension multiplyby (double k){//overload operator Dim*=num
        x *= k; y *= k; z *= k;
        return this;
    }
    /**<p>Divide this vector by a divisor, modify this</p>
     * @param k divisor
     * @throws ArithmeticException division by zero
     * @return result (Physics.Dimension)
     */
    public Dimension divideby(double k){//overload operator Dim/=num
        if (k == 0){throw new ArithmeticException("Division by Zero");}
        x /= k; y /= k; z /= k;
        return this;
    }
    /**<p>Multiply this vector by itself</p>
     * @return product (Physics.Dimension)
     */
    public Dimension multiply(){
        return this.multiply(this);
    }
    /**<p>Dot this vector by itself</p>
     * <p>see {@link Dimension#dot(Dimension)}</p>
     * @return result (Physics.Dimension)
     */
    public Dimension dot(){
        return this.multiply(this);
    }
    /**<p>Mathematical dot product with self, aka square magnitude</p>
     * <a href="https://en.wikipedia.org/wiki/Dot_product">Dot Product</a>
     * @return dot product (double)*/
    public double numDot(){
        return (x * x + y * y + z * z);
    }

    /**<p>Hashcode</p>
     * <p>see {@link Dimension#equals(Object)}</p>
     * @return hashcode of the 3D vector (int)*/
    @Override
    public int hashCode() {
        // Use a prime number to mix the hash codes of the fields
        int result = 7; // A non-zero constant

        // Convert the double values to long using Double.doubleToLongBits
        result = 31 * result + Double.hashCode(x);
        result = 31 * result + Double.hashCode(y);
        result = 31 * result + Double.hashCode(z);

        return result;
    }

    /**<p>Judge if the vector is equal (direction or magnitude) to an object</p>
     * <p>not to be confused with {@link Dimension#equal(Dimension)}</p>
     * @param obj object to compare with
     * @return true if equal*/
    @Override
    //booleans
    public boolean equals(Object obj){
        if (obj.getClass()==Double.class){return (double)obj == abs();}
        else if (obj.getClass()==Integer.class){return (int)obj == (int)abs();}
        else if (obj.getClass()==Long.class){return (long)obj == (long)abs();}
        else if (obj.getClass()==Dimension.class){return equal((Dimension)obj);}
        else return false;
    }
    /**<p>Judge if the vector is absolutely (direction and magnitude) to a vector</p>
     * <p>not to be confused with {@link Dimension#equals(Object)}</p>
     * @param other Physics.Dimension vector to compare with
     * @return true if equal*/
    public boolean equal(Dimension other){//overload operator==
        return (x == other.x && y == other.y && z == other.z);
    }
    /**<p>Judge if the vector overlap in x, y, or z with another vector</p>
     * @param other Physics.Dimension vector to compare with
     * @return true if overlap*/
    public boolean overlap(Dimension other){//overload operator==
        return (x == other.x || y == other.y || z == other.z);
    }
    /**<p>Judge if the vector is not absolutely (direction and magnitude) to a vector</p>
     * <pre>see: {@link Dimension}</pre>
     * @param other Physics.Dimension vector to compare with
     * @return true if not equal*/
    public boolean notequal(Dimension other){//overload operator!=
        return (x != other.x || y != other.y || z != other.z);
    }
    /**<p>Judge if the vector is absolutely in between max and min</p>
     * <p>see {@link Dimension#min(Dimension, Dimension)}, {@link Dimension#max(Dimension, Dimension)}</p>
     * @param min minimum
     * @param max maximum
     * @return true if in between*/
    public boolean between(Dimension min, Dimension max){
        return (this.greater(min)||this.equal(min)) && this.lesser(max);
    }
    /**<p>Judge if the vector is absolutely greater than the other</p>
     * @param other Physics.Dimension vector to compare with
     * @return true if greater*/
    public boolean greater(Dimension other){
        return (this.x > other.x && this.y > other.y && this.z > other.z);
    }
    /**<p>Judge if the vector is absolutely lesser than the other</p>
     * @param other Physics.Dimension vector to compare with
     * @return true if lesser*/
    public boolean lesser(Dimension other){
        return (this.x < other.x && this.y < other.y && this.z < other.z);
    }
    /**<p>Judge if the vector is absolutely greater than or equal to the other</p>
     * @param other Physics.Dimension vector to compare with
     * @return true if greater or equal*/
    public boolean greaterEqual(Dimension other){
        return (this.x >= other.x && this.y >= other.y && this.z >= other.z);
    }
    /**<p>Judge if the vector is absolutely lesser than or equal to the other</p>
     * @param other Physics.Dimension vector to compare with
     * @return true if lesser or equal*/
    public boolean lesserEqual(Dimension other){
        return (this.x <= other.x && this.y <= other.y && this.z <= other.z);
    }
    /**<p>Judge if the vector is not absolutely greater than the other</p>
     * @param other Physics.Dimension vector to compare with
     * @return true if not greater*/
    public boolean notgreater(Dimension other) {
        return (this.x <= other.x || this.y <= other.y || this.z <= other.z);
    }
    /**<p>Judge if the vector is not absolutely lesser than the other</p>
     * @param other Physics.Dimension vector to compare with
     * @return true if not lesser*/
    public boolean notlesser(Dimension other) {
        return (this.x >= other.x || this.y >= other.y || this.z >= other.z);
    }
    /**<p>Judge if the vector is zero</p>
     * @return true if is zero*/
    public boolean isZero(){
        return (x == 0 && y == 0 && z == 0);
    }
    /**<p>Judge if the vector is a unit vector</p>
     * @return true if is a unit vector*/
    public boolean isUnit(){
        return (this.sqmag() == 1);
    }
    /**<p>Judge if the vector is parallel to the other</p>
     * @param other the other vector
     * @return true if is a unit vector*/
    public boolean isParallel(Dimension other){
        return this.cross(other).isZero();
    }
    /**<p>Judge if the vector is perpendicular to the other</p>
     * @param other the other vector
     * @return true if is a unit vector*/
    public boolean isPerpendicular(Dimension other){
        return this.dot(other).isZero();
    }

    //math
    /**<p>Find the distance between two vectors, always positive</p>
     * @param a a Physics.Dimension vector
     * @param b a Physics.Dimension vector
     * @return distance (Physics.Dimension)*/
    public static Dimension distance(Dimension a, Dimension b){
        return a.refer(b).positive();
    }
    /**<p>Find the maximum, using the reference vector as origin, this is not find the bigger vector</p>
     * @param a a Physics.Dimension vector
     * @param b a Physics.Dimension vector
     * @param reference the origin for reference
     * @return the maximum originated from reference vector (Physics.Dimension)*/
    public static Dimension max(Dimension a, Dimension b, Dimension reference) {
        // Calculate the difference of a and b relative to the reference
        Dimension aRelative = a.minus(reference);
        Dimension bRelative = b.minus(reference);
        // Find the term wise maximum relative to reference
        Dimension maxRelative = new Dimension(
                Math.max(aRelative.x, bRelative.x),
                Math.max(aRelative.y, bRelative.y),
                Math.max(aRelative.z, bRelative.z)
        );
        // Add reference back to return to the original scale
        return maxRelative.plus(reference);
    }
    /**<p>Find the maximum between two vectors, this is not find the bigger vector</p>
     * @param a a Physics.Dimension vector
     * @param b a Physics.Dimension vector
     * @return the maximum (Physics.Dimension)*/
    public static Dimension max(Dimension a, Dimension b) {
        return new Dimension(Math.max(a.x, b.x), Math.max(a.y, b.y), Math.max(a.z, b.z));
    }
    /**<p>Find the maximum in an array of vectors</p>
     * <p>see {@link Dimension#max(Dimension, Dimension)}</p>
     * @param dimensions array of vectors
     * @throws IllegalArgumentException array length cannot be 0
     * @return the maximum (Physics.Dimension)*/
    public static Dimension max(Dimension[] dimensions) {//find the max of an array of Physics.Dimension objects
        if (dimensions.length == 0){throw new IllegalArgumentException("Physics.Dimension Array length 0 prohibited");}
        else if (dimensions.length == 1){return dimensions[0];}
        else if (dimensions.length == 2){return max(dimensions[0], dimensions[1]);}

        Dimension max = dimensions[0];
        // find the max
        for (Dimension dim : dimensions) {
            max = Dimension.max(max, dim);
        }
        return max;
    }
    /**<p>Find the minimum, using the reference vector as origin, this is not find the smaller vector</p>
     * @param a a Physics.Dimension vector
     * @param b a Physics.Dimension vector
     * @param reference the origin for reference
     * @return the minimum originated from reference vector (Physics.Dimension)*/
    public static Dimension min(Dimension a, Dimension b, Dimension reference) {
        // Calculate the difference of a and b relative to the reference
        Dimension aRelative = a.minus(reference);
        Dimension bRelative = b.minus(reference);
        // Find the term wise maximum relative to reference
        Dimension minRelative = new Dimension(
                Math.min(aRelative.x, bRelative.x),
                Math.min(aRelative.y, bRelative.y),
                Math.min(aRelative.z, bRelative.z)
        );
        // Add reference back to return to the original scale
        return minRelative.plus(reference);
    }
    /**<p>Find the minimum between two vectors, this is not find the smaller vector</p>
     * @param a a Physics.Dimension vector
     * @param b a Physics.Dimension vector
     * @return the minimum (Physics.Dimension)*/
    public static Dimension min(Dimension a, Dimension b) {
        return new Dimension(Math.min(a.x, b.x), Math.min(a.y, b.y), Math.min(a.z, b.z));
    }
    /**<p>Find the minimum in an array of vectors</p>
     * <p>see {@link Dimension#min(Dimension, Dimension)}</p>
     * @param dimensions array of vectors
     * @throws IllegalArgumentException array length cannot be 0
     * @return the minimum (Physics.Dimension)*/
    public static Dimension min(Dimension[] dimensions) {//find the min of an array of Physics.Dimension objects
        if (dimensions.length == 0){throw new IllegalArgumentException("Physics.Dimension Array length 0 prohibited");}
        else if (dimensions.length == 1){return dimensions[0];}
        else if (dimensions.length == 2){return min(dimensions[0], dimensions[1]);}

        Dimension min = dimensions[0];
        // find the min
        for (Dimension dim : dimensions) {
            min = Dimension.min(min, dim);
        }
        return min;
    }
    /**<p>Find a vector in between a and b and equal distance to them</p>
     * <p>see {@link Dimension#interpolate(Dimension, Dimension, double)}</p>
     * @param a a Physics.Dimension vector
     * @param b a Physics.Dimension vector
     * @return the midpoint (Physics.Dimension)*/
    public static Dimension midpoint(Dimension a, Dimension b) {//find the midpoint between a and b
        return new Dimension((a.x + b.x) / 2, (a.y + b.y) / 2, (a.z + b.z) / 2);
    }
    /**<p>Find the midpoint in an array of vectors</p>
     * <p>see {@link Dimension#midpoint(Dimension, Dimension)}</p>
     * @param dimensions array of vectors
     * @throws IllegalArgumentException array length cannot be 0
     * @return the midpoint in the array (Physics.Dimension)*/
    public static Dimension midpoint(Dimension[] dimensions){
        if (dimensions.length == 0){throw new IllegalArgumentException("Physics.Dimension Array length 0 prohibited");}
        else if (dimensions.length == 1){return dimensions[0];}
        else if (dimensions.length == 2){return midpoint(dimensions[0], dimensions[1]);}
        return sum(dimensions).divideby(dimensions.length);
    }
    /**<p>Find a vector in between a and b using a factor</p>
     * <p>see {@link Dimension#midpoint(Dimension, Dimension)}, {@link Dimension#interpolate(Dimension, Dimension, Dimension)}</p>
     * @param a a Physics.Dimension vector
     * @param b a Physics.Dimension vector
     * @param factor the factor [0, 1] -> [a, b]
     * @return the interpolation point (Physics.Dimension)*/
    public static Dimension interpolate(Dimension a, Dimension b, double factor){
        //factor between 0 and 1
        if (factor > 1){factor = 1;}else if (factor < 0){factor = 0;}
        return a.plus(b.minus(a).multiply(factor));
    }
    /**<p>Find a vector in between a and b using a factor</p>'
     * <p>see {@link Dimension#midpoint(Dimension, Dimension)}, {@link Dimension#interpolate(Dimension, Dimension, double)}</p>
     * @param a a Physics.Dimension vector
     * @param b a Physics.Dimension vector
     * @param factor the factor [0, 1] -> [a, b]
     * @return the interpolation point (Physics.Dimension)*/
    public static Dimension interpolate(Dimension a, Dimension b, Dimension factor){
        //factor between 0 and 1
        if (factor.x > 1){factor.x = 1;}else if (factor.x < 0){factor.x = 0;}
        if (factor.y > 1){factor.y = 1;}else if (factor.y < 0){factor.y = 0;}
        if (factor.z > 1){factor.z = 1;}else if (factor.z < 0){factor.z = 0;}
        return a.plus(b.minus(a).multiply(factor));
    }
    /**<p>Return vectors in a certain area between max and min in the array</p>
     * <p>this method use these comparators: {@link Dimension#lesser(Dimension)}, {@link Dimension#greater(Dimension)}, {@link Dimension#greaterEqual(Dimension)},
     * {@link Dimension#greaterEqual(Dimension)}, {@link Dimension#notlesser(Dimension)}, {@link Dimension#notgreater(Dimension)}, {@link Dimension#overlap(Dimension)}</p>
     * <p>use {@link Dimension#trim(Dimension[], Dimension, Dimension, boolean, boolean)} for more functionality</p>
     * @param dimensions array of vectors
     * @param min given lower bound, can be null -> no bound
     * @param max given upper bound, can be null -> no bound
     * @throws IllegalArgumentException min should be lesser than or equal to max
     * @return new array containing vectors in given area (Physics.Dimension[])*/
    public static Dimension[] trim(Dimension[] dimensions, Dimension min, Dimension max) {
        if (min != null && max != null && (min.notlesser(max) || max.notgreater(min))) {
            throw new IllegalArgumentException("Invalid bounds: min should be lesser than or equal to max.");
        }
        //find number
        int counter = 0;
        for (Dimension dim : dimensions) {
            boolean withinMin = (min == null) || dim.greater(min);
            boolean withinMax = (max == null) || dim.lesser(max);
            if (withinMin && withinMax)counter++;
        }
        //form array
        if (counter==0){return new Dimension[0];}
        Dimension[] trimmedArray = new Dimension[counter];
        counter = 0;
        for (Dimension dim : dimensions) {
            boolean withinMin = (min == null) || dim.greater(min);
            boolean withinMax = (max == null) || dim.lesser(max);
            if (withinMin && withinMax){
                trimmedArray[counter] = dim;
                counter++;
            }
        }
        return trimmedArray;
    }
    /**<p>Return vectors in a certain area between max and min in the array</p>
     * <p>this method use these comparators: {@link Dimension#lesser(Dimension)}, {@link Dimension#greater(Dimension)}, {@link Dimension#greaterEqual(Dimension)},
     * {@link Dimension#greaterEqual(Dimension)}, {@link Dimension#notlesser(Dimension)}, {@link Dimension#notgreater(Dimension)}, {@link Dimension#overlap(Dimension)}</p>
     * @param dimensions array of vectors
     * @param min given lower bound, can be null -> no bound
     * @param max given upper bound, can be null -> no bound
     * @param allowEqual true if allow vector to be equal to bounds
     * @param allowPartial true if allow vector to partially fit in range (x or y or z)
     * @throws IllegalArgumentException min should be lesser than or equal to max
     * @return new array containing vectors in given area (Physics.Dimension[])*/
    public static Dimension[] trim(Dimension[] dimensions, Dimension min, Dimension max, boolean allowEqual, boolean allowPartial) {
        if (min != null && max != null && (min.notlesser(max) || max.notgreater(min))) {
            throw new IllegalArgumentException("Invalid bounds: min should be lesser than or equal to max.");
        }
        //find number
        int counter = 0;
        for (Dimension dim : dimensions) {
            boolean withinMin = ((min == null)
                    || (dim.greater(min) || (allowEqual && dim.greaterEqual(min)))
                    || (allowPartial && dim.notlesser(min))
            );
            boolean withinMax = ((max == null)
                    || (dim.lesser(max) || (allowEqual && dim.lesserEqual(max)))
                    || (allowPartial && dim.notgreater(max))
            );
            if (withinMin && withinMax)counter++;
        }
        //form array
        if (counter==0){return new Dimension[0];}
        Dimension[] trimmedArray = new Dimension[counter];
        counter = 0;
        for (Dimension dim : dimensions) {
            boolean withinMin = ((min == null)
                    || (dim.greater(min) || (allowEqual && dim.greaterEqual(min)))
                    || (allowPartial && dim.notlesser(min))
            );
            boolean withinMax = ((max == null)
                    || (dim.lesser(max) || (allowEqual && dim.lesserEqual(max)))
                    || (allowPartial && dim.notgreater(max))
            );
            if (withinMin && withinMax){
                trimmedArray[counter] = dim;
                counter++;
            }
        }
        return trimmedArray;
    }
    //vector math
    /**<p>Dot product with v</p>
     * <a href="https://en.wikipedia.org/wiki/Dot_product">Dot Product</a>
     * @param v another vector
     * @return dot product (Physics.Dimension)*/
    public Dimension dot(Dimension v){
        return new Dimension(x * v.x, y * v.y, z * v.z);
    }
    /**<p>Scalar dot product with v</p>
     * <a href="https://en.wikipedia.org/wiki/Dot_product">Dot Product</a>
     * @param v another vector
     * @return dot product (double)*/
    public double numDot(Dimension v){
        return (x * v.x + y * v.y + z * v.z);
    }
    /**<p>Cross product with v</p>
     * <a href="https://en.wikipedia.org/wiki/Cross_product">Cross Product</a>
     * @param v another vector
     * @return cross product (Physics.Dimension)*/
    public Dimension cross(Dimension v){
        return new Dimension(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x);
    }
    /**<p>Scalar cross product with v</p>
     * <a href="https://en.wikipedia.org/wiki/Cross_product">Cross Product</a>
     * @param v another vector
     * @return cross product (double)*/
    public double numCross(Dimension v){
        return (y * v.z - z * v.y - z * v.x + x * v.z + x * v.y - y * v.x);
    }
    /**<p>This vector's projection on a vector</p>
     * <p>to project on an plane, use {@link Dimension#projectPlane(Dimension)} instead</p>
     * @param v the vector
     * @return projection (Physics.Dimension)*/
    public Dimension projectVector(Dimension v){//projected on vector v
        double k = (v.x*this.x+v.y*this.y+v.z*this.z)/v.sqmag();
        return (v.multiply(k));
    }
    /**<p>This vector's projection on plane perpendicular to the plane-indicating vector</p>
     * <p>to project on an vector, use {@link Dimension#projectVector(Dimension)} instead</p>
     * @param v the plane-indicating vector
     * @return projection (Physics.Dimension)*/
    public Dimension projectPlane(Dimension v){//project on plane perpendicular to vector v
        double k = (v.x*this.x+v.y*this.y+v.z*this.z)/v.sqmag();
        return (this.minus(v.multiply(k)));
    }
    /**<p>Mirror the vector about the normal vector</p>
     * <p>to reflect on an vector, use {@link Dimension#reflect(Dimension)} instead</p>
     * @param v the normal vector
     * @return mirrored result (Physics.Dimension)*/
    public Dimension mirror(Dimension v){
        Dimension normal = v.normalized();
        return normal.multiply(this.numDot(normal)*2).minus(this);
    }
    /**<p>Reflect the vector with the normal vector like a light ray</p>
     * <p>to mirror an vector, use {@link Dimension#mirror(Dimension)} instead</p>
     * @param v the normal vector
     * @return reflection (Physics.Dimension)*/
    public Dimension reflect(Dimension v){
        Dimension normal = v.normalized();
        return this.minus(normal.multiply(this.numDot(normal)*2));
    }
    /**<p>Calculate the angle between two vectors</p>
     * @param a Physics.Dimension vector
     * @param b Physics.Dimension vector
     * @return angle in radians (double)*/
    public static double angleBetween(Dimension a, Dimension b){
        return Math.acos(Dimension.numDot(a, b)/a.abs()/b.abs());
    }
    /**<p>Rotation around an axis</p>
     * @param axis the rotation axis
     * @param rotation rotation in radians
     * @return rotated vector (Physics.Dimension)*/
    public Dimension rotateAround(Dimension axis, double rotation){
        if(axis.isZero())return this;//if the axis is degenerate, do nothing
        Dimension k = axis.unit();
        //formula:v_rot=v*cos(θ)+(k×v)*sin(θ)+k*(k⋅v)*(1−cos(θ))
        return this.multiply(Math.cos(rotation)).plus(k.cross(this).multiply(Math.sin(rotation))).add(k.multiply(this.numDot(k)*(1-Math.cos(rotation))));
    }
    /**<p>The rotation of a vector around an axis</p>
     * <p>see {@link Dimension#rotateAround(Dimension, double)}</p>
     * @param axis the rotation axis
     * @param vector the vector to be rotated
     * @param rotation rotation in radians
     * @return rotated vector (Physics.Dimension)*/
    public static Dimension rotate(Dimension axis, Dimension vector, double rotation){
        if(axis.isZero())return vector;//if the axis is degenerate, do nothing
        Dimension k = axis.unit();
        //formula:v_rot=v*cos(θ)+(k×v)*sin(θ)+k*(k⋅v)*(1−cos(θ))
        return vector.multiply(Math.cos(rotation)).plus(k.cross(vector).multiply(Math.sin(rotation))).add(k.multiply(vector.numDot(k)*(1-Math.cos(rotation))));
    }
    /**<p>Scale along an axis</p>
     * @param axis the axis
     * @param scale scale
     * @return scaled vector (Physics.Dimension)*/
    public Dimension scaleAlong(Dimension axis, double scale){
        if(axis.isZero()){
            //scale for all
            return this.multiply(scale);
        }else {
            Dimension k = axis.unit();
            //formula (s-1)*k*(v⋅k)+v
            return k.multiply((scale-1)*(this.numDot(k))).add(this);
        }
    }
    /**<p>Scale around an axis</p>
     * @param axis the axis
     * @param scale scale
     * @return scaled vector (Physics.Dimension)*/
    public Dimension scaleAround(Dimension axis, double scale){
        if(axis.isZero()){
            //scale for all
            return this.multiply(scale);
        }else {
            Dimension k = axis.unit();
            //formula(1−s)⋅(v⋅k)*k+s⋅v
            return k.multiply((1-scale)*(this.numDot(k))).add(this.multiply(scale));
        }
    }
    /**<p>Scale a vector along an axis</p>
     * <p>see {@link Dimension#scaleAlong(Dimension, double)}</p>
     * @param axis the axis
     * @param vector the vector to be scaled
     * @param scale scale
     * @return scaled vector (Physics.Dimension)*/
    public static Dimension scaleAlong(Dimension axis, Dimension vector, double scale){
        if(axis.isZero()){
            //scale for all
            return vector.multiply(scale);
        }else {
            Dimension k = axis.unit();
            //formula v+(s-1)*(v-k*(v⋅k))
            return k.multiply((scale-1)*(vector.numDot(k))).add(vector);
        }
    }
    /**<p>Scale a vector around an axis</p>
     * <p>see {@link Dimension#scaleAround(Dimension, double)}</p>
     * @param axis the axis
     * @param vector the vector to be scaled
     * @param scale scale
     * @return scaled vector (Physics.Dimension)*/
    public static Dimension scaleAround(Dimension axis, Dimension vector, double scale){
        if(axis.isZero()){
            //scale for all
            return vector.multiply(scale);
        }else {
            Dimension k = axis.unit();
            //formula(1−s)⋅(v⋅k)*k+s⋅v
            return k.multiply((1-scale)*(vector.numDot(k))).add(vector.multiply(scale));
        }
    }

    /**<p>Create plane based on three points, the output will be a vector perpendicular to the plane and of distance of the plane to origin</p>
     * <p>see for perpendicular plane representation {@link Dimension#projectPlane(Dimension)}</p>
     * @param a a 3D point
     * @param b a 3D point
     * @param c a 3D point
     * @return perpendicular vector (Physics.Dimension)*/
    public static Dimension threePointPlane(Dimension a, Dimension b, Dimension c) {
        // Compute vectors AB and AC
        Dimension AB = b.minus(a);
        Dimension AC = c.minus(a);
        // Compute the normal vector (cross product of AB and AC)
        Dimension normal = AB.cross(AC);
        // project any vector on the normal vector
        return a.projectVector(normal);
    }
    /**<p>Scalar triple product of vector a, b, c, also know as the determinant</p>
     * <a href="https://en.wikipedia.org/wiki/Triple_product">Triple Product</a>
     * @param a Physics.Dimension vector
     * @param b Physics.Dimension vector
     * @param c Physics.Dimension vector
     * @return triple product (double)*/
    public static double numTriple(Dimension a, Dimension b, Dimension c){
        return a.cross(b).numDot(c);
    }
    /**<p>Vector triple product of vector a, b, c</p>
     * <a href="https://en.wikipedia.org/wiki/Triple_product">Triple Product</a>
     * <p>To obtain the volume, please use {@link Dimension#numTriple(Dimension, Dimension, Dimension) numTriple}</p>
     * @param a Physics.Dimension vector
     * @param b Physics.Dimension vector
     * @param c Physics.Dimension vector
     * @return triple product (Physics.Dimension)*/
    public static Dimension triple(Dimension a, Dimension b, Dimension c){
        return a.cross(b.cross(c));
    }

    //other
    /**<p>Perspective transform a vector into a 2D vector as viewed from the Z+</p>
     * <p>See {@link Dimension#perspective(double)}</p>
     * @param vector vector to add perspective
     * @return vector viewed from x-y plane (Physics.Dimension, 2D vector)*/
    public static Dimension perspective(Dimension vector, double scale) {
        if (scale == 0)throw new IllegalArgumentException("Scale cannot be zero.");
        // If z is 0, return the original x, y with z = 0 (no perspective transformation)
        if (vector.z == 0) return new Dimension(vector.x, vector.y);
        // Apply perspective transformation
        double factor = 1.0 / (scale * vector.z);
        return new Dimension(vector.x * factor, vector.y * factor); //2d vector
    }
    /**<p>Perspective transform this vector into a 2D vector as viewed from the Z+</p>
     * @return this vector viewed from x-y plane (Physics.Dimension, 2D vector)*/
    public Dimension perspective(double scale) {
        if (scale == 0)throw new IllegalArgumentException("Scale cannot be zero.");
        // If z is 0, return the original x, y with z = 0 (no perspective transformation)
        if (z == 0) return new Dimension(x, y);
        // Apply perspective transformation
        double factor = 1.0 / (scale * z);
        return new Dimension(x * factor, y * factor); //2d vector
    }
}