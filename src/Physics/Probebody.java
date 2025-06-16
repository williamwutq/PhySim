//class Physics.Probebody
//Extension of Class Physics.Phybody ©William Wu
//William Wu
//Oct 10
package Physics;

/**<h1>Body Class Probebody</h1>
 * <p>Mass, Position, Velocity</p>
 * <p>Floating points calculation & storage</p>
 * <p>Gravity Simulation</p>
 * <p>Field Probe with no mass, interacting only with the field</p>
 * <p>See {@link Phybody} for more information</p>
 * @author William Wu
 * <pre>
 *     All rights reserved, suggestion welcome
 * </pre>
 * @version 1.4.4 @Oct 31, 2024
 * <p>UPGRADES: methods incorporation in subclasses</p>
 */
public class Probebody extends Phybody{
    private Dimension pos;
    private Dimension prevPos;
    private Dimension acc;
    private boolean initialized = false;

    /**<p>Constructor</p>
     * <p>Full: position + velocity</p>
     * <p><b>Warning: this body does not have mass and cannot accelerate other bodies</b>, use {@link Phybody} for normal bodies</p>
     * @param body_pos position of the body
     * @param body_vel velocity of the body*/
    public Probebody(Dimension body_pos, Dimension body_vel){
        pos = body_pos.copy();
        prevPos = body_pos.copy().minus(body_vel.multiply(Phybody.time));
        acc = Dimension.zero();
    }
    /**<p>Constructor</p>
     * <p>Position: position + 0</p>
     * <p>See {@link Probebody#Probebody(Dimension, Dimension) Full Constructor}</p>
     * <p><b>Warning: this body does not have mass and cannot accelerate other bodies</b>, use {@link Phybody} for normal bodies</p>
     * @param body_pos position of the body*/
    public Probebody(Dimension body_pos){
        pos = body_pos.copy();
        prevPos = body_pos.copy();
        acc = Dimension.zero();
    }
    /**<p>Constructor</p>
     * <p>No Input</p>
     * <p>See {@link Probebody#Probebody(Dimension, Dimension) Full Constructor}</p>
     * <p><b>Warning: this body does not have mass and cannot accelerate other bodies</b>, use {@link Phybody} for normal bodies</p>
     * <b>Warning: overlapping potential!</b>, use {@link Probebody#Probebody(Dimension) Zero Velocity constructor} to mitigate
     * @value (1,1,1) + 0
     * */
    public Probebody(){
        pos = new Dimension(1);
        prevPos = new Dimension(1);
        acc = Dimension.zero();
    }

    //get methods
    /**<p>Whether this body has been initialized and can be used in simulation</p>
     * <b>Always initialize with simulated acceleration and {@link Phybody#init()} before simulation</b>
     * @return true if this Phybody object is initialized*/
    public boolean initialized(){
        return initialized;
    }
    /**<p>Return the mass of this body</p>
     * @return 0*/
    public long getMass(){//get the mass
        return 0;
    }
    /**<p>Current position of this body</p>
     * @return position ({@link Physics.Dimension})*/
    public Dimension getPos(){//get the position relative to a reference
        return pos.copy();
    }
    /**<p>Previous position of this body</p>
     * @return previous position ({@link Physics.Dimension})*/
    public Dimension getPrevPos(){
        return prevPos.copy();
    }
    /**<p>Velocity of this body</p>
     * @return velocity ({@link Physics.Dimension})*/
    public Dimension getVel(){//get the velocity relative to a reference
        return this.getPos().minus(this.getPrevPos()).divide(2*time).copy();
    }
    /**<p>Acceleration of this body</p>
     * @return acceleration ({@link Physics.Dimension})*/
    public Dimension getAcc(){//get the acceleration relative to a reference (use if exterior force is applied)
        return acc.copy();
    }

    //set methods
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
    /**Disabled for {@link Probebody}, mass is always 0*/
    public void setMass(long body_mass){}
    /**<p>Set the position of this body</p>
     * @param new_pos new position of the body*/
    public void setPos(Dimension new_pos){//set the position
        pos = new_pos.copy();
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
    /**<p>Set the previous position of this body</p>
     * @param new_prevPos new position of the body*/
    public void setPrevPos(Dimension new_prevPos){
        prevPos = new_prevPos.copy();
    }
}
