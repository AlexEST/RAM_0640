/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ulesanne1_1;


/**
 *
 * @author Alexander Chelpanov EDTR34
 * Date: 07.09.2019
 * 
 * 
 */
public class Book {
    public static final String ANSI_GREEN = "\u001B[32m";//зеленый цвет
    public static final String ANSI_RESET = "\u001B[0m";//сброс цвета
    
    private String ID;
    private String Type;
    private String Tytle;
    private String Author;
    private double Price;
    private int Qty;
    
    public Book(){
    }
    
    public Book(String ID, String Type, String Tytle, String Author, double Price, int Qty){
        this.ID = ID;
        this.Type = Type;
        this.Tytle = Tytle;
        this.Author = Author;
        this.Price = Price;
        this.Qty = Qty;
    }
    //сеттеры
    public void SetID(String ID){
        this.ID = ID;
    }
    
    public void SetType(String Type){
        this.Type = Type;
    }
    
    public void SetTytle(String Tytle){
        this.Tytle = Tytle;
    }
    
    public void SetAuthor(String Author){
        this.Author = Author;
    }
    
    public void SetPrice(Double Price){
        this.Price = Price;
    }
    
    public void SetQty(int Qty){
        this.Qty = Qty;
    }
    //далее список геттеров
    public String getID(){
        return ID;
    }
    
    public String getType(){
        return Type;
    }
    
    public String getTytle(){
        return Tytle;
    }
    
    public String getAuthor(){
        return Author;
    }
    
    public double getPrice(){
        return Price;
    }
    
    public int getQty(){
        return Qty;
    }
    
    //вывод в строку
    @Override
    public String toString(){
        return ANSI_GREEN+"Type: "+ANSI_RESET+getType()+"."+ANSI_GREEN+" Tytle: "
                +ANSI_RESET+getTytle()+"."+ANSI_GREEN+" Author: "+ANSI_RESET+getAuthor()+"."+
                ANSI_GREEN+" Price: "+ANSI_RESET+getPrice();                
    }
}
