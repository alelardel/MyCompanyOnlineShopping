/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services.report;

import com.mycompany.interfaces.ReportServiceLocal;
import com.mycompany.models.helper.MyReport;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Md Mojahidul Islam
 */
@Stateless
public class ReportService implements ReportServiceLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<MyReport> findProductSalesByQty(String _qry) {

        Query query = em.createNativeQuery(_qry, "MyReport.findBySalesQty");
        List<MyReport> list = query.getResultList();

        return list;
    }

    @Override
    public List<MyReport> findProductSalesByVendor(String _qry) {

        Query query = em.createNativeQuery(_qry, "MyReport.findByVendor");
        List<MyReport> list = query.getResultList();
        return list;
    }
}
