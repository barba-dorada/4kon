import model.*
import org.junit.After
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import java.time.DayOfWeek
import kotlin.test.assertEquals

/**
 * Created by vad on 26.08.2015 23:08
 * javaProj
 */
public class JUnit4StringTest {

    fun prepareTestData(): List<PlanTemplate> {
        val sc = SubConto("default")
        val plans = listOf(PlanTemplate(Money("350"), "обеды", sc, PlanPeriod.WORKDAY, d("01.07.2015"), d("31.12.2020")),
                PlanTemplate(Money("600"), "проезд", sc, PlanPeriod.WEEKLY, d("03.07.2015"), d("31.12.2020")),
                PlanTemplate(Money("45000"), "КАСКО", sc, PlanPeriod.YEARLY, d("10.09.2014"), d("31.12.2020")),
                PlanTemplate(Money("15000"), "ОСАГО", sc, PlanPeriod.YEARLY, d("10.09.2014"), d("31.12.2020")),
                PlanTemplate(Money("5000"), "стом", sc, PlanPeriod.MONTHLY, d("01.07.2015"), d("31.12.2020")),
                PlanTemplate(Money("5000"), "стом", sc, PlanPeriod.MONTHLY, d("01.07.2015"), d("31.12.2020")),
                PlanTemplate(Money("17000"), "кредит", sc, PlanPeriod.MONTHLY, d("17.09.2014"), d("31.12.2020"))
        )
        return plans
    }

    Test fun testPlansGenerator() {
        val plansTempl = prepareTestData()
        val plans = generatePlans(plansTempl, d("01.09.2015"), d("30.09.2015"))

        assertEquals(0, plans.filter({ it.date > d("30.09.2015") }).size())
        assertEquals(0, plans.filter({ it.date < d("01.09.2015") }).size())

        assertEquals(2, plans.filter({ it.name == "стом" }).size())
        assertEquals(4, plans.filter({ it.name == "проезд" }).size(), "проездной")

        assertEquals(2400, plans.filter({ it.name == "проезд" }).sumBy { it.money.amount.intValue() }, "проездной сумма")

        assertEquals(22, plans.filter({ it.name == "обеды" }).size(), "обеды")
        assertEquals(7700, plans.filter({ it.name == "обеды" }).sumBy { it.money.amount.intValue() }, "обеды сумма")

        assertEquals(1, plans.filter({ it.name == "КАСКО" }).size(), "КАСКО")
        assertEquals(1, plans.filter({ it.name == "ОСАГО" }).size(), "ОСАГО")

        assertEquals(22 + 2 + 4 + 2 + 1, plans.size(), "колво планов")

        assertEquals(89400 + 350 * 22, plans.sumBy { it.money.amount.intValue() }, "сентябрь сумма")
    }

    Test fun testWorkDayGen() {
        val workDays = dateGenWorkDay(start = d("01.08.2015"), from = d("15.08.2015"), to = d("31.08.2015"))
        assertEquals(11, workDays.size())
        assertEquals(0, workDays.filter { it.getDayOfWeek() in arrayOf(DayOfWeek.SUNDAY, DayOfWeek.SATURDAY) }.size())
    }

    Test fun testDataGen() {
        val a = dateGen(start = d("01.08.2015"), from = d("15.08.2015"), to = d("31.08.2015"))
        assertEquals(a.joinToString(","), "2015-08-15,2015-08-22,2015-08-29")
    }

    Test fun testCapitalize() {
        assertEquals(d("01.01.2015"), d("01.01.2015"))
        // assertEquals comes from kotlin.test.*
        assertEquals("Hello world!", "hello world!".capitalize())
    }
}