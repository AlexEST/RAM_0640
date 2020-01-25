/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ulesanne3;

/**
 *
 * @author Alexander Chelpanov
 * Date: 12.10.2019
 * 
 * 
 */
public class Trapeze implements Shape, Polygon {
    private double sideBottom;
    private double sideTop;
    private double sideRight;
    private double sideLeft;
    private double Height;

    Trapeze(){
        sideTop = 0.5;
        sideBottom = 1.0;
        sideRight = 1.5;
        sideLeft = 1.3;
        Height = calcHeight(sideLeft,sideBottom,sideTop,sideRight);
    }
    //на входе декартовы координаты точек
    Trapeze(double Ax, double Ay, double Bx, double By, double Cx, double Cy, double Dx, double Dy){
        this.sideLeft = calcWidth(Ax,Ay,Bx,By);
        this.sideTop = calcWidth(Bx,By,Cx,Cy);
        this.sideRight = calcWidth(Cx,Cy,Dx,Dy);
        this.sideBottom = calcWidth(Ax,Ay,Dx,Dy);
        Height = calcHeight(sideLeft,sideBottom,sideTop,sideRight);
    }
    
    public void setTop(double Bx, double By, double Cx, double Cy) {
        this.sideTop = calcWidth(Bx,By,Cx,Cy);
    }
    
    public void setBottom(double Ax, double Ay, double Dx, double Dy){
        this.sideBottom = calcWidth(Ax,Ay,Dx,Dy);
    }
    
    public void setRight(double Cx, double Cy, double Dx, double Dy) {
        this.sideRight = calcWidth(Cx,Cy,Dx,Dy);
    }
    
    public void setLeft(double Ax, double Ay, double Bx, double By){
        this.sideLeft = calcWidth(Ax,Ay,Bx,By);
    }
    
    public double getTop(){
        return sideTop;
    }
    
    public double getBottom(){
        return sideBottom;
    }
    
    public double getLeft(){
        return sideLeft;
    }
    
    public double getRight(){
        return sideRight;
    }
     
    @Override
    public double getArea() {
        return ((sideBottom+sideTop)/2)*Height;
    }

    @Override
    public double getPerimeter() {
        return sideTop+sideBottom+sideRight+sideLeft;
    }

    @Override
    public void printInfo() {
        String out = String.format(GREEN+"TRAPEZE:"+RESET+BLUE+" Sides"+RESET+
                " %.2f %.2f %.2f %.2f"+BLUE+" Height"+RESET+" %.2f"
                +BLUE+ " Perimeter"+RESET+" %.2f"+BLUE+" Area"+RESET+" %.2f",
                sideBottom,sideTop,sideLeft,sideRight,Height,getPerimeter(),getArea());
        System.out.println(out);
    }

    @Override
    public final double calcWidth(double x, double y, double x2, double y2) {
        return Math.sqrt(Math.pow(x2-x, 2)+Math.pow(y2-y, 2));
    }
    
    //x-sideleft, y-sideBottom, x2-sideTop, y2-sideRight 
    @Override
     public final double calcHeight(double sideLeft, double sideBottom, double sideTop, double sideRight) {
        return Math.sqrt(sideLeft*sideLeft-Math.pow((((sideBottom-sideTop)*(sideBottom-sideTop)+sideLeft*sideLeft-
                        sideRight*sideRight)/2*(sideBottom-sideTop) ), 2)); 
    }
}
