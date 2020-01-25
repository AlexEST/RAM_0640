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
public final class Triangle implements Shape,Polygon {
    private double sideA;
    private double sideB;
    private double sideC;
    private double Height;

    Triangle(){
        sideA = 1.0;
        sideB = 1.0;
        sideC = 1.0;
        Height = calcHeight(sideA,sideB,sideC,getHalfP());
    }
    
    Triangle(double Ax, double Ay, double Bx, double By, double Cx, double Cy){
        this.sideA = calcWidth(Ax,Ay,Bx,By);
        this.sideB = calcWidth(Ax,Ay,Cx,Cy);
        this.sideC = calcWidth(Bx,By,Cx,Cy);
        Height = calcHeight(sideA,sideB,sideC,getHalfP());//расчет высоты треугольника.
    }
    
    public void setSideA(double Ax, double Ay, double Bx, double By) {
        this.sideA = calcWidth(Ax,Ay,Bx,By);
    }
    
    public void setSideB(double Ax, double Ay, double Cx, double Cy){
        this.sideB = calcWidth(Ax,Ay,Cx,Cy);
    }
    
    public void setRight(double Bx, double By, double Cx, double Cy) {
        this.sideC = calcWidth(Bx,By,Cx,Cy);
    }

    
    public double getSideA(){
        return sideA;
    }
    
    public double getSideB(){
        return sideB;
    }
    
    public double getSideC(){
        return sideC;
    }
    
    public double getHalfP(){
        return (getPerimeter())/2;
    }
    
    @Override
    public final double calcWidth(double x, double y, double x2, double y2) {
        return Math.sqrt(Math.pow(x2-x, 2)+Math.pow(y2-2, 2));
    }
    
    @Override
    public double getPerimeter() {        
        return sideA+sideB+sideC;
    } 
    
    @Override
    public double getArea() {
        double halfP = getHalfP();//половина периметра
        return Math.sqrt(halfP*(halfP-sideA)*(halfP-sideB)*(halfP-sideC));//формула Герона
    }

    @Override
    public final double calcHeight(double sideA, double sideB, double sideC, double halfP) { 
        //Формула длины высоты через стороны
        return 2/sideA*Math.sqrt(halfP*(halfP-sideA)*(halfP-sideB)*(halfP-sideC));
    }
    
    @Override
    public void printInfo() {
        String out = String.format(GREEN+"TRIANGLE:"+RESET+BLUE+" Sides"+RESET+
                " %.2f %.2f %.2f"+BLUE+" Height"+RESET+" %.2f "
                +BLUE+ " Perimeter"+RESET+" %.2f"+BLUE+" Area"+RESET+
                " %.2f",sideA,sideB,sideC,Height,getPerimeter(),getArea());
        System.out.println(out);
    }    
}
