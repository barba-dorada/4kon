package ru.cwl.dao.simple;

import ru.cwl.dao.FactDao;
import ru.cwl.kon.model.Fact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 24.04.2017.
 */
public class SimpleFactDao implements FactDao {
    Map<Integer,Fact> map=new HashMap<>();
    Integer nextId=1;

    @Override
    public List<Fact> getAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public Fact getById(Integer id) {
        Fact v = map.get(id);
        if(v==null){
            throw new RuntimeException();
        }
        return v;
    }

    @Override
    public Fact update(Integer id, Fact v) {
        getById(id);
        v.setId(id);
        map.put(id,v);
        return v;
    }

    @Override
    public void delete(Integer id) {
        getById(id);
        map.remove(id);
    }

    @Override
    public Fact save(Fact v) {
        Integer id=getNextId();
        v.setId(id);
        map.put(id,v);
        return v;
    }

    @Override
    public Long size() {
        return Long.valueOf(map.size());
    }

    private Integer getNextId() {
        return nextId++;
    }
}
