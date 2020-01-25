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
 * Интерфейс для всех фигур.
 * 
 */
public interface Shape{
    public static final String BLUE = "\u001B[34m";
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";   
    
    double getArea();
    double getPerimeter();
    void printInfo();   
}


