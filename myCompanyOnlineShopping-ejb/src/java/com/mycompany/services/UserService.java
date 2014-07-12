package com.mycompany.services;

import com.mycompany.models.Users;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author TalakB
 */
@Stateless
public class UserService {

    @PersistenceContext
    private EntityManager em;

    public boolean saveUser(Users user) {
     
        boolean saved = false;
        try {
            em.persist(user);
            saved = true;
        } catch (Exception e) {

        }

        return saved;
    }

    public boolean authenticateUser(Users user) {
        boolean userAuthenticated = false;
        String userName = user.getEmail();
        String passw = user.getPassword();

        Query query = em.createNamedQuery("checkUser");
        query.setParameter("uname", userName);
        query.setParameter("upass", passw);

        if(!query.getResultList().isEmpty())
            userAuthenticated = true;
        return userAuthenticated;
    }

}
