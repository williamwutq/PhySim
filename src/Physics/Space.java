//class Physics.Space
//William Wu
//Oct 4
package Physics;

/**
 * <h1>3D Spacial Transformation Class Space</h1>
 * represent transformation by defining x, y, z axes, use 3D vectors
 * In packet {@link Physics}
 * <h3>Dependencies: </h3>{@link Dimension Physics.Dimension} 1.4.0 or higher.
 * <h3>Functions: </h3> Mathematical functions, Vector transformation, Physics.Space transformation, comparison, etc.
 * <h3>Applications: </h3> Suitable for physics simulation, vector calculation, or other 3d spacial application
 * <h3>Contains: </h3>Class Space ©2024 William Wu
 * @author William Wu
 * <p>All rights reserved, suggestion welcome</p>
 * @version 1.4.1 @Oct 4, 2024
 * <p>UPGRADES: Inverse functions, bug fixes, change function type to Space</p>
 * @Functions
 * <pre>
 *     Constructor:
 *     {@link Space#Space(Dimension, Dimension, Dimension) Three input}
 *     {@link Space#Space(Dimension, Dimension) Two input (Plane)}
 *     {@link Space#Space(Dimension) One input (x-axis)}
 *     {@link Space#Space(Dimension) No input}
 *     {@link Space#Space(double, double, double) Double input (Scale)}
 *     {@link Space#Space(Dimension, double, Dimension) Rotation and Scaling}
 *     {@link Space#Space(Dimension, double, Dimension) Rotation}
 *
 *     //constants
 *     {@link Space#Cartesian() .Cartesian() -> Space}
 *     {@link Space#rotateX90() .rotateX90() -> Space}
 *     {@link Space#rotateX180() .rotateX90() -> Space}
 *     {@link Space#rotateX270() .rotateX90() -> Space}
 *     {@link Space#rotateY90() .rotateX90() -> Space}
 *     {@link Space#rotateY180() .rotateX90() -> Space}
 *     {@link Space#rotateY270() .rotateX90() -> Space}
 *     {@link Space#rotateZ90() .rotateX90() -> Space}
 *     {@link Space#rotateZ180() .rotateX90() -> Space}
 *     {@link Space#rotateZ270() .rotateX90() -> Space}
 *
 *     //Identity
 *     {@link Space#copy() .copy() -> Space}
 *     {@link Space#face() .face() -> Dimension}
 *     {@link Space#determinant() .determinant() -> double}
 *     {@link Space#access(int, int) .access(int, int) -> double} ***Developer Only***
 *     {@link Space#hashCode() .hashCode() -> int}
 *     {@link Space#equals(Object) .equals(Object) -> boolean}
 *
 *     //Boolean
 *     {@link Space#equals(Object) .equals(Object) -> boolean}
 *     {@link Space#isCartesian() .isCartesian() -> boolean}
 *
 *     //Transformation
 *     {@link Space#transformVector(Dimension) .transformVector(Physics.Dimension) -> Physics.Dimension}
 *     {@link Space#transformSpace(Space) .transformSpace(Physics.Space) -> Physics.Space}
 *     {@link Space#transform(Space) .transform(Physics.Space) -> Physics.Space, modify}
 *     {@link Space#inverse() .inverse() -> Physics.Space}
 *     {@link Space#adjugate() .adjugate() -> Physics.Space}
 *     {@link Space#rotate(Dimension, double) .rotate(Physics.Dimension, double) -> Physics.Space}
 *     {@link Space#scaleAlong(Dimension, double) .scaleAlong(Physics.Dimension, double) -> Physics.Space}
 *     {@link Space#scaleAround(Dimension, double) .scaleAround(Physics.Dimension, double) -> Physics.Space}
 *     {@link Space#scale(double) .scale(double) -> Physics.Space}
 * </pre>*/
public final class Space{
    private Dimension xAxis;
    private Dimension yAxis;
    private Dimension zAxis;

    //constructors
    /**<p>Constructor</p>
     * <p>Three input: Creator a new Physics.Space transformation matrix holding value {x: x, y: y, z: z}</p>
     * @param x input x-axis
     * @param y input y-axis
     * @param z input z-axis
     * @throws IllegalArgumentException when axes are degenerate, parallel, or coplanar
     * @value {x: x, y: y, z: z}*/
    public Space(Dimension x, Dimension y, Dimension z){
        if(x.isZero()||y.isZero()||z.isZero())throw new IllegalArgumentException("axes cannot be zero");
        if(x.isParallel(y)||x.isParallel(z)||z.isParallel(y))throw new IllegalArgumentException("axes cannot be parallel");
        if(Dimension.threePointPlane(x,y,z).mag()==0)throw new IllegalArgumentException("axes cannot be coplanar");
        xAxis = x.copy(); yAxis = y.copy(); zAxis = z.copy();
    }
    /**<p>Constructor</p>
     * <p>Three double input: Creator a new Physics.Space transformation matrix that is capable of scaling {x, y, z}</p>
     * @param x the x-scale component
     * @param y the y-scale component
     * @param z the z-scale component
     * @throws IllegalArgumentException when any scale is zero, which will degenerate the space
     * @value {x: {x: x, y: 0, z: 0}, y: {x: 0, y: y, z: 0}, z: {x: 0, y: 0, z: z}}*/
    public Space(double x, double y, double z){
        if(x == 0||y == 0||z == 0)throw new IllegalArgumentException("axes cannot be zero");
        xAxis = Dimension.IDx().multiply(x);
        yAxis = Dimension.IDy().multiply(y);
        zAxis = Dimension.IDz().multiply(z);
    }
    /**<p>Constructor</p>
     * <p>Two input: Creator a new Physics.Space transformation matrix with defined x and y axes, with perpendicular z calculated to conserve the determinant</p>
     * @param x input x-axis
     * @param y input y-axis
     * @throws IllegalArgumentException when axes are degenerate or parallel
     * @value {x: x, y: y, z: ^x×y}*/
    public Space(Dimension x, Dimension y){
        if(x.isZero()||y.isZero())throw new IllegalArgumentException("axes cannot be zero");
        if(x.isParallel(y))throw new IllegalArgumentException("axes cannot be parallel");
        xAxis = x.copy(); yAxis = y.copy(); zAxis = Dimension.cross(x, y).unit().divideby(xAxis.abs()*yAxis.abs());
    }
    /**<p>Constructor</p>
     * <p>Two input: Creator a new Physics.Space transformation matrix with defined x-axis, with y and z axes following the rotation pattern</p>
     * @param x input x-axis
     * @throws IllegalArgumentException when axis is zero
     * @value {x: x, y: J rotated, z: K rotated}*/
    public Space(Dimension x){
        if(x.isZero())throw new IllegalArgumentException("axis cannot be zero");
        xAxis = x.copy();
        double rotation = Dimension.angleBetween(x, Dimension.IDx());
        // Check if the xAxis is parallel to the original x-axis to avoid unnecessary rotation
        if(rotation == 0.0) {
            //axes will be the same length, rotated
            yAxis = Dimension.IDy().scale(x.abs());
            zAxis = Dimension.IDz().scale(x.abs());
        } else {
            Dimension axis = Dimension.cross(xAxis, Dimension.IDx()).unit();
            //axes will be the same length, rotated
            yAxis = Dimension.IDy().scale(x.abs()).rotateAround(axis, rotation);
            zAxis = Dimension.IDz().scale(x.abs()).rotateAround(axis, rotation);
        }
    }
    /**<p>Constructor</p>
     * <p>Rotation and Expansion: Creator a new Physics.Space transformation matrix based on a rotation pattern and a expansion around axes</p>
     * <p>See {@link Space#rotate(Dimension, double) rotate} and {@link Space#scale(double) scale}</p>
     * @throws IllegalArgumentException when {@code expansion} is set to zero
     * @param axis the rotation axis
     * @param rotation rotation, in radians
     * @param expansion the multiplier for x, y, z axis*/
    public Space(Dimension axis, double rotation, Dimension expansion){
        if (expansion.isZero())throw new IllegalArgumentException("expansion of zero prohibited, as it will make all vectors zero");
        xAxis = Dimension.IDx().rotateAround(axis, rotation).multiply(expansion.numCross(Dimension.IDx()));
        yAxis = Dimension.IDy().rotateAround(axis, rotation).multiply(expansion.numCross(Dimension.IDy()));
        zAxis = Dimension.IDz().rotateAround(axis, rotation).multiply(expansion.numCross(Dimension.IDz()));
    }
    /**<p>Constructor</p>
     * <p>Rotation: Creator a new Physics.Space transformation for a rotation pattern</p>
     * <p>See {@link Space#rotate(Dimension, double) rotate}</p>
     * @param axis the rotation axis
     * @param rotation rotation, in radians*/
    public Space(Dimension axis, double rotation){
        xAxis = Dimension.IDx().rotateAround(axis, rotation);
        yAxis = Dimension.IDy().rotateAround(axis, rotation);
        zAxis = Dimension.IDz().rotateAround(axis, rotation);
    }
    /**<p>Constructor</p>
     * <p>No input: Create the standard {@link Space#Cartesian} space coordinate</p>
     * @value {x: I, y: J, z: K}*/
    public Space(){
        xAxis = Dimension.IDx();
        yAxis = Dimension.IDy();
        zAxis = Dimension.IDz();
    }
    /**<p>The standard Cartesian space coordinate, aka the identity matrix</p>
     * @value (I, J, K)
     * @return Cartesian (Physics.Space)*/
    public static Space Cartesian(){
        return new Space();
    }
    /**<p>a Physics.Space matrix enable rotation around the X axis for 90 degrees</p>
     * @value (I, -K, J)
     * @return rotation matrix (Physics.Space)*/
    public static Space rotateX90() {
        return new Space(Dimension.IDx(), Dimension.IDz().not(), Dimension.IDy());
    }
    /**<p>a Physics.Space matrix enable rotation around the X axis for 180 degrees</p>
     * @value (I, -J, -K)
     * @return rotation matrix (Physics.Space)*/
    public static Space rotateX180() {
        return new Space(Dimension.IDx(), Dimension.IDy().not(), Dimension.IDz().not());
    }
    /**<p>a Physics.Space matrix enable rotation around the X axis for 270 degrees</p>
     * @value (I, K, -J)
     * @return rotation matrix (Physics.Space)*/
    public static Space rotateX270() {
        return new Space(Dimension.IDx(), Dimension.IDz(), Dimension.IDy().not());
    }
    /**<p>a Physics.Space matrix enable rotation around the Y axis for 90 degrees</p>
     * @value (K, J, -I)
     * @return rotation matrix (Physics.Space)*/
    public static Space rotateY90() {
        return new Space(Dimension.IDz(), Dimension.IDy(), Dimension.IDx().not());
    }
    /**<p>a Physics.Space matrix enable rotation around the Y axis for 180 degrees</p>
     * @value (-I, J, -K)
     * @return rotation matrix (Physics.Space)*/
    public static Space rotateY180() {
        return new Space(Dimension.IDx().not(), Dimension.IDy(), Dimension.IDz().not());
    }
    /**<p>a Physics.Space matrix enable rotation around the Y axis for 270 degrees</p>
     * @value (-K, J, I)
     * @return rotation matrix (Physics.Space)*/
    public static Space rotateY270() {
        return new Space(Dimension.IDz().not(), Dimension.IDy(), Dimension.IDx());
    }
    /**<p>a Physics.Space matrix enable rotation around the Z axis for 90 degrees</p>
     * @value (-J, I, K)
     * @return rotation matrix (Physics.Space)*/
    public static Space rotateZ90() {
        return new Space(Dimension.IDy().not(), Dimension.IDx(), Dimension.IDz());
    }
    /**<p>a Physics.Space matrix enable rotation around the Z axis for 180 degrees</p>
     * @value (-I, -J, K)
     * @return rotation matrix (Physics.Space)*/
    public static Space rotateZ180() {
        return new Space(Dimension.IDx().not(), Dimension.IDy().not(), Dimension.IDz());
    }
    /**<p>a Physics.Space matrix enable rotation around the Z axis for 270 degrees</p>
     * @value (J, -I, K)
     * @return rotation matrix (Physics.Space)*/
    public static Space rotateZ270() {
        return new Space(Dimension.IDy(), Dimension.IDx().not(), Dimension.IDz());
    }

    /**
     * <p>Accesses key values in this transformation matrix based on the given axis and value.</p>
     * <p>Developer Only, marked @Deprecated</p>
     * If the axis value is:
     * <ul>
     *   <li>0: Returns the determinant of the transformation matrix.</li>
     *   <li>1: Accesses the value of the x-axis vector using the provided index.</li>
     *   <li>2: Accesses the value of the y-axis vector using the provided index.</li>
     *   <li>3: Accesses the value of the z-axis vector using the provided index.</li>
     *   <li>4 or Other: Returns the magnitude of the vector from the origin to the plane formed by the x, y, z axes.</li>
     * </ul>
     * @param axis  The axis value to determine which vector (x, y, z) or determinant to access.
     *              <pre>***Must be 0, 1, 2, 3, or 4 for valid access***</pre>
     * @param value The index to specify which component (x, y, z) of the vector to access, if applicable.
     *              <pre>***Must be 0, 1, 2, or 3 for valid access, or another value for magnitude squared***</pre>
     * @return      The value corresponding to the axis and value combination.
     */
    @Deprecated
    public double access(int axis, int value){
        return switch (Math.abs(axis)){
            case 0 -> determinant();
            case 1 -> getValue(xAxis, value);
            case 2 -> getValue(yAxis, value);
            case 3 -> getValue(zAxis, value);
            default -> this.face().magnitude();
        };
    }
    /**Return an identical copy of the transformation space. This will be a different object.
     <p>See {@link Dimension#copy()}, which implement the copying of axes</p>
     @return copy (Physics.Space)*/
    public Space copy(){
        return new Space(this.xAxis.copy(), this.yAxis.copy(), this.zAxis.copy());
    }
    /**<p>The face value for this space, a vector of distance to the plane of the three axes of this space</p>
     * See {@link Space#equals(Object)} and {@link Dimension#threePointPlane(Dimension, Dimension, Dimension) threePointPlane} in {@link Dimension} class
     * @return the face value (Physics.Dimension)*/
    public Dimension face(){
        //this is unique for the transformation space
        //for Cartesian, the value is Physics.Dimension(1/3,1/3,1/3) |vector| = √3/3
        return Dimension.threePointPlane(xAxis, yAxis, zAxis);
    }
    /**<p>The determinant for this space, cannot be zero</p>
     * see <a href="https://en.wikipedia.org/wiki/Determinant">determinant in matrix</a>
     * @return the determinant (double)*/
    public double determinant(){
        return xAxis.cross(yAxis).numDot(zAxis);
    }
    /**<p>The hashcode for this space</p>
     * See {@link Space#equals(Object)} and {@link Space#face()}; this is able to compare with {@link Dimension} objects
     * @return the hashcode representation of the face value of the Physics.Space*/
    @Override
    public int hashCode() {
        return this.face().hashCode();
    }
    /**<p>Test if this space equals to another object</p>
     * See {@link Space#hashCode()}
     * @return true if equals this Physics.Space or the face value of this Physics.Space*/
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || (Space.class != obj.getClass() && Dimension.class != obj.getClass())) return false;
        if(Dimension.class == obj.getClass()){
            return ((Dimension)obj).equal(this.face());
        }
        Space space = (Space) obj;
        return xAxis.equals(space.xAxis) && yAxis.equals(space.yAxis) && zAxis.equals(space.zAxis);
    }
    /**Return a string representation
     * @return String representation of this transformation space*/
    @Override
    public String toString(){
        return "{x: "+this.xAxis.toString() + ", y: " +  this.yAxis.toString() +", z: "+ this.zAxis.toString() + '}';
    }

    /**<p>Test if this space is the standard Cartesian coordinates</p>
     * See {@link Space#Cartesian()} for Cartesian
     * @return true if is Cartesian*/
    public boolean isCartesian(){
        return xAxis.equal(Dimension.IDx())&&yAxis.equal(Dimension.IDy())&&zAxis.equal(Dimension.IDz());
    }

    /**<p>Transform a vector using this space transformation</p>
     * See {@link Dimension 3D Vector Physics.Dimension Class} for vector methods
     * @param vector vector to transform, will NOT be modified
     * @return the transformed vector (Physics.Space)*/
    public Dimension transformVector(Dimension vector){
        Dimension x = xAxis.multiply(vector.numDot(Dimension.IDx()));
        Dimension y = yAxis.multiply(vector.numDot(Dimension.IDy()));
        Dimension z = zAxis.multiply(vector.numDot(Dimension.IDz()));
        return Dimension.plus(x,y).add(z);
    }
    /**<p>Transform another space</p>
     * <pre>
     * This method use {@link Space#transformVector(Dimension) vector transformation} in the {@link Space this} class
     * See {@link Space#transform(Space) transform} to transform this space
     * </pre>
     * @param space Physics.Space to be transformed
     * @return the transformed space (Physics.Space)*/
    public Space transformSpace(Space space){
        return new Space(transformVector(space.xAxis), transformVector(space.yAxis), transformVector(space.zAxis));
    }
    /**<p>Transform this space with a transform Physics.Space</p>
     * <pre>
     * This method use {@link Space#transformVector(Dimension) vector transformation} in the {@link Space this} class
     * See {@link Space#transformSpace(Space) transformSpace} to use this space to transform another space
     * </pre>
     * @param transMatrix the transform space the transformation is based on
     * @return the transformed space (Physics.Space)*/
    public Space transform(Space transMatrix){
        xAxis = transMatrix.transformVector(xAxis);
        yAxis = transMatrix.transformVector(yAxis);
        zAxis = transMatrix.transformVector(zAxis);
        return this;
    }
    /**<p>The inverse of the transformation that will transform a transformed vector to its original state</p>
     * <pre>
     * This method use {@link Dimension#cross(Dimension) the vector cross product} in the {@link Dimension} class
     * See {@link Space#adjugate() adjugate} and <a href="https://en.wikipedia.org/wiki/Invertible_matrix">Invertible matrix and inverse matrix</a>
     * </pre>
     * @return the inverse of this space transformation (Physics.Space)*/
    public Space inverse() {
        return this.adjugate().scale(this.determinant());
    }
    /**<p>The adjugate of the transformation</p>
     * <pre>
     * This method use {@link Dimension#cross(Dimension) the vector cross product} in the {@link Dimension} class
     * <a href="https://en.wikipedia.org/wiki/Adjugate_matrix">Adjugate of a transformation matrix</a>
     * </pre>
     * @return the adjugate of this space transformation (Physics.Space)*/
    public Space adjugate(){
        return new Space(
            yAxis.cross(zAxis),
            zAxis.cross(xAxis),
            xAxis.cross(yAxis)
        );
    }
    /**<p>Rotate the dimension object along an axis</p>
     * <p>This method use {@link Dimension#rotateAround(Dimension, double) rotateAround} in the {@link Dimension} class</p>
     * @param axis the axis  Zero Vector no permitted
     * @param rotation rotation in radians
     * @throws IllegalArgumentException Zero Vector no permitted for rotation
     * @return rotated space transformation (Physics.Space)*/
    public Space rotate(Dimension axis, double rotation){
        if(axis.isZero())throw new IllegalArgumentException("zero vector prohibited in rotation");
        return new Space(
            xAxis = xAxis.rotateAround(axis, rotation),
            yAxis = yAxis.rotateAround(axis, rotation),
            zAxis = zAxis.rotateAround(axis, rotation)
        );
    }
    /**<p>Scale the space around an axis by a ratio</p>
     * <pre>
     * Only component perpendicular to the centerAxis will be scaled
     * This method use {@link Dimension#multiplyby(double) multiplyby} in the {@link Dimension} class
     * See {@link Space#scale(double) scale} for scaling in all directions
     * </pre>
     * @param centerAxis the axis  Zero Vector -> scale in all direction
     * @param ratio ratio of scaling
     * @return scaled space transformation (Physics.Space)*/
    public Space scaleAround(Dimension centerAxis, double ratio){
        if (ratio == 0)throw new IllegalArgumentException("ratio of zero prohibited, as it will make all vectors zero");
        if(centerAxis.isZero()) {
            return scale(ratio);
        }else{
            return new Space(
                xAxis.scaleAround(centerAxis, ratio),
                yAxis.scaleAround(centerAxis, ratio),
                zAxis.scaleAround(centerAxis, ratio)
            );
        }
    }
    /**<p>Scale the space along an axis by a ratio</p>
     * <pre>
     * Only component parallel to the centerAxis will be scaled
     * This method use {@link Dimension#multiplyby(double) multiplyby} in the {@link Dimension} class
     * See {@link Space#scale(double) scale} for scaling in all direction
     * </pre>
     * @param centerAxis the axis  Zero Vector -> scale in all direction
     * @param ratio ratio of scaling
     * @return scaled space transformation (Physics.Space)*/
    public Space scaleAlong(Dimension centerAxis, double ratio){
        if (ratio == 0)throw new IllegalArgumentException("ratio of zero prohibited, as it will make all vectors zero");
        if(centerAxis.isZero()) {
            return scale(ratio);
        }else{
            return new Space(
                xAxis.scaleAlong(centerAxis, ratio),
                yAxis.scaleAlong(centerAxis, ratio),
                zAxis.scaleAlong(centerAxis, ratio)
            );
        }
    }
    /**<p>Scale the entire Physics.Space by a ratio</p>
     * <pre>This method use {@link Dimension#multiplyby(double) multiplyby method} in the Physics.Dimension class</pre>
     * @param ratio ratio of scaling
     * @return scaled space transformation (Physics.Space)*/
    public Space scale(double ratio){
        return new Space(
            xAxis.multiplyby(ratio),
            yAxis.multiplyby(ratio),
            zAxis.multiplyby(ratio)
        );
    }
    //private
    private double getValue(Dimension target, int value){
        return switch (Math.abs(value)){
            case 1 -> target.numDot(Dimension.IDx());
            case 2 -> target.numDot(Dimension.IDy());
            case 3 -> target.numDot(Dimension.IDz());
            default -> target.numDot(Dimension.ID());
        };
    }
}