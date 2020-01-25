
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ulesanne3;



import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author Alexander Chelpanov
 * Date: 14.10.2019
 * 
 * Ülesanne3
 * 
 * Luua klasside teek, mis koosneb geomeetrilistest kujunditest - ringjoon, 
 * romb, parallelogram, trapets, kolmnurk. Realiseerida pindala, ümbermõõdu, 
 * külgservade pikkuse, kõrguse, raadiuse, diameetri arvutusmeetodeid - 
 * rakendada liidesed. On vajalik realiseerida järgmised meetodid: pindala, 
 * ümbermõõdu (kõikide kujundite jaoks), kõrguse, külgservade pikkuse 
 * (hulknurkade jaoks), raadiuse, diameetri (ringjoone jaoks) arvutused. 
 * Tippude viitamiseks kasutada ristkoordinaatide süsteemi koordinaate.
 * 
 * Luua rakendus, kasutades klasside teek. Looge üks kollektsioon 
 * (List või Array) kujunditest, kus hoitakse kõikide geomeetriliste kujundite 
 * tüübid. Printige välja täpset informatsiooni iga kujundi kohta. Sorteerige 
 * kujundite loend pindala/ümbermõõdu kasvu/kahanemise järgi 
 * (kasutage 2 varianti: teek klasside meetodid ja lambda avaldised )
 * 
 */
public class Ulesanne3 {
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
        System.out.println("|1.Вывод всех фигур                     |");
        System.out.println("|2.Сортировка фигур по периметру        |");
        System.out.println("|3.Сортировка фигур по площади          |");
        System.out.println("|4.Сортировка фигур по периметру в      |");
        System.out.println("|  порядке убывания                     |");
        System.out.println("|5.Сортировка фигур по площади в        |");
        System.out.println("|  порядке убывания                     |");
        System.out.println("|6.Группировка фигур по названию класса |");//19.10.2019 modified
        System.out.println("|7.Вывод минимального и максимального   |");//19.10.2019 modified
        System.out.println("|  значения площади для каждого типа.   |");
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
                        System.out.println("Вывод всех фигур:");
                        ShapesMethods.shapes.stream()//формируем поток из листа
                                .forEach(a -> a.printInfo());//для каждого обьекта потока применяем метод вывода в консоль 
                        whatNext();
                        break;
                    case 2:
                        System.out.println("Вывод всех фигур по возрастанию величины их "
                                + "периметра: ");
                        ShapesMethods.shapes.stream()//получаем поток 
                                //далее сортируем поток по периметру применяя компаратор для сравнения чисел с пл.точкой
                                .sorted(Comparator.comparingDouble((shape) -> shape.getPerimeter()))
                                .forEach(shape -> shape.printInfo());//вывод обьектов в консоль
                        whatNext();
                        break;
                    case 3:
                        System.out.println("Вывод всех фигур по возрастанию величины их "
                                + "площади: ");
                        ShapesMethods.output(ShapesMethods.sortingByArea(ShapesMethods.shapes));
                        whatNext();
                        break;
                    case 4:
                        System.out.println("Вывод всех фигур по убыванию величины их "
                                + "периметра: ");
                        ShapesMethods.shapes.stream()
                                // тоже сортировка только в обратном порядке (reversed)
                                .sorted(Comparator.comparingDouble(Shape::getPerimeter).reversed())                                
                                .forEach(shape -> shape.printInfo());
                        whatNext();
                        break;
                    case 5:
                        System.out.println("Вывод всех фигур по убыванию величины их "
                                + "площади: ");
                        ShapesMethods.shapes.stream()
                                .sorted(Comparator.comparingDouble(Shape::getArea).reversed())
                                .forEach(shape -> shape.printInfo());
                        whatNext();
                        break;
                    case 6:
                        System.out.println("Вывод всех фигур сгруппированных по классам и отсортировынных"
                                + " по возрастанию:");
                        ShapesMethods.shapes.stream()
                                //далее к потоку вызываем компаратор который сортирует и группирует обьекты по имени класса
                                .sorted(Comparator.comparing(a -> a.getClass().getName()))
                                .forEach(shape -> shape.printInfo());
                        whatNext();
                        break;
                    case 7:
                        System.out.println("Вывод маскимальной и минимальной площади для каждой фигуры"
                                + " отсортированных в порядке увеличения:");
                        List<Shape> minMaxList = ShapesMethods.sorting(ShapesMethods.shapes);
                        minMaxList.stream().sorted(Comparator.comparing(a -> a.getClass().getName())).forEach(s -> s.printInfo());
                        whatNext();
                        break;
                    case 8:
                        System.out.println("Выход");
                        System.exit(0);
                    default:
                        System.out.println(ANSI_RED + "Неверный выбор! Вы должны ввести "
                                + "число от 1 до 8! 8 - это выход из программы." + ANSI_RESET);
                        whatNext();
                        break;
                }
            }
        } catch (Exception e) {//перехват исключений при неправильном вводе
            System.out.println(ANSI_RED+"Вы ввели недопустимое значение!"+ANSI_RESET);
            System.out.println("Oшибка: "+e);
            whatNext();
        }
    }
    
    /**
     * Метод для определения дальнейших действий
     * @return 
     */
    private static int whatNext() {
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
                    System.out.println(ANSI_RED+"Вы ввели неверный вариант!"+ANSI_RESET);
                    break;
            }
        }
    }
}
