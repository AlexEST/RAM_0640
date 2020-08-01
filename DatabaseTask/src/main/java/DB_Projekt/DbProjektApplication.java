package DB_Projekt;

import entity.Book;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import service.BooksMethods;
import service.BooksSQL;

/**
 *
 * @author Alexander Chelpanov
 * Date: 23.12.2019
 * 
 * В приложении необходимо продемонстрировать работу с базами данных, 
 * используя SpringBoot фреймворк  и дэрби базу данных dbBooks .
 * Также в приложении должна быть возможность поиска/ добавление/редактирование/удаление
 * 
 * !!!
 * 02.01.2020 - Добавлен вывод количества всех книг в базе. 
 * Изменен вывод книг с максимальной ценой (теперь вывод для каждой категории отдельно)
 * 29.12.2019 - Изменен вывод книг в консоль. Теперь выводятся вместе с авторами и 
 * в удобном виде. Также добавлен раздел с выводом книги с максимальной ценой.
 * 
 * Консольная версия с реализацией интерфейса CommandLineRunner.
 * При добавлении/редактировании данных на русском языке могут проявляться проблемы с кодировкой.
 * Цветные выводы, кодировки - Maven может перекрыть. 
 * 
 * Название базы, пользователь и пароль те же самые оставил.
 */

@SpringBootApplication
public class DbProjektApplication implements CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(DbProjektApplication.class);
        app.setBannerMode(Banner.Mode.OFF);//убираем баннер спринга
        app.run(args);//запускаем консольное приложение
    }

    @Override
    public void run(String... args) throws Exception {
        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));//проблемы с кодировкой. Явно ставим ее значение для вывода. (Иногда приходится менять)
        Menu();
    }

    public static void Menu() throws IOException, FileNotFoundException, ParseException {
        System.out.println("=========================================");
        System.out.println("|                 МЕНЮ                  |");
        System.out.println("=========================================");
        System.out.println("|Разделы:                               |");
        System.out.println("|1.Вывод всех книг в базе               |");//modified 01.01.2020
        System.out.println("|2.Поиск книги по ID                    |");
        System.out.println("|3.Вывод книг отсортированных по цене   |");
        System.out.println("|4.Вывод книг отсортированных по дате   |");
        System.out.println("|5.Вывод книг с максимальной ценой в    |");//added 29.12.2019, modified 02.01.2020
        System.out.println("|  своей категории                      |");
        System.out.println("|6.Удаление книги                       |");
        System.out.println("|7.Добавление книги                     |");
        System.out.println("|8.Редактирование книги                 |");
        System.out.println("|9.Выход                                |");
        System.out.println("=========================================");
        System.out.println("");
        System.out.println("Выберите раздел и нажмите Enter:");
        try {
            Scanner scan = new Scanner(System.in);
            int choice = scan.nextInt();
            while (true) {
                switch (choice) {
                    case 1:
                        System.out.println("Вывод всех книг:");
                        BooksMethods.toConsole(BooksSQL.getAllBooks());
                        System.out.println("Всего книг: "+BooksSQL.getBooksCount());
                        whatNext();
                        break;
                    case 2:
                        System.out.println("Вывод книги по ее ID");
                        BooksMethods.toConsole(BooksSQL.getBookByID(checkID()));//from JavaHibernateExample
                        whatNext();
                        break;
                    case 3:
                        System.out.println("Вывод книг отсортированных по цене: ");
                        BooksMethods.toConsole(BooksSQL.getBooksByPriceDesc());
                        whatNext();
                        break;
                    case 4:
                        System.out.println("Вывод книг отсортированных по дате (новинки сверху): ");
                        BooksMethods.toConsole(BooksSQL.getBooksByDateDesc());
                        whatNext();
                        break;
                    case 5:
                        System.out.println("Вывод книги c максимальной ценой: ");
                        BooksMethods.toConsole(BooksSQL.getBooksWithMaxPrice());
                        whatNext();
                        break;    
                    case 6:
                        System.out.println("Удаление книги: ");                        
                        BooksSQL.deleteBook(checkID()); //from JavaHibernateExample
                        System.out.println("Книги удалена!");
                        whatNext();
                        break;
                    case 7:
                        System.out.println("Добавление книги: ");
                        BooksMethods.insertBook();
                        whatNext();
                        break;
                    case 8:
                        System.out.println("Редактирование книги: ");
                        BooksMethods.editBook();
                        whatNext();
                        break;
                    case 9:
                        System.out.println("Выход из программы: ");
                        System.exit(0);
                    default:
                        System.out.println("Неверный выбор! Вы должны ввести "
                                + "число от 1 до 9! 9 - это выход из программы.");
                        whatNext();
                        break;
                }
                break;
            }
        } catch (InputMismatchException е) {
            System.out.println("ВВОД НЕВЕРНЫЙ! Попробуйте заново.");
            whatNext();
        }
    }

    private static void whatNext() throws IOException, FileNotFoundException, ParseException {
        while (true) {
            System.out.println("Введите 1 если хотите завершить программу. "
                    + "Если хотите вернуться в меню введите 2");
            Scanner choice = new Scanner(System.in);
            int input = choice.nextInt();
            switch (input) {
                case 2:
                    Menu();
                    break;
                case 1:
                    System.exit(0);
                default:
                    System.out.println("Вы ввели неверный вариант!");
                    break;
            }
        }
    }
    
    private static Integer checkID() throws IOException, FileNotFoundException, ParseException {
        System.out.println("Введите ID книги:");
        Integer id = new Scanner(System.in).nextInt();
        if (BooksSQL.checkID(id) == false) {
            System.out.println("Нет книги с таким ID в базе! Выход в меню.");
            Menu();
        }
        return id;
    }
}
