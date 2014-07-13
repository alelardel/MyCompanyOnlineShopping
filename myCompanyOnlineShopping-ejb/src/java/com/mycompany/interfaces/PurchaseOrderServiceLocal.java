/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.interfaces;

import com.mycompany.models.PurchaseOrder;
import javax.ejb.Local;

/**
 *
 * @author Md Mojahidul Islam
 */
@Local
public interface PurchaseOrderServiceLocal {
    public PurchaseOrder findById(int id);
}
