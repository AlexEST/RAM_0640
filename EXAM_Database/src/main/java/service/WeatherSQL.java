/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Weather;
import java.util.Date;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;

/**
 *
 * @author Alexander Chelpanov
 * Date: 25.01.2020
 * 
 */
public class WeatherSQL {

    public static double getAvgWeather(String city) {
        double avg = 0;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            TypedQuery<Double> query = session.createQuery("SELECT AVG(w.temperature) FROM Weather w"
                    + " where lower(w.city)=:city" );
            query.setParameter("city", city);
            avg = query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Ошибка. Нет данных по вашему городу.");
        }
        return avg;
    }
    
    public static Integer getWeatherToday(String city, Date today) {
        Integer temp = 0;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            TypedQuery<Integer> query = session.createQuery("SELECT w.temperature FROM Weather w"
                    + " where lower(w.city)=:city and w.date=:date" );
            query.setParameter("city", city);
            query.setParameter("date", today);
            temp = query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Ошибка. Нет данных по вашему городу.");
        }
        return temp;
    }
    
    public static List<Weather> getAllWeather() {
        List<Weather> list;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            TypedQuery<Weather> query = session.createQuery("FROM Weather");
            list = query.getResultList();
        }
        return list;
    }
    
    public static List<Weather> getAllWeatherForCity(String city) {
        List<Weather> list = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            TypedQuery<Weather> query = session.createQuery("FROM Weather w WHERE lower(w.city)=:city");
            query.setParameter("city", city);
            list = query.getResultList();
        } catch (Exception e) {
            System.out.println("Ошибка. Нет данных по вашему городу. Возможно неправильный ввод.");
        }
        return list;
    }
}
