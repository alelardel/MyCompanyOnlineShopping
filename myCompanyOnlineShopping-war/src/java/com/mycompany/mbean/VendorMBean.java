
package com.mycompany.mbean;

import com.mycompany.models.Vendor;
import com.mycompany.models.VendorUser;
import com.mycompany.services.VendorService;
import com.mycompany.util.PasswordService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author TalakB
 */
@Named
@SessionScoped
public class VendorMBean implements Serializable {

    @EJB
    private VendorService vendorService;
    private Vendor vendor = new Vendor();
    private VendorUser vuser = new VendorUser();
    private PasswordService passwordservice = new PasswordService();

    public VendorMBean() {
    }

    public VendorService getVendorService() {
        return vendorService;
    }

    public void setVendorService(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public VendorUser getVuser() {
        return vuser;
    }

    public void setVuser(VendorUser vuser) {
        this.vuser = vuser;
    }

    /**
     * Send request that will be approved by the admin.
     *
     * @return
     * @throws Exception
     */
    public String sendVendorRequest() throws Exception {

        vuser.setVendor(vendor);

        //encrypt vendor user passworcd 
        vuser.setPassword(passwordservice.encrypt(vuser.getPassword()));
        if (vendorService.sendVendorReq(vendor, vuser)) {
            return "registration_confirmation";
        }
        return null;
    }

    /**
     * List all the vendors
     *
     * @return
     */
    public List<Vendor> getAllVendors() {
        return vendorService.getAllVendors();
    }
    
    /**
     * 
     * @return 
     */
     public List<Vendor> getNonApprovedVendors() {
        return vendorService.getNonApprovedVendors();
    }

    /**
     * Approve vendors
     *
     * @param vendor
     * @return
     */
    public boolean approveVendor(Vendor vendor) {
        boolean approved = false;
        vendor.setApproved(true);
        if (vendorService.updateVendor(vendor)){
            approved = true;
        }

        return approved;

    }

}
