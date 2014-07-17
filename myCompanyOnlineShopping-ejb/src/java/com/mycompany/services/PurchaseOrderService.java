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
import com.mycompany.models.Users;
import com.mycompany.services.myfiance.MyFinanceService;
import com.mycompany.services.myfiance.OrderTransaction;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 * This service class is responsible for persisting order info and finding order
 * by id.
 *
 * @author Md Mojahidul Islam
 * @version 1.0.0
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class PurchaseOrderService implements PurchaseOrderServiceLocal {

    @PersistenceContext
    private EntityManager em;

    @EJB
    MyFinanceService client;

    @EJB
    MandrillService mandrillService;

    @Resource
    UserTransaction utx;
    /**
     * This method gives order by id
     * @param id
     * @return 
     */

    @Override
    public PurchaseOrder findById(int id) {
        return em.find(PurchaseOrder.class, id);
    }

    private PurchaseOrder savePurchaseOrder(PurchaseOrder order) {

        try {
            utx.begin();
            PurchaseOrder porder = em.merge(order);
            try {
                utx.commit();
            } catch (RollbackException ex) {
                Logger.getLogger(PurchaseOrderService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (HeuristicMixedException ex) {
                Logger.getLogger(PurchaseOrderService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (HeuristicRollbackException ex) {
                Logger.getLogger(PurchaseOrderService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(PurchaseOrderService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalStateException ex) {
                Logger.getLogger(PurchaseOrderService.class.getName()).log(Level.SEVERE, null, ex);
            }
            return porder;
        } catch (NotSupportedException ex) {
            Logger.getLogger(PurchaseOrderService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(PurchaseOrderService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    /**
     * This method saves order info into database 
     * @param shoppingCart
     * @param billingAddress
     * @param shippingAddress
     * @param creditCard
     * @return order
     */

    @Override
    public PurchaseOrder saveOrder(ShoppingCart shoppingCart, BillingAddress billingAddress, ShippingAddress shippingAddress, CreditCard creditCard) {
        PurchaseOrder order = new PurchaseOrder();

        order.setBuyingDate(Calendar.getInstance());
        order.setUser(shoppingCart.getUser());
        order.setBillingAddress(billingAddress);
        order.setShippingAddress(shippingAddress);
        order.setCardInformation(creditCard);
        order.setShoppingCart(shoppingCart);

        // order = em.merge(order);
        order = savePurchaseOrder(order);

        if (shoppingCart.getUser() != null) {
            try {

                MandrillTemplatedMessageRequest mandrillMessage = mandrillService.getMandrillOrderMessageObject(null, order, "Thank you for shopping with us.");
                if (mandrillMessage != null) {
                    mandrillService.sendTemplatedMessage(mandrillMessage);
                }
            } catch (RequestFailedException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //em.flush();
        //Update MyFinance.com
        OrderTransaction orderTransaction = new OrderTransaction();
        orderTransaction.setOrderID(order.getId());
        orderTransaction.setTransactionDate(new Date());
        client.create_JSON(orderTransaction);

        return order;
    }
}
