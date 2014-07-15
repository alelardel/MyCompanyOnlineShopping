package com.mycompany.services;

import com.mycompany.models.Role;
import com.mycompany.models.Users;
import com.mycompany.models.VendorUser;
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

    public Users authenticateUser(Users user) {

        String userName = user.getEmail();
        String passw = user.getPassword();

        Query query = em.createNamedQuery("checkUser");
        query.setParameter("uname", userName);
        query.setParameter("upass", passw);

        if (!query.getResultList().isEmpty()) {
            Users userAuthenticated = (Users) query.getSingleResult();
            return userAuthenticated;
        }
        return null;
    }

    /**
     * Fins user by ID
     *
     * @param id
     * @return
     */
    public Users findById(int id) {
        return em.find(Users.class, id);
    }

    /**
     * Get which user role is the user assigned.
     *
     * @param user
     * @return
     */
    public Role getUserRole(Users user) {
        Role userRole = em.find(Role.class, user.getRole().getId());
        if (userRole != null) {
            return userRole;
        } else {
            return null;
        }

    }	

    public Users findBYEmailId(String email) {
        Query query = em.createNamedQuery("findUserByEmailId");
        query.setParameter("uemail", email);
        if (!query.getResultList().isEmpty()) {
            Users registeredUser = (Users) query.getSingleResult();
            return registeredUser;
        }
        return null;
    }
    
    public VendorUser findVuserBYEmailId(String email) {
        Query query = em.createNamedQuery("findUserByEmailId");
        query.setParameter("uemail", email);
        if (!query.getResultList().isEmpty()) {
            VendorUser registeredUser = (VendorUser) query.getSingleResult();
            return registeredUser;
        }
        return null;
    }

}
