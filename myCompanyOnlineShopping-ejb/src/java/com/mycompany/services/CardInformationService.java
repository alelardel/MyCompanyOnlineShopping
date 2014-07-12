/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.models.CreditCard;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Md Mojahidul Islam
 */
@Stateless
public class CardInformationService {

    @PersistenceContext
    private EntityManager em;

    public boolean save(CreditCard cardInfo) {
        boolean saved = false;
        try {
            em.persist(cardInfo);
            saved = true;
        } catch (Exception e) {

        }
        return saved;
    }
}
