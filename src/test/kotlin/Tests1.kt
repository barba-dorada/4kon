import model.d
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.time.DayOfWeek
import kotlin.test.assertEquals

/**
* Created by vad on 26.08.2015 23:08
* javaProj
*/
public class JUnit4StringTest {

    Before fun setUp() {
        // set up the test case
    }

    After fun tearDown() {
        // tear down the test case
    }

    Test fun testWorkDayGen(){
        val workDays=dateGenWorkDay(start =d("01.08.2015"), from =d("15.08.2015"), to =d("31.08.2015"))
        assertEquals(11,workDays.size())
        assertEquals(0,workDays.filter { it.getDayOfWeek() in arrayOf(DayOfWeek.SUNDAY,DayOfWeek.SATURDAY) }.size())
    }

    Test fun testDataGen(){
        val a= dateGen(start =d("01.08.2015"), from =d("15.08.2015"), to =d("31.08.2015"))
        assertEquals(a.joinToString(","),"2015-08-15,2015-08-22,2015-08-29")
    }
    Test fun testCapitalize() {
        assertEquals(d("01.01.2015"),d("01.01.2015"))
        // assertEquals comes from kotlin.test.*
        assertEquals("Hello world!", "hello world!".capitalize())
    }
}