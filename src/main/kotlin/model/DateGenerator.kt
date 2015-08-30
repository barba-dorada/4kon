package model

import model.Plan
import model.PlanTemplate
import model.d
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*

/**
 * Created by vad on 27.08.2015 20:08
 * javaProj
 */

//TODO generator ->Plan
fun generatePlans(plans: Collection<PlanTemplate>, from: LocalDate, to: LocalDate):List<Plan>{
    val res= ArrayList<Plan>()

    for(t in plans){
        val dates=
        if(t.period==PlanPeriod.WORKDAY){
            dateGenWorkDay(t.firstDate,from,to)
        }else{
            dateGen(t.firstDate,from,to,t.period.chronoUnit)
        }
        dates forEach {
            res.add(Plan(it, t.subConto, t.name, t.money))
        }
    }
    return res;
}

public fun dateGen(start: LocalDate, from: LocalDate, to: LocalDate =from.plusMonths(1), chronoUnit: ChronoUnit = ChronoUnit.WEEKS): List<LocalDate> {
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

public fun dateGenWorkDay(start: LocalDate, from: LocalDate, to: LocalDate =from.plusMonths(1)): List<LocalDate> {
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

public enum class PlanPeriod(val chronoUnit: ChronoUnit = ChronoUnit.DAYS) {
    WEEKLY(ChronoUnit.WEEKS),
    MONTHLY(ChronoUnit.MONTHS),
    DAILY(ChronoUnit.DAYS),
    YEARLY(ChronoUnit.YEARS),
    WORKDAY(ChronoUnit.DAYS){
        override fun inr(date: LocalDate): LocalDate {
            var nextDay= date.plus(1, chronoUnit)
            while(nextDay.getDayOfWeek()== DayOfWeek.SATURDAY ||nextDay.getDayOfWeek()== DayOfWeek.SUNDAY) nextDay=nextDay.plus(1, chronoUnit)
            return nextDay
        }
    };
    open fun inr(date: LocalDate): LocalDate = date.plus(1, chronoUnit)
}