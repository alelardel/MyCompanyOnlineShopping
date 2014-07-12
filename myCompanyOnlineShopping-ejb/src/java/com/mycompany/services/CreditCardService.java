
package com.mycompany.services;

import com.mycompany.models.CreditCard;
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
public class CreditCardService {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext
    private EntityManager em;

    public boolean saveCeditCard(CreditCard userCreditCard) {
        //encrypt users password before persist 
        boolean saved = false;
        try {
            em.persist(userCreditCard);
            saved = true;
        } catch (Exception e) {

        }

        return saved;
    }
}
