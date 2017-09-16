package tinkoff;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vadim.tishenko
 * on 10.09.2017 14:46.
 */
public class CsvReader {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    public List<Row> read(Reader reader) throws IOException {
        final CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader());
        ArrayList<Row> result = new ArrayList<Row>();
        try {
            for (final CSVRecord r : parser) {
                /*
"Дата операции";"Дата платежа";
"Номер карты";"Статус";
"Сумма операции";"Валюта операции";
"Сумма платежа";"Валюта платежа";
"Кэшбэк";
"Категория";"MCC";"Описание";
"Бонусы (включая кэшбэк)"
 */
                try {
                    Row res = new Row();

                    res.operationDate = getDT(r, 0);
                    res.payDate = getDT(r, 1);
                    res.cardNumber = r.get(2);
                    res.status = r.get(3);
                    res.sum = getNum(r, 4);
                    res.val = r.get(5);

                    res.paySum = getNum(r, 6);
                    res.payVal = r.get(7);

                    res.cashbak = getNum(r, 8);

                    res.category = r.get(9);
                    res.mcc = r.get(10);
                    res.description = r.get(11);
                    res.bonus = getNum(r, 12);

                    result.add(res);
                }catch (Throwable t){
                    System.out.println(r.toString());
                    t.printStackTrace();
                }

            }

        } finally {
            parser.close();
            reader.close();
        }
        return result;
    }

    private BigDecimal getNum(CSVRecord record, int n) {
        String v=record.get(n);
        return v.isEmpty() ? null: new BigDecimal(v.replace(",","."));
    }

    LocalDateTime getDT(CSVRecord record, int n) {
        String v1 = record.get(n);
        LocalDateTime result = v1.isEmpty() ? null : LocalDateTime.parse(v1, formatter);
        return result;
    }


}
