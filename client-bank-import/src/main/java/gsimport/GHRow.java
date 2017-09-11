package gsimport;

import com.google.common.base.Objects;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by tischenko on 11.09.2017.
 */
public class GHRow {
    String user;
    LocalDate date;
    String acc;
    String sunc;
    BigDecimal sum;
    String description;
    BigDecimal sumAfter;
    String month;

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("user", user)
                .add("date", date)
                .add("acc", acc)
                .add("sunc", sunc)
                .add("sum", sum)
                .add("description", description)
                .add("sumAfter", sumAfter)
                .add("month", month)
                .toString();
    }
}
