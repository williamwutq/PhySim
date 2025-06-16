//class Physics.Phybody
//William Wu
//Oct 10
package Physics;

/**
 * <h1>Body Class Phybody</h1>
 * <p>Mass, Position, Velocity, Acceleration</p>
 * <p>Floating points calculation & storage</p>
 * <p>Gravity Simulation</p>
 * <p>Specialized bodies: {@link Probebody Physics.Probebody} and {@link Fixedbody Physics.Fixedbody}</p>
 * <h3>Functions: </h3> get and set functions, Physics simulations
 * <h3>Applications: </h3> Gravity & Electric Field Simulation, Points representation
 * <h3>Contains: </h3> Class Physics.Phybody, Class Physics.Probebody, Class Physics.Fixedbody ©William Wu
 * <h3>Construction: </h3> Assumption: mass = 0; pos = 1; vel = 0; time = 0.1;
 * @author William Wu
 * <pre>
 *     All rights reserved, suggestion welcome
 * </pre>
 * @version 1.4.4 @Oct 31, 2024
 * <p>UPGRADES: methods incorporation in subclasses, added bodyCluster</p>
 * @Functions
 * <pre>
 * public:
 *    {@link Phybody#time .time static double}
 *    {@link Phybody#testBodyArray() .testBodyArray() static -> Physics.Phybody[]}
 *
 *    {@link Phybody#copy() .copy() -> Phybody}
 *    {@link Phybody#initialized() .initialized() -> boolean}
 *    {@link Phybody#getTime() .getTime() -> double}
 *    {@link Phybody#getmPos() .getmPos() -> double}
 *    {@link Phybody#getPrevPos() .getPrevPos() -> double}
 *    {@link Phybody#getmVel() .getmVel() -> double}
 *    {@link Phybody#getmAcc() .getmAcc() -> double}
 *    {@link Phybody#getMass() .getMass() -> long}
 *    {@link Phybody#getPos() .getPos() -> Dimension}
 *    {@link Phybody#getPrevPos() .getPrevPos() -> Dimension}
 *    {@link Phybody#getVel() .getVel() -> Dimension}
 *    {@link Phybody#getAcc() .getAcc() -> Dimension}
 *    {@link Phybody#setTime(double) .setTime(double) -> modify}
 *    {@link Phybody#setMass(long) .setMass(long) -> modify}
 *    {@link Phybody#setPos(Dimension) .setPos(Dimension) -> modify}
 *    {@link Phybody#setPrevPos(Dimension) .setPrevPos(Dimension) -> modify}
 *    {@link Phybody#setVel(Dimension) .setVel(Dimension) -> modify}
 *    {@link Phybody#setVel(double) .setVel(double) -> modify}
 *    {@link Phybody#setAcc(Dimension) .setAcc(Dimension) -> modify}
 *    {@link Phybody#addVel(Dimension) .addVel(Dimension) -> modify}
 *    {@link Phybody#addAcc(Dimension) .addAcc(Dimension) -> modify}
 *    {@link Phybody#addF(Dimension) .addF(Dimension) -> modify}
 *
 *    {@link Phybody#centerData(Phybody[], Phybody) .centerData(Phybody[], Phybody) -> modify}
 *    {@link Phybody#getMass(Phybody[]) .getMass(Phybody[]) -> long}
 *    {@link Phybody#getCenter(Phybody[]) .getCenter(Phybody[]) -> Phybody}
 *    {@link Phybody#getMax(Phybody[]) .getMax(Phybody[]) -> Dimension}
 *    {@link Phybody#getMin(Phybody[]) .getMin(Phybody[]) -> Dimension}
 *    {@link Phybody#findMostDense(Phybody[]) .findMostDense(Physics.Phybody[]) -> Dimension}
 *    {@link Phybody#getEnergy(Phybody[], Dimension, double) .getEnergy(Phybody[], Dimension, double) -> double}
 *    {@link Phybody#avgMinDistance(Phybody[]) .avgMinDistance(Phybody[]) -> double}
 *    {@link Phybody#init(Phybody[], double, boolean) .init(Phybody[], double, boolean) -> modify}
 *
 *    {@link Phybody#init() .init() -> modify}
 *    {@link Phybody#kineticEnergy(Dimension) .kineticEnergy(Dimension) -> double}
 *    {@link Phybody#potentialEnergy(Phybody, double) .potentialEnergy(Phybody, double) -> double}
 *    {@link Phybody#applyGravity(Phybody, double) .applyGravity(Phybody, double) -> modify}
 *    {@link Phybody#update() .update() -> modify}
 * </pre>
*/
public class Phybody {
    /**time interval used in simulation
     * @value 0.1 (default)
     * <p>change value with {@link Phybody#setTime(double)}</p>*/
    public static double time = 0.1;
    private Dimension pos;
    private Dimension prevPos;
    private Dimension acc;
    private long mass;
    private boolean initialized = false;

    //constructor
    /**<p>Constructor</p>
     * <p>Full: mass + position + velocity</p>
     * @param body_mass mass of the body
     * @param body_pos position of the body
     * @param body_vel velocity of the body*/
    public Phybody(long body_mass, Dimension body_pos, Dimension body_vel){
        mass = body_mass;
        pos = body_pos.copy();
        prevPos = body_pos.minus(body_vel.multiply(time));
        acc = Dimension.zero();
    }
    /**<p>Constructor</p>
     * <p>No mass: 0 + position + velocity</p>
     * <p>See {@link Phybody#Phybody(long, Dimension, Dimension) Full Constructor}</p>
     * <b>Warning: will not interact with field</b>, to declare a probe, please use {@link Physics.Probebody}
     * @param body_pos position of the body
     * @param body_vel velocity of the body*/
    public Phybody(Dimension body_pos, Dimension body_vel){
        mass = 0;
        pos = body_pos.copy();
        prevPos = body_pos.minus(body_vel.multiply(time));
        acc = Dimension.zero();
    }
    /**<p>Constructor</p>
     * <p>Zero Velocity: mass + position + 0</p>
     * <p>See {@link Phybody#Phybody(long, Dimension, Dimension) Full Constructor}</p>
     * @param body_mass mass of the body
     * @param body_pos position of the body*/
    public Phybody(long body_mass, Dimension body_pos){
        mass = body_mass;
        pos = body_pos.copy();
        prevPos = body_pos.copy();
        acc = Dimension.zero();
    }
    /**<p>Constructor</p>
     * <p>Position: 0 + position + 0</p>
     * <p>See {@link Phybody#Phybody(long, Dimension, Dimension) Full Constructor}</p>
     * <b>Warning: will not interact with field</b>, to declare a probe, please use {@link Physics.Probebody}
     * @param body_pos position of the body*/
    public Phybody(Dimension body_pos){
        mass = 0;
        pos = body_pos.copy();
        prevPos = body_pos.copy();
        acc = Dimension.zero();
    }
    /**<p>Constructor</p>
     * <p>Mass: mass + 1 + 0</p>
     * <p>See {@link Phybody#Phybody(long, Dimension, Dimension) Full Constructor}</p>
     * <b>Warning: overlapping potential!</b>, use {@link Physics.Phybody#Phybody(long, Dimension) Zero Velocity constructor} to mitigate
     * @param body_mass mass of body*/
    public Phybody(long body_mass){
        mass = body_mass;
        pos = new Dimension(1);
        prevPos = new Dimension(1);
        acc = Dimension.zero();
    }
    /**<p>Constructor</p>
     * <p>No Input</p>
     * <p>See {@link Phybody#Phybody(long, Dimension, Dimension) Full Constructor}</p>
     * <b>Warning: will not interact with field</b>, to declare a probe, please use {@link Physics.Probebody};
     * <b>Warning: overlapping potential!</b>, use {@link Physics.Phybody#Phybody(long, Dimension) Zero Velocity constructor} to mitigate
     * @value 0 + (1,1,1) + 0
     * */
    public Phybody(){
        mass = 0;
        pos = new Dimension(1);
        prevPos = new Dimension(1);
        acc = Dimension.zero();
    }
    /**<p>Constructor</p>
     * <p>Auto Type & Position Based</p>
     * <p>See {@link Phybody#Phybody(long, Dimension, Dimension) Phybody Constructor},
     * {@link Fixedbody#Fixedbody(long, Dimension, Dimension) Fixedbody Constructor},
     * {@link Probebody#Probebody(Dimension, Dimension) Probebody Constructor}</p>
     * <b>Warning: not initialized!</b>, use {@link Phybody#init()} to initialize.
     * @param fixed whether this is a fixed body
     * @param mass the mass of the body, mass of 0 will automatically create a {@link Probebody probe}
     * @param position the current position of this body
     * @param prevPosition the previous position of this body
     * @return created body ({@link Phybody})
     * */
    public static final Phybody create(long mass, Dimension position, Dimension prevPosition, boolean fixed){
        if (mass == 0){
            //declare a Probebody
            Probebody body =  new Probebody(position);
            body.setPrevPos(prevPosition);
            return body;
        }else if(fixed){
            //declare a Fixedbody
            Fixedbody body =  new Fixedbody(mass, position);
            body.setPrevPos(prevPosition);
            return body;
        }else{
            //declare a normal body
            Phybody body = new Phybody(mass, position);
            body.setPrevPos(prevPosition);
            return body;
        }
    }

    //identity
    /**Make a numerical copy of this Phybody object*/
    public Phybody copy() {
        return new Phybody(this.getMass(), this.getPos(), this.getVel());
    }
    /**<p>Whether this body has been initialized and can be used in simulation</p>
     * <b>Always initialize with simulated acceleration and {@link Phybody#init()} before simulation</b>
     * @return true if this Phybody object is initialized*/
    public boolean initialized(){
        return initialized;
    }
    /**<p>Whether this body equals another object</p>
     * <p>See {@link Phybody#hashCode()}</p>
     * @return true if exact match*/
    @Override
    public final boolean equals(Object obj) {
        if (this.getPos().equals(obj)){
            return true;
        }else if (obj instanceof Phybody){
            return (((Phybody) obj).getPos().equal(this.getPos())&&((Phybody) obj).getPrevPos().equal(this.getPrevPos())&&
                    ((Phybody) obj).getAcc().equal(this.getAcc())&&((Phybody) obj).getMass() == this.getMass())&&
                    ((Phybody) obj).initialized() == this.initialized();
        }else return false;
    }
    /**<p>Return the hashcode of the body</p>
     * <p>See {@link Phybody#equals(Object)}</p>
     * @return the hashcode (int)*/
    @Override
    public final int hashCode() {
        int result = 23; // A non-zero constant to start with

        result = 31 * result + (this.getPos() != null ? this.getPos().hashCode() : 0);
        result = 31 * result + (this.getAcc() != null ? this.getAcc().hashCode() : 0);
        result = 29 * result + Long.hashCode(this.getMass());
        result = 29 * result + Boolean.hashCode(this.initialized());

        return result;
    }
    /**<p>Return the int identifier of the object, which is the magnitude of the position</p>
     * <p>Use {@link Phybody#hashCode()} for exact match</p>
     * @return identifier (int)*/
    public final int toInt(){
        return (int)this.getPos().mag();
    }
    /**<p>Return the double identifier of the object, which is the magnitude of the position</p>
     * <p>Use {@link Phybody#hashCode()} for exact match</p>
     * @return identifier (double)*/
    public final double toDouble(){
        return (int)this.getPos().mag();
    }

    //String
    /**<p>A string representation, including mass, position, velocity, acceleration</p>
     * @return String representation (String)*/
    @Override
    public String toString(){
        return "{mass: " + this.getMass() + ", pos: " + this.getPos().toString() + ", prev: " + this.getPrevPos().toString() + ", vel: " + this.getVel().toString() + ", acc: " + this.getAcc().toString() + '}';
    }
    /**<p>A string representation, including (mass), position, velocity, acceleration</p>
     * <p>Compatible with older versions and C++ versions</p>
     * <p>Please use {@link Phybody#toString()} for modern representation</p>
     * @param printMass whether the mass value should be included
     * @return String representation (String)*/
    public String print(boolean printMass){
        if (printMass){return this.getMass() + " | " + this.getPos().toString() + " | " + this.getVel().toString() + ';';}
        else return this.getPos().toString() + " | " + this.getVel().toString() + ';';
    }

    //get methods
    /**<p>Return the {@link Phybody#time}</p>
     * @return time*/
    public double getTime(){
        return time;
    }
    /**<p>Return the {@link Dimension#magnitude() magnitude} of the position of this body</p>
     * @return magnitude(double)*/
    public double getmPos(){//get the position
        return this.getPos().magnitude();
    }
    /**<p>Return the {@link Dimension#magnitude() magnitude} of the previous position of this body</p>
     * @return magnitude(double)*/
    public double getmPrevPos(){
        return this.getPrevPos().magnitude();
    }
    /**<p>Return the {@link Dimension#magnitude() magnitude} of the velocity of this body</p>
     * @return magnitude(double)*/
    public double getmVel(){//get the velocity
        return this.getPos().minus(this.getPrevPos()).magnitude()/(2*time);
    }
    /**<p>Return the {@link Dimension#magnitude() magnitude} of the acceleration of this body</p>
     * @return magnitude(double)*/
    public double getmAcc(){//get the acceleration
        return this.getAcc().magnitude();
    }
    /**<p>Return the mass of this body</p>
     * @return mass(long)*/
    public long getMass(){//get the mass
        return mass;
    }
    /**<p>Previous position of this body relative to a reference coordinate</p>
     * @param posReference reference position
     * @return previous position ({@link Physics.Dimension})*/
    public Dimension getPrevPos(Dimension posReference){
        return this.getPrevPos().minus(posReference);
    }
    /**<p>Current position of this body relative to a reference coordinate</p>
     * @param posReference reference position
     * @return position ({@link Physics.Dimension})*/
    public Dimension getPos(Dimension posReference){//get the position relative to a reference
        return this.getPos().minus(posReference);
    }
    /**<p>Velocity of this body relative to a reference velocity</p>
     * @param velReference reference velocity
     * @return velocity ({@link Physics.Dimension})*/
    public Dimension getVel(Dimension velReference){//get the velocity relative to a reference
        return this.getPos().minus(this.getPrevPos()).divide(2*time).minus(velReference);
    }
    /**<p>Velocity of this body relative to a reference acceleration</p>
     * @param accReference reference velocity
     * @return acceleration ({@link Physics.Dimension})*/
    public Dimension getAcc(Dimension accReference){//get the acceleration relative to a reference (use if exterior force is applied)
        return this.getAcc().minus(accReference);
    }
    /**<p>Previous position of this body</p>
     * @return previous position ({@link Physics.Dimension})*/
    public Dimension getPrevPos(){
        return prevPos.copy();
    }
    /**<p>Current position of this body</p>
     * @return position ({@link Physics.Dimension})*/
    public Dimension getPos(){//get the position relative to a reference
        return pos.copy();
    }
    /**<p>Velocity of this body</p>
     * @return velocity ({@link Physics.Dimension})*/
    public Dimension getVel(){//get the velocity relative to a reference
        return this.getPos().minus(this.getPrevPos()).divide(2*time);
    }
    /**<p>Acceleration of this body</p>
     * @return acceleration ({@link Physics.Dimension})*/
    public Dimension getAcc(){//get the acceleration relative to a reference (use if exterior force is applied)
        return acc.copy();
    }

    //set methods
    /**<p>Set the {@link Phybody#time} across <b>All</b> simulations</p>
     * <b>Warning: time will be set for all simulations</b>*/
    public static void setTime(double time){
        Phybody.time = time;
    }
    /**<p>Set the initialization state of this body to true</p>
     * <b>Warning: Use NOT permitted</b>*/
    protected void setInitialized(){
        //be careful when use
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (!stackTrace[2].getClassName().equals("Physics.Phybody")||!stackTrace[2].getMethodName().equals("init")){throw new IllegalCallerException("only .init() function in this class can modify initialized");}
        initialized = true;
    }
    /**<p>Set the mass this body</p>
     * @param body_mass new mass of the body*/
    public void setMass(long body_mass){//set the mass
        mass = body_mass;
    }
    /**<p>Set the position of this body</p>
     * @param new_pos new position of the body*/
    public void setPos(Dimension new_pos){//set the position
        pos = new_pos.copy();
    }
    /**<p>Set the previous position of this body</p>
     * @param new_prevPos new position of the body*/
    public void setPrevPos(Dimension new_prevPos){
        prevPos = new_prevPos.copy();
    }

    /**<p>Scale the velocity of this body</p>
     * <b>Warning: this method modify the prevPos variable, AND velocity</b>
     * @param scalar the scalar of scaling, preferably not 0*/
    public void setVel(double scalar){
        prevPos = prevPos.multiply(scalar).plus(pos.multiply(1-scalar));
    }
    /**<p>Set the velocity of this body</p>
     * <b>Warning: this method modify the prevPos variable, AND velocity</b>
     * @param new_vel the new velocity*/
    public void setVel(Dimension new_vel){//set the velocity
        this.setPrevPos(this.getPos().minus(new_vel.multiply(time)).minus(this.getAcc().multiply(time*time/2)));
    }
    /**<p>Set the acceleration of this body</p>
     * @param new_acc the new acceleration*/
    public void setAcc(Dimension new_acc){//set the acceleration
        acc = new_acc.copy();
    }
    /**<p>Add a velocity to the velocity of this body</p>
     * <b>Warning: this method modify the prevPos variable, AND velocity</b>
     * @param add_vel added velocity*/
    public void addVel(Dimension add_vel){//add a velocity vector
        this.setPrevPos(this.getPrevPos().minus(add_vel.multiply(time)));
    }
    /**<p>Add an acceleration to this body</p>
     * @param add_acc added acceleration*/
    public void addAcc(Dimension add_acc){//add an acceleration vector
        this.setAcc(this.getAcc().plus(add_acc));
    }
    /**<p>Add a force to this body if the mass is <b>not zero</b></p>
     * <p>See {@link Phybody#addAcc(Dimension)}</p>
     * @param add_F added force*/
    public void addF(Dimension add_F){//add an external force
        if (mass != 0){this.setAcc(this.getAcc().plus(add_F.divide(mass)));}
    }

    //center methods
    /**Centers data, which are the positions, previous positions, and velocities of all bodies in the array relative to the provided center body.
     * <p>This method adjusts the position, previous position, and velocity of each body in the array
     * relative to the reference positions and velocities of the provided center body.
     * The positions are recalculated using the reference to the center, effectively centering the system. This can improve accuracy of simulation</p>
     * <p>Use {@link Phybody#getCenter(Phybody[])} to find center of the array</p>
     * @param bodyArray The array of bodies
     * @param center The reference {@link Phybody}*/
    public static void centerData(Phybody[] bodyArray, Phybody center){
        //center the data
        for (Phybody phybody : bodyArray){
            phybody.setPos(phybody.getPos().referto(center.getPos()));
            phybody.setPrevPos(phybody.getPrevPos().referto(center.getPrevPos()));
            phybody.setVel(phybody.getVel().referto(center.getVel()));
        }
    }
    /**Calculates and returns the barycenter (center of mass) of an array of bodies.
     * <p>This method calculates the barycenter position and velocity of the system based on
     * the total mass and mass-weighted positions and velocities of the bodies in the array.
     * If the array is empty, an {@link IllegalArgumentException} is thrown.</p>
     * @param bodyArray the array of bodies
     * @return A {@link Phybody body} representing the barycenter with the calculated position and velocity.
     * @throws IllegalArgumentException when the array length is 0.
     * @see #centerData
     */
    public static Phybody getCenter(Phybody[] bodyArray){
        if (bodyArray.length == 0){throw new IllegalArgumentException("Array length 0 prohibited");}
        else if (bodyArray.length == 1){return bodyArray[0];}
        //get total mass
        double ttl_mass = getMass(bodyArray);

        //get the barycenter position and velocity
        Dimension dim_center = new Dimension();
        Dimension dim_centerVel = new Dimension();
        for (Phybody phybody : bodyArray) {//calculate the barycenter
            if(phybody.getMass()==0)continue;
            dim_center.addby(phybody.getPos().multiply((double)phybody.getMass() / ttl_mass));
            dim_centerVel.addby(phybody.getVel().multiply((double)phybody.getMass() / ttl_mass));
        }

        //return Barycenter
        return new Phybody((long)ttl_mass, dim_center, dim_centerVel);
    }
    /**Calculate the total mass of the body array
     * @param bodyArray the array of bodies
     * @return the total mass (long)
     * @throws IllegalArgumentException as length of array cannot be 0*/
    public static long getMass(Phybody[] bodyArray){
        if (bodyArray.length == 0){throw new IllegalArgumentException("Array length 0 prohibited");}
        else if (bodyArray.length == 1){return bodyArray[0].getMass();}
        //get total mass
        long ttl_mass = 0;
        for (Phybody phybody : bodyArray) {
            ttl_mass += phybody.getMass();
        }
        return ttl_mass;
    }
    /**Calculate the (weighted) density of an array of bodies
     * <p>This method calculate the density of an array of bodies of the array of bodies by dividing the number of
     * bodies by the space occupied, or the weighted density by dividing the {@link Phybody#getMass(Phybody[]) total mass} of this array by the space occupied</p>
     * @param bodyArray the array of bodies
     * @param space the space occupied by the array of bodies
     * @param weighted whether the density should be weighed
     * @return density (double)
     * @throws IllegalArgumentException as length of array cannot be 0*/
    public static double getDensity(Phybody[] bodyArray, Dimension space, boolean weighted){
        if (weighted){return getMass(bodyArray)/space.volume();}
        else return bodyArray.length/space.volume();
    }
    /**Calculate the minimum coordinate of the body array
     * <p>This method calculate the smallest x, y, z coordinates in the array of bodies, which is useful
     * to determine the bounds or the space the array of bodies occupies</p>
     * <p>Use {@link Phybody#getMax(Phybody[])} to get maximum</p>
     * @param bodyArray the array of bodies
     * @return the minimum coordinate ({@link Dimension})
     * @throws IllegalArgumentException as length of array cannot be 0*/
    public static Dimension getMin(Phybody[] bodyArray){
        if (bodyArray.length == 0){throw new IllegalArgumentException("Array length 0 prohibited");}
        else if (bodyArray.length == 1){return bodyArray[0].getPos();}
        else if (bodyArray.length == 2){return Dimension.min(bodyArray[0].getPos(), bodyArray[1].getPos());}
        Dimension min = bodyArray[0].getPos();
        for (Phybody phybody : bodyArray){
            if(phybody.getClass() == Probebody.class)continue;
            min = Dimension.min(min, phybody.getPos());
        }
        return min;
    }
    /**Calculate the maximum coordinate of the body array
     * <p>This method calculate the greatest x, y, z coordinates in the array of bodies, which is useful
     * to determine the bounds or the space the array of bodies occupies</p>
     * <p>Use {@link Phybody#getMin(Phybody[])} to get minimum</p>
     * @param bodyArray the array of bodies
     * @return the maximum coordinate ({@link Dimension})
     * @throws IllegalArgumentException as length of array cannot be 0*/
    public static Dimension getMax(Phybody[] bodyArray){
        if (bodyArray.length == 0){throw new IllegalArgumentException("Array length 0 prohibited");}
        else if (bodyArray.length == 1){return bodyArray[0].getPos();}
        else if (bodyArray.length == 2){return Dimension.max(bodyArray[0].getPos(), bodyArray[1].getPos());}
        Dimension max = bodyArray[0].getPos();
        for (Phybody phybody : bodyArray){
            if(phybody.getClass() == Probebody.class)continue;
            max = Dimension.max(max, phybody.getPos());
        }
        return max;
    }
    /**Calculate the total energy of the array of bodies
     * <p>This method calculate the total energy of the array of bodies, including {@link Phybody#kineticEnergy} and
     * {@link Phybody#potentialEnergy}</p>
     * @param bodyArray the array of bodies
     * @param reference the reference position used to calculate energy
     * @param constant the gravitational constant (G)
     * @return energy (double)*/
    public static double getEnergy(Phybody[] bodyArray, Dimension reference, double constant){
        double totalEnergy = 0;
        //calculate
        for(int b = 0; b < bodyArray.length; ++b){
            //find the given body's kinetic energy
            totalEnergy += bodyArray[b].kineticEnergy(reference);
            for(int k = 0; k < bodyArray.length; ++k){
                if (b == k)continue;//avoid self interaction
                //find the potential energy between paired bodies
                totalEnergy += bodyArray[b].potentialEnergy(bodyArray[k], constant);
            }
        }
        //return value
        return totalEnergy;
    }

    /**Find the position of maximum density the array of bodies
     * <p>This method calculate the most dense part of the array of bodies by dividing the array of bodies into
     * grids, and recursively find the most dense grid. The grid size is determined by the number of bodies</p>
     * @param bodyArray the array of bodies
     * @return the most dense part ({@link Dimension})*/
    public static Dimension findMostDense(Phybody[] bodyArray){
        //find max and min: Debug: checked
        Dimension max = getMax(bodyArray);
        Dimension min = getMin(bodyArray);
        //calculate n
        int n = 8;
        //establish matrix: Debug: checked
        long[][][] cells = new long[n][n][n];
        for (Phybody phybody : bodyArray) {
            Dimension pos = phybody.getPos();
            Dimension ijk = pos.minus(min).divide(max.minus(min)).multiply(n-1);
            // Accumulate the mass in the appropriate cell
            //round down
            cells[(int)ijk.x().abs()][(int)ijk.y().abs()][(int)ijk.z().abs()] += phybody.getMass();
        }
        //find cell with the greatest mass: Debug: checked
        long maxMass = 0;
        Dimension densestCell = new Dimension(-1);//Array index
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (cells[i][j][k]>maxMass){
                        maxMass=cells[i][j][k];
                        densestCell = new Dimension(i,j,k);
                    }
                }
            }
        }
        if (densestCell.overlap(new Dimension(-1)))throw new IndexOutOfBoundsException("Cell coordinate not permitted: no cell with the greatest mass found");
        //find bodies in the cell
        //find bounds
        Dimension lowerBound = Dimension.interpolate(min, max, densestCell.divide(n-1));
        Dimension upperBound = Dimension.interpolate(min, max, densestCell.plus(Dimension.ID()).divide(n-1));
        //set up an array
        int counter = 0;
        for (Phybody phybody : bodyArray){
            if (phybody.getPos().between(lowerBound,upperBound))counter ++;
        }
        Phybody[] newBodyArray = new Phybody[counter];
        counter = 0;
        //store data
        for (Phybody phybody : bodyArray){
            if (phybody.getPos().between(lowerBound,upperBound)){
                newBodyArray[counter] = phybody;
                counter ++;
            }
        }

        if (counter == 1){
            //terminate function with coordinate of the body in the cell
            return newBodyArray[0].getPos();
        }else if (counter <= 8){
            return getCenter(newBodyArray).getPos();
        }else return findMostDense(newBodyArray);
    }
    /**Calculate the average minimum distance for an array of bodies
     * <p>This method calculate sum of minimum distance possible for each body to another physical body,
     * then divide by the number of bodies</p>
     * @param bodyArray the array of bodies
     * @throws IllegalArgumentException as length of array cannot be 0 or 1*/
    public static double avgMinDistance(Phybody[] bodyArray) {
        if (bodyArray.length < 2) throw new IllegalArgumentException("Array length 0 or 1 prohibited");
        double sum = 0;
        int count = 0;

        for (int i = 0; i < bodyArray.length; i++) {
            if(bodyArray[i].getClass()==Probebody.class)continue;
            double minDistance = Double.MAX_VALUE;
            Dimension pos1 = bodyArray[i].getPos();
            for (int j = 0; j < bodyArray.length; j++) {
                if (i != j && bodyArray[j].getClass()!=Probebody.class) {
                    Dimension pos2 = bodyArray[j].getPos();
                    double distance = pos1.minus(pos2).magnitude();
                    minDistance = Math.min(minDistance, distance);
                }
            }
            sum += minDistance;
            count++;
        }
        return sum / count; // Average distance to nearest body
    }
    /**Initialize an array of Phybodys
     * <p>See {@link Phybody#init()} for initialization of a single body</p>
     * <p>Use {@link Phybody#centerData(Phybody[], Phybody)} to center the array of bodies in a center</p>
     * @param bodyArray the array of bodies
     * @param GravityConstant the gravitational constant (G)
     * @param initAll whether the bodies are initialized. If so, it refines the initialized values to reduce the change
     * in energy occurred during the simulation by matching the energy. This method modify the previous position and
     * velocity of bodies.
     * @throws IllegalArgumentException as length of array cannot be 0*/
    public static void init(Phybody[] bodyArray, double GravityConstant, boolean initAll){
        if (bodyArray.length == 0){throw new IllegalArgumentException("Array length 0 prohibited");}
        else if (bodyArray.length == 1){bodyArray[0].init();}
        else if (!initAll){
            for(int b = 0; b < bodyArray.length; ++b){
                for(int r = 0; r < bodyArray.length; ++r){
                    if (b == r)continue;//avoid self-interaction
                    //interact with body
                    bodyArray[b].applyGravity(bodyArray[r],GravityConstant);
                }
            }
            //init
            for(Phybody body : bodyArray){
                body.init();
            }
        }else {
            //energy
            double energy = getEnergy(bodyArray, Dimension.zero(), GravityConstant);
            double next = nextEnergy(bodyArray, GravityConstant);
            double ratio = calculateRatio(next, energy);
            int i = (int)1e8;//safeguard
            while(Math.abs(ratio-1) > 1e-4){
                i--;
                double amplifier = 36;
                ratio = calculateRatio(next, energy);
                for (Phybody body : bodyArray){
                    body.setVel(amplify(ratio,1,amplifier)*amplify(ratio,1,amplifier));
                }
                energy = getEnergy(bodyArray, Dimension.zero(), GravityConstant);
                next = nextEnergy(bodyArray, GravityConstant);
                if (i <= 0){
                    String code = energy + " / " + next;
                    throw new RuntimeException("failed to initialize: " + code + '=' + ratio);
                }
            }
        }
    }
    //predict the energy of the next timestamp
    private static double nextEnergy(Phybody[] bodyArray, double GravityConstant){
        //create array of next timestamps
        Dimension[] Acceleration = new Dimension[bodyArray.length];
        Dimension[] Position = new Dimension[bodyArray.length];
        //simulate accelerations
        for(int b = 0; b < bodyArray.length; ++b){
            //initializing
            Acceleration[b] = new Dimension();
            for(int r = 0; r < bodyArray.length; ++r){
                if (b == r || bodyArray[r].getClass() == Probebody.class) continue;  // skip self-interaction and ProbeBody
                //formula: a = G * M / d^2 = sqrt(Ax^2 + Ay^2 + Az^2);
                //d = sqrt(Px^2 + Py^2 + Pz^2)
                //Ax / Dx = Ay / Dy = Az / Dz
                double dissq = (bodyArray[r].getPos().minus(bodyArray[b].getPos())).multiply().magnitude();//D^2 = Px^2 + Py^2 + Pz^2
                if (dissq == 0){throw new ArithmeticException("Division by Zero");}
                double k = (GravityConstant*bodyArray[r].getMass()/dissq)/Math.sqrt(dissq);//find the coefficient as Ax = k * Px, Ay = k * Py, Az = k * Pz (k = G * M / d^3), using a = G * M / d^2
                Acceleration[b].addby((bodyArray[r].getPos().minus(bodyArray[b].getPos())).multiply(-k));//the acceleration vector should point toward the object
            }
        }
        //simulate update
        for(int b = 0; b < bodyArray.length; ++b){
            Position[b] = bodyArray[b].getPos().multiply(2).minus(bodyArray[b].getPrevPos()).plus(Acceleration[b].multiply(time*time));//set new position using Verlet Integration
        }
        //calculate energy
        double totalEnergy = 0;
        for(int b = 0; b < bodyArray.length; ++b){
            //find the given body's kinetic energy
            totalEnergy += bodyArray[b].getMass()*Position[b].minus(bodyArray[b].getPrevPos()).sqmag()/8.0/time/time;//velocity included
            for(int k = 0; k < bodyArray.length; ++k){
                if (b == k || bodyArray[k].getClass() == Probebody.class) continue;  // skip self-interaction and ProbeBody
                //potential energy
                double dis = (Position[k].minus(Position[b])).magnitude();
                if (dis == 0){throw new ArithmeticException("Division by Zero");}
                totalEnergy += GravityConstant*bodyArray[b].getMass()*bodyArray[k].getMass()/-dis;
            }
        }
        return totalEnergy;
    }
    private static double calculateRatio(double energy, double prev_energy) {
        double constant = 0;//0-inf, the bigger, the smaller the impact will be
        if (energy > 0) {
            if (prev_energy > 0) {//all positive
                return ((prev_energy / energy + constant) / (constant + 1));
            } else {//now positive, prev negative or 0;
                return ((constant + prev_energy / (prev_energy - energy)) / (constant + 1));
            }
        } else {
            if (prev_energy > 0) {//now negative or 0, prev positive
                return (((prev_energy - energy) / prev_energy + constant) / (constant + 1));
            } else {
                return ((energy / prev_energy + constant) / (constant + 1));
            }
        }
    }
    private static double amplify(double in, double normal, double constant){
        return normal + constant * (in-normal);
    }
    //physics
    /**Initializes the object's position and acceleration.
     * <p>This method adjusts the object's previous position based on the current acceleration
     * using the formula: prevPos = prevPos - (acc * (t^2 / 2)), where t is the time step.
     * It sets the acceleration to zero after initialization and marks the object as initialized.
     * </p>*/
    public final void init(){
        this.setPrevPos(this.getPrevPos().minus(this.getAcc().multiply(time*time/2)));
        this.setInitialized();
        this.setAcc(new Dimension());//set acc to zero
    }
    /**The kinetic energy of the object relative to a reference velocity
     * <p>Formula: (1/2) * m * v^2</p>
     * @param velReference The reference velocity
     * @return The kinetic energy of the object (double)*/
    public final double kineticEnergy(Dimension velReference){
        double v = (this.getPos().minus(this.getPrevPos()).divide(2*time).minus(velReference)).magnitude();//the velocity
        return this.getMass()*v*v/2.0;
    }
    /**The gravitational potential energy between this body and another physical body.
     * <p>Formula: U = -G * m1 * m2 / d. To prevent divide by zero, d cannot be zero</p>
     * @param other The other physical body in relation to which potential energy is calculated.
     * @param GravityConstant The gravitational constant (G).
     * @return The potential energy between the two objects (double), or 0 if the other object is {@link Probebody}.
     * @throws ArithmeticException if the distance between the two objects is zero.
     */
    public final double potentialEnergy(Phybody other, double GravityConstant){
        if(other.getClass() == Probebody.class)return 0;
        double dis = (other.getPos().minus(this.getPos())).magnitude();
        if (dis == 0){throw new ArithmeticException("Division by Zero");}
        return GravityConstant*this.getMass()*other.getMass()/-dis;
    }
    /**Applies the gravitational force from another physical body to this body.
     * <p>Calculation of the acceleration is based on Newton's law of gravitation: a = G * M / d^2</p>
     * @param other The other physical body exerting gravitational force on this object.
     * @param GravityConstant The gravitational constant (G).
     * @throws ArithmeticException If the distance between the two objects is zero.
     */
    public final void applyGravity(Phybody other, double GravityConstant){
        if(other.getClass() == Probebody.class)return;
        //formula: a = G * M / d^2 = sqrt(Ax^2 + Ay^2 + Az^2);
        //d = sqrt(Px^2 + Py^2 + Pz^2)
        //Ax / Dx = Ay / Dy = Az / Dz
        double dissq = (other.getPos().minus(this.getPos())).multiply().magnitude();//D^2 = Px^2 + Py^2 + Pz^2
        if (dissq == 0){throw new ArithmeticException("Division by Zero");}
        double k = (GravityConstant*other.getMass()/dissq)/Math.sqrt(dissq);//find the coefficient as Ax = k * Px, Ay = k * Py, Az = k * Pz (k = G * M / d^3), using a = G * M / d^2
        this.addAcc(((other.getPos().minus(this.getPos())).multiply(k)));//the acceleration vector should point toward the object
    }
    /**Updates the position and velocity of this object using Verlet Integration.
     * <p>
     * The position is updated based on the current acceleration, and the previous position is stored
     * to use in the next update. The acceleration is reset to zero for force application in the next step.
     * </p>
     * <p>Verlet Integration: x_{t+Δt} = 2 * x_t - x_{t-Δt} + a(x_t) * Δt^2</p>
     * @throws IllegalArgumentException If the object is not initialized.
     */
    public final void update(){//update the data for this body
        if (!this.initialized()){throw new IllegalArgumentException("this object not initialized");}
        Dimension tempPos = this.getPos();//store current position into tempPos
        this.setPos(this.getPos().multiply(2).minus(this.getPrevPos()).plus(this.getAcc().multiply(time*time)));//set new position using Verlet Integration: x_{t+Δt} = 2 * x_t - x_{t-Δt} + a(x_t) * Δt^2
        this.setPrevPos(tempPos);//set previous pos to pos
        this.setAcc(Dimension.zero());//set acceleration to zero for force application
    }

    /**An array of Phybodys for testing purpose*/
    public static Phybody[] testBodyArray() {
        Phybody[] bodies = {
                new Phybody(120, new Dimension(32.1, -785.5, 28.3), new Dimension(0.03, 0, -0)),
                new Phybody(98, new Dimension(34.7, -789.3, 26.8), new Dimension(-0, 0, 0.01)),
                new Phybody(159, new Dimension(28.5, -783.9, 30.1), new Dimension(0.01, -0, 0.02)),
                new Phybody(102, new Dimension(30.9, -780.5, 29.7), new Dimension(-0.04, 0, -0.02)),
                new Phybody(170, new Dimension(31.2, -787.7, 26.4), new Dimension(0.01, 0.03, -0)),
                new Phybody(90, new Dimension(29.1, -782.5, 25.9), new Dimension(0, -0.02, 0.01)),
                new Phybody(210, new Dimension(35.3, -790.1, 31.5), new Dimension(0, 0.01, 0)),
                new Phybody(112, new Dimension(33.4, -786.8, 28.9), new Dimension(-0.02, 0, -0.01)),
                new Phybody(175, new Dimension(27.6, -781.2, 29.3), new Dimension(0, -0.01, 0)),
                new Phybody(88, new Dimension(29.9, -785.0, 24.7), new Dimension(-0, 0, 0.01)),
                new Phybody(242, new Dimension(36.5, -792.0, 27.5), new Dimension(0, 0, 0)),
                new Phybody(130, new Dimension(33.2, -788.6, 29.9), new Dimension(0, 0, -0.01)),
                new Phybody(111, new Dimension(28.3, -783.3, 28.0), new Dimension(-0.01, 0, -0)),
                new Phybody(90, new Dimension(31.5, -785.5, 32.2), new Dimension(0, 0, 0.01)),
                new Phybody(125, new Dimension(32.8, -787.0, 24.3), new Dimension(0, 0.02, 0)),
                new Phybody(149, new Dimension(30.0, -789.2, 30.4), new Dimension(0, 0, -0.02)),
                new Phybody(195, new Dimension(37.2, -791.7, 28.2), new Dimension(-0.01, 0, 0.004)),
                new Phybody(73, new Dimension(29.7, -780.8, 25.6), new Dimension(0.01, 0.01, -0.01)),
                new Phybody(175, new Dimension(33.5, -783.1, 31.1), new Dimension(-0.03, 0, 0.02)),
                new Phybody(110, new Dimension(30.8, -785.9, 27.3), new Dimension(0.01, -0.01, 0)),
                new Phybody(102, new Dimension(28.1, -782.6, 26.0), new Dimension(0, -0.02, 0)),
                new Phybody(135, new Dimension(35.7, -788.9, 29.6), new Dimension(0, 0.02, 0)),
                new Phybody(88, new Dimension(32.0, -787.3, 27.1), new Dimension(0, 0.03, 0)),
                new Phybody(229, new Dimension(36.8, -792.4, 28.9), new Dimension(0.01, 0, 0.02)),
                new Phybody(120, new Dimension(30.9, -781.4, 26.2), new Dimension(-0.02, 0.01, -0.05)),
                new Phybody(202, new Dimension(34.5, -789.0, 31.8), new Dimension(0.01, 0.0, 0.03)),
                new Phybody(153, new Dimension(28.6, -786.5, 25.1), new Dimension(-0.04, 0.05, -0.02)),
                new Phybody(115, new Dimension(33.3, -783.9, 27.7), new Dimension(0.02, -0.01, 0.04)),
                new Phybody(95, new Dimension(29.4, -785.3, 29.5), new Dimension(-0.03, 0.02, -0.03)),
                new Phybody(176, new Dimension(31.7, -790.4, 28.1), new Dimension(0.05, -0.0, 0.01)),
                new Phybody(135, new Dimension(27.9, -782.8, 27.0), new Dimension(-0.04, 0.0, -0.05)),
                new Phybody(190, new Dimension(36.1, -793.5, 30.6), new Dimension(0.0, 0.03, -0.04)),
                new Phybody(125, new Dimension(30.4, -781.7, 25.4), new Dimension(-0.00, -0.02, 0.02)),
                new Phybody(144, new Dimension(33.9, -789.1, 29.3), new Dimension(0.04, 0.05, -0.01)),
                new Phybody(80, new Dimension(29.2, -784.5, 26.7), new Dimension(-0.01, -0.03, 0.03)),
                new Phybody(115, new Dimension(31.0, -787.6, 30.9), new Dimension(0.02, 0.0, -0.02)),
                new Phybody(153, new Dimension(35.4, -791.0, 28.5), new Dimension(-0.02, 0.01, 0.012)),
                new Phybody(200, new Dimension(37.0, -795.2, 29.4), new Dimension(0.00, 0.04, -0.03)),
                new Phybody(114, new Dimension(28.8, -784.9, 25.2), new Dimension(0.03, -0.01, 0.04)),
                new Phybody(130, new Dimension(30.5, -786.3, 27.8), new Dimension(-0.012, -0.02, -0.01)),
                new Phybody(140, new Dimension(32.9, -790.8, 26.5), new Dimension(0.003, 0.02, -0.04)),
                new Phybody(165, new Dimension(35.6, -792.7, 31.2), new Dimension(-0.03, 0.00, 0.00)),
                new Phybody(103, new Dimension(29.0, -781.9, 30.7), new Dimension(0.01, -0.05, -0.02)),
                new Phybody(195, new Dimension(33.7, -789.4, 24.9), new Dimension(0.04, 0.03, 0.01)),
                new Phybody(80, new Dimension(31.6, -785.8, 27.6), new Dimension(-0.02, -0.01, -0.03)),
                new Phybody(125, new Dimension(29.3, -782.1, 29.0), new Dimension(0.02, 0.0, 0.01)),
                new Phybody(167, new Dimension(34.2, -787.5, 25.7), new Dimension(-0.01, 0.2, -0.1)),
                new Phybody(185, new Dimension(36.3, -791.2, 32.5), new Dimension(0.02, 0.5, -0.2)),
                new Phybody(90, new Dimension(30.7, -780.6, 28.4), new Dimension(-0, -0, 0)),
                new Phybody(210, new Dimension(32.4, -789.9, 26.1), new Dimension(0.0, -0.01, -0)),
                new Phybody(113, new Dimension(29.5, -784.2, 31.9), new Dimension(0.01, 0, 0.02)),
                new Phybody(190, new Dimension(33.1, -790.0, 25.3), new Dimension(-0.0, -0.0, -0.0)),
                new Phybody(105, new Dimension(31.9, -783.4, 30.2), new Dimension(0.01, 0.0, 0.02)),
                new Phybody(155, new Dimension(28.4, -786.1, 27.7), new Dimension(-0.0, 0.0, -0.02)),
                new Phybody(177, new Dimension(37.1, -793.0, 29.8), new Dimension(0.02, -0.01, 0.04)),
                new Phybody(99, new Dimension(34.0, -787.2, 24.5), new Dimension(-0.01, 0.03, -0.03)),
                new Phybody(205, new Dimension(35.1, -794.1, 31.6), new Dimension(0.00, -0.02, 0.05)),
                new Phybody(140, new Dimension(30.6, -781.3, 26.3), new Dimension(0.04, 0.01, -0.04)),
                new Phybody(165, new Dimension(29.8, -788.7, 30.5), new Dimension(-0.02, 0.00, 0.03)),
                new Phybody(121, new Dimension(34.6, -790.5, 27.2), new Dimension(0.03, -0.04, -0.02)),
                new Phybody(185, new Dimension(31.1, -784.7, 25.5), new Dimension(-0.05, 0.01, 0.00)),
                new Phybody(161, new Dimension(36.9, -792.3, 32.1), new Dimension(0.02, 0.03, -0.05)),
                new Phybody(178, new Dimension(30.2, -782.0, 29.4), new Dimension(-0.03, -0.01, 0.04)),
                new Phybody(58, new Dimension(32.7, -785.4, 26.6), new Dimension(0.00, 0.05, -0.02)),
                new Phybody(195, new Dimension(34.8, -788.4, 28.8), new Dimension(0.04, -0.02, 0.03)),
                new Phybody(93, new Dimension(29.6, -783.0, 30.0), new Dimension(-0.01, 0.03, -0.04)),
                new Phybody(210, new Dimension(37.3, -793.9, 27.4), new Dimension(0.03, 0.00, 0.05)),
                new Phybody(84, new Dimension(28.9, -785.7, 26.9), new Dimension(-0.04, -0.03, -0.01))
        };
        return bodies;
    }
}