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
 * Debug 26.10.2019
 */
public class Parallelogram implements Shape, Polygon {
    private double Width;// сторона параллелограмма
    private double Width2;// сторона параллелограмма
    private double Height;// высота опущенная из конца стороны
    
    Parallelogram(){
        Width = 1.0;
        Width2 = 1.0;
        Height = 1.0;
    }
    //на входе декартовы координаты точек. Две стороны и высота.
    Parallelogram(double Ax, double Ay, double Bx, double By, double Hx, double Hy, double Cx, double Cy){
        this.Width = calcWidth(Ax,Ay,Bx,By);
        this.Height = calcHeight(Bx,By,Hx,Hy);
        this.Width2 = calcHeight(Bx,By,Cx,Cy);       
    }

    public double getWidth2() {
        return Width2;
    }

    public void setWidth2(double Bx, double By, double Cx, double Cy) {
        this.Width2 = calcHeight(Bx,By,Cx,Cy);
    }
    
    
    public void setWidth(double Ax, double Ay, double Bx, double By) {
        this.Width = calcWidth(Ax,Ay,Bx,By);
    }
    
    public void setHeight(double Bx, double By, double Cx, double Cy){
        this.Width = calcHeight(Bx,By,Cx,Cy);
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
        return 2*(Height+Width);
    }

    @Override
    public void printInfo() {
        String out = String.format(GREEN+"PARALLELOGRAM:"+RESET+BLUE+" First side"
                +RESET+" %.2f "+BLUE+" Second side"+RESET+" %.2f "+BLUE+"Height"+RESET+" %.2f "
                +BLUE+ " Perimeter"+RESET+" %.2f "+BLUE+"Area"+RESET+" %.2f",
                Width,Width2,Height,getPerimeter(),getArea());
        System.out.println(out);
    }

    @Override
    public final double calcWidth(double x, double y, double x2, double y2) {
        return Math.sqrt(Math.pow(x2-x, 2)+Math.pow(y2-y, 2));
    }

    @Override
    public final double calcHeight(double x, double y, double x2, double y2) {
        return  Math.sqrt(Math.pow(x2-x, 2)+Math.pow(y2-y, 2)); 
    }
}
