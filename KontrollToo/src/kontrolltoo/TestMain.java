/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontrolltoo;

import java.util.InputMismatchException;
import java.util.Scanner;
import javax.xml.bind.JAXBException;




/**
 *
 * @author Alexander Chelpanov EDTR34
 * Date: 07.12.2019
 * 
 * 
 */
public class TestMain {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    
    public static void main(String[] args) throws JAXBException {
        Menu();
    }

    public static void Menu() throws JAXBException{       
        System.out.println("=========================================");
        System.out.println("|                 МЕНЮ                  |");
        System.out.println("=========================================");
        System.out.println("|Разделы:                               |");
        System.out.println("|1.Вывод всего списка валют             |");
        System.out.println("|2.Запись JSON и XML                    |");
        System.out.println("|3.Выход                                |");
        System.out.println("=========================================");
        System.out.println("");
        System.out.println("Выберите раздел и нажмите Enter:");        
        try {
            Scanner scan = new Scanner(System.in);
            int choice = scan.nextInt();
            while (true) {
                switch (choice) {
                    case 1:
                        System.out.println("Вывод всего списка валют:");
                        CurrencyDAO.toConsole();
                        whatNext();
                        break;
                    case 2:
                        System.out.println("Запись XML и JSON:");
                        XMLWriter.listToXML(CurrencyDAO.currencies);
                        JSONWriter.writeJSON(CurrencyDAO.currencies);
                        whatNext();
                        break;
                    case 3:
                        System.out.println("Выход");
                        System.exit(0);
                    default:
                        System.out.println(ANSI_RED + "Неверный выбор! Вы должны ввести "
                                + "число от 1 до 3! 3 - это выход из программы." + ANSI_RESET);
                        whatNext();
                        break;
                }
                break;
            }
        } catch (InputMismatchException е) {
             System.out.println("Ввод неверный! Завершение с ошибкой.");
        }

    }
    
    /**
     * Определение дальнейштх действий.
     * @return
     * @throws JAXBException 
     */
    private static int whatNext() throws JAXBException {
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
