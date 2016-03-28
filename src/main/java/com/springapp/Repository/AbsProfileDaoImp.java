package com.springapp.Repository;

import com.springapp.Dao.Dao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.util.ReflectionUtils;

import javax.inject.Inject;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivanybma on 3/27/16.
 */
public abstract class AbsProfileDaoImp <T extends Object> implements Dao<T> {
    @Inject
    private SessionFactory mySessionFactory;

    private Class<T> domainClass;

    protected Session getSession(){
        return mySessionFactory.getCurrentSession();
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

    public List<T> queryall(){
        List profilelst = new ArrayList();
//        List prts = mySessionFactory.getCurrentSession().getNamedQuery("findProfileByLastName").list();
//        for (Iterator iterator1 =
//             prts.iterator(); iterator1.hasNext();)
//        {
//            T prt = (T) iterator1.next();
//            profilelst.add(prt);
//        }
//
        return profilelst;
    }

    public List<T> query(T obj){
        List prl = new ArrayList();
//        List profilelst = mySessionFactory.getCurrentSession().getNamedQuery("findProfileByLastName").list();
//        for (Iterator iterator1 =
//             profilelst.iterator(); iterator1.hasNext();)
//        {
//            T prt = (T) iterator1.next();
//            prl.add(prt);
//        }
        return prl;
    }

    public void update(T obj){
        System.out.println(obj);
        getSession().update(obj);
    }

    public void delete(T obj){
        System.out.println(obj);
        getSession().delete(obj);
    }

    public void deleteById(Serializable id){
        delete(load(id));
    }

    public void deleteAll(){
        getSession().createQuery("delete " + getDomainClassName()).executeUpdate();
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

    public T get(Serializable id){
        Session session=getSession();
        return (T) session.get(getDomainClass(), id);
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

}
