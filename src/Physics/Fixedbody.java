//class Physics.Fixedbody
//Extension of Class Physics.Phybody ©William Wu
//William Wu
//Oct 10
package Physics;

/**<h1>Body Class Fixedbody</h1>
 * <p>Mass, Position, Velocity</p>
 * <p>Floating points calculation & storage</p>
 * <p>Gravity Simulation</p>
 * <p>Fixed body, cannot be accelerated, which means velocity is constant</p>
 * <p>See {@link Phybody} for more information</p>
 * @author William Wu
 * <pre>
 *     All rights reserved, suggestion welcome
 * </pre>
 * @version 1.4.4 @Oct 31, 2024
 * <p>UPGRADES: methods incorporation in subclasses</p>
 */
public class Fixedbody extends Phybody{
    private Dimension pos;
    private Dimension prevPos;
    private long mass;

    //constructor
    /**<p>Constructor</p>
     * <p>Full: mass + position + velocity</p>
     * <b>Warning: this body cannot be accelerated</b>, use {@link Phybody} for normal bodies
     * @param body_mass mass of the body
     * @param body_pos position of the body
     * @param body_vel velocity of the body*/
    public Fixedbody(long body_mass, Dimension body_pos, Dimension body_vel){
        mass = body_mass;
        pos = body_pos.copy();
        prevPos = body_pos.minus(body_vel.multiply(Phybody.time));
    }
    /**<p>Constructor</p>
     * <p>No mass: 0 + position + velocity</p>
     * <p>See {@link Fixedbody#Fixedbody(long, Dimension, Dimension) Full Constructor}</p>
     * <p><b>Warning: this body cannot be accelerated</b>, use {@link Phybody} for normal bodies</p>
     * <b>Warning: will not interact with field</b>, to declare a probe, please use {@link Physics.Probebody}
     * @param body_pos position of the body
     * @param body_vel velocity of the body*/
    public Fixedbody(Dimension body_pos, Dimension body_vel){
        mass = 0;
        pos = body_pos.copy();
        prevPos = body_pos.minus(body_vel.multiply(Phybody.time));
    }
    /**<p>Constructor</p>
     * <p>Zero Velocity: mass + position + 0</p>
     * <p>See {@link Fixedbody#Fixedbody(long, Dimension, Dimension) Full Constructor}</p>
     * <p><b>Warning: this body cannot be accelerated</b>, use {@link Phybody} for normal bodies</p>
     * @param body_mass mass of the body
     * @param body_pos position of the body*/
    public Fixedbody(long body_mass, Dimension body_pos){
        mass = body_mass;
        pos = body_pos.copy();
        prevPos = body_pos.copy();
    }
    /**<p>Constructor</p>
     * <p>Position: 0 + position + 0</p>
     * <p>See {@link Fixedbody#Fixedbody(long, Dimension, Dimension) Full Constructor}</p>
     * <p><b>Warning: this body cannot be accelerated</b>, use {@link Phybody} for normal bodies</p>
     * <b>Warning: will not interact with field</b>, to declare a probe, please use {@link Physics.Probebody}
     * @param body_pos position of the body*/
    public Fixedbody(Dimension body_pos){
        mass = 0;
        pos = body_pos.copy();
        prevPos = body_pos.copy();
    }
    /**<p>Constructor</p>
     * <p>Mass: mass + 1 + 0</p>
     * <p>See {@link Fixedbody#Fixedbody(long, Dimension, Dimension) Full Constructor}</p>
     * <p><b>Warning: this body cannot be accelerated</b>, use {@link Phybody} for normal bodies</p>
     * <b>Warning: overlapping potential!</b>, use {@link Fixedbody#Fixedbody(long, Dimension) Zero Velocity constructor} to mitigate
     * @param body_mass mass of body*/
    public Fixedbody(long body_mass){
        mass = body_mass;
        pos = new Dimension(1);
        prevPos = new Dimension(1);
    }
    /**<p>Constructor</p>
     * <p>No Input</p>
     * <p>See {@link Fixedbody#Fixedbody(long, Dimension, Dimension) Full Constructor}</p>
     * <p><b>Warning: this body cannot be accelerated</b>, use {@link Phybody} for normal bodies</p>
     * <b>Warning: will not interact with field</b>, to declare a probe, please use {@link Physics.Probebody};
     * <b>Warning: overlapping potential!</b>, use {@link Fixedbody#Fixedbody(long, Dimension) Zero Velocity constructor} to mitigate
     * @value 0 + (1,1,1) + 0
     * */
    public Fixedbody(){
        mass = 0;
        pos = new Dimension(1);
        prevPos = new Dimension(1);
    }

    //get methods
    /**<p>Whether this body has been initialized and can be used in simulation</p>
     * @return always true*/
    public boolean initialized(){
        return true;
    }
    /**<p>Return the mass of this body</p>
     * @return mass(long)*/
    public long getMass(){//get the mass
        return mass;
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
     * @return velocity ({@link Physics.Dimension}), which is constant*/
    public Dimension getVel(){//get the velocity relative to a reference
        return this.getPos().minus(this.getPrevPos()).divide(2*time);
    }
    /**<p>Acceleration of this body</p>
     * @return acceleration ({@link Physics.Dimension}): zero
     * @value {@link Physics.Dimension#zero() zero}*/
    public Dimension getAcc(){//get the acceleration relative to a reference (use if exterior force is applied)
        return new Dimension();
    }

    //set methods
    /**Disabled for {@link Fixedbody}, always initialized*/
    protected void setInitialized(){
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
    /**Disabled for {@link Fixedbody}*/
    public void setVel(Dimension new_vel){//set the velocity
    }
    /**Disabled for {@link Fixedbody}*/
    public void setAcc(Dimension new_acc){//set the acceleration
    }
}