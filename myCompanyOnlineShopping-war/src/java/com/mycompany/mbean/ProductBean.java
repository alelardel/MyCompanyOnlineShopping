package com.mycompany.mbean;

import com.mycompany.models.Product;
import com.mycompany.services.ProductService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author james
 */
@Named
@SessionScoped
public class ProductBean implements Serializable {

    @EJB
    ProductService productService;
    Product product = null;
    List<Product> products = null;

    public ProductBean() {
        product = new Product();
        products = new ArrayList<>();
    }
    
    @PostConstruct
    private void init(){
        products = productService.findAll();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String addProduct() throws Exception {
        productService.saveProduct(product);
        return listProduct();
    }

    public String listProduct() throws Exception {
        products = productService.findAll();
        return "product_list";
    }

    public String addProductPage() {
        return "product_add";
    }
}
