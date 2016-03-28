package com.springapp.XDao;
import com.springapp.Dao.Dao;
import com.springapp.Entity.Profile;

/**
 * Created by ivanybma on 3/27/16.
 */
public interface ProfileDao extends Dao<Profile> {

    //other additional method for profile
    String testquery();

}
