import java.time.LocalDate
import java.util.*
import java.util.ArrayList
import kotlin.system.exitProcess

/**
 * Author Alexander Chelpanov
 * Date: 16.09.2019
 *
 * Cобытия в календаре
 *
 * 1.Создайте абстрактный класс Event
 * 2.Создайте производные классы:  StandartEvent (название события, дата ),
 * EventReminder (название,  дата , время для напоминания, частота повторений),
 * EventDistribution (название, дата, список для рассылки, текст рассылки, дата рассылки)
 * 3.Создайте массив из n видов событий или список ArrayList
 * 4.Необходимо вывести полную информацию из массива на экран, а также
 * организовать вывод событий, напоминаний и рассылок на  конкретную дату.
 * Oрганизовать поиск по названию (фрагменту названия).
 * 5.Используй текстовый файл для хранения информации
 *
 */
const val ANSI_RESET = "\u001B[0m"
const val ANSI_RED = "\u001B[31m"

fun main() {
    menu()
}

fun menu() {
    println("=========================================")
    println("|                 МЕНЮ                  |")
    println("=========================================")
    println("|Разделы:                               |")
    println("|1.Вывод всех событий                   |")
    println("|2.Поиск события по дате                |")
    println("|3.Поиск события по названию            |")
    println("|4.Выход                                |")
    println("=========================================")
    println("")
    println("Выберите раздел и нажмите Enter:")
    val scan = Scanner(System.`in`)
    val choice = scan.nextInt()
    while (true) {
        when (choice) {
            1 -> {
                println("Вывод всех событий:")
                EventDAO.output()
                EventDAO.writeToTextFile(EventDAO.events)
                val list1 = ArrayList<Event>(EventDAO.events)
                whatNext(list1)
            }
            2 -> {
                println("Поиск событий по дате: ")
                println("Введите дату цифрами через точку (пример: 25.9.2019 (день.месяц.год))")
                val date = scan.next()
                EventDAO.output(EventDAO.eventByDate(date))
                val list2 = ArrayList(EventDAO.eventByDate(date))
                if (list2.isEmpty()) {
                    println("Не было найдено событий соответсвующих критериям поиска.")
                }
                whatNext(list2)
            }
            3 -> {
                println("Поиск событий по названию: ")
                val partName = scan.next()
                val evs = ArrayList(EventDAO.eventByName(partName))
                if (evs.isEmpty()) {
                    println("Не было найдено событий соответсвующих критериям поиска.")
                }
                EventDAO.output(evs)
                whatNext(evs)
            }
            4 -> {
                println("Выход")
                exitProcess(0);
            }
            else -> {
                println((ANSI_RED + "Неверный выбор! Вы должны ввести "
                        + "число от 1 до 4! 4 - это выход из программы." + ANSI_RESET))
                whatNext()
            }
        }
    }
}

/**
 * Метод для определения дальнейших действий
 *
 * @return
 */
private fun whatNext(list: List<Event>): Int {
    while (true) {
        println(
            "Введите 1 если хотите завершить программу. "
                    + "Если хотите вернуться в меню введите 2. Для выгрузки данных"
                    + " в текстовый файл нажмите 3.(После возврат в меню.)"
        )
        val choice = Scanner(System.`in`)
        when (choice.nextInt()) {
            2 -> menu()
            1 -> exitProcess(0)
            3 -> {
                EventDAO.writeToTextFile(list)
                println("Файл записан!")
                menu()
            }
            else -> println(ANSI_RED + "Вы ввели неверный вариант!" + ANSI_RESET)
        }
    }
}

private fun whatNext(): Int {
    while (true) {
        println(("Введите 1 если хотите завершить программу. " + "Если хотите вернуться в меню введите 2"))
        val choice = Scanner(System.`in`)
        when (choice.nextInt()) {
            2 -> menu()
            1 -> exitProcess(0)
            else -> println(ANSI_RED + "Вы ввели неверный вариант!" + ANSI_RESET)
        }
    }
}


