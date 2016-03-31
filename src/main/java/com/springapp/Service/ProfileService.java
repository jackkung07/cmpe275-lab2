package com.springapp.Service;

import com.springapp.Entity.Profile;

import java.util.List;

/**
 * Created by ivanybma on 3/27/16.
 */
public interface ProfileService {

    /**
     * persist object
     *
     * @author  Yuebiao ma
     * @version 1.0
     * @param st object pending to be persisted
     * @since 2016-03-30
     *
     */
    public void insert(Profile st);

    /**
     * delete object
     *
     * @author  Yuebiao ma
     * @version 1.0
     * @param id of object pending to be deleted
     * @since 2016-03-30
     *
     */
    public void delete(String id);

    /**
     * query object list
     *
     * @author  Yuebiao ma
     * @version 1.0
     * @param id of object to be queried
     * @since 2016-03-30
     *
     */
    public List<Profile> getProfilebyid(String id);

    public void update(Profile obj);

    public String testquery();

}
