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
 * Modified: 20.10.2019
 * 
 */

public final class Circle implements Shape, Circumference{
    private double radius;
    
    Circle(){
        radius = 1.0;
    }
    
    Circle(double Ax, double Ay, double Bx, double By){
        this.radius = getRadius(Ax,Ay,Bx,By);
    }
    
    public void setRadius(double Ax, double Ay, double Bx, double By){
        this.radius = getRadius(Ax,Ay,Bx,By);
    }
    
    public double getRadius(){
        return radius;
    }
    
    @Override
    public double getArea() {
        return PI*radius*radius;//площадь расчет
    }

    @Override
    public double getPerimeter() {
       return 2*PI*radius;//расчет периметра
    }

    @Override
    public void printInfo() {
        String out = String.format(GREEN+"CIRCLE: "+RESET+BLUE+"Radius"+RESET+" %.2f "+BLUE+"Perimeter"+RESET+" %.2f "
                +BLUE+ " Area"+RESET+" %.2f",getRadius(),getPerimeter(),getArea());
        System.out.println(out);
    }

    @Override
    public double getDiameter(double radius) {
        return radius*2;
    }

    @Override
    public double getRadius(double x, double y, double x2, double y2) {
        return Math.sqrt(Math.pow(x2-x, 2)+Math.pow(y2-y, 2));
    }

}
