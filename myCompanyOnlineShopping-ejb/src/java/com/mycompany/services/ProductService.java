/*
 * Copyright (c)2014
 */

package com.mycompany.services;

import com.mycompany.interfaces.ProductServiceLocal;
import com.mycompany.models.Category;
import com.mycompany.models.Product;
import com.mycompany.models.Vendor;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Va Y.
 */
@Stateless
public class ProductService {

    @PersistenceContext
    private EntityManager em;
    
    public ProductService(){
        
    }
    
    public void initdata(){
        
        Product p = new Product("Watch", 123);
        p.setCategory(new Category("Wear"));
        p.setVendor(new Vendor("Omega"));
        em.persist(p);
        
    }

    public Product get(int id) {
        
        return em.find(Product.class, id);
    }
    
    public boolean saveProduct(Product product) {
        boolean saved = false;
        try {
            em.persist(product);
            saved = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saved;
    }
    
    public List<Product> findAll() {
        TypedQuery<Product> q = em.createNamedQuery("Product.list", Product.class);
        return q.getResultList();
    }

    public void delete(int id) {
        Product p = em.find(Product.class, id);
        em.remove(p);
    }
    
    public Product find(int id) {
        return em.find(Product.class, id);
    }
    
    
}
