package tinkoff;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by vadim.tishenko
 * on 10.09.2017 14:40.
 */
public class Row {
    //"Дата операции";"Дата платежа";
    LocalDateTime operationDate;
    LocalDateTime payDate;
    // "Номер карты";"Статус";
    String cardNumber;
    String status;

    // "Сумма операции";"Валюта операции";"Сумма платежа";"Валюта платежа";"Кэшбэк";
    BigDecimal sum;
    String val;
    BigDecimal paySum;
    String payVal;
    BigDecimal cashbak;
    // "Категория";"MCC";"Описание";"Бонусы (включая кэшбэк)"
    String category;
    String mcc;
    String description;
    BigDecimal bonus;

    public LocalDateTime getPayDate() {
        return payDate;
    }

    public void setPayDate(LocalDateTime payDate) {
        this.payDate = payDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(LocalDateTime operationDate) {
        this.operationDate = operationDate;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public BigDecimal getPaySum() {
        return paySum;
    }

    public void setPaySum(BigDecimal paySum) {
        this.paySum = paySum;
    }

    public String getPayVal() {
        return payVal;
    }

    public void setPayVal(String payVal) {
        this.payVal = payVal;
    }

    public BigDecimal getCashbak() {
        return cashbak;
    }

    public void setCashbak(BigDecimal cashbak) {
        this.cashbak = cashbak;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return String.format("%20s|%20s|%5s|%6s|%10s|%3s|%20s|%4s|%20s",operationDate,payDate,cardNumber,status,sum,val,category,mcc,description);


        // "Сумма операции";"Валюта операции";"Сумма платежа";"Валюта платежа";"Кэшбэк";

//        BigDecimal paySum;
//        String payVal;
//        BigDecimal cashbak;
//        // "Категория";"MCC";"Описание";"Бонусы (включая кэшбэк)"
//        String category;
//        String mcc;
//        String description;
//        BigDecimal bonus;

        /*return "Row{" +
                "payDate=" + payDate +
                ", cardNumber='" + cardNumber + '\'' +
                ", status='" + status + '\'' +
                ", operationDate=" + operationDate +
                ", sum=" + sum +
                ", val='" + val + '\'' +
                ", paySum=" + paySum +
                ", payVal='" + payVal + '\'' +
                ", cashbak=" + cashbak +
                ", category='" + category + '\'' +
                ", mcc='" + mcc + '\'' +
                ", description='" + description + '\'' +
                ", bonus=" + bonus +
                '}';*/
    }
}
