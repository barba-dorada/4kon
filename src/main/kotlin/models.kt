package model

import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.ArrayList
import java.util.LinkedList
import kotlin.math.div
import kotlin.math.times

/**
* Created by vad on 23.08.2015 11:33 23:08
* javaProj
*/



data class Account(val name: String)

data class PlanTemplate(val money: Money, val name: String, val subConto: SubConto, val period: PlanPeriod, val firstDate: LocalDate, val lastDate: LocalDate) {
    fun toPlans(): List<Plan> {
        val result = ArrayList<Plan>()
        var i = firstDate;
        while (i.isBefore(lastDate)) {
            result.add(Plan(i, subConto, name, money))
            i = i.plus(1, ChronoUnit.YEARS)
        }

        return result
    }

    fun toPlans(from: LocalDate, to: LocalDate): List<Plan> {
        val result = ArrayList<Plan>()
        var date = firstDate;



        while (date <= lastDate) {
            if (date > to) break
            if (date >= from) {
                result.add(Plan(date, subConto, name, money))
            }
            //TODO add model.model.PlanPeriod implementation
            date = period.inr(date)
            date = date.plus(1, ChronoUnit.YEARS)

        }

        return result
    }
}


data class SubConto(val name: String)
data class Plan(val date: LocalDate, val subConto: SubConto, val name: String, val money: Money)


data class Fact(val account: Account, val subConto: SubConto, val money: Money, val date: LocalDate)
data class User(val name: String)

fun d(s: String): LocalDate {
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    val date = LocalDate.parse(s, formatter);
    return date;
}
fun d(date:LocalDate):String{
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    return formatter.format(date)
}

fun main(args: Array<String>) {
    val auto = SubConto("Авто")
    val pt = PlanTemplate(Money("40000", "RUR"), "КАСКО", auto, PlanPeriod.YEARLY, d("10.09.2014"), d("25.09.2017"))
    val pt2 = PlanTemplate(Money("350", "RUR"), "обеды", auto, PlanPeriod.WORKDAY, d("17.09.2014"), d("17.09.2017"))
    val pt3 = PlanTemplate(Money("20000", "RUR"), "аванс", auto, PlanPeriod.MONTHLY, d("10.09.2014"), d("17.09.2017"))
    val pt4 = PlanTemplate(Money("40000", "RUR"), "получка", auto, PlanPeriod.MONTHLY, d("20.09.2014"), d("17.09.2017"))
    val pt5 = PlanTemplate(Money("1200", "RUR"), "проездной", auto, PlanPeriod.MONTHLY, d("17.09.2014"), d("17.09.2017"))
    val pt6 = PlanTemplate(Money("300", "RUR"), "парикмах", auto, PlanPeriod.MONTHLY, d("17.09.2014"), d("17.09.2017"))

    val templates = arrayOf(pt, pt2, pt2, pt3, pt4, pt5, pt6)
    val res = templates.toPlans(d("01.08.2015"), d("30.09.2015"))
    println("${res.toString2()}")

    println("$pt")
    val plans = pt.toPlans()
    println("$plans")
    val m = Money("102.54", "RUR")
    val eur = m.convert(Currency.EUR)
    println("m:$m, eur:$eur")
}

fun Array<PlanTemplate>.toPlans(from: LocalDate, to: LocalDate): List<Plan> {
    val result = LinkedList<Plan>()
    for (p in this) {
        result.addAll(p.toPlans(from, to))
    }
    return result.sortBy { it.date }
}

fun List<Plan>.toString2(): String {
    var s: String = "plans....\n"
    this.forEach { s += "$it\n" }
    return s + "-----\n";
}


