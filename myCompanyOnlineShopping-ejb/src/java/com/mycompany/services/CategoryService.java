package com.mycompany.services;

import com.mycompany.models.Category;
import com.mycompany.models.Vendor;
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
public class CategoryService {

    @PersistenceContext
    private EntityManager em;

    public List<Category> findAllCategories() {
        TypedQuery<Category> query = em.createNamedQuery("listAllCategories", Category.class);
        return query.getResultList();
    }
    
    public Category findCategoryByName(String name){
        TypedQuery<Category> query = em.createNamedQuery("findCategorByName", Category.class);
        query.setParameter("cname", name);
        return query.getSingleResult();
    
    }
}
