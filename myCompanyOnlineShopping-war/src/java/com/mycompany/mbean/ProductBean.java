package com.mycompany.mbean;

import com.mycompany.models.Category;
import com.mycompany.models.Product;
import com.mycompany.models.Vendor;
import com.mycompany.models.VendorUser;
import com.mycompany.services.ProductService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author james
 */
@Named
@SessionScoped
public class ProductBean implements Serializable {

    @EJB
    private ProductService productService;
    private Product product = null;
    private List<Product> products = null;

    private Vendor vendor = new Vendor();
    private VendorUser user = new VendorUser();
    private Category category = new Category();
    private String categoryName;

    @Inject
    UserBean usersmbean;

    @Inject
    CategoryMB categorymb;

    public ProductBean() {
        product = new Product();
        products = new ArrayList<>();
        vendor = new Vendor();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @PostConstruct
    private void init(){
        products = productService.getAll();
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
        VendorUser user = (VendorUser) usersmbean.getLoggedInUser();
        vendor = user.getVendor();
        category = categorymb.findCategoryByName(categoryName);
        product.setCategory(category);
        product.setVendor(vendor);
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

    public void valueChanged(ValueChangeEvent v) {
        categoryName = (String) v.getNewValue();
    }

    public String viewProductDetail(Product productIn) {
        product = productIn;
        if (product != null) {
            return "product_detail";
        } else {
            return null;
        }
    }

    /**
     * add product to shipping cart
     *
     * @param product
     * @return
     */
    public String addToCart(Product product) {
        //set all shipping cart related info here
       
        return "shoppingCartInfo";
    }

}
