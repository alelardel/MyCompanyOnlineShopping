package com.mycompany.mbean;

import com.mycompany.models.Category;
import com.mycompany.services.AdminService;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author TalakB
 */
@Named
@SessionScoped
public class AdminMB implements Serializable {

    @EJB
    private AdminService adminservice;

    private Category category = new Category();

    public AdminMB() {
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Staff users can be created only by admin.
     *
     * @return
     */
    public String addStaffUser() {
        return "adminAddInternalUserRegistration";
    }

    /**
     * Add product category
     *
     * @return
     */
    public String showVendorRequest() {
        return "adminVendorRequestApproval";
    }

    public String addCategory() {
        return "adminAddcategory";
    }

    public String saveCategory() throws Exception {

        if (adminservice.persistCustomer(category)) {
            return "admin_home";
        }
        return null;
    }

}
