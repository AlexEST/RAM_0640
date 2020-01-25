/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ulesanne3;

/**
 *
 * @author Alexander Chelpanov
 * Date 12.10.2019
 * 
 * 
 */
public class Rhomb implements Shape, Polygon {
    private double Width;//сторона ромба
    private double Height;// высота ромба проведенная под прямым углом из угла к стороне
    
    Rhomb(){
        Width = 1.0;
        Height = 0.5;
    }
    //на входе декартовы координаты точек. Координаты одной стороны и высота. (Все стороны ромба равны)
    Rhomb(double Ax, double Ay, double Bx, double By, double Hx, double Hy){
        this.Width = calcWidth(Ax,Ay,Bx,By);
        this.Height = calcHeight(Bx,By,Hx,Hy);
    }

    public void setWidth(double Ax, double Ay, double Bx, double By) {
        this.Width = calcWidth(Ax,Ay,Bx,By);
    }
    
    public void setHeight(double Bx, double By, double Hx, double Hy){
        this.Width = calcHeight(Bx,By,Hx,Hy);
    }
    
    public double getWidth(){
        return Width;
    }
    
    public double getHeight(){
        return Height;
    }
        
    @Override
    public double getArea() {
        return Height*Width;
    }

    @Override
    public double getPerimeter() {
        return 4*Width;
    }

    @Override
    public void printInfo() {
        String out = String.format(GREEN+"RHOMB:"+RESET+BLUE+" Width"+RESET+" %.2f"
                +BLUE+" Height"+RESET+" %.2f "
                +BLUE+ " Perimeter"+RESET+" %.2f"+BLUE+" Area"+RESET+" %.2f",
                Width,Height,getPerimeter(),getArea());
        System.out.println(out);        
    }

    @Override
    public final double calcWidth(double x, double y, double x2, double y2) {
        return Math.sqrt(Math.pow(x2-x, 2)+Math.pow(y2-y, 2));
    }

    @Override
    public final double calcHeight(double x, double y, double x2, double y2) {
        return Math.sqrt(Math.pow(x2-x, 2)+Math.pow(y2-y, 2)); 
    }  
}
