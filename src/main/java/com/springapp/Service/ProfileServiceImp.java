package com.springapp.Service;

import javax.inject.Inject;
import com.springapp.Entity.Profile;
import com.springapp.XDao.ProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ivanybma on 3/27/16.
 */
@Service
public class ProfileServiceImp implements ProfileService {

    @Inject
    private ProfileDao profiledaoimp;

    @Override
    public void insert(Profile st) {
        profiledaoimp.insert(st);
    }

    @Override
    public void delete(String id) {
        profiledaoimp.deleteById(id);
    }

    @Override
    public String testquery(){
        return profiledaoimp.testquery();
    }

    @Override
    public List<Profile> getProfilebyid(String id) {
        return profiledaoimp.query(id);
    }

    @Override
    public void update(Profile obj) {
            profiledaoimp.update(obj);
    }


}
