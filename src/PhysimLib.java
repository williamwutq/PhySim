//class PhysimLib
//William Wu
//Sep 11

import Physics.Dimension;
import Physics.Phybody;

/**
 * <pre>
 * Names:
 * private:
 *     .numberOfBodies int
 *     .Barycenter Physics.Phybody
 *     .forceArray Physics.Dimension[]
 *     .bodyArray Physics.Phybody[]
 *
 *     .centerData() -> modify
 *     .initBarycenter() -> modify
 *
 * public:
 *     .GravitationalConstant static long
 *     .modifyGravitationalConstant(int, boolean) -> modify, boolean
 *     .createBody(Physics.Phybody) -> modify
 *     .createBody(Physics.Phybody, int) -> modify
 *     .createForce(Physics.Dimension) -> modify
 *     .createForce(Physics.Dimension, int) -> modify
 *     .init() -> modify
 *
 *     .getBarycenter() -> Physics.Phybody Barycenter
 *     .applyForce() -> modify
 *     .update(double) -> modify
 *     .saveData(Physics.Dimension[index][][] dataArray, int index) -> modify dataArray
 *
 *     .calculateTotalEnergy() -> double
 *     .calculateTotalEnergy(Physics.Dimension) -> double
 *     .conserveBody(double) -> modify
 </pre>*/
public class PhysimLib {
    public final int HexID = 0x0402;
    public static long GravitationalConstant = 16;//the Gravitational Constant
    public static double time = 0.1;
    @SuppressWarnings("FieldMayBeFinal")
    private int numberOfBodies;
    @SuppressWarnings("FieldMayBeFinal")
    private Phybody Barycenter;
    @SuppressWarnings("FieldMayBeFinal")
    private Phybody[] bodyArray;
    @SuppressWarnings("FieldMayBeFinal")
    private Dimension[] forceArray;

    public PhysimLib(int Bodies){
        numberOfBodies = Bodies;
        bodyArray = new Phybody[numberOfBodies];
        forceArray = new Dimension[numberOfBodies];
    }
    public void createBody(Phybody body, int bodyIndex){
        if (bodyIndex > numberOfBodies-1 || bodyIndex < 0)throw new IndexOutOfBoundsException("bodyIndex out of range: 0 - " + (bodyArray.length-1));
        bodyArray[bodyIndex]=body;
    }
    public void createBody(Phybody body){
        for (int i = 0; i < bodyArray.length; i++) {
            Phybody phybody = bodyArray[i];
            if (phybody == null) {
                bodyArray[i] = body;
                break;
            }
        }
    }
    public void createBody(long bodyMass, Dimension bodyPos, Dimension bodyVel, int bodyIndex){
        if (bodyIndex > numberOfBodies-1 || bodyIndex < 0)throw new IndexOutOfBoundsException("bodyIndex out of range: 0 - " + (bodyArray.length-1));
        bodyArray[bodyIndex]=new Phybody(bodyMass, bodyPos, bodyVel);
    }
    public void createBody(long bodyMass, Dimension bodyPos, Dimension bodyVel){
        for (int i = 0; i < bodyArray.length; i++) {
            Phybody phybody = bodyArray[i];
            if (phybody == null) {
                bodyArray[i] = new Phybody(bodyMass, bodyPos, bodyVel);
                break;
            }
        }
    }
    public Phybody getBody(int bodyIndex){
        if (bodyIndex > numberOfBodies-1 || bodyIndex < 0)throw new IndexOutOfBoundsException("bodyIndex out of range: 0 - " + (bodyArray.length-1));
        return bodyArray[bodyIndex];
    }
    public int getID(){
        return HexID;
    }
    public String getHexID(){
        return Integer.toHexString(HexID);
    }


    public void createForce(Dimension force, int bodyIndex){
        if (bodyIndex > numberOfBodies-1 || bodyIndex < 0)throw new IndexOutOfBoundsException("bodyIndex out of range: 0 - " + (bodyArray.length-1));
        forceArray[bodyIndex]=force;
    }
    public void createForce(Dimension force){
        for (Dimension dimension : forceArray) {
            if (dimension == null){dimension = force;break;}
        }
    }
    public boolean modifyGravitationalConstant(int newConstant, boolean permitNegative){
        if (newConstant < 65536 && newConstant !=0 && (permitNegative&&newConstant >= -65536||newConstant>0)){
            GravitationalConstant = newConstant; return true;
        }else return false;
    }
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        for (Phybody phybody : bodyArray){
            result.append(phybody.toString());
        }
        return result.toString();
    }
    public String print(){
        StringBuilder result = new StringBuilder();
        result.append("T = ");
        result.append(time);
        result.append(";\n{");
        for (Phybody phybody : bodyArray){
            result.append("   ");
            result.append(phybody.print(false)+'\n');
        }
        result.append('}');result.append(';');
        return result.toString();
    }
    /*
    Initialize the simulation program
    Input: void
    Output: body with data of the barycenter (Physics.Phybody)
    */
    public void init(){
        //check bodies
        for (int i = 0; i < bodyArray.length; i++) {
            Phybody phybody = bodyArray[i];
            if (phybody == null) {
                bodyArray[i] = new Phybody();
            }
        }
        //check force
        for (int i = 0; i < forceArray.length; i++) {
            Dimension dimension = forceArray[i];
            if (dimension == null) {
                forceArray[i] = new Dimension();
            }
        }
        Phybody.init(bodyArray, GravitationalConstant, false);
        Barycenter = Phybody.getCenter(bodyArray);
        Barycenter.init();
        Phybody.centerData(bodyArray, Barycenter);
        Phybody.init(bodyArray, GravitationalConstant, true);
    }

    public Phybody getBarycenter(){
        return Barycenter;
    }

    /*
    Apply external forces stored in the forceArray to all objects and the Barycenter
    Input: void
    Output: Modify
    */
    void applyForce(){
        for(int b = 0; b < bodyArray.length; ++b){
            //apply force to the body
            bodyArray[b].addF(forceArray[b]);
            //apply force to the Barycenter
            Barycenter.addF(forceArray[b]);
        }
    }

    /*
    update the Barycenter and all bodies
    Input: TimeInterval
    Output: modify
    */
    public void update(){
        Barycenter.update();
        //update data for each bodies
        for (Phybody phybody: bodyArray) {
            //update physics simulation data based on TimeInterval
            phybody.update();
        }
    }

    public double calculateTotalEnergy(Dimension referenceVelocity){
        return Phybody.getEnergy(bodyArray, referenceVelocity, GravitationalConstant);
    }
    public double calculateTotalEnergy(){
        return Phybody.getEnergy(bodyArray, Dimension.zero(), GravitationalConstant);
    }

    /*
    Apply gravitational forces based on the bodies
    Input: void
    Output: Modify
    */
    public void applyGravity(){
        for(int b = 0; b < numberOfBodies; ++b){
            for(int r = 0; r < numberOfBodies; ++r){
                if (b == r)continue;//avoid self-interaction
                //interact with body
                bodyArray[b].applyGravity(bodyArray[r],GravitationalConstant);
            }
        }
    }
    /*
    Store Data in to DataArray
    Input: 3d Array of Data applied (Physics.Dimension***); index of position in array (unsigned long)
    Output: Modify
    */
    public void saveData(Dimension[][][] dataArray, int index){
        if (dataArray.length != numberOfBodies)throw new IndexOutOfBoundsException("err: dataArray length does not match numberOfBodies");
        for(int b = 0; b < numberOfBodies; ++b){
            dataArray[index][b][0] = bodyArray[b].getVel().add(Barycenter.getVel());
            dataArray[index][b][1] = bodyArray[b].getPos().add(Barycenter.getPos());
        }
    }

    public void reverse(){
        for(Phybody phybody: bodyArray){
            Dimension temppos = phybody.getPos();
            phybody.setPos(phybody.getPrevPos());
            phybody.setPrevPos(temppos);
        }
    }


}
