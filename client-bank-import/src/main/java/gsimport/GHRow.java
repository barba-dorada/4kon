package gsimport;

import com.google.common.base.Objects;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by tischenko on 11.09.2017.
 */
public class GHRow {
    public String user;
    public LocalDate date;
    public String acc;
    public String sunc;
    public BigDecimal sum;
    public String description;
    public BigDecimal sumAfter;
    public String month;

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
