/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mbean;

import com.mycompany.models.PurchaseOrder;
import com.mycompany.services.PurchaseOrderService;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Md Mojahidul Islam
 */
@Named(value = "purchaseOrderMB")
@SessionScoped
public class PurchaseOrderMB implements Serializable {

    @EJB
    private PurchaseOrderService purchaseOrderService;

    private PurchaseOrder purchaseOrder;

    public PurchaseOrderMB() {
        purchaseOrder=new PurchaseOrder();
    }

    public void findOrderDetails() {
        purchaseOrder = purchaseOrderService.findById(1);
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }


}
