package com.springapp.Repository;

import com.springapp.Dao.Dao;
import com.springapp.Entity.Profile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivanybma on 3/27/16.
 */
    @Repository
public abstract class AbsProfileDaoImp <T extends Object> implements Dao<T> {
    @PersistenceContext
    EntityManager entityManager;

    private Class<T> domainClass;


    /**
     * Reflect the concrete class object
     *
     * @author  Yuebiao ma
     * @version 1.0
     * @since 2016-03-30
     *
     */
    private Class<T> getDomainClass(){
        if (domainClass==null)
        {
            ParameterizedType thisType=(ParameterizedType) getClass().getGenericSuperclass();
            this.domainClass=(Class<T>) thisType.getActualTypeArguments()[0];
        }
        return domainClass;
    }

    /**
     * Reflect the concrete class name
     *
     * @author  Yuebiao ma
     * @version 1.0
     * @since 2016-03-30
     *
     */
    private String getDomainClassName(){
        return getDomainClass().getName();
    }

    /**
     * Persist object
     *
     * @author  Yuebiao ma
     * @version 1.0
     * @param obj object pending to be persisted
     * @since 2016-03-30
     *
     */
    public void insert(T obj){

        Method method= ReflectionUtils.findMethod(getDomainClass(),"toString");
        System.out.println("Saving.....");
        if(method!=null)
        {
            try{
                System.out.println(method.invoke(obj,null));
            }catch(Exception e){}
        }
        entityManager.persist(obj);
        System.out.println("Saved!");
    }

    /**
     * Retrieve object list according to object id
     *
     * @author  Yuebiao ma
     * @version 1.0
     * @param id for query
     * @since 2016-03-30
     *
     */
    public List<T> query(String id){
        return entityManager.createQuery("from Profile as p where id=:id").setParameter("id",id).getResultList();
    }

    /**
     * Update object
     *
     * @author  Yuebiao ma
     * @version 1.0
     * @param obj object pending to be updated
     * @since 2016-03-30
     *
     */
    public void update(T obj){
        System.out.println(obj);
        entityManager.merge(obj);
    }

    /**
     * delete object by id
     *
     * @author  Yuebiao ma
     * @version 1.0
     * @param id object pending to be deleted
     * @since 2016-03-30
     *
     */
    public void deleteById(Serializable id){
        List<T> profilelst = entityManager.createQuery("from Profile as p where id=:id").setParameter("id",id).getResultList();
        for(T obj:profilelst){
            entityManager.remove(obj);
        }
    }

}
