package com.springapp.Repository;

import com.springapp.Entity.Profile;
import com.springapp.XDao.ProfileDao;

/**
 * Created by ivanybma on 3/27/16.
 */
public class ProfileDaoImp extends AbsProfileDaoImp<Profile> implements ProfileDao {

    public String testquery(){
        System.out.println("profile query test before");
        String rpl = getSession().getNamedQuery("findAll").toString();
        System.out.println("profile query test after");
        return rpl;
    }
    //for implementation of profile xdao special interface
}
