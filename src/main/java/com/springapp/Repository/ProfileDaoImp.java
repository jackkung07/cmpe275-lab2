package com.springapp.Repository;

import com.springapp.Entity.Profile;
import com.springapp.XDao.ProfileDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.ArrayList;

/**
 * Created by ivanybma on 3/27/16.
 */
@Repository
public class ProfileDaoImp extends AbsProfileDaoImp<Profile> implements ProfileDao {

    /**
     * testing query for all the data in table
     *
     * @author  Yuebiao ma
     * @version 1.0
     * @since 2016-03-30
     *
     */
    public String testquery(){
        System.out.println("profile query test before");
        TypedQuery<Profile> qry = entityManager.createNamedQuery("findAll", Profile.class);
        String rst ="";
        ArrayList<Profile> rstlst = (ArrayList)qry.getResultList();
        for(int i=0; i<rstlst.size();i++)
            rst+=rstlst.get(i).toString();
        System.out.println("profile query test after");
        return rst;
    }
    //for implementation of profile xdao special interface
}
