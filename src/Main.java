import Display.DimDisplay;
import Physics.*;

public class Main {
    public static void main(String[] args) {
        Phybody.setTime(0.0001);
        Phybody[] bodyArray = Phybody.testBodyArray();
        Phybody center = Phybody.getCenter(bodyArray);
        for(Phybody body : bodyArray){
            body.setVel(0);
            System.out.println(body);
        }
        center.addVel(new Dimension(10,-10,4));
        center.init();
        System.out.println(Phybody.getEnergy(bodyArray, Dimension.zero(), 2));
        DimDisplay display = new DimDisplay(bodyArray, 7, 4);
        display.setRotation(Space.rotateZ270());
        display.addData(bodyArray);
        display.setRotation(Space.Cartesian());
        //physics
        Phybody.init(bodyArray, 2, false);
        while(true) {
            for (double rotate = 0; rotate < 2 * Math.PI; rotate += 0.0005) {
                try {
                    Thread.sleep(2);
                } catch (InterruptedException _) {}
                for(int b = 0; b < bodyArray.length; ++b){
                    for(int r = 0; r < bodyArray.length; ++r){
                        if (b == r)continue;//avoid self-interaction
                        //interact with body
                        bodyArray[b].applyGravity(bodyArray[r],2);
                    }
                }
                for(int b = 0; b < bodyArray.length; ++b){
                    bodyArray[b].update();
                }
                center.update();
                display.setCenter(center);
                display.setRotation(Space.Cartesian().rotate(new Dimension(1,-4,-2), rotate));
                display.repaint();
                display.addData(bodyArray);
                //System.out.println(Phybody.getEnergy(bodyArray, Dimension.zero(), 2));
                /*try {
                   display.setRotation(Space.Cartesian().rotate(new Dimension(32, 45, -16), rotate));
                } catch (IndexOutOfBoundsException _){}*/
            }

        }
    }
}

