package model

import java.time.LocalDate

data class Plan(val date: LocalDate, val category: Category, val name: String, val money: Money)