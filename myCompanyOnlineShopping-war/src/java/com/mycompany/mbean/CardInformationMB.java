/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mbean;

import com.mycompany.models.CreditCard;
import com.mycompany.services.CardInformationService;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Md Mojahidul Islam
 */
@Named(value = "cardInformationMB")
@SessionScoped
public class CardInformationMB implements Serializable {

    @EJB
    private CardInformationService cardInfoService;

    private CreditCard cardInfo;

    public CardInformationMB() {
        cardInfo = new CreditCard();
    }

    public String save() {
        if (cardInfoService.save(cardInfo)) {
            return "success?faces-redirect=true";
        }
        return "fail?faces-redirect=true";
    }

    public CreditCard getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(CreditCard cardInfo) {
        this.cardInfo = cardInfo;
    }

}
