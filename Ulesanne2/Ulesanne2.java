/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ulesanne2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Alexander Chelpanov EDTR34
 * Date: 16.09.2019
 * 
 * Cобытия в календаре
 * 
 * 1.Создайте абстрактный класс Event
 * 2.Создайте производные классы:  StandartEvent (название события, дата ), 
 * EventReminder (название,  дата , время для напоминания, частота повторений), 
 * EventDistribution (название, дата, список для рассылки, текст рассылки, дата рассылки)
 * 3.Создайте массив из n видов событий или список ArrayList
 * 4.Необходимо вывести полную информацию из массива на экран, а также 
 * организовать вывод событий, напоминаний и рассылок на  конкретную дату. 
 * Oрганизовать поиск по названию (фрагменту названия).
 * 5.Используй текстовый файл для хранения информации
 * 
 */
public class Ulesanne2 {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args) {
        Menu();
    }
    
    public static void Menu(){
        System.out.println("=========================================");
        System.out.println("|                 МЕНЮ                  |");
        System.out.println("=========================================");
        System.out.println("|Разделы:                               |");
        System.out.println("|1.Вывод всех событий                   |");
        System.out.println("|2.Поиск события по дате                |");
        System.out.println("|3.Поиск события по названию            |");
        System.out.println("|4.Выход                                |");
        System.out.println("=========================================");
        System.out.println("");
        System.out.println("Выберите раздел и нажмите Enter:");
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        while (true) {
            switch (choice) {
            case 1:
                System.out.println("Вывод всех событий:");
                EventDAO.output();
                EventDAO.writeToTextFile(EventDAO.events);
                List<Event> list1 = new ArrayList<>(EventDAO.events);
                whatNext(list1);
                break;
            case 2:
                System.out.println("Поиск событий по дате: ");
                System.out.println("Введите дату цифрами через точку (пример: 25.9.2019 (день.месяц.год))");
                String date = scan.next();
                EventDAO.output(EventDAO.eventByDate(date));
                List<Event> list2 = new ArrayList<>(EventDAO.eventByDate(date));
                if (list2.isEmpty()==true) {
                    System.out.println("Не было найдено событий соответсвующих критериям поиска.");
                }
                whatNext(list2);
                break;
            case 3:
                System.out.println("Поиск событий по названию: ");
                String partName = scan.next();
                List<Event> evs = new ArrayList<>(EventDAO.eventByName(partName));
                if (evs.isEmpty()==true) {
                    System.out.println("Не было найдено событий соответсвующих критериям поиска.");
                }
                EventDAO.output(evs);
                whatNext(evs);
                break;
            case 4:
                System.out.println("Выход");
                System.exit(0);
            default:
                System.out.println(ANSI_RED+"Неверный выбор! Вы должны ввести "
                            + "число от 1 до 4! 4 - это выход из программы."+ANSI_RESET);
                whatNext();
                break;
            }
        }        
    }
    
    /**
     * Метод для определения дальнейших действий 
     * 
     * @return 
     */
    private static int whatNext(List<Event> list) {
        while (true) {
            System.out.println("Введите 1 если хотите завершить программу. "
                    + "Если хотите вернуться в меню введите 2. Для выгрузки данных"
                    + " в текстовый файл нажмите 3.(После возврат в меню.)");
            Scanner choice = new Scanner(System.in);
            int input = choice.nextInt();
            if (input == 2) {
                Menu();
            } else if (input == 1) {
                System.exit(0);
            } else if (input == 3) {
                EventDAO.writeToTextFile(list);
                Menu();
            } else {
                System.out.println(ANSI_RED+"Вы ввели неверный вариант!"+ANSI_RESET);
            }
        }
    }
    
    private static int whatNext() {
        while (true) {
            System.out.println("Введите 1 если хотите завершить программу. "
                    + "Если хотите вернуться в меню введите 2");
            Scanner choice = new Scanner(System.in);
            int input = choice.nextInt();
            if (input == 2) {
                Menu();
            } else if (input == 1) {
                System.exit(0);
            } else {
                System.out.println(ANSI_RED+"Вы ввели неверный вариант!"+ANSI_RESET);
            }
        }
    }
}
