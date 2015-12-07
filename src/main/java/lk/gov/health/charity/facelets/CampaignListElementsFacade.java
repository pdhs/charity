/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.charity.facelets;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lk.gov.health.charity.entity.CampaignListElements;

/**
 *
 * @author buddhika
 */
@Stateless
public class CampaignListElementsFacade extends AbstractFacade<CampaignListElements> {
    @PersistenceContext(unitName = "lk.gov.health_charity_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CampaignListElementsFacade() {
        super(CampaignListElements.class);
    }
    
}
