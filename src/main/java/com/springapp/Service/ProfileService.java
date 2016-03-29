package com.springapp.Service;

import com.springapp.Entity.Profile;

import java.util.List;

/**
 * Created by ivanybma on 3/27/16.
 */
public interface ProfileService {

    public void insert(Profile st);

    public void delete(String id);

    public List<Profile> getProfilebyid(String id);

    public void update(Profile obj);

    public String testquery();

}
