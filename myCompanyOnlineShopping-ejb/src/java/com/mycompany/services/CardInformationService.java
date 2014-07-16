/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author Md Mojahidul Islam
 */
@Stateless
public class CardInformationService implements CardInformationServiceLocal {

    @PersistenceContext
    private EntityManager em;
    
//    @EJB
//    private PaymentGatewayService client;

    @Interceptors(PaymentGatewayListener.class)
    @Override
    public CreditCard save(CreditCard cardInfo) {

        //client.create_JSON(cardInfo);
        
        cardInfo=em.merge(cardInfo);
           
        return cardInfo;
    }
}
