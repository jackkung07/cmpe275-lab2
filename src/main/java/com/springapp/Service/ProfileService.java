package com.springapp.Service;

import com.springapp.Entity.Profile;

import java.util.List;

/**
 * Created by ivanybma on 3/27/16.
 */
public interface ProfileService {

    public void insert(Profile st);

    public void delete(Integer id);

    public Profile getStudent(Integer id);

    public void update(List<Profile> stl);

    public List<Profile> query(Profile st);

    public List<Profile> queryall();

    public String testquery();

}
