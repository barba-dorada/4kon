package model

import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*

data class PlanTemplate(val money: Money, val name: String, val category: Category, val period: PlanPeriod, val firstDate: LocalDate, val lastDate: LocalDate) {
    fun toPlans(): List<Plan> {
        val result = ArrayList<Plan>()
        var i = firstDate;
        while (i.isBefore(lastDate)) {
            result.add(Plan(i, category, name, money))
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
                result.add(Plan(date, category, name, money))
            }
            //TODO add model.model.PlanPeriod implementation
            date = period.inr(date)
            date = date.plus(1, ChronoUnit.YEARS)

        }

        return result
    }
}