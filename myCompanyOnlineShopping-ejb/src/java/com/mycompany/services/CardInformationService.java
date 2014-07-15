/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.interfaces.CardInformationServiceLocal;
import com.mycompany.models.CreditCard;
import javax.ejb.Stateless;
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

    @Override
    public CreditCard save(CreditCard cardInfo) {

            cardInfo=em.merge(cardInfo);
           
        return cardInfo;
    }
}
