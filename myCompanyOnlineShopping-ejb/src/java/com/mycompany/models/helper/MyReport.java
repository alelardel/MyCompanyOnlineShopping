/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.models.helper;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Temporal;

/**
 *
 * @author Md Mojahidul Islam
 * @version 0.0.2
 */
@Entity
@SqlResultSetMappings({
    @SqlResultSetMapping(
            name = "MyReport.findBySalesQty",
            entities = {
                @EntityResult(
                        entityClass = MyReport.class,
                        fields = {
                            @FieldResult(name = "id", column = "ID"),
                            @FieldResult(name = "pid", column = "PID"),
                            @FieldResult(name = "quantity", column = "QTY"),
                            @FieldResult(name = "productName", column = "PRODUCT_NAME"),
                            @FieldResult(name = "unitPrice", column = "UNIT_PRICE"),
                            @FieldResult(name = "vendorName", column = "VENDOR_NAME"),
                            @FieldResult(name = "totalPrice", column = "TOTAL_PRICE")
                        }
                )
            }
    ),

    @SqlResultSetMapping(
            name = "MyReport.findByVendor",
            entities = {
                @EntityResult(
                        entityClass = MyReport.class,
                        fields = {
                            @FieldResult(name = "id", column = "ID"),
                            @FieldResult(name = "quantity", column = "QTY"),
                            @FieldResult(name = "vendorName", column = "VENDOR_NAME"),
                            @FieldResult(name = "totalPrice", column = "TOTAL_PRICE")
                        }
                )
            }
    )
})
public class MyReport implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int pid;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar buyingDate;

    private int quantity;

    private String productName;

    private double unitPrice;

    private String vendorName;

    private double totalPrice;

    public MyReport() {
    }

    public MyReport(int pid, Calendar buyingDate, int quantity, String productName, double unitPrice, String vendorName, double totalPrice) {
        this.pid = pid;
        this.buyingDate = buyingDate;
        this.quantity = quantity;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.vendorName = vendorName;
        this.totalPrice = totalPrice;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getBuyingDate() {
        return buyingDate;
    }

    public void setBuyingDate(Calendar buyingDate) {
        this.buyingDate = buyingDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

}
