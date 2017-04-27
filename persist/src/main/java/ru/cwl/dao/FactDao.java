package ru.cwl.dao;

import ru.cwl.kon.model.Fact;

import java.util.List;

/**
 * Created by tischenko on 27.04.2017.
 */
public interface FactDao {
    List<Fact> getAll();
    Fact getById(Integer id);
    Fact update(Integer id,Fact v);
    void delete(Integer id);
    Fact save(Fact v);
    Long size();
}
