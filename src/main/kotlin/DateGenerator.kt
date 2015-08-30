import model.d
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*

/**
 * Created by vad on 27.08.2015 20:08
 * javaProj
 */

public fun dateGen(start: LocalDate, from: LocalDate, to: LocalDate=from.plusMonths(1), chronoUnit:ChronoUnit=ChronoUnit.WEEKS): List<LocalDate> {
    val res = ArrayList<LocalDate>()
    var d=start
    while(d<from) {
        d=d.plus(1, chronoUnit)
    }
    while(d<=to){
        res.add(d)
        d=d.plus(1, chronoUnit)
    }

    return res
}
public fun dateGenWorkDay(start: LocalDate, from: LocalDate, to: LocalDate=from.plusMonths(1)): List<LocalDate> {
    val res = ArrayList<LocalDate>()
    var d=if(start> from ) start else from

    d = skipHolyDays(d)
    while(d<=to){
        res.add(d)
        d=d.plusDays(1)
        d = skipHolyDays(d)
    }
    return res
}

private fun skipHolyDays(d: LocalDate): LocalDate {
    var d1 = d
    while (d1.getDayOfWeek() == DayOfWeek.SUNDAY || d1.getDayOfWeek() == DayOfWeek.SATURDAY) {
        d1 = d1.plusDays(1)
    }
    return d1
}