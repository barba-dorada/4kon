package ru.cwl.kon.model;

import java.util.Date;

/**
 * Created by vad on 18.12.2015 22:33
 * 4kon
 */
public class Fact {
    int id;
    Date date;
    String account;
    Double amount;
    String descr1;
    String descr2;
    Double subTotal;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescr1() {
        return descr1;
    }

    public void setDescr1(String descr1) {
        this.descr1 = descr1;
    }

    public String getDescr2() {
        return descr2;
    }

    public void setDescr2(String descr2) {
        this.descr2 = descr2;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public String toString() {
        return "Fact{" +
                "id=" + id +
                ", date=" + date +
                ", account='" + account + '\'' +
                ", amount=" + amount +
                ", descr1='" + descr1 + '\'' +
                ", descr2='" + descr2 + '\'' +
                ", subTotal=" + subTotal +
                '}';
    }
}
