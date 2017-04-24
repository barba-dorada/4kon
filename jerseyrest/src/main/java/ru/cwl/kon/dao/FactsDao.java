package ru.cwl.kon.dao;

import ru.cwl.kon.model.Fact;

import java.util.List;

/**
 * Created by vad on 24.01.2016 14:40
 * 4kon
 */
public interface FactsDao {
    Fact get(int id);
    Fact save(Fact value);
    Fact update(Fact value);
    List<Fact> getAll();
    void delete(Fact value);
    long size();
}
