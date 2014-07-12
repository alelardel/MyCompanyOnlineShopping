package com.mycompany.mbean;

import com.mycompany.services.CreditCardService;
import com.mycompany.models.CreditCard;
import com.mycompany.util.PasswordService;
import java.io.Serializable;
import java.util.Random;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author TalakB
 */
@Named
@SessionScoped
public class CreditCardBean implements Serializable {

    @EJB
    CreditCardService creditCardService;
    CreditCard userCreditCard = new CreditCard();
    private PasswordService encpass = new PasswordService();

    public CreditCardBean() {
    }

    public CreditCard getUserCreditCard() {
        return userCreditCard;
    }

    public void setUserCreditCard(CreditCard userCreditCard) {
        this.userCreditCard = userCreditCard;
    }

    public void saveCreditCardDetail(CreditCard creaditcard) throws Exception {
        creaditcard.setCardNumber(generateCreditCardNo());
        creaditcard.setSecurityCode(generateSecurityCode());
        String encCreditCardSec =  encryptSecurityCode(creaditcard.getSecurityCode());
        //String encSecCode = encryptSecurityCode();
 
        //encrypt the security code 
        creaditcard.setSecurityCode(encCreditCardSec);
        creaditcard.setExpiryDate("01-01-2020");
        creditCardService.saveCeditCard(creaditcard);
    }

    /**
     * Generate 16 digit credit card no.
     *
     * @return
     */
    public String generateCreditCardNo() {
        Random rand = new Random();
        long accumulator = 1 + rand.nextInt(9);
        for (int i = 0; i < 15; i++) {
            accumulator *= 10L;
            accumulator += rand.nextInt(10);
        }

        return String.valueOf(accumulator);
    }

    /**
     * generate 3 digit security code
     *
     * @return
     */
    public String generateSecurityCode() {
        Random rand = new Random();
        int randomSecCode = rand.nextInt(999) + 100;
        return String.valueOf(randomSecCode);
    }
    
       public String encryptSecurityCode(String password) throws Exception {
        String encrptedSecCode = encpass.encrypt(password);
        return encrptedSecCode;
    }
}
