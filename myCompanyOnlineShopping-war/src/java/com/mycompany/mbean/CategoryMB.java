
package com.mycompany.mbean;

import com.mycompany.models.Category;
import com.mycompany.services.CategoryService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author TalakB
 */
@Named
@SessionScoped
public class CategoryMB implements Serializable{
    @EJB
    private CategoryService categoryService;
    private Category category = new Category();

    public CategoryMB() {
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
    public List<Category> getAllCategories() throws Exception {
        return categoryService.findAllCategories();
        
    }
    
    public Category findCategoryByName(String catName){
        return categoryService.findCategoryByName(catName);
    
    }
    
}
