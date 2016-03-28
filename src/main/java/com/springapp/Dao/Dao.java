package com.springapp.Dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ivanybma on 3/27/16.
 */

public interface Dao<T extends Object> {

    public void insert(T obj);
    public void delete(T obj);
    public void deleteById(Serializable id);
    public void deleteAll();
    public void update(T st);
    public List<T> query(T st);
    public List<T> queryall();
    public T get(Serializable id);
    public T load(Serializable id);
    public long count();
    public boolean exists(Serializable id);

}
