/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.models;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author james
 */
@Entity
public class Admin extends Users implements Serializable {
    private static final long serialVersionUID = 1L;
}
