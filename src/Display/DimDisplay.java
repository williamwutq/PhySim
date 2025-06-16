package Display;

import Physics.*;
import javax.swing.*;

public class DimDisplay {
    private int time;
    private Space rotation = Space.Cartesian();
    private Dimension bound;
    private Phybody center;
    private Dimension[][] data;
    private long[] massArray;
    Window displayWindow;
    JFrame frame = new JFrame("PhyDisplay");
    //constructor: assume centered data
    public DimDisplay(Phybody[] bodyArray, int time, int currentTime) {
        if (time < 1){time = 1;}
        else if (time > 16) time = 16;
        int leng = bodyArray.length;//find length
        if (leng < 2) throw new IllegalArgumentException("must have at least two body");
        data = new Dimension[time][leng];
        this.time = currentTime;
        //find center
        center = Phybody.getCenter(bodyArray); center.init();
        //copy array
        Phybody[] tempArray = new Phybody[bodyArray.length];
        for (int i = 0; i < bodyArray.length; i++) {
            tempArray[i] = new Phybody(bodyArray[i].getMass());
            tempArray[i].setPos(bodyArray[i].getPos().referto(center.getPos()));
            tempArray[i].setPrevPos(bodyArray[i].getPrevPos().referto(center.getPrevPos()));
        }
        //create mass array and find max mass
        massArray = new long[leng];
        long maxMass = 0;
        for(int i = 0; i < leng; i++){
            //set mass
            long mass = tempArray[i].getMass();
            massArray[i] = mass;
            if(mass > maxMass)maxMass=mass;
        }
        //find bounds
        Dimension minimum = tempArray[0].getPos();
        Dimension maximum = tempArray[0].getPos();
        if (bodyArray.length == 2){
            minimum = Dimension.min(tempArray[0].getPos(), tempArray[1].getPos());
            maximum = Dimension.max(tempArray[0].getPos(), tempArray[1].getPos());
        }else for(Phybody phybody : tempArray){
            if(phybody.getClass() == Probebody.class)continue;
            minimum = Dimension.min(minimum, phybody.getPos());
            maximum = Dimension.max(maximum, phybody.getPos());
        }
        //assign bound, to the maximum distance from center multiply by a number
        bound = Dimension.max(maximum, minimum.not()).multiply(4);

        displayWindow = new Window(200, 200, center.getPos(), bound.not(), bound);
        displayWindow.setMaxMass(maxMass);
        displayWindow.setFaintRate(0.9);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 228);
        frame.setMinimumSize(new java.awt.Dimension(200, 228));
        frame.add(displayWindow);
        frame.setVisible(true);
    }

    //get functions
    public final int length(){return massArray.length;}
    public final int time(){
        //if only have data of one time
        if(data.length == 1){
            return (data[0][0] == null) ? 0 : 1;
        }
        //go through the loop
        for (int i = 1; i <= data.length; i++) {
            Dimension[] array = data[i-1];
            if (array[0] == null) return i;
        }
        return data.length;
    }
    public final int currentTime(){return time;}
    public Dimension[] getData(int index){
        if(index < 0)index = -index;
        if(index > data.length-1)throw new IndexOutOfBoundsException("array data index "+index+" out of bound "+data.length);
        return data[index];
    }
    public Space getRotation(){
        return rotation.copy();
    }
    //set functions
    public final void setTime(int time){this.time = time;}
    public final void addTime(int time){this.time += time;}
    public void addData(Phybody[] bodyArray){
        if(bodyArray.length != massArray.length){
            throw new IllegalArgumentException("input array length does not match recorded length");
        }
        time++;
        //create position array and copy data
        Dimension[] positionArray = new Dimension[massArray.length];
        Phybody[] newBodyArray = new Phybody[massArray.length];
        //loop
        for (int i = 0; i < massArray.length; i++){
            newBodyArray[i] = bodyArray[i].copy();
            positionArray[i] = bodyArray[i].getPos(center.getPos());
            Phybody phybody = newBodyArray[i];
            phybody.setPos(phybody.getPos(center.getPos()));
            phybody.setPrevPos(phybody.getPrevPos(center.getPrevPos()));
        }
        //append if found
        for(int i = data.length-2; i >= 0; i--){
            data[i+1] = data[i];
        }
        data[0] = positionArray;//add new array in the front
        displayWindow.faintList();
        displayWindow.addBody(reconstructBody(0));
        displayWindow.removeByAlpha(data.length);
        displayWindow.repaint();
    }
    public void setRotation(Dimension x, Dimension y, Dimension z){
        try {
            this.rotation = new Space(x, y, z);
        }catch (IllegalArgumentException exception){
            System.out.println("Rotation matrix creation failed: degenerate axes - " + exception);
        }catch (Exception _){
            System.out.println("Rotation matrix creation failed: Unknown problem");
        }
    }
    public void setRotation(Space space){
        this.rotation = space.copy();
        displayWindow.setRotation(space.transformVector(Dimension.IDx()), space.transformVector(Dimension.IDy()), space.transformVector(Dimension.IDz()));
    }
    public void setCenter(Phybody center){
        this.center = center;
        displayWindow.setCenter(center.getPos());
        center.init();
    }
    public void repaint(){
        displayWindow.clearList();
        for(int i = data.length-1; i >=0; i --){
            if(data[i][0] != null){
                displayWindow.addBody(reconstructBody(i));
                displayWindow.faintList();
            }
        }
    }
    private Phybody[] reconstructBody(int time){
        Phybody[] bodyArray = new Phybody[massArray.length];
        for(int i = 0; i < massArray.length; i++){
            Dimension value = data[time][i];
            bodyArray[i] = new Phybody(massArray[i], rotation.transformVector(value));
        }
        return bodyArray;
    }
}