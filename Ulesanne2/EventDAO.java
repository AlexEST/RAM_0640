/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ulesanne2;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author Alexander Chelpanov 182730 EDTR34
 * Date: 16.09.2019
 * 
 */
public class EventDAO {
    public static List<Event> events = new ArrayList<>();
    static {
        //генерация каких-то произвольных событий
        for (int i = 1; i < 20; i++) {
            Calendar date = new GregorianCalendar(2019, Calendar.SEPTEMBER , i);
            Event one = new StandartEvent("Event"+i,date);
            events.add(one);
            Event two = new EventReminder("Holiday"+i,date,date,i);
            events.add(two);
            Event three = new EventDistribution("Birthday"+i,date,"Anton, John, Omar"," Gongratulations!",date);
            events.add(three);
        }
    }
    
    /**
     * Метод для поиска событий по дате. На входе строка с датой.
     * @param date
     * @return 
     */
    public static List<Event> eventByDate(String date){
        //для расшифровки строки использую класс StringTokenizer. Сепаратор точка
        StringTokenizer st = new StringTokenizer(date,".");
        List<Event> Evs = new ArrayList<>();
        Calendar date2 = new GregorianCalendar();//переменная для записи даты
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM yyyy");//для отображения переменных типа календар
        String [] sarr = new String [3];
        int counter = 0;
        String token;
        //разбор строки на отдельные лексемы и запись их в массив
        while(st.hasMoreTokens()) {
	    token = st.nextToken();
	    sarr[counter] = token;
            counter++;
        }
        // пишем в массив и одновременно парсим в число
        date2.set(Integer.parseInt(sarr[2]), Integer.parseInt(sarr[1])-1, Integer.parseInt(sarr[0]));
        //далее ищем в листе с событиями события с нашей датой
        for (Event event : events) {
            if (dateFormat.format(event.getDate().getTime()).equals(dateFormat.format(date2.getTime()))) {
                Evs.add(event);
            }
        }
        return Evs;
    }
    
    /**
     * Метод для поиска по началу или полному названию события
     * @param name
     * @return 
     */
    public static List<Event> eventByName(String name){
        name = name.replaceAll(" ", "");//убираем все пробелы
        List<Event> evs = new ArrayList<>();
        for (Event event : events) {
            //ищем нашу подстроку в строке название события игнорируя регистр букв
            if (event.getName().regionMatches(true, 0, name, 0, name.length())==true) {
                evs.add(event);
            }
        }
        return evs;
    }
    
    /**
     * Выгрузка в файл.
     * @param evs 
     */
    public static void writeToTextFile(List<Event> evs){
        try (FileWriter writer = new FileWriter("events.txt")) {
            for(Event event : evs){
                String lineSeparator = System.getProperty("line.separator");//сепаратор для переноса на новую строку
                writer.write(event.toString()+lineSeparator);
            }
            writer.flush();
            writer.close();//закрываем поток.
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Методы для вывода на экран. С листом на входе и его перегруженная версия без.
     * @param list 
     */
    public static void output(List<Event> list){
        list.forEach((object) -> {
            System.out.println(object);
        });
    }
    
    public static void output(){
        events.forEach((_item) -> {
            System.out.println(_item);
        });
    }
}
