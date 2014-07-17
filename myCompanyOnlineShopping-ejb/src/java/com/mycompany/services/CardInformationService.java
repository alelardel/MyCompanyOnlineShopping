
package com.mycompany.services;

import com.mycompany.interfaces.CardInformationServiceLocal;
import com.mycompany.models.CreditCard;
import com.mycompany.services.paymentgateway.PaymentGatewayService;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This service class is responsible for persisting credit card info.
 * 
 * @author Md Mojahidul Islam
 * @version 1.0.0
 */
@Stateless
public class CardInformationService implements CardInformationServiceLocal {

    @PersistenceContext
    private EntityManager em;
    
    @EJB
    private PaymentGatewayService client;
/**
 * This method saves credit card information into database
 * 
 * @param cardInfo
 * @return 
 */
    //@Interceptors(PaymentGatewayListener.class)
    @Override
    public CreditCard save(CreditCard cardInfo) {

        //client.create_JSON(cardInfo);
        
        cardInfo=em.merge(cardInfo);
           
        return cardInfo;
    }
    

    @Override
    public CreditCard validateSave(CreditCard cardInfo, double amount) {
        //validate creditcard
        
        boolean isValid = client.validate(boolean.class, cardInfo.getCardNumber() , cardInfo.getCardholderName(),
                cardInfo.getExpiryDate().replace("/", ""), cardInfo.getSecurityCode(), String.valueOf(amount));
        
        if  (isValid) {
            
            //cardInfo = em.merge(cardInfo);
            em.persist(cardInfo);
           
            return cardInfo;
            
        } else return null;
    }
}
