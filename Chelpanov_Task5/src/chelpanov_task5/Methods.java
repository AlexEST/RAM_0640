/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chelpanov_task5;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Alexander Chelpanov
 * Date: 14.11.2019
 * 
 * Отдельный класс для методов обработки листа с обьектами класса Person.
 * 
 */
public class Methods {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    /**
     * Метод для определения будет ли день рождение в ближайшие 30 дней.
     *
     * @param date
     * @return
     */
    public static boolean checkBirthday(LocalDate date) {
        //У дня рождения меняем год на текущий для расчета
        LocalDate date1 = LocalDate.of(LocalDate.now().getYear(), date.getMonth(), date.getDayOfMonth());
        LocalDate today = LocalDate.now();// текущая дата
        //если день рождения в этом году был прибавляем один год 
        if (date1.isBefore(today)) {
            date1 = date1.plusYears(1);
        }
        //расчет разницы между двумя датами в днях
        long difference = ChronoUnit.DAYS.between(today, date1);
        //возвращаем булево значение
        return difference <= 30;
    }

    /**
     * Метод для удаления обьекта
     *
     * @param removeList
     * @return
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ParseException
     */
    public static List removePerson(List<Person> removeList) throws IOException, FileNotFoundException, ParseException {
        while (true) {
            System.out.println("Введите ID человека, которого хотите удалить: ");
            Scanner scan = new Scanner(System.in);
            int id = scan.nextInt();
            List<Person> listNew = removeList.stream().filter(a -> a.getId() != id).collect(Collectors.toList());
            System.out.println("Обьект удален.");
            whatNextInside(listNew);
            return listNew;
        }
    }

    /**
     * Метод для добавления обьектов.
     *
     * @param addList
     * @return
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ParseException
     */
    public static List addPerson(List<Person> addList) throws IOException, FileNotFoundException, ParseException {
        while (true) {
            try {
                Scanner scan = new Scanner(System.in);
                int id = createID(addList);// новый ид теперь генерируется
                System.out.println("Введите Фамилию: ");
                String Lastname = scan.next().replaceAll(" ", "");
                System.out.println("Введите имя: ");
                String firstName = scan.next();
                System.out.println("Введите День Рождения (Через точку день.месяц.год): ");
                String date = scan.next().replaceAll(" ", "");
                LocalDate Birthday = getDate(date);
                if (Birthday == null) {
                    System.out.println("Из-за неправильного ввода дата рождения не занесена.");
                }
                System.out.println("Введите через запятую почтовые адреса: ");
                Scanner scan1 = new Scanner(System.in);
                String mails = scan1.nextLine().replaceAll(" ", "");
                List<String> mailsList = new ArrayList<>(getStringTokens(mails));
                System.out.println("Введите через запятую какие языки знает: ");
                Scanner scan2 = new Scanner(System.in);
                String langs = scan2.nextLine().replaceAll(" ", "");
                List<String> langList = new ArrayList<>(getStringTokens(langs));
                Person person = new Person(id, Lastname, firstName, Birthday, mailsList, langList);
                List<Person> newAddList = new ArrayList<>(addList);
                newAddList.add(person);
                System.out.println("Обьект сохранен.");
                whatNextInside(newAddList);
            } catch (IOException | ParseException e) {
                System.out.println("Неправильный ввод!!");
            }

        }
    }
    
    /**
     * Метод для создания нового идентификатора.
     * @param list
     * @return 
     */
    private static int createID (List<Person> list) {
        Collections.sort(list, Comparator.comparingInt(Person::getId));
        int counter = 1;
        for (Person person : list) {
            if (person.getId()!=counter) {
                return counter;
            }
            counter++;
        }
        counter=list.size()+1;
        return  counter;       
    }

    /**
     * Метод для выбора действий внутри раздела
     *
     * @param list
     * @return
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ParseException
     */
    public static List whatNextInside(List<Person> list) throws IOException, FileNotFoundException, ParseException {
        while (true) {
            try {
                System.out.println("Хотите остаться в данном разделе? НЕТ - введите 1."
                        + " ДА - введите 2.");
                System.out.println("Если хотите записать в JSON введите 3 (После выход в меню)");
                Scanner scan = new Scanner(System.in);
                int Answer = scan.nextInt();
                if (Answer == 1) {
                    Ulesanne5.Menu(list);
                }
                if (Answer == 2) {
                    return list;
                }
                if (Answer == 3) {
                    JSONwriter.writeJSON(list);
                    Ulesanne5.Menu(list);
                }
            } catch (IOException | ParseException e) {
                System.out.println("Вы ввели что-то неправильное. Попробуйте заново.");
            }
        }
    }

    /**
     * Метод для разрезания строки на части по делиметеру
     *
     * @param str
     * @return
     */
    public static List getStringTokens(String str) {
        List<String> tokList = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(str, ",");
        while (st.hasMoreTokens()) {
            tokList.add(st.nextToken());
        }
        return tokList;
    }

    /**
     * Метод для преобразования строки в дату с проверкой
     *
     * @param str
     * @return
     */
    public static LocalDate getDate(String str) {
        StringTokenizer st = new StringTokenizer(str, ".");
        int[] list = new int[3];
        LocalDate Birthday;
        int counter = 0;
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (isNumeric(token) == true) {
                list[counter] = Integer.parseInt(token);
                counter++;
            }
        }
        if (list[2] == 0 || list[1] == 0 || list[0] == 0) {
            Birthday = null;
            return Birthday;
        }
        Birthday = LocalDate.of(list[2], list[1], list[0]);
        return Birthday;
    }

    /**
     * Проверяет можно ли строку преоразовать в число
     *
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

    /**
     * Метод для редактирования обьекто класса Person
     *
     * @param list
     * @return
     */
    public static List editPersons(List<Person> list) {
        while (true) {
            Scanner scan = new Scanner(System.in);
            try {
                System.out.println("Введите ID человека, данные которого хотите отредактировать:");
                int id = scan.nextInt();
                if (checkId(list, id) == false) {
                    System.out.println(ANSI_RED + "Нет такого ID в списке. Введите снова." + ANSI_RESET);
                    editPersons(list);
                }
                System.out.println("Какой аттрибут у обьекта вы хотите отредактировать? ");
                System.out.println("Введите соответсвующую аттрибуту цифру:");
                System.out.println("1-Lastname, 2-Firstname, 3-Birthday, 4-Emails, 5-Languages");
                int ans = scan.nextInt();
                switch (ans) {
                    case 1:
                        String lastname = getValue();
                        list.stream().filter((person) -> (person.getId() == id)).forEachOrdered((person) -> {
                            person.setLastname(lastname);
                        });
                        System.out.println("Изменения сохранены!");
                        whatNextInside(list);
                        break;
                    case 2:
                        String firstname = getValue();
                        list.stream().filter((person) -> (person.getId() == id)).forEachOrdered((person) -> {
                            person.setFirstname(firstname);
                        });
                        System.out.println("Изменения сохранены!");
                        whatNextInside(list);
                        break;
                    case 3:
                        System.out.println("Введите новое значение Birthday");
                        String date = scan.nextLine();
                        LocalDate birthday = getDate(date);
                        list.stream().filter((person) -> (person.getId() == id)).forEachOrdered((person) -> {
                            person.setBirthDay(birthday);
                        });
                        System.out.println("Изменения сохранены!");
                        whatNextInside(list);
                        break;
                    case 4:
                        System.out.println("Введите новые данные:");
                        list.stream()
                                .filter(a -> a.getId() == id)
                                .forEach(a -> a.setEmails(editList(a.getEmails())));
                        System.out.println("Изменения сохранены!");
                        whatNextInside(list);
                        break;
                    case 5:
                        System.out.println("Введите новые данные:");
                        list.stream()
                                .filter(a -> a.getId() == id)
                                .forEach(a -> a.setLanguages(editList(a.getLanguages())));
                        System.out.println("Изменения сохранены!");
                        whatNextInside(list);
                        break;
                    default:
                        System.out.println(ANSI_RED + "Неверный выбор! Вы должны ввести "
                                + "число от 1 до 5! 5 - это выход из программы." + ANSI_RESET);
                        System.out.println("Введите заново.");
                        break;
                }
            } catch (IOException | ParseException e) {
                System.out.println(ANSI_RED + "НЕПРАВИЛЬНЫЙ ВВОД ДАННЫХ!" + ANSI_RESET
                        + " Введите заново.");
            }
        }
    }

    /**
     * Получение нового значения.
     *
     * @return
     */
    public static String getValue() {
        System.out.println("Введите новое значение: ");
        Scanner scan = new Scanner(System.in);
        String value = scan.nextLine();
        return value;
    }

    /**
     * Проверка есть ли такой Id в списке.
     *
     * @param list
     * @param id
     * @return
     */
    public static boolean checkId(List<Person> list, int id) {
        return list.stream().anyMatch((person) -> (person.getId() == id));
    }

    /**
     * Метод для редактирования списков с языками и почтой
     *
     * @param list
     * @return
     */
    public static List editList(List<String> list) {
        while (true) {
            try {
                System.out.println("Желаете очистить список от значений перед добавлением?");
                System.out.println("1-Нет 2-Да");
                Scanner s = new Scanner(System.in);
                int ans = s.nextInt();
                if (ans == 2) {
                    list.clear();
                }
                System.out.println("Введите новые значения через запятую:");
                Scanner s2 = new Scanner(System.in);
                String str = s2.nextLine().replaceAll(" ", "");
                List<String> newlist = new ArrayList<>(getStringTokens(str));
                if (ans == 2) {
                    list = newlist;
                    return list;
                }
                for (String string : newlist) {
                    list.add(string);
                }
                return list;
            } catch (Exception e) {
                System.out.println(ANSI_RED + "НЕПРАВИЛЬНЫЙ ВВОД ДАННЫХ!" + ANSI_RESET);
                System.out.println("Вводите заново.");
            }
        }
    }
}
