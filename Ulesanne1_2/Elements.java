/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ulesanne1_2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Double.NaN;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexander Chelpanov
 * Date: 10.09.2019
 * 
 * 
 */
public class Elements {
    public static List<ChemicalElement> elements = new ArrayList<>();
    static{
        try {
            BufferedReader reader = new BufferedReader(new FileReader(
                "elements.csv"));
            String Line;
            int skip = 0;
            while ((Line = reader.readLine()) != null) {
                Line = Line.substring(0, Line.length()-1);//отрезаю запятую в конце
                String[] line = Line.split(",");//запятая сепаратор
                if (skip > 0) {//скип для пропуска первой строки
                    // в файле попадаются пустые значения. Заменяю их на NaN.
                    for (int i = 0; i < line.length; i++) {
                            if (line[i].isEmpty()) {
                                line[i] = Double.toString(NaN);
                            }
                        }
                    // В зависимости от длины массива в разные конструкторы
                    if (line.length==9) { 
                        //Соответственно строки парсим где надо в числа.
                        ChemicalElement element = new ChemicalElement(line[0], 
                            Integer.parseInt(line[1]), line[2], Double.parseDouble(line[3]),
                            Double.parseDouble(line[4]), Double.parseDouble(line[5]),
                            Double.parseDouble(line[6]), Double.parseDouble(line[7]),
                            Double.parseDouble(line[8]));
                    elements.add(element);     
                    }else{
                        ChemicalElement element = new ChemicalElement(line[0], 
                            Integer.parseInt(line[1]), line[2], Double.parseDouble(line[3])
                            );
                        elements.add(element);
                    }  
                }
                skip=1;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Elements.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Elements.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Поиск элемента по символу. Элементы для сравнения перевожу в нижний регистр.
     * 
     * @param Symbol
     * @return 
     */
    public static ChemicalElement getBySymbol(String Symbol){
        for (ChemicalElement element : elements) {
            if (element.getSymbol().toLowerCase().equals(Symbol.toLowerCase())) {
                return element;
            }
        }
        return null;
    }
    
    /**
     * Метод для расчета молекулярной массы. На входе строка. Считает формулы вида
     * H2SO4, H2O, NaCl, KOH и тому подобное. В виде Na+CL не считает.
     * Проверил с десяток соединений вроде правильно.
     * 
     * @param string
     * @return 
     */
    public static double calculateMass(String string){
        string = string.trim();//режем пробелы в начале и конце
        string = string.replace(" ", "");// убираем пробелы в строке
        char [] arr = string.toCharArray();// строку нарезаем в массив
        double mass = 0;
        String str = "";
        for (int i = 0; i < arr.length; i++) {
            //проверка буква или цифра и далее расшифровка строки
            if (Character.isLetter(arr[i])) {
                if(i<1 && arr.length==2 && Character.isUpperCase(arr[i])==true && 
                        Character.isLetter(arr[i+1])==true
                        && Character.isLowerCase(arr[i+1])==true){
                    str += Character.toString(arr[i])+Character.toString(arr[i+1]);
                    mass += getWeight(str);
                    return mass;
                }else if (i+1<arr.length && Character.isUpperCase(arr[i])==true 
                        && Character.isLetter(arr[i+1])==false) {
                    mass += getWeight(Character.toString(arr[i]));
                }else if(i+1<arr.length && Character.isUpperCase(arr[i])==true 
                        && Character.isLetter(arr[i+1])==true
                        && Character.isUpperCase(arr[i+1])==true){
                    mass += getWeight(Character.toString(arr[i]));
                }else if(i+1<arr.length && Character.isUpperCase(arr[i])==true 
                        && Character.isLetter(arr[i+1])==true
                        && Character.isUpperCase(arr[i+1])==false){
                    str += Character.toString(arr[i])+Character.toString(arr[i+1]);
                    mass += getWeight(str);
                    str = "";
                    i++;             
                }else if(i==arr.length-1 && Character.isUpperCase(arr[i])==true){
                    mass += getWeight(Character.toString(arr[i]));
                }else if(arr.length==1){
                    mass += getWeight(Character.toString(arr[i]));
                }
            }
            if (Character.isDigit(arr[i])) {
                if (Character.isLowerCase(arr[i - 1]) == true) {
                    str += Character.toString(arr[i - 2]) + Character.toString(arr[i - 1]);
                    mass = mass - getWeight(str) + (getWeight(str)
                            * Double.parseDouble(Character.toString(arr[i])));
                    str = "";
                } else {
                    mass = mass - getWeight(Character.toString(arr[i - 1]))
                            + (getWeight(Character.toString(arr[i - 1]))
                            * Double.parseDouble(Character.toString(arr[i])));
                }
            }
        }
        return mass;
    }
    
    /**
     * Метод который возвращает молекулярный вес элемента. На входе строка.
     * @param s
     * @return 
     */
    public static double getWeight(String s){
        for (ChemicalElement element : elements) {
            if (element.getSymbol().toLowerCase().equals(s.toLowerCase())) {
                return element.getWeight();
            }
        }
        return NaN;
    }
}
