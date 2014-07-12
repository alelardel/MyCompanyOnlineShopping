package com.mycompany.mbean;

import com.mycompany.services.ProductService;
import com.mycompany.models.Product;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
    ProductService productservice;
    Product product = null;
    List<Product> products = null;

    public ProductBean() {
        product = new Product();
        products = new ArrayList<Product>();
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
        productservice.saveProduct(product);
        return listProduct();
    }

    public String listProduct() throws Exception {
        products = productservice.findAll();
        return "product_list";
    }

    public String addProductPage() {
        return "product_add";
    }
}
