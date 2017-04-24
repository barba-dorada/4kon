package ru.cwl.kon.dao;

import ru.cwl.kon.model.Fact;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by vad on 24.01.2016 14:46
 * 4kon
 */
public class FactsDaoHashListImpl implements FactsDao {
    List<Fact> factsList = new ArrayList<>();

    public FactsDaoHashListImpl() {
    }


    @Override
    public Fact get(int id) {
        for (Fact fact : factsList) {
            if (id == fact.getId()) {
                return fact;
            }
        }
        return null;
    }

    @Override
    public Fact save(Fact value) {
        if (value.getId() == 0) {
            value.setId(getNextId());
            factsList.add(value);
            return value;
        }
        return null;
    }

    @Override
    public Fact update(Fact value) {
        int index = factsList.indexOf(value);
        factsList.set(index, value);
        return value;
    }

    @Override
    public List<Fact> getAll() {
        return factsList;
    }

    @Override
    public void delete(Fact value) {
        factsList.remove(value);
    }

    @Override
    public long size() {
        return factsList.size();
    }

    static int nextId = 1;

    public int getNextId() {
        return nextId++;
    }
}
