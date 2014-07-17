/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.interfaces;

import com.mycompany.models.helper.MyReport;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Md Mojahidul Islam
 */
@Local
public interface ReportServiceLocal {

    public List<MyReport> findProductSalesByQty(String _qry);
    
     public List<MyReport> findProductSalesByVendor(String _qry);
    
}
