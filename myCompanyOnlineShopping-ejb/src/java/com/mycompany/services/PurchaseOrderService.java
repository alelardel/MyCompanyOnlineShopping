/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.services;

import com.mycompany.interfaces.PurchaseOrderServiceLocal;
import com.mycompany.models.PurchaseOrder;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Md Mojahidul Islam
 */
@Stateless
public class PurchaseOrderService implements PurchaseOrderServiceLocal{

    @PersistenceContext
    private EntityManager em;
    
    public PurchaseOrder findById(int id){
        return em.find(PurchaseOrder.class, id);
    }
}
