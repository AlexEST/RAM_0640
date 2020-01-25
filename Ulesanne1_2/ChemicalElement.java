/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ulesanne1_2;

/**
 *
 * @author Alexander Chelpanov EDTR34
 * Date: 10.09.2019
 * 
 * 
 */
public class ChemicalElement {
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";
    
    private String Element;
    private int Number;
    private String Symbol;
    private double Weight;
    private double Boil;
    private double Melt;
    private double Density;
    private double Vapour;
    private double Fusion;
    
    public ChemicalElement(){
    }
    
    public ChemicalElement(String Element, int Number, String Symbol, double Weight){
        this.Element = Element;
        this.Number = Number;
        this.Symbol = Symbol;
        this.Weight = Weight;        
    }
    
    public ChemicalElement(String Element, int Number, String Symbol, double Weight,
            double Boil, double Melt, double Density, double Vapour, double Fusion){
        this.Element = Element;
        this.Number = Number;
        this.Symbol = Symbol;
        this.Weight = Weight;
        this.Boil = Boil;
        this.Melt = Melt;
        this.Density = Density;
        this.Vapour = Vapour;
        this.Fusion = Fusion;
    }
    
    public void setElement(String Element){
        this.Element = Element;
    }
    
    public void setNumber(int Number){
        this.Number = Number;
    }
    
    public void setSymbol(String Symbol){
        this.Symbol = Symbol;
    }
    
    public void setWeight(double Weight){
        this.Weight = Weight;
    }
    
    public void setBoil(double Boil){
        this.Boil = Boil;
    }
    
    public void setMelt(double Melt){
        this.Melt = Melt;
    }
    
    public void setDensity(double Density){
        this.Density = Density;
    }
    
    public void setVapour(double Vapour){
        this.Vapour = Vapour;
    }
    
    public void setFusion(double Fusion){
        this.Fusion = Fusion;
    }
    
    public String getElement(){
        return Element;
    }
    
    public int getNumber(){
        return Number;
    }
    
    public String getSymbol(){
        return Symbol;
    }
    
    public double getWeight(){
        return Weight;
    }
    
    public double getBoil(){
        return Boil;
    }
    
    public double getMelt(){
        return Melt;
    }
    
    public double getDensity(){
        return Density;
    }
    
    public double getVapour(){
        return Vapour;
    }
    
    public double getFusion(){
        return Fusion;
    }
    
    
    @Override
    public String toString() {
        return ANSI_BLUE+"Element: "+ANSI_RESET+getElement()+ANSI_BLUE+" Number: "+
                ANSI_RESET+getNumber()+ANSI_BLUE+" Symbol: "+ANSI_RESET+getSymbol()
                +ANSI_BLUE+" Weight: "+ANSI_RESET+getWeight();
    }
}
