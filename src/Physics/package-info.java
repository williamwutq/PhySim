/**
 * <h3>Package Display</h3>
 * <p>Main Package for physics simulation, contain fundamental classes</p>
 * <p>Use floating point simulation and precision vector math</p>
 * <p>Use Verlet Integration in simulation, reversible</p>
 * <h5>Contains: </h5>
 * <ul>
 *     <li>{@link Physics.Dimension}: basic 3D vector class, including mathematical, String and array functions</li>
 *     <li>{@link Physics.Space}: basic transformation class, used to transform {@link Physics.Dimension Physics.Dimension} vectors
 *     including matrix math, transformation functions, standard transformations</li>
 *     <li>{@link Physics.Phybody}: field simulation class dealing with field simulation, representing a physical body</li>
 *     <li>{@link Physics.Fixedbody}: extension of {@link Physics.Phybody}, fixed and cannot be accelerated</li>
 *     <li>{@link Physics.Probebody}: extension of {@link Physics.Phybody}, having mass of zero but interact with the field</li>
 *     <li>{@link Physics.PhyCluster}: extension of {@link Physics.Phybody}, behave exactly like a normal {@link Physics.Phybody}
 *     but represent an array of Phybodys as a whole</li>
 * </ul>
 * <h5>Author:</h5><p>William Wu, All rights reserved</p>
 * <h5>Version:</h5><p>1.4.4 @Oct 31, 2024</p>
 * <p>Upgrades: added .copy() methods for classes, modified Phybody to use getters and setters, accommodating subclasses. Added PhyCluster</p>
 * */
package Physics;