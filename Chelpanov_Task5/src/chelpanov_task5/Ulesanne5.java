/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chelpanov_task5;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Alexander Chelpanov
 * Date: 6.11.2019
 * 
 * Создайте класс Person, на основании файла person.json. Получите список объектов из файла
 * 
 * Распечатайте информацию:
 * 
 * Все объекты класса Person
 * У какого день рождение в ближайшие 30 дней
 * Кто знает больше 2 языков
 * У кого нет e-mail
 * 
 * Реализуйте добавление/редактирование/удаление объекта c сохранением в файл
 * 
 * ВЕРСИЯ БЕЗ MAVEN.
 * 
 * 14.11.2019 - some bugs fixed
 */
public class Ulesanne5 {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    
    public static void main(String[] args) throws IOException, FileNotFoundException, org.json.simple.parser.ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("people.json"));
        JSONArray json =  (JSONArray) obj;
        String jsonString = json.toString();
        List<Person> list = JSONreader.getPersons(jsonString);
        Menu(list); 
    }
    
    public static void Menu(List<Person> list) throws FileNotFoundException, IOException, org.json.simple.parser.ParseException {        
        System.out.println("=========================================");
        System.out.println("|                 МЕНЮ                  |");
        System.out.println("=========================================");
        System.out.println("|Разделы:                               |");
        System.out.println("|1.Вывод всех обьектов класса Person    |");
        System.out.println("|2.Вывод людей у кого день рождения в   |");
        System.out.println("|  ближайшие 30 дней.                   |");
        System.out.println("|3.Вывод людей знающих больше 2 языков  |");
        System.out.println("|4.Вывод людей без e-mail               |");
        System.out.println("|5.Удаление обьекта                     |");
        System.out.println("|6.Добавление обьекта                   |");//modified 14.11.2019 
        System.out.println("|7.Редактирование обьекта               |");//added 14.11.2019
        System.out.println("|8.Выход                                |");
        System.out.println("=========================================");
        System.out.println("");
        System.out.println("Выберите раздел и нажмите Enter:");        
        try {
            Scanner scan = new Scanner(System.in);
            int choice = scan.nextInt();
            while (true) {
                switch (choice) {
                    case 1:
                        System.out.println("Вывод всех обьектов класса Person:");
                        list.forEach((person) -> {
                            System.out.println(person);
                        });
                        whatNext(list);
                        break;
                    case 2:
                        System.out.println("Вывод людей, у кого день рождения в ближайшие"
                                + " 30 дней:");
                        list.stream()
                                .filter(a -> Methods.checkBirthday(a.getBirthDay())==true)
                                .forEach(a->System.out.println(a));
                        whatNext(list);
                        break;
                    case 3:
                        System.out.println("Вывод людей знающих больше 2 языков: ");
                        list.stream()
                                .filter(a -> a.getLanguages()!=null)
                                .filter(a -> a.getLanguages().size()>2)
                                .forEach(a -> System.out.println(a));
                        whatNext(list);
                        break;
                    case 4:
                        System.out.println("Вывод людей без e-mail: ");
                        list.stream()
                                .filter(a->a.getEmails()==null)
                                .forEach(a->System.out.println(a));
                        whatNext(list);
                        break;
                    case 5:
                        System.out.println("Удаление обьекта: ");
                        list = Methods.removePerson(list);                        
                        whatNext(list);
                        break;
                    case 6:
                        System.out.println("Добавление обьекта: ");
                        list = Methods.addPerson(list);
                        whatNext(list);
                        break;
                    case 7:
                        System.out.println("Редактирование обьекта: ");
                        list = Methods.editPersons(list);
                        whatNext(list);
                        break;    
                    case 8:
                        System.out.println("Выход из программы: ");
                        System.exit(0);
                    default:
                        System.out.println(ANSI_RED + "Неверный выбор! Вы должны ввести "
                                + "число от 1 до 8! 8 - это выход из программы." + ANSI_RESET);
                        whatNext(list);
                        break;
                }
                break;
            }
        } catch (InputMismatchException е) {
            System.out.println(ANSI_RED+"ВВОД НЕВЕРНЫЙ!"+ANSI_RESET+"Попробуйте заново.");
            whatNext(list);
        }
    }
    
    /**
     * Метод для определения дальнейших действий в меню.
     * @param list
     * @throws IOException
     * @throws FileNotFoundException
     * @throws org.json.simple.parser.ParseException 
     */
    private static void whatNext(List list) throws IOException, FileNotFoundException, org.json.simple.parser.ParseException {
        while (true) {
            System.out.println("Введите 1 если хотите завершить программу. "
                    + "Если хотите вернуться в меню введите 2");
            Scanner choice = new Scanner(System.in);
            int input = choice.nextInt();
            switch (input) {
                case 2:
                    Menu(list);
                    break;
                case 1:
                    System.exit(0);
                default:
                    System.out.println(ANSI_RED+"Вы ввели неверный вариант!"+ANSI_RESET);
                    break;
            }
        }
    }
}
