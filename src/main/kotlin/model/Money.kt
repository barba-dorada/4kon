package model

import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode
import kotlin.math.div
import kotlin.math.times

data class Money(val amount: BigDecimal, val cur: Currency = Currency.RUR) {
    constructor(amount: String, cur: String = "RUR") : this(BigDecimal(amount, MathContext.DECIMAL64).setScale(2), Currency.valueOf(cur))
    override fun toString() = "${amount}${cur.s}"
}

fun Money.convert(currency: Currency): Money {
    val usdRate = BigDecimal("68.5", MathContext.DECIMAL64)
    val eurRate = BigDecimal("75.8", MathContext.DECIMAL64)
    if (this.cur == currency) return this

    var res: BigDecimal? = when (this.cur) {
        Currency.RUR -> when (currency) {
            Currency.USD -> amount / usdRate
            Currency.EUR -> amount.divide(eurRate, 2, RoundingMode.HALF_UP)
            else -> null
        }
        Currency.USD -> when (currency) {
            Currency.RUR -> amount * usdRate
            Currency.EUR -> amount * BigDecimal("75.3")
            else -> null
        }
        Currency.EUR -> when (currency) {
            Currency.RUR -> amount * eurRate
            Currency.USD -> amount * BigDecimal("75.3")
            else -> null
        }
        else -> null
    }
    return Money(res ?: BigDecimal(-1.0), currency)
}