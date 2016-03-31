package com.springapp.Dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ivanybma on 3/27/16.
 */

public interface Dao<T extends Object> {

    public void insert(T obj);
    public void deleteById(Serializable id);
    public void update(T st);
    public List<T> query(String id);

}
