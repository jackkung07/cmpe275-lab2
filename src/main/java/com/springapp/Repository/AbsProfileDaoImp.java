package com.springapp.Repository;

import com.springapp.Dao.Dao;
import com.springapp.Entity.Profile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import javax.inject.Inject;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivanybma on 3/27/16.
 */
@Transactional
public abstract class AbsProfileDaoImp <T extends Object> implements Dao<T> {
    @Inject
    private SessionFactory mySessionFactory;

    private Class<T> domainClass;

    protected Session getSession(){
        Session sess =  mySessionFactory.getCurrentSession();
        return sess;
    }

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

        getSession().save(obj);
        System.out.println("Saved!");
    }

    public List<T> query(String id){
        return  getSession().createCriteria(Profile.class)
                .add( Restrictions.eq("id",id) ).list();
    }

    public void update(T obj){
        System.out.println(obj);
        getSession().update(obj);
    }

    public void deleteById(Serializable id){
        Session tmpsess =getSession();
        List<T> profilelst = tmpsess.createCriteria(Profile.class)
                .add( Restrictions.eq("id",id) ).list();
        for(T obj:profilelst){
            tmpsess.delete(obj);
        }
    }

    //below functions are not used by lab_2 project

    public void delete(T obj){
        System.out.println(obj);
        getSession().delete(obj);
    }

    public void deleteAll(){
        getSession().createQuery("delete " + getDomainClassName()).executeUpdate();
    }

    public T get(Serializable id){
        return null;
    }

    public T load(Serializable id){
        Session session=getSession();
        return (T) session.load(getDomainClass(), id);
    }

    public long count(){
        return Long.valueOf(getSession().createQuery("select count(*) from " +
                getDomainClassName()).uniqueResult().toString());
    }

    public boolean exists(Serializable id){
        return(get(id)!=null);
    }

    public List<T> queryall(){
        List profilelst = new ArrayList();
        return profilelst;
    }

}
