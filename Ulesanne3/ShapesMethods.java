/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ulesanne3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Alexander Chelpanov
 * Date: 12.10.2019
 * 
 * 
 */
public class ShapesMethods{
    public static List<Shape> shapes = new ArrayList<>();
    static{//генерация различных фигур для списка.
        for (double i = 0; i < 10; i++) {
            if (i==0) {
                shapes.add(new Circle());
                shapes.add(new Parallelogram());
                shapes.add(new Rhomb());
                shapes.add(new Trapeze());
                shapes.add(new Triangle());
            }
            shapes.add(new Circle(1,1,2+i,2+i));
            shapes.add(new Parallelogram(1,1,2+i,3+i,2+i,1,5+i,3+i));
            shapes.add(new Rhomb(2,1,1,2.5+i,2.25+i,1.5+i));
            shapes.add(new Trapeze(2,1,2,3,4+i,3,5+i,1));
            shapes.add(new Triangle(1,1,2.5+i,3+i,4+i,1+i));
        }  
    }
    /**
     * Вывод фигур.
     * @param list 
     */
    public static void output(List<Shape> list) {
        list.forEach((shape) -> {
            shape.printInfo();
        });
    }
    
    /**
     * Сортировка по площади. Используем лямбда-выражения и компаратор.
     * @param list
     * @return 
     */
    public static List sortingByArea(List<Shape> list) {
        Collections.sort(list,Comparator.comparingDouble(shape -> shape.getArea()));
        return list;
    }
    
    /**
     * Проверяет фигура с максимальной ли площадью в своей категории
     * @param shape
     * @return 
     */
    public static boolean isMaxArea(Shape shape){
        return shapes.stream().noneMatch((shape1) -> (shape.getClass().getName().equals(shape1.getClass().getName()) &&
                shape.getArea()<shape1.getArea()));
    }
    
    /**
     * Проверяет фигура с минимальной ли площадью в своей категории
     * @param shape
     * @return 
     */
    public static boolean isMinArea(Shape shape){
        return shapes.stream().noneMatch((shape1) -> (shape.getClass().getName().equals(shape1.getClass().getName()) &&
                shape.getArea()>shape1.getArea()));
    }
    
    /**
     * Метод для создания списка каждого типа фигуры с максимальной и минимальной площадью
     * @param list
     * @return 
     */
    public static List sorting(List<Shape> list){
        List<Shape> list1 = new ArrayList<>();
        list.stream().filter((shape) -> (isMaxArea(shape) || isMinArea(shape))).forEachOrdered((shape) -> {
            list1.add(shape);
        });
        return list1;
    }

}
