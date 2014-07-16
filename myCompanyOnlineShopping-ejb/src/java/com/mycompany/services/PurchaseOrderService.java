/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mandrill.clients.exception.RequestFailedException;
import com.mandrill.clients.model.MandrillTemplatedMessageRequest;
import com.mycompany.interfaces.PurchaseOrderServiceLocal;
import com.mycompany.models.BillingAddress;
import com.mycompany.models.CreditCard;
import com.mycompany.models.PurchaseOrder;
import com.mycompany.models.ShippingAddress;
import com.mycompany.models.ShoppingCart;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Md Mojahidul Islam
 */
@Stateless
public class PurchaseOrderService implements PurchaseOrderServiceLocal {

    @PersistenceContext
    private EntityManager em;

    @EJB
    MandrillService mandrillService;

    @Override
    public PurchaseOrder findById(int id) {
        return em.find(PurchaseOrder.class, id);
    }

    @Override
    public PurchaseOrder saveOrder(ShoppingCart shoppingCart, BillingAddress billingAddress, ShippingAddress shippingAddress, CreditCard creditCard) {
        PurchaseOrder order = new PurchaseOrder();

        order.setBuyingDate(Calendar.getInstance());
        order.setUser(shoppingCart.getUser());
        order.setBillingAddress(billingAddress);
        order.setShippingAddress(shippingAddress);
        order.setCardInformation(creditCard);
        order.setShoppingCart(shoppingCart);

        order = em.merge(order);

        if (shoppingCart.getUser() != null) {
            try {

                MandrillTemplatedMessageRequest mandrillMessage = mandrillService.getMandrillMessageObject(null, shoppingCart.getUser(), "Thank you for shopping with us.");
                if(mandrillMessage!=null) {
                    mandrillService.sendTemplatedMessage(mandrillMessage);
                }
            } catch (RequestFailedException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return order;
    }
}
