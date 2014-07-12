/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.models.Address;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author shahin
 */
@Stateless
public class AddressService {

    @PersistenceContext
    private EntityManager em;

    public boolean save(Address address) {
        boolean saved = false;
        try {
            em.persist(address);
            saved = true;
        } catch (Exception e) {

        }
        return saved;
    }

}
