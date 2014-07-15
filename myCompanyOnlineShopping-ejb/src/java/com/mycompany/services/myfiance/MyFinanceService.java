/*
 * Copyright (c)2014
 */

package com.mycompany.services.myfiance;

import javax.ejb.Stateless;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:OrderTransactionFacadeREST
 * [entities.ordertransaction]<br>
 * USAGE:
 * <pre>
 *        @EJB
 *        MyFinanceService client;
 *        Object response = client.XXX(...);
 *        // do whatever with response
 * </pre>
 *
 * @author Va Y.
 */
@Stateless
public class MyFinanceService {
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/MyFinance.com/webresources";

    public MyFinanceService() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("entities.ordertransaction");
    }

    public void create_XML(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    public void create_JSON(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    

    public void close() {
        client.close();
    }
    
}
