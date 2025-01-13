package statics.app.view;

import statics.app.model.SystemPos;

import java.awt.*;

//todo rewrite
public class PaintingUtil {

    public static void drawTriangleOnPoint(Graphics2D g2d, ScreenPos c, double pAngle, double pRadius){
        double[] f1, f2;

        f1 = getRadiusTipOffset(pAngle + 145, pRadius);
        f2 = getRadiusTipOffset(pAngle + 215, pRadius);


        int[] i1 = {c.getX()+(int)Math.round(f1[0]),c.getY()+(int)Math.round(f1[1])};
        int[] i2 = {c.getX()+(int)Math.round(f2[0]),c.getY()+(int)Math.round(f2[1])};


        g2d.drawLine(i1[0],i1[1],i2[0],i2[1]);
        g2d.drawLine(i1[0],i1[1],c.getX(),c.getY());
        g2d.drawLine(c.getX(),c.getY(),i2[0],i2[1]);
    }

    /**
     * berechnet den Winkel von Y-Achse an Punkt1 zu Punkt2
     * @param pFrom Punkt1
     * @param pTo Punkt2
     * @return Winkel in Grad, im Uhrzeigersinn
     */
    public double angleNegYToPoint(ScreenPos pFrom, ScreenPos pTo){
        if (pFrom.equals(pTo)){

            return 0d;

        }

        double deltaY = pTo.getY() - pFrom.getY();

        double deltaX = pTo.getX() - pFrom.getX();


        double angle = Math.toDegrees(Math.atan2(deltaY, deltaX));


        if (angle < 0) {

            angle += 360;

        }
        return angle;
    }

    /**
     * Berechnet den Punkt auf einem Kreis, der die Spitze des Radius in einem gegebenen Winkel darstellt
     * @param pPt Mittelpunkt des Kreises
     * @param pAngle Winkel des Radius
     * @return Punkt auf dem Rand
     */
    private ScreenPos getPtWOffsetAtAngle(ScreenPos pPt, ViewState vs,double pAngle, double pRad){

        if (pAngle < 0) {
            pAngle += 360;
        }

        //System.out.println("angle "+getFrom().getId()+" to "+getTo().getId()+" "+angle1to2);

        double[] pOff = getRadiusTipOffset(pAngle, vs.getScale()*pRad);//mit Einbezug der Kreisgröße

        ScreenPos p = new ScreenPos(0,0);

        p.setX((int)Math.round(pPt.getX()-pOff[1]));
        p.setY((int)Math.round(pPt.getY()-pOff[0]));

        return p;
    }


    //Ausschnitt aus altem Artilleriesimulator; rewrite vtl? nuh uh keine Zeit
    /**
     * Berechnet den Offset, den die Spitze des Radius in einem gegebenen Winkel der Mitte eines Kreises gegenüber innehält
     * @param pAngle Winkel des Radius
     * @param pRad Betrag des Radius
     * @return Array mit Offset X und Y
     */
    public static double[] getRadiusTipOffset(double pAngle, double pRad){
        double tipPos[] = new double[2];
        double angle = pAngle;
        double rad = pRad;

        if(angle==0){
            tipPos[0]=-0;
            tipPos[1]=-rad;
        }

        if(angle>0&&angle<90){

            angle = 90-angle;

            tipPos[0]=-calcAnkathete(angle,rad);
            tipPos[1]=calcGgKathete(angle,rad)*-1;
        }

        if(angle==90){
            tipPos[0]=-rad;
            tipPos[1]=0;
        }

        if(angle>90&&angle<180){

            angle = angle-90;

            tipPos[0]=-calcAnkathete(angle,rad);
            tipPos[1]=calcGgKathete(angle,rad);
        }

        if(angle==180){
            tipPos[0]=-0;
            tipPos[1]=rad;
        }

        if(angle>180&&angle<270){

            angle = 270-angle;

            tipPos[0]=-calcAnkathete(angle,rad)*-1;
            tipPos[1]=calcGgKathete(angle,rad);
        }

        if(angle==270){
            tipPos[0]=rad;
            tipPos[1]=0;
        }

        if(angle>270/*&&angle<360*/){

            angle = angle-270;

            tipPos[0]=-calcAnkathete(angle,rad)*-1;
            tipPos[1]=calcGgKathete(angle,rad)*-1;
        }

        return tipPos;
    }

    /**
     * Kalkuliert die Ankathete aus Winkel und Hypotenuse
     * @param pAngle Winkel
     * @param pHypo Hypotenuse
     * @return Länge der Ankathete
     */
    private static double calcAnkathete(double pAngle, double pHypo){
        return (Math.cos(Math.toRadians(pAngle))*pHypo);
    }

    /**
     * Kalkuliert die Gegenkathete aus Winkel und Hypotenuse
     * @param pAngle Winkel
     * @param pHypo Hypotenuse
     * @return Länge der Gegenkathete
     */
    private static double calcGgKathete(double pAngle, double pHypo){
        return (Math.sin(Math.toRadians(pAngle))*pHypo);
    }
}
