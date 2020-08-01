package EKSAM;

import entity.Weather;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import service.WeatherSQL;

/**
 * 
 * @author Alexander Chelpanov 182730EDTR
 * Date: 25.01.2020
 * 
 * Loo derby andmebaas DbTemperature ja käivita SQL päringud tabeli ja andmete loomiseks 
 * CREATE TABLE Weather( id INT NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT 
 * PEOPLE_PK PRIMARY KEY, city VARCHAR(50) NOT NULL, temperature INT NOT NULL, 
 * date DATE NOT NULL ) 
 * 
 * INSERT INTO Weather(CITY,TEMPERATURE,DATE) VALUES('TALLINN',-5,'18.01.2020') 
 * INSERT INTO Weather(CITY,TEMPERATURE,DATE) VALUES('TALLINN',0,'16.01.2020') 
 * INSERT INTO Weather(CITY,TEMPERATURE,DATE) VALUES('TALLINN',-1,'15.01.2020') 
 * INSERT INTO Weather(CITY,TEMPERATURE,DATE) VALUES('JÕHVI',-7,'18.01.2020') 
 * INSERT INTO Weather(CITY,TEMPERATURE,DATE) VALUES('JÕHVI',2,'16.01.2020') 
 * INSERT INTO Weather(CITY,TEMPERATURE,DATE) VALUES('JÕHVI',-3,'15.01.2020') 
 * INSERT INTO Weather(CITY,TEMPERATURE,DATE) VALUES('TARTU',-2,'18.01.2020') 
 * INSERT INTO Weather(CITY,TEMPERATURE,DATE) VALUES('TARTU',2,'16.01.2020') 
 * INSERT INTO Weather(CITY,TEMPERATURE,DATE) VALUES('TARTU',0,'15.01.2020') 
 * 
 * Loo klass Weather, mis sisaldab välju, meetodeid, get/set(omadused), 
 * konstruktoreid, ToString meetodit ja meetodeid teises klassis, 
 * mis realiseerivad ärgmisi ülesandeid: (Andmed loe andmebaasist)
 * 
 * Leia keskmise ilma valitud linnas 
 * Näita kehtivat ilma (tänane kuupäev) 
 * Näita kõik andmed määratud linnas 
 * 
 * Loo loogiline kasutajaliides.
 * 
 * Database name: DbTemperature 
 * Username: student
 * Password: student
 * 
 */
@SpringBootApplication
public class EksamApplication implements CommandLineRunner{
     public static void main(String[] args) {
        SpringApplication app = new SpringApplication(EksamApplication.class);
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
        System.out.println("|1.Средняя погода в городе              |");
        System.out.println("|2.Погода сегодня                       |");
        System.out.println("|3.Вывод всех данных по городу          |");
        System.out.println("|4.Вывод всех данных из базы            |");
        System.out.println("|5.Выход                                |");
        System.out.println("=========================================");
        System.out.println("");
        System.out.println("Выберите раздел и нажмите Enter:");
        try {
            Scanner scan = new Scanner(System.in);
            int choice = scan.nextInt();
            while (true) {
                switch (choice) {
                    case 1:
                        System.out.println("Вывод средней погоды по городу");
                        System.out.println("Введите название города:");
                        String city = scan.next().trim().toLowerCase();
                        System.out.println("Среднее значение погоды: " + WeatherSQL.getAvgWeather(city));
                        whatNext();
                        break;
                    case 2:
                        System.out.println("Вывод погоды в городе сегодня:");              
                        Date date = new Date ();
                        System.out.println("Введите название города:");
                        String city2 = scan.next().trim().toLowerCase();
                        System.out.println("Погода сегодня: " + WeatherSQL.getWeatherToday(city2, date));
                        whatNext();
                        break;
                    case 3:
                        System.out.println("Вывод всех данных по городу: ");
                        System.out.println("Введите название города:");
                        String city3 = scan.next().trim().toLowerCase();
                        toConsole(WeatherSQL.getAllWeatherForCity(city3));
                        whatNext();
                        break;
                    case 4:
                        System.out.println("Вывод всех данных из базы: ");
                        toConsole(WeatherSQL.getAllWeather());
                        whatNext();
                        break;                   
                    default:
                        System.out.println("Неверный выбор! Вы должны ввести "
                                + "число от 1 до 4! 4 - это выход из программы.");
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
    
    private static void toConsole(List<Weather> list) {
        System.out.printf("%-2s %-20s %-12s %-10s%n", "ID", "CITY", "TEMPERATURE", "DATE");
        list.stream().forEach(a -> System.out.println(a));
    }
    
    
}
