/*
 * Copyright (c)2014
 */

package com.mycompany.mbean;

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
    
    private List<Product> productList;
    private Product product;

    /**
     * Creates a new instance of ProductMB
     */
    public ProductMB() {
    }
    
    @PostConstruct
    private void init(){
        productList = productService.getAll();
    }
    
    public String listProduct() {
        productService.initdata();
        productList = productService.getAll();
        
        
        return null;
    }
    
    public String getProduct(int id) {

        product = productService.get(id);
        
        
        return "product";
    }

    public List<Product> getProductList() {
        return productList;
    }

    public Product getProduct() {
        return product;
    }

    
    
}
