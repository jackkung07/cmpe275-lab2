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

    private Class<T> getDomainClass(){
        if (domainClass==null)
        {
            ParameterizedType thisType=(ParameterizedType) getClass().getGenericSuperclass();
            this.domainClass=(Class<T>) thisType.getActualTypeArguments()[0];
        }
        return domainClass;
    }

    private String getDomainClassName(){
        return getDomainClass().getName();
    }

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

    public List<T> query(String id){
        return entityManager.createQuery("from Profile as p where id=:id").setParameter("id",id).getResultList();
    }

    public void update(T obj){
        System.out.println(obj);
        entityManager.merge(obj);
       // entityManager.flush();
    }

    public void deleteById(Serializable id){
        List<T> profilelst = entityManager.createQuery("from Profile as p where id=:id").setParameter("id",id).getResultList();
        for(T obj:profilelst){
            entityManager.remove(obj);
        }
    }

}
