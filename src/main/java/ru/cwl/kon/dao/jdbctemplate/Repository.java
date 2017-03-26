package ru.cwl.kon.dao.jdbctemplate;


import java.util.List;

/**
 * Created by vad on 07.02.2016 11:31
 * 4kon
 */
public interface Repository<T, ID> {
    public T create(T fact);

    public T read(ID id);

    public T update(T fact);

    public void delete(ID id);

    public List<T> readAll();
}
