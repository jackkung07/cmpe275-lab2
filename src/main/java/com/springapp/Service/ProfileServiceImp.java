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
    public void delete(Integer id) {
        profiledaoimp.deleteById(id);
    }

    @Override
    public Profile getStudent(Integer id) {
        return profiledaoimp.get(id);
    }

    @Override
    public void update(List<Profile> stl) {
        for(Profile st:stl) {
            Profile pro = profiledaoimp.get(st.getId());
            pro.setAboutMyself(st.getAboutMyself());
            pro.setAddress(st.getAddress());
            pro.setEmail(st.getEmail());
            pro.setFirstname(st.getFirstname());
            pro.setLastname(st.getLastname());
            pro.setOrganization(st.getOrganization());

            profiledaoimp.update(pro);
        }

    }

    @Override
    public List<Profile> query(Profile st) {
        return profiledaoimp.query(st);
    }

    @Override
    public List<Profile> queryall() {
        return profiledaoimp.queryall();
    }

    @Override
    public String testquery(){
        return profiledaoimp.testquery();
    }
}
