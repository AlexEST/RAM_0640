/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ulesanne1_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Alexander Chelpanov EDTR34
 * Date: 07.09.2019
 * 
 * Задание 1_1
 * 
 * Создайте класс Book, который содержит поля, методы get/set (свойства), 
 * конструкторы, метод toString и методы, реализующие следующие задачи:
 * 
 * 1.Отобразите книги определенной категории (например, Database, Programming ...)
 * 2.Отобразите книги, которые принадлежат определенному диапазону цен 
 * (например, книги стоимость, которых от 15 до 30 Евро)
 * 3.Осуществите поиск книг по нескольким критериям 
 * (например, по автору, названию, фрагменту названия...)
 * 
 * Данные в файле Books.csv
 */
public class Ulesanne1_1 {
    public static void main(String[] args) {
        Menu();
    }
    
    public static void Menu(){
        System.out.println("=========================================");
        System.out.println("|                 МЕНЮ                  |");
        System.out.println("=========================================");
        System.out.println("| Разделы:                              |");
        System.out.println("|1.Поиск книги по разделу               |");
        System.out.println("|2.Поиск по цене                        |");
        System.out.println("|3.Поиск по нескольким критериям        |");
        System.out.println("|4.Вывод всего списка книг              |");
        System.out.println("|5.Поиск книги с самой маленькой ценой  |");
        System.out.println("|6.Поиск по начальным буквам названия   |");
        System.out.println("|7.Поиск подстроки в названии книги     |");// добавленный раздел
        System.out.println("|8.Выход                                |");
        System.out.println("=========================================");
        System.out.println("");
        System.out.println("Введите номер пункта меню и нажмите Enter:");
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();

        // Вызывает соответсвующий выбору метод или завершает программу
        while (true) {
            switch (choice) {
                case 1:
                    System.out.println("Поиск по разделу.");
                    System.out.println("Введите название раздела");
                    String type = scan.next();
                    List<Book> typeList = new ArrayList<>(BooksDAO.findByType(type));
                    if (typeList.isEmpty()) {
                        System.out.println("Нет книг с таким разделом");
                    }
                    typeList.forEach((typeBook) -> {
                        System.out.println(typeBook);
                    });
                    whatNext();
                    break;
                case 2:
                    System.out.println("Вы выбрали поиск по цене");
                    System.out.println("Введите начальную сумму:");
                    double min = scan.nextDouble();
                    System.out.println("Введите конечную сумму:");
                    double max = scan.nextDouble();
                    List<Book> priceList = new ArrayList<>(BooksDAO.rangeByPrice(min, max));
                    if (priceList.isEmpty()) {
                        System.out.println("Нет книг с таким ценовым диапазоном");
                    }
                    priceList.forEach((priceBook) -> {
                        System.out.println(priceBook);
                    });
                    whatNext();
                    break;
                case 3:
                    System.out.println("Вы выбрали поиск по нескольким критериям");
                    System.out.println("Введите название раздела:");
                    String type1 = scan.next();
                    System.out.println("Введите стоимость книги, которую не хотите превышать:");
                    double price = scan.nextDouble();
                    List<Book> authorList = new ArrayList<>(BooksDAO.findByTypePrice(type1, price));
                    if (authorList.isEmpty()) {
                        System.out.println("Нет книг с такими критериями.");
                    }
                    authorList.forEach((authorBook) -> {
                        System.out.println(authorBook);
                    });
                    whatNext();
                    break;
                case 4:
                    System.out.println("Вывод всех книг.");
                    BooksDAO.allBooksOutput();
                    whatNext();
                    break;
                case 5:
                    System.out.println("Вы выбрали поиск книги с самой маленькой ценой.");
                    List<Book> minList = new ArrayList<>(BooksDAO.getBookMinPrice());
                    if (minList.isEmpty()) {
                        System.out.println("Нет книг с таким ценовым диапазоном");
                    }
                    minList.forEach((minBook) -> {
                        System.out.println(minBook);
                    });
                    whatNext();
                    break;
                case 6:
                    System.out.println("Поиск по началу названия");
                    System.out.println("Введите начальные буквы названия книги.");
                    String part = scan.next();
                    List<Book> partList = new ArrayList<>(BooksDAO.findByTytlePart(part));
                    if (partList.isEmpty()) {
                        System.out.println("Не найдено книги с такими начальными буквами.");
                    }
                    partList.forEach((partBook) -> {
                        System.out.println(partBook);
                    });
                    whatNext();
                    break;
                case 7:
                    System.out.println("Поиск подстроки в названии книги:");
                    System.out.println("Введите часть названия книги:");
                    String subStr = scan.next();
                    BooksDAO.findBySubstrPart(subStr);
                    whatNext();
                    break;        
                case 8:
                    System.out.println("Выход");
                    System.exit(0);
                default:
                    System.out.println("Неверный выбор! Вы должны ввести "
                            + "число от 1 до 8! 8 - это выход из программы.");
                    break;
            }
        }
    }
    
    /**
     * Метод для определения дальнейших действий 
     * 
     * @return 
     */
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
                System.out.println("Вы ввели неверный вариант!");
            }
        }
    }
}
