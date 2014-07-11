/*
 * Copyright (c)2014
 */

package com.mycompany.mb;

import com.mycompany.models.Product;
import com.mycompany.services.ProductService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Va Y.
 */
@Named(value = "productMB")
@RequestScoped
public class ProductMB {
    
    @EJB
    private ProductService productService;
    
    private List<Product> products;

    /**
     * Creates a new instance of ProductMB
     */
    public ProductMB() {
    }
    
    @PostConstruct
    private void init(){
        products = productService.getAll();
    }
    
    public String listProduct() {
        productService.initdata();
        products = productService.getAll();
        
        
        return null;
    }

    public List<Product> getProducts() {
        return products;
    }

    
    
}
