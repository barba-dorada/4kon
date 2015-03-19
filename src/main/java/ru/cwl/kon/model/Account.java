package ru.cwl.kon.model;

/**
 * Created by vad on 19.03.2015 4:11.
 */
public class Account {
    int id;
    String name;
    String currency;
    int amount;

    public Account(int id, String name,  int amount, String currency) {
        this.id = id;
        this.name = name;
        this.currency = currency;
        this.amount = amount;
    }
}
