package com.mycompany.services;

import com.mycompany.models.Role;
import com.mycompany.models.Vendor;
import com.mycompany.models.VendorUser;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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

    public boolean sendVendorReq(Vendor vendor, VendorUser user) {

        boolean saved = false;
        try {
            em.persist(vendor);
            em.persist(user);
            saved = true;
        } catch (Exception e) {

        }

        return saved;
    }

    public List<Vendor> getAllVendors() {

        TypedQuery<Vendor> query = em.createNamedQuery("listAllVendors", Vendor.class);
        return query.getResultList();

    }

    public List<Vendor> getNonApprovedVendors() {

        TypedQuery<Vendor> query = em.createNamedQuery("listNonApprovedVendors", Vendor.class);
        return query.getResultList();

    }

    /**
     * Approve vendor
     *
     * @param vendor
     * @return
     */
    public boolean updateVendor(Vendor vendor) {
        boolean updated = false;
        try {
            em.merge(vendor);
            updated = true;
        } catch (Exception e) {

        }

        //if updated then update users role 
        if (updated) {
            if (!updateVendorUserRole(vendor)) {
                updated = false;
            }

        }

        return updated;

    }

    /**
     * Set the vendor user role 3 (vendor user)
     *
     * @param vendor
     * @return 
     */
    public boolean updateVendorUserRole(Vendor vendor) {
        boolean vendorUserRoleupdated = false;
        TypedQuery<VendorUser> query = em.createNamedQuery("listVendorUsers", VendorUser.class);
        query.setParameter("vid", vendor.getId());
        List<VendorUser> vusers = query.getResultList();
        for (VendorUser v : vusers) {
            Role role = new Role();
            role.setId(3);
            v.setRole(role);

        }
        return vendorUserRoleupdated;

    }

    public boolean isVendorApproved(Vendor vendor) {
        boolean approved = false;
        if (vendor.isApproved()) {
            approved = true;
        }
        return approved;
    }

    /**
     * find vendor by ID
     *
     * @param id
     * @return
     */
    public Vendor findVendor(int id) {
        Vendor vendor = em.find(Vendor.class, id);
        if (vendor != null) {
            return vendor;
        } else {
            return null;
        }
    }
}
