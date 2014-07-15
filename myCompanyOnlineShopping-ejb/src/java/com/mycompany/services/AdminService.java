package com.mycompany.services;

import com.mycompany.models.Category;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author TalakB
 */
@Stateless
@LocalBean
public class AdminService {

    @PersistenceContext
    private EntityManager em;
    
      public boolean persistCustomer(Category category) {

        boolean saved = false;
        try {
            em.persist(category);
            saved = true;
        } catch (Exception e) {

        }

        return saved;
    }
}
