/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.services;

import com.mycompany.interfaces.PurchaseOrderServiceLocal;
import com.mycompany.models.BillingAddress;
import com.mycompany.models.CreditCard;
import com.mycompany.models.PurchaseOrder;
import com.mycompany.models.ShippingAddress;
import com.mycompany.models.ShoppingCart;
import java.util.Calendar;
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
    
    @Override
    public PurchaseOrder findById(int id){
        return em.find(PurchaseOrder.class, id);
    }
    
    @Override
        public PurchaseOrder saveOrder(ShoppingCart shoppingCart,BillingAddress billingAddress,ShippingAddress shippingAddress,CreditCard creditCard){
            PurchaseOrder order=new PurchaseOrder();
            
            order.setBuyingDate(Calendar.getInstance());
            order.setUser(shoppingCart.getUser());
            order.setBillingAddress(billingAddress);
            order.setShippingAddress(shippingAddress);
            order.setCardInformation(creditCard);
            order.setShoppingCart(shoppingCart);
            
            order=em.merge(order);
            
            return order;
        }
}
