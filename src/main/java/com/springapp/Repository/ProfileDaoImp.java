package com.springapp.Repository;

import com.springapp.Entity.Profile;
import com.springapp.XDao.ProfileDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * Created by ivanybma on 3/27/16.
 */
@Transactional
@Repository
public class ProfileDaoImp extends AbsProfileDaoImp<Profile> implements ProfileDao {

    public String testquery(){
        System.out.println("profile query test before");
        Query qry = getSession().getNamedQuery("findAll");
        String rst ="";
        ArrayList<Profile> rstlst = (ArrayList)qry.list();
        for(int i=0; i<rstlst.size();i++)
            rst+=rstlst.get(i).toString();
        System.out.println("profile query test after");
        return rst;
    }
    //for implementation of profile xdao special interface
}
