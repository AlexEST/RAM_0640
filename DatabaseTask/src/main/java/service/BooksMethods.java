/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DB_Projekt.DbProjektApplication;
import entity.Author;
import entity.Book;
import entity.Category;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author Alexander Chelpanov
 * Date: 23.12.2019
 * 
 * 
 */
public class BooksMethods {
    
    /**
     * Метод для преобразования строки с датой в дату.
     * @param str
     * @return 
     */
    public static Date getDate(String str) {
        StringTokenizer st = new StringTokenizer(str, ".");
        int[] list = new int[3];
        Date DatePublication;
        int counter = 0;
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (isNumeric(token) == true) {
                list[counter] = Integer.parseInt(token);
                counter++;
            }
        }
        if (list[2] == 0 || list[1] == 0 || list[0] == 0) {
            DatePublication = null;
            return DatePublication;
        }
        DatePublication = new Date(list[2], list[1], list[0]);
        return DatePublication;
    }
    
    /**
     * Проверка является ли строка числом
     * @param strNum
     * @return 
     */
    public static boolean isNumeric(String strNum) {
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
    
    private static boolean isBigDecimal(String strNum) {
        try {
            BigDecimal d = new BigDecimal(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
    
    /**
     * Метод для сохранения в базу данных.
     * @throws ParseException
     * @throws IOException 
     */
    public static void insertBook() throws ParseException, IOException {
        try {
            Collection<Book> books = new ArrayList<>();
            Book book = new Book();
            book.setTitle(getTitle());
            book.setPrice(new BigDecimal(getPrice()));
            book.setPages(Integer.parseInt(getPages()));
            book.setDatePublication(new SimpleDateFormat("dd.MM.yyyy").parse(getDate()));
            book.setLanguage(getLanguage());
            book.setCategoryId(getCategory());
            book.setAuthorCollection(getAuthor());
            books.add(book);
            //для каждого автора книги добавляем лист с книгой для реализации отношений ManyToMany
            book.getAuthorCollection().stream().forEach(a->a.setBookCollection(books));
            BooksSQL.insertBook(book);
            book.getAuthorCollection().stream().forEach(author->BooksSQL.addAuthor(author));
            System.out.println("Книга добавленa в базу. Ее ID: " + book.getId());
        } catch (NumberFormatException | ParseException e) {
            System.out.println("Ошибка при вводе данных. Выход в меню.");
            DbProjektApplication.Menu();
        }
    }
    
    /**
     * 
     * Метод для редактирования книги.
     * 
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ParseException 
     */
    public static void editBook() throws IOException, FileNotFoundException, ParseException {
        while (true) {
            Integer id = getId();
            System.out.println("Какой параметр у книги хотите отредактировать? ");
            System.out.println("Введите соответсвующую параметру цифру:");
            System.out.println("1-Название, 2-Цена, 3-Количество страниц, 4-Дата публикации, 5-Язык");
            int ans = new Scanner(System.in).nextInt();
            switch (ans) {
                case 1:                   
                    BooksSQL.updateBook(id, 1, getTitle());         
                    System.out.println("Изменения сохранены!");
                    whatNextInside();
                    break;
                case 2:
                    BooksSQL.updateBook(id, 2, getPrice());
                    System.out.println("Изменения сохранены!");
                    whatNextInside();
                    break;
                case 3:                  
                    BooksSQL.updateBook(id, 3, getPages());
                    System.out.println("Изменения сохранены!");
                    whatNextInside();
                    break;
                case 4:
                    BooksSQL.updateBook(id, 4, getDate());
                    System.out.println("Изменения сохранены!");
                    whatNextInside();
                    break;
                case 5:
                    BooksSQL.updateBook(id, 5, getLanguage());
                    System.out.println("Изменения сохранены!");
                    whatNextInside();
                    break;
                default:
                    System.out.println("Неверный выбор! Вы должны ввести "
                            + "число от 1 до 6!");
                    System.out.println("Введите заново.");
                    break;
            }
        }
    }
    
    
    private static String getTitle() {
        while (true) {
            System.out.println("Введите новое название книги:");
            String title = new Scanner(System.in).nextLine();
            if (title.trim() == null || isNumeric(title)==true) {
                System.out.println("Название книги не может быть пустым!! Введите заново.");
            } else {
                return title;
            }
        }
    }
    
    private static String getPrice() {
        System.out.println("Введите новую цену книги:");
        while (true) {
            String price = new Scanner(System.in).nextLine();
            if (isBigDecimal(price) == false || Double.parseDouble(price) < 0) {
                System.out.println("Вы ввели неверное значение! Попробуйте заново!");
            } else {
                return price;
            }
        }
    }

    private static String getDate() {
        while (true) {
            System.out.println("Введите новое значение даты публикации (в формате дд.ММ.ГГГГ через точку): ");
            String date = new Scanner(System.in).nextLine();
            if (getDate(date) != null) {
                return date;               
            }
            System.out.println("Неправильный ввод! Попробуйте заново!");
        }
    }
    
    private static String getPages() {
        while (true) {
            System.out.println("Введите новое количество страниц: ");
            String pages = new Scanner(System.in).nextLine();
            if (isNumeric(pages) && Integer.parseInt(pages) > 0) {
                return pages;
            }
            System.out.println("Неправильный ввод! Количество страниц болжно быть больше нуля!");
        }
    }
    
    private static String getLanguage() {
        while (true) {
            System.out.println("Введите язык книги: ");
            String lang = new Scanner(System.in).nextLine().replaceAll(" ", "");
            if (lang.length()!=0 || isNumeric(lang)==true) {
                return lang;
            }
            System.out.println("Неправильный ввод!! Пробуйте заново!");
        }
    }
    
    private static Collection getAuthor() {       
        Collection<Author> coll = new ArrayList<>() ;
        while (true) {
            System.out.println("Ввод данных по автору.");
            Author author = new Author();
            System.out.println("Имя автора.");
            author.setFirstname(getNameOrLastname());
            System.out.println("Фамилия автора.");
            author.setLastname(getNameOrLastname());
            coll.add(author);
            while (true) {
                try {
                    System.out.println("Желаете добавить еще автора или нет? 1-нет 2-да");
                    int ans = new Scanner(System.in).nextInt();
                    switch (ans) {
                        case 1:
                            return coll;
                        case 2:
                            break;    
                        default:
                            System.out.println("Неправильный ввод!");
                    }
                } catch (Exception e) {
                    System.out.println("Неправильный ввод!");
                }
                break;
            }           
        }
    }
    
    private static Category getCategory() {
        while (true) {
            Category cat = new Category();
            System.out.println("Введите категорию книги 1-Web 2-Programming.");
            String category = new Scanner(System.in).nextLine();
            if (isNumeric(category) && Integer.parseInt(category) > 0 && Integer.parseInt(category) < 3) {
                if (Integer.parseInt(category)==1) {
                    cat.setId(1);
                    cat.setTitle("Web");
                    return cat;
                }
                if (Integer.parseInt(category)==2) {
                    cat.setId(2);
                    cat.setTitle("Programming");
                    return cat;
                }
            }
            System.out.println("Неправильный ввод! Категория должна быть цифрой 1 или 2");
        }
    }
    
    /**
     * Метод для ввода нового значени имени или фамилии.
     * @return 
     */
    private static String getNameOrLastname() {
        String name;
        while (true) {
            System.out.println("Введите новое значение:");
            name = new Scanner(System.in).nextLine().trim();
            if (name.length()>0) {
                return name;
            }
            System.out.println("Это значение не может быть пустым! Попробуйте заново!");
        }
    }
    
    /**
     * Вывод в консоль.
     * @param list 
     */
    public static void toConsole(List<Book> list) {    
        System.out.printf("%-2s %-80s %-40s %-6s %-7s %-12s %-10s %-10s%n",
                "ID", "НАЗВАНИЕ КНИГИ", "АВТОР(Ы)", "ЦЕНА", "СТРАНИЦ", "ДАТА ИЗДАНИЯ", "ЯЗЫК", "КАТЕГОРИЯ");
        try {
            list.stream().forEach((book) -> {
                Object[] authorList = book.getAuthorCollection().toArray();
                Author a = (Author) authorList[0];
                System.out.printf("%-2s %-80s %-40s %-6s %-7s %-12s %-10s %-10s%n",
                        book.getId(), book.getTitle(), a.getFirstname() + " " + a.getLastname(), book.getPrice(), book.getPages(),
                        book.getDatePublication(), book.getLanguage(), book.getCategoryId().toString());
                if (authorList.length > 1) {
                    for (int i = 1; i < authorList.length; i++) {
                        a = (Author) authorList[i];
                        System.out.printf("%-83s %-40s%n", " ", "" + a.getFirstname() + " " + a.getLastname());
                    }
                }
            });
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static Integer getId() {
        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Введите ID книги, которой хотите отредактировать:");
            Integer id = scan.nextInt();
            if (BooksSQL.checkID(id) == false) {
                System.out.println("Нет книги с таким ID в списке. Введите снова.");
            } else {
                return id;
            }
        }
    }
    /**
     * Для выбора действий внутри раздела.
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ParseException 
     */
    public static void whatNextInside() throws IOException, FileNotFoundException, ParseException {
        while (true) {
            try {
                System.out.println("Хотите остаться в данном разделе? НЕТ - введите 1 (Выход в меню)."
                        + " ДА - введите 2.");
                Scanner scan = new Scanner(System.in);
                int Answer = scan.nextInt();
                if (Answer == 1) {
                    DbProjektApplication.Menu();
                }
                if (Answer == 2) {
                    break;
                }
            } catch (IOException | ParseException e) {
                System.out.println("Вы ввели что-то неправильное. Попробуйте заново.");
            }
        }
    }
}
