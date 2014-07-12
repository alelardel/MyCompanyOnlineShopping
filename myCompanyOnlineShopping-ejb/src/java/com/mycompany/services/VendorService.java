package com.mycompany.services;

import com.mycompany.models.Vendor;
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
public class VendorService {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    private EntityManager em;

    public boolean saveVendor(Vendor vendor) {

        boolean saved = false;
        try {
            em.persist(vendor);
            saved = true;
        } catch (Exception e) {

        }

        return saved;
    }
}
