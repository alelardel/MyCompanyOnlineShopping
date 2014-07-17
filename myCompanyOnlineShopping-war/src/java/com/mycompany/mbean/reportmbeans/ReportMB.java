/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mbean.reportmbeans;

import com.mycompany.interfaces.ReportServiceLocal;
import com.mycompany.models.helper.MyReport;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author shahin
 */
@Named(value = "reportMB")
@RequestScoped
public class ReportMB {

    @EJB
    private ReportServiceLocal reportService;

    private Date fromDate;

    private Date toDate;

    private int noOfResult;

    JRBeanCollectionDataSource beanCollectionDataSource;
    JasperPrint jasperPrint;

    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    DateFormat df2 = new SimpleDateFormat("MM/dd/yyyy");

    /**
     * Creates a new instance of ReportMB
     */
    public ReportMB() {
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public int getNoOfResult() {
        return noOfResult;
    }

    public void setNoOfResult(int noOfResult) {
        this.noOfResult = noOfResult;
    }

    public void generateTop() throws JRException, IOException {

        String fDate = df.format(fromDate);
        String tDate = df.format(toDate);

        if (noOfResult == 0) {
            noOfResult = 5;
        }

        //String _qry="SELECT sci.PRODUCT_ID ID,sc.shopdate BUYINGDATE,sum(sci.QUANTITY) qty,p.name CUST_NAME FROM mycomtest.shoppingcartitem sci,mycomtest.shoppingcart sc, mycomtest.product p where sci.SHOPPINGCART_ID=sc.ID and p.id=sci.product_id and sc.SHOPDATE between '2014-01-01' and '2014-07-20' group by sci.PRODUCT_ID,sc.SHOPDATE order by qty desc";
        // String _qry="SELECT @rownum:=@rownum+1 ID,sci.PRODUCT_ID PID,sc.shopdate BUYINGDATE,sum(sci.QUANTITY) QTY,p.name PRODUCT_NAME FROM shoppingcartitem sci,shoppingcart sc, product p,(SELECT @rownum:=0) r where sci.SHOPPINGCART_ID=sc.ID and p.id=sci.product_id and sc.SHOPDATE between '2014-01-01' and '2014-07-20' group by sci.PRODUCT_ID,sc.SHOPDATE order by qty desc";
        String _qry = "SELECT @rownum:=@rownum+1 ID,sci.PRODUCT_ID PID,p.name PRODUCT_NAME,p.PRICE UNIT_PRICE,sum(sci.QUANTITY) QTY,(select v.VENDORNAME from VENDOR v where v.ID=p.VENDOR_ID) VENDOR_NAME, (p.PRICE*sum(sci.QUANTITY)) TOTAL_PRICE FROM SHOPPINGCARTITEM sci,SHOPPINGCART sc, PRODUCT p,(SELECT @rownum:=0) r where sci.SHOPPINGCART_ID=sc.ID and p.id=sci.product_id and sc.SHOPDATE between '" + fDate + "' and '" + tDate + "' group by sci.PRODUCT_ID order by qty desc LIMIT " + noOfResult;
        List<MyReport> list = reportService.findProductSalesByQty(_qry);

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("fromDate", df2.format(fromDate));
        param.put("toDate", df2.format(toDate));

        //List<ShoppingCartItem> items = order.getShoppingCart().getShoppingCartItems();
        beanCollectionDataSource = new JRBeanCollectionDataSource(list);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/jsfpages/reports/rptTopSellingProduct2.jasper");
        jasperPrint = JasperFillManager.fillReport(reportPath, param, beanCollectionDataSource);
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=topSellingProduct.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void generateLeast() throws JRException, IOException {

        String fDate = df.format(fromDate);
        String tDate = df.format(toDate);
        if (noOfResult == 0) {
            noOfResult = 5;
        }
        //String _qry="SELECT sci.PRODUCT_ID ID,sc.shopdate BUYINGDATE,sum(sci.QUANTITY) qty,p.name CUST_NAME FROM mycomtest.shoppingcartitem sci,mycomtest.shoppingcart sc, mycomtest.product p where sci.SHOPPINGCART_ID=sc.ID and p.id=sci.product_id and sc.SHOPDATE between '2014-01-01' and '2014-07-20' group by sci.PRODUCT_ID,sc.SHOPDATE order by qty desc";
        // String _qry="SELECT @rownum:=@rownum+1 ID,sci.PRODUCT_ID PID,sc.shopdate BUYINGDATE,sum(sci.QUANTITY) QTY,p.name PRODUCT_NAME FROM shoppingcartitem sci,shoppingcart sc, product p,(SELECT @rownum:=0) r where sci.SHOPPINGCART_ID=sc.ID and p.id=sci.product_id and sc.SHOPDATE between '2014-01-01' and '2014-07-20' group by sci.PRODUCT_ID,sc.SHOPDATE order by qty desc";
        String _qry = "SELECT @rownum:=@rownum+1 ID,sci.PRODUCT_ID PID,p.name PRODUCT_NAME,p.PRICE UNIT_PRICE,sum(sci.QUANTITY) QTY,(select v.VENDORNAME from VENDOR v where v.ID=p.VENDOR_ID) VENDOR_NAME, (p.PRICE*sum(sci.QUANTITY)) TOTAL_PRICE FROM SHOPPINGCARTITEM sci,SHOPPINGCART sc, PRODUCT p,(SELECT @rownum:=0) r where sci.SHOPPINGCART_ID=sc.ID and p.id=sci.product_id and sc.SHOPDATE between '" + fDate + "' and '" + tDate + "' group by sci.PRODUCT_ID order by qty asc LIMIT " + noOfResult;
        List<MyReport> list = reportService.findProductSalesByQty(_qry);

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("fromDate", df2.format(fromDate));
        param.put("toDate", df2.format(toDate));

        //List<ShoppingCartItem> items = order.getShoppingCart().getShoppingCartItems();
        beanCollectionDataSource = new JRBeanCollectionDataSource(list);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/jsfpages/reports/rptLeastSellingProduct.jasper");
        jasperPrint = JasperFillManager.fillReport(reportPath, param, beanCollectionDataSource);
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=LeastSellingProduct.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void generateBestVendor() throws JRException, IOException {

        String fDate = df.format(fromDate);
        String tDate = df.format(toDate);
        if (noOfResult == 0) {
            noOfResult = 5;
        }
        String _qry = "SELECT @rownum:=@rownum+1 ID,(select v.VENDORNAME from VENDOR v where v.ID=p.VENDOR_ID) VENDOR_NAME,sum(sci.QUANTITY) QTY,(p.PRICE*sum(sci.QUANTITY)) TOTAL_PRICE FROM SHOPPINGCARTITEM sci,SHOPPINGCART sc, PRODUCT p,(SELECT @rownum:=0) r where sci.SHOPPINGCART_ID=sc.ID and p.id=sci.product_id and sc.SHOPDATE between '" + fDate + "' and '" + tDate + "' group by p.VENDOR_ID order by TOTAL_PRICE desc LIMIT " + noOfResult;

        List<MyReport> list = reportService.findProductSalesByVendor(_qry);

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("fromDate", df2.format(fromDate));
        param.put("toDate", df2.format(toDate));

        //List<ShoppingCartItem> items = order.getShoppingCart().getShoppingCartItems();
        beanCollectionDataSource = new JRBeanCollectionDataSource(list);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/jsfpages/reports/rptTopVendor.jasper");
        jasperPrint = JasperFillManager.fillReport(reportPath, param, beanCollectionDataSource);
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=TopVendors.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void generateLeastVendor() throws JRException, IOException {

        String fDate = df.format(fromDate);
        String tDate = df.format(toDate);
        if (noOfResult == 0) {
            noOfResult = 5;
        }
        String _qry = "SELECT @rownum:=@rownum+1 ID,(select v.VENDORNAME from VENDOR v where v.ID=p.VENDOR_ID) VENDOR_NAME,sum(sci.QUANTITY) QTY,(p.PRICE*sum(sci.QUANTITY)) TOTAL_PRICE FROM SHOPPINGCARTITEM sci,SHOPPINGCART sc, PRODUCT p,(SELECT @rownum:=0) r where sci.SHOPPINGCART_ID=sc.ID and p.id=sci.product_id and sc.SHOPDATE between '" + fDate + "' and '" + tDate + "' group by p.VENDOR_ID order by TOTAL_PRICE asc LIMIT " + noOfResult;

        List<MyReport> list = reportService.findProductSalesByVendor(_qry);

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("fromDate", df2.format(fromDate));
        param.put("toDate", df2.format(toDate));

        //List<ShoppingCartItem> items = order.getShoppingCart().getShoppingCartItems();
        beanCollectionDataSource = new JRBeanCollectionDataSource(list);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/jsfpages/reports/rptLeastVendor.jasper");
        jasperPrint = JasperFillManager.fillReport(reportPath, param, beanCollectionDataSource);
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=LeastVendors.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        FacesContext.getCurrentInstance().responseComplete();
    }
}
