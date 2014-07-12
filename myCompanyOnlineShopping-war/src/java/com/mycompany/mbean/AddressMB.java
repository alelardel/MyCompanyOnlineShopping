/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mbean;

import com.mycompany.models.Address;
import com.mycompany.services.AddressService;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Md Mojahidul Islam
 */
@Named(value = "addressMB")
@SessionScoped
public class AddressMB implements Serializable {

    @EJB
    private AddressService addressService;

    private Address address;

    public AddressMB() {
        address = new Address();
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String save() {        
        if (addressService.save(address)) {
            return "success?faces-redirect=true";
        }
        return "fail?faces-redirect=true";
    }
}
