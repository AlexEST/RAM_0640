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
 * Интерфейс для многоугольников
 * 
 */
public interface Polygon {
    double calcWidth(double x, double y, double x2, double y2);
    double calcHeight(double x, double y, double x2, double y2);
}
