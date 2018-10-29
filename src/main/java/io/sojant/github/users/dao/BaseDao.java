package io.sojant.github.users.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sojant on 2018-10-27.
 */


@Transactional
public abstract class BaseDao< T extends Serializable>{
    private Class< T > clazz;

    @Autowired
    private SessionFactory sessionFactory;

    public void setClazz( final Class< T > clazzToSet ){
        clazz = clazzToSet;
    }

    public T findOne( final long id ){
        return (T) getCurrentSession().get( clazz, id );
    }
    public List< T > findAll(){
        return getCurrentSession()
                .createQuery( "from " + clazz.getName() ).list();
    }

    public void save( final T entity ){
        getCurrentSession().persist( entity );
    }

    public T update( final T entity ){
        return (T) getCurrentSession().merge( entity );
    }

    public void delete( final T entity ){
        getCurrentSession().delete( entity );
    }
    public void deleteById( final long id ){
        final T entity = findOne( id);
        delete( entity );
    }

    protected final Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    // TODO
    // Implement methods to process transaction operations
    // Session session = sessionFactory.openSession();
    // session.beginTransaction();
    // session.saveOrUpdate(u);
    // session.getTransaction().commit();
}