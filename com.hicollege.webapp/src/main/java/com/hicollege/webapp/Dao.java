package com.hicollege.webapp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hicollege.webapp.dtos.Album;
import com.hicollege.webapp.dtos.User;

@Repository
@Component
public class Dao {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    public Dao() {
    }
 
    public Dao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Transactional
    public void saveBatch(Object... objs) {
        for(Object obj : objs) {
            sessionFactory.getCurrentSession().save(obj);
        }
    }
    
    @Transactional
    public void save(Object obj) {
        saveBatch(obj);
    }
    
    @Transactional
    public Album getAlbumByTitle(String title) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Album as album where album.title = :title");
        query.setParameter("title", title);
        List result = query.list();
        return result.size() == 0 ? null : (Album) result.get(0);
    }
    
    @Transactional
    public User getUserByName(String username) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User as usr where usr.name = :username");
        query.setParameter("username", username);
        List result = query.list();
        return result.size() == 0 ? null : (User) result.get(0);
    }
    
    
    @Transactional
    public List<User> getAllUsers() {
        @SuppressWarnings("unchecked")
        List<User> allAlbums = (List<User>) sessionFactory
                .getCurrentSession()
                .createQuery("from User")
                .list();
        return allAlbums;
    }
    
    @Transactional
    public List<Album> getAllAlbums() {
        @SuppressWarnings("unchecked")
        List<Album> allAlbums = (List<Album>) sessionFactory
                .getCurrentSession()
                .createQuery("from Album")
                .list();
        return allAlbums;
    }
}
