package Physics;

/**<h1>Body Class PhyCluster</h1>
 * <p>Array of Phybodys</p>
 * <p>Floating points calculation & storage</p>
 * <p>Gravity Simulation</p>
 * <p>See {@link Phybody} for more information</p>
 * @author William Wu
 * <pre>
 *     All rights reserved, suggestion welcome
 * </pre>
 * @version 1.4.4 @Oct 31, 2024
 * <p>UPGRADES: new</p>
 */
public class PhyCluster extends Phybody {
    private Phybody[] bodies;
    private boolean initialized = false;

    /**<p>Constructor</p>
     * @param bodies the array of bodies, please pass by reference
     * @throws IllegalArgumentException if the number of bodies is less than 2 or if any bodies in the array is null*/
    public PhyCluster(Phybody[] bodies) {
        //check
        if (bodies.length < 2)throw new IllegalArgumentException("Array must contain at least 2 bodies.");
        for (Phybody body : bodies) {
            if (body == null)throw new IllegalArgumentException("Array cannot contain null objects.");
        }
        this.bodies = bodies;
    }

    /**<p>Whether this body has been initialized and can be used in simulation</p>
     * <b>Always initialize with simulated acceleration and {@link Phybody#init()} before simulation</b>
     * @return true if this Phybody object is initialized*/
    public boolean initialized(){
        return initialized;
    }
    /**<p>Return the mass of this array of bodies</p>
     * @return mass(long)*/
    public long getMass() {
        return Phybody.getMass(bodies);
    }
    /**<p>Previous position of this array of bodies</p>
     * @return previous position ({@link Physics.Dimension})*/
    public Dimension getPrevPos() {
        return Phybody.getCenter(bodies).getPrevPos();
    }
    /**<p>Current position of this array of bodies</p>
     * @return position ({@link Physics.Dimension})*/
    public Dimension getPos() {
        return Phybody.getCenter(bodies).getPos();
    }
    /**<p>Velocity of this array of bodies</p>
     * @return velocity ({@link Physics.Dimension})*/
    public Dimension getVel() {
        return Phybody.getCenter(bodies).getVel();
    }
    /**<p>Acceleration of this array of bodies</p>
     * @return acceleration ({@link Physics.Dimension})*/
    public Dimension getAcc() {
        return Phybody.getCenter(bodies).getAcc();
    }

    /**<p>Set the initialization state of this body to true</p>
     * <b>Warning: Use NOT permitted</b>*/
    protected void setInitialized(){
        //be careful when use
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (!stackTrace[2].getClassName().equals("Physics.Phybody")||!stackTrace[2].getMethodName().equals("init")){
            throw new IllegalCallerException("only .init() function in this class can modify initialized");
        }
        initialized = true;
    }
    /**Disabled for {@link PhyCluster}*/
    public void setMass(long body_mass) {
        // Leave empty, mass is not directly settable for the entire array
    }
    /**<p>Set the previous position of this array of bodies</p>
     * @param new_prevPos new position of the array of bodies*/
    public void setPrevPos(Dimension new_prevPos) {
        Dimension prevCenter = this.getPrevPos();
        Dimension shift = new_prevPos.minus(prevCenter);
        for (int i = 0; i < bodies.length; i++) {
            bodies[i].setPrevPos(bodies[i].getPrevPos().add(shift));
        }
    }
    /**<p>Set the position of this array of bodies</p>
     * @param new_pos new position of the bodies*/
    public void setPos(Dimension new_pos) {
        Dimension currentCenter = this.getPos();
        Dimension shift = new_pos.minus(currentCenter);
        for (int i = 0; i < bodies.length; i++) {
            bodies[i].setPos(bodies[i].getPos().add(shift));
        }
    }
    /**<p>Set the velocity of this array of bodies</p>
     * <b>Warning: this method modify the prevPos variable, AND velocity</b>
     * @param new_vel the new velocity*/
    public void setVel(Dimension new_vel) {
        Dimension currentVel = this.getVel();
        Dimension shift = new_vel.minus(currentVel);
        for (int i = 0; i < bodies.length; i++) {
            bodies[i].setVel(bodies[i].getVel().add(shift));
        }
    }
    /**<p>Set the acceleration of this array of bodies</p>
     * @param new_acc the new acceleration*/
    public void setAcc(Dimension new_acc) {
        Dimension currentAcc = this.getAcc();
        Dimension shift = new_acc.minus(currentAcc);
        for (int i = 0; i < bodies.length; i++) {
            bodies[i].setAcc(bodies[i].getAcc().add(shift));
        }
    }

    //special
    /**Applies gravitational forces between all pairs of physical bodies in this cluster.
     * <p>This method iterates through the array of bodies and applies the gravitational force
     * from each body to every other body, excluding itself. The calculation of acceleration
     * for each interaction is based on Newton's law of gravitation.</p>
     * @throws ArithmeticException If the distance between any of the two objects in the array is zero.
     * @param GravityConstant The gravitational constant used in the calculations.
     * @see Phybody#applyGravity */
    public void applyGravityToAll(double GravityConstant) {
        for (int i = 0; i < bodies.length; i++) {
            for (int j = 0; j < bodies.length; j++) {
                if (i != j) {
                    bodies[i].applyGravity(bodies[j], GravityConstant);
                }
            }
        }
    }
}

