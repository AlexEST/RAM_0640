/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ulesanne1_2;

import java.util.Scanner;

/**
 *
 * @author Alexander Chelpanov
 * Date: 10.09.2019
 * 
 * Ülesanne1_2
 * 
 * Ülesanne. Keemiliste elementide perioodilisuse tabel
 * Analüüsige fail elements.csv. Looge klass ChemicalElement, mis sisaldab välju,
 * meetodeid get/ set (omadused), konstruktoreid, toString.
 * Otsi element nimetuse järgi massiivis.
 * 
 * *Kasutaja sisestab molekulaarvalemi, näiteks, H2O ja programm arvutab
 *  molekulaarkaalu.
 * 
 */
public class Ulesanne1_2 {
        public static final String ANSI_BLUE = "\u001B[34m";
        public static final String ANSI_RESET = "\u001B[0m";
        public static final String ANSI_RED = "\u001B[31m";
        
    public static void main(String[] args) {
        Menu();
    }
    public static void Menu(){
        System.out.println("=========================================");
        System.out.println("|                 MENÜÜ                 |");
        System.out.println("=========================================");
        System.out.println("|Sektsioonid:                           |");
        System.out.println("|1.Elementide otsing                    |");
        System.out.println("|2.Molekulaarkaalu kalkulaator          |");
        System.out.println("|3.Elementide väljund                   |");
        System.out.println("|4.Väljumine                            |");
        System.out.println("=========================================");
        System.out.println("");
        System.out.println("Sisestage number ja vajutage Enter:");
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        while (true) {
            switch (choice) {
            case 1:
                System.out.println("Введите символ элемента:");
                String str = scan.next();
                System.out.println("Элемент:");
                System.out.println(Elements.getBySymbol(str));
                whatNext();
                break;
            case 2:
                System.out.println("Расчет молекулярной массы. "
                        + ANSI_RED+"При вводе необходимо учитывать регистр букв!!! "
                        + "Пример ввода: Na2SO4, H2O, NaCl, C2H5OH, NO и т.д."+ANSI_RESET); 
                System.out.println("Иначе алгоритм калькулятора ошибочно "
                        + "распознает формулу. Введите ваш элемент или формулу:");
                int counter = 0;
                while (counter<4) {                   
                    try {
                    String mass = scan.next();
                    System.out.println("Молярная масса: "+ANSI_BLUE+Elements.calculateMass(mass)+ANSI_RESET);
                    whatNext();
                } catch (Exception e) {
                        if (counter<2) {
                            System.out.println(ANSI_RED+"Вы ввели некорректное значение!!! "
                            +" Вводите заново!"+ANSI_RESET);
                        } else {
                            System.out.println(ANSI_RED+"Вы так и не ввели некорректное значение!!! "
                            +ANSI_RESET);
                        }     
                        counter++;
                        if (counter==3) {
                            System.out.println(ANSI_RED+"Завершение программы!"+ANSI_RESET);
                            System.exit(0);
                        }
                    }
                }                
                break;
            case 3:
                System.out.println("");
                for (ChemicalElement element: Elements.elements) {
                    System.out.println(element);
                }
                whatNext();
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
