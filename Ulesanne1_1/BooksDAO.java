/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ulesanne1_1;

import static Ulesanne1_1.Book.ANSI_GREEN;
import static Ulesanne1_1.Book.ANSI_RESET;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexander Chelpanov EDTR34
 * Date: 07.09.2019
 * 
 * 
 */
public class BooksDAO {
    public static List<Book> books = new ArrayList<>();
    public static final String ANSI_RED = "\u001B[31m";
    
    static{
        try {
            BufferedReader reader = new BufferedReader(new FileReader(
                "Books.csv"));
            String Line;
            int skip = 0;
            while ((Line = reader.readLine()) != null) {
                String[] line = Line.split(";");
                if (skip > 0) {
                    Book book = new Book(line[0], line[1], line[2], line[3],
                            Double.parseDouble(line[4].replace(",", ".")), Integer.parseInt(line[5]));
                    books.add(book);
                }
                skip=1;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BooksDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BooksDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Поиск книги по разделу
     * 
     * @param type
     * @return 
     */
    public static List<Book> findByType(String type){
        List<Book> typeList = new ArrayList<>();
        for (Book book: books) {
            if (book.getType().toLowerCase().equals(type.toLowerCase())) {
                typeList.add(book);
            }
        }
        return typeList;        
    }
    /**
     * Поиск по ценовому диапазону
     * @param min
     * @param max
     * @return 
     */
    public static List<Book> rangeByPrice(double min, double max){
        List<Book> priceList = new ArrayList<>();
        for (Book book: books) {
            if (book.getPrice()>=min && book.getPrice()<=max) {
                priceList.add(book);
            }
        }
        return priceList;        
    }
    /**
     * Поиск по двум критериям автору и цене
     * @param type
     * @param price
     * @return 
     */
    public static List<Book> findByTypePrice(String type, double price){
        List<Book> authorList = new ArrayList<>();
        for (Book book: books) {
            if (book.getType().toLowerCase().equals(type) && book.getPrice()<price) {
                authorList.add(book);
            }
        }
        return authorList;        
    }
    /**
     * Вывод всех книг в списке
     */
    public static void allBooksOutput() {
        books.forEach((book) -> {
            System.out.println(book);
        });
    }
    /**
     * Поиск по начальны буквам названия книги.
     * @param part
     * @return 
     */
    public static List<Book> findByTytlePart(String part){
        List<Book> partList = new ArrayList<>();
        part = part.trim();//режем пробелы по концам 
        int length = part.length();//длина введенного начала названия
        for (Book book: books) {
            //при сравнении вырезаем из названия начальную часть равную длине введенного значения
            if (book.getTytle().substring(0, length).toLowerCase().equals(part.toLowerCase())) {
                partList.add(book);
            }
        }
        return partList;        
    }
    
    /**
     * Поиск подстроки в названии книги. Подстрока подсвечивается красным цветом.
     * 
     * @param subStr 
     */
    public static void findBySubstrPart(String subStr){
        List<Book> substrList = new ArrayList<>();
        String name;
        for (Book book: books) {
            name = book.getTytle();
            for (int i = 0; i < name.length()-subStr.length(); i++) {
                //ищем подстроку в названии по постоянно сдвигающемуся начальному индексу
                // т.о. проходим по всему названию на предмет соответствия подстроке
                if (name.regionMatches(true, i, subStr, 0, subStr.length())==true) {
                    substrList.add(book);
                    break;
                }
            }
        }
        //вывод найденных книг
        substrList.forEach((book) -> {
            System.out.println(ANSI_GREEN+"Tytle: "
                    +ANSI_RESET+book.getTytle().toUpperCase().replaceAll(subStr.toUpperCase(),
                            ANSI_RED+subStr.toUpperCase()+ANSI_RESET)
                    +"."+ANSI_GREEN+" Author: "+ANSI_RESET+book.getAuthor()+"."+
                    ANSI_GREEN+" Price: "+ANSI_RESET+book.getPrice());
        });       
    }
    
    /**
     * Поиск книги (или книг если несколько экземпляров с мин.ценой)
     * @return 
     */
    public static List<Book> getBookMinPrice() {
        double minPrice = (books.get(0)).getPrice();
        List<Book> minList = new ArrayList<>();
        for (Book book: books) {
            if (book.getPrice()<minPrice) {
                minPrice = book.getPrice();
            }
        }
        for (Book book: books) {
            if (book.getPrice()==minPrice) {
                minList.add(book);
            }
        }
        return minList;
    }
}
