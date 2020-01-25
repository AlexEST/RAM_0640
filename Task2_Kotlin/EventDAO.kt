import java.io.FileWriter
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Calendar
import java.util.GregorianCalendar
import java.util.StringTokenizer

import jdk.nashorn.internal.objects.NativeDate.getTime
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 *
 * @author Alexander Chelpanov 182730 EDTR34
 * Date: 16.09.2019
 */
object EventDAO {
    var events: MutableList<Event> = ArrayList()

    init {
        //генерация каких-то произвольных событий
        for (i in 1..19) {
            val date = LocalDate.of(2019, Calendar.SEPTEMBER, i)
            val one = standartEvent("Event$i", date)
            events.add(one)
            val two = eventReminder("Holiday$i", date, date, i)
            events.add(two)
            val three = eventDistribution("Birthday$i", date, "Anton, John, Omar", " Gongratulations!", date)
            events.add(three)
        }
    }

    /**
     * Метод для поиска событий по дате. На входе строка с датой.
     * @param date
     * @return
     */
    fun eventByDate(date: String): List<Event> {
        //для расшифровки строки использую класс StringTokenizer. Сепаратор точка
        val st = StringTokenizer(date, ".")
        var Evs = ArrayList<Event>()
        var date2: LocalDate//переменная для записи даты
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
        var sarr = arrayOfNulls<String>(3)
        var counter = 0
        var token: String
        //разбор строки на отдельные лексемы и запись их в массив
        while (st.hasMoreTokens()) {
            token = st.nextToken()
            sarr[counter] = token
            counter++
        }
        // пишем в массив и одновременно парсим в число
        date2 = LocalDate.of(Integer.parseInt(sarr[2]), Integer.parseInt(sarr[1]) - 1, Integer.parseInt(sarr[0]))
        //далее ищем в листе с событиями события с нашей датой
        for (event in events) {
            if (event.date.format(formatter) == date2.format(formatter)) {
                Evs.add(event)
            }
        }
        return Evs
    }

    /**
     * Метод для поиска по началу или полному названию события
     * @param name
     * @return
     */
    fun eventByName(name: String): List<Event> {
        var name = name
        name = name.replace(" ".toRegex(), "")//убираем все пробелы
        var evs = ArrayList<Event>()
        for (event in events) {
            //ищем нашу подстроку в строке название события игнорируя регистр букв
            if (event.name.regionMatches(0, name, 0, name.length, ignoreCase = true)) {
                evs.add(event)
            }
        }
        return evs
    }

    /**
     * Выгрузка в файл.
     * @param evs
     */
    fun writeToTextFile(evs: List<Event>) {
        try {
            FileWriter("events.txt").use { writer ->
                for (event in evs) {
                    val lineSeparator = System.getProperty("line.separator")//сепаратор для переноса на новую строку
                    writer.write(event.toString() + lineSeparator)
                }
                writer.flush()
                writer.close()//закрываем поток.
            }
        } catch (e: IOException) {
            println(e.message)
        }

    }

    /**
     * Методы для вывода на экран. С листом на входе и его перегруженная версия без.
     * @param list
     */
    fun output(list: List<Event>) {
        list.forEach { `object` -> println(`object`) }
    }

    fun output() {
        events.forEach { _item -> println(_item) }
    }
}