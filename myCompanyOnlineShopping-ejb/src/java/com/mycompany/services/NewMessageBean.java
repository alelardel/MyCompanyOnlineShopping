/*
 * Copyright (c)2014
 */

package com.mycompany.services;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author Va Y.
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "queue1"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class NewMessageBean implements MessageListener {
    
    public NewMessageBean() {
    }
    
    @Override
    public void onMessage(Message message) {
    }
    
}
