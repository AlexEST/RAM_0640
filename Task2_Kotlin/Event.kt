import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

abstract class Event {
    abstract var name: String
    abstract var date: LocalDate
    var formatter: DateTimeFormatter  = DateTimeFormatter.ofPattern("dd.MM.YYYY")
    abstract override fun toString(): String
}

open class standartEvent(override var name: String, override var date: LocalDate): Event(){
    override fun toString(): String {
        return "Название: "+"$name"+" Дата: "+"${date.format(formatter)}"
    }
}

class eventReminder(name: String, date:LocalDate, var timeRemind:LocalDate, var remCount: Number): standartEvent(name, date){
    override fun toString(): String{
        return "Название: "+"$name"+" Дата: "+"${date.format(formatter)}"+
                " Дата напоминания: "+"${timeRemind.format(formatter)}" +
                " Количество напоминаний: "+"$remCount "
    }
}

class eventDistribution(name: String, date: LocalDate, var mailList:String, var mailText: String, var mailDate: LocalDate):standartEvent(name,date){
    override fun toString(): String {
        return "Название: "+"$name"+" Дата: "+"${date.format(formatter)}"+" Лист рассылки: "+"$mailList" +
                " Текст: "+"$mailText "+" Дата рассылки: "+"${mailDate.format(formatter)}"
    }
}