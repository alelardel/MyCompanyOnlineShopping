package com.mycompany.mbean;

import com.mycompany.services.CreditCardService;
import com.mycompany.services.UserService;
import com.mycompany.models.CreditCard;
import com.mycompany.models.Role;
import com.mycompany.models.Users;
import com.mycompany.models.Vendor;
import com.mycompany.models.VendorUser;
import com.mycompany.services.VendorService;
import com.mycompany.util.PasswordService;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TalakB User bean
 */
@Named
@SessionScoped
public class UserBean implements Serializable {

    @EJB
    private UserService userservice;
    private Users user = new Users();
    private Role userrole = new Role();

    @EJB
    private CreditCardService creaditCardService;
    private CreditCard userCreditCard = new CreditCard();

    @EJB
    private VendorService vendorService;
    private VendorUser vendoruser = new VendorUser();

    @Inject
    CreditCardBean creditcardMBean;

    private PasswordService encpass = new PasswordService();
    private Vendor vendor = new Vendor();

    public UserBean() {
        user = new Users();
        userCreditCard = new CreditCard();
        userrole = new Role();
        vendor = new Vendor();

    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    /**
     * add customer and
     *
     * @return
     * @throws Exception
     */
    public String addCustomer() throws Exception {
        String encPass = encryptUserPassword(user.getPassword());
        user.setPassword(encPass);

        //TODO: user role 5 is mapped to customer 
        userrole.setId(5);
        user.setRole(userrole);

        // generate credit card detail
        userCreditCard.setCardholderName(user.getFirstName() + " " + user.getLastName());
        creditcardMBean.saveCreditCardDetail(userCreditCard);

        if (userservice.saveUser(user)) {
            return "registration_confirmation";
        }
        return null;
    }

    public String loginUser() throws Exception {
        String encPass = encryptUserPassword(user.getPassword());
        user.setPassword(encPass);

        if (userservice.authenticateUser(user) != null) {
            //get the authenticated user 
            user = userservice.authenticateUser(user);
            userrole = userservice.getUserRole(user);
            //admin
            if (userrole.getId() == 1) {
                return "admin_home";
            } //vedor user 
            else if (userrole.getId() == 3) {
                //check if the vendor company is approved
                vendoruser = (VendorUser) user;
                vendor = vendorService.findVendor(vendoruser.getVendor().getId());
                if (vendorService.isVendorApproved(vendor)) {
                    return "vendoruser_home";
                }

            } //cusotmer 
            else if (userrole.getId() == 5) {
                return "user_home";
            } else {
                return "user_home";
            }
        }
        return "";
    }

    public String registerUser() {
        return "register_user";
    }

    public String encryptUserPassword(String password) throws Exception {
        String encrptedPass = encpass.encrypt(user.getPassword());
        return encrptedPass;
    }

    /**
     * Logout user -Invalidate the session and redirect to home page
     *
     * @return
     */
    public String logoutUser() {
        HttpSession activeSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        activeSession.invalidate();
        return "index";
    }

    public Users getLoggedInUser() {
         HttpSession activeSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
         activeSession.setAttribute("loggedUser", user);
         return (Users) activeSession.getAttribute("loggedUser");
         
    }
}
