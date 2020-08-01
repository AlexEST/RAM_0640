/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Author;
import entity.Book;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Alexander Chelpanov
 * Date: 24.12.2019
 * 
 */
public class BooksSQL {
    
    private static List<Book> books;

    public static List<Book> getAllBooks() {
        List<Book> list;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            TypedQuery<Book> query = session.createQuery("FROM Book");
            list = query.getResultList();
        }
        return list;
    }
    
    public static List<Author> getBookAuthors(int id) {
        List<Author> list;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            TypedQuery<Author> query = session.createQuery("from Author where id=:id");
            query.setParameter("id", id);
            list = query.getResultList();
        }
        return list;
    }

    public static List<Book> getBookByID(int id) {
        List <Book> book;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            TypedQuery<Book> query = session.createQuery("from Book where id=:id");
            query.setParameter("id", id);
            book = query.getResultList();
        }
        return book;
    }
    
    /**
     * Запрос на вывод книг с максимальной ценой в своей категории.
     * @return 
     */
    public static List<Book> getBooksWithMaxPrice() {       
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            TypedQuery<Book> query = session.createQuery("from Book b "
                    + "where b.price in (select max(b.price) from Book b group by b.categoryId)");
            books = query.getResultList();
            return books;
        } catch (HibernateException e) {
            System.out.println(e);
        }
        return books;        
    }    
    
    public static Long getBooksCount() {
        Long count = 0L;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession(); 
            TypedQuery<Long> query = session.createQuery("SELECT COUNT(b) FROM Book b");            
            count = query.getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
        } 
        return count;
    }
    
    /**
     * Сортировка книги по цене в порядке убывания
     * @return 
     */
    public static List<Book> getBooksByPriceDesc() {
        List<Book> list;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            TypedQuery<Book> query = session.createQuery("from Book b order by b.price desc");
            list = query.getResultList();
        }
        return list;
    }
    
    /**
     * Сортировка книг по дате в порядке убывания.
     * @return 
     */
    public static List<Book> getBooksByDateDesc() {
        List<Book> list;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            TypedQuery<Book> query = session.createQuery("from Book b order by b.datePublication desc");
            list = query.getResultList();
        }
        return list;
    }

    public static int deleteBook(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        TypedQuery query = session.createQuery("delete Book where id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        session.getTransaction().commit();
        return result;
    }
    
    public static void addAuthor(Author author){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.persist(author);
        session.save(author); 
        session.getTransaction().commit();
    }
    
    /**
     * Метод для редактирования книг. Первый параметр метода - id книги,
     * вторым какой аттрибут обьекта (книги) меняем, третий значение этого аттрибута в виде строки
     * 
     * 1-Название, 2-Цена, 3-Количество страниц, 4-Дата публикации, 5-Язык
     * 
     * @param id
     * @param parameter
     * @param value 
     * @throws java.text.ParseException 
     * @throws java.io.UnsupportedEncodingException 
     */
    public static void updateBook(Integer id, int parameter, String value) throws ParseException, UnsupportedEncodingException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Book book = session.find(Book.class, id);
        switch (parameter) {
            case 1:
                book.setTitle(value);
                session.getTransaction().commit();
                session.close();
                break;
            case 2:
                book.setPrice(new BigDecimal(value));
                session.getTransaction().commit();
                session.close();
                break;
            case 3:
                book.setPages(Integer.parseInt(value));
                session.getTransaction().commit();
                session.close();
                break;
            case 4:
                book.setDatePublication(new SimpleDateFormat("dd.MM.yyyy").parse(value));
                session.getTransaction().commit();
                session.close();
                break;
            case 5:
                book.setLanguage(value);
                session.getTransaction().commit();
                session.close();
                break; 
            default:
                throw new AssertionError();
        }
    }
    
    /**
     * Вставка книги в базу.
     * @param book 
     */
    public static void insertBook(Book book) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(book);
        session.save(book);
        session.getTransaction().commit();
    }
    
    /**
     * Метод для проверки наличия книги по Id
     * @param ID
     * @return 
     */
    public static boolean checkID(Integer ID) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Book book = session.find(Book.class, ID);
        session.close();
        return book != null;   
    }
    
}
