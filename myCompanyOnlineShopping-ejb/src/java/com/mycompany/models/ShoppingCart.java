/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.models;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author james
 */
@Entity
public class ShoppingCart implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar shopDate;
    
    private double totalPrice;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ShoppingCartItem> ShoppingCartItems;

    public Calendar getShopDate() {
        return shopDate;
    }

    public void setShopDate(Calendar shopDate) {
        this.shopDate = shopDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ShoppingCartItem> getShoppingCartItems() {
        return ShoppingCartItems;
    }

    public void addShoppingCartItem(ShoppingCartItem shoppingCartItem) {
        this.ShoppingCartItems.add(shoppingCartItem);
    }
}
