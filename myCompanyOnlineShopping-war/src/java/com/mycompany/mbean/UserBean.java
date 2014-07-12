package com.mycompany.mbean;

import com.mycompany.services.CreditCardService;
import com.mycompany.services.UserService;
import com.mycompany.models.CreditCard;
import com.mycompany.models.Users;
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
    UserService userservice;
    Users user = new Users();

    @EJB
    CreditCardService creaditCardService;
    CreditCard userCreditCard = new CreditCard();

    @Inject
    CreditCardBean creditcardMBean;

    private PasswordService encpass = new PasswordService();

    public UserBean() {
        user = new Users();
        userCreditCard = new CreditCard();
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
        if (userservice.authenticateUser(user)) {
            return "user_home";
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

}
