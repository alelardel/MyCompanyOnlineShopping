package com.mycompany.services;

import com.mycompany.models.Role;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author TalakB
 */
@Stateless
@LocalBean
public class RoleService {

    @PersistenceContext
    EntityManager em;

    /**
     * find role by ID
     *
     * @param id
     * @return
     */
    public Role findById(int id) {
        return em.find(Role.class, id);
    }

    /**
     * Get which user role is the user assigned.
     *
     * @param ucode
     * @param user
     * @return
     */
    public Role getUserRoleBYUserCode(int ucode) {
        Query query = em.createNamedQuery("findRoleByUserCode");
        query.setParameter("rcode", ucode);
        if (!query.getResultList().isEmpty()) {
            Role userRole = (Role) query.getSingleResult();
            return userRole;
        }
        return null;

    }
}
