/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.charity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author buddhika
 */
@Entity
@Table(name = "campaign")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Campaign.findAll", query = "SELECT c FROM Campaign c"),
    @NamedQuery(name = "Campaign.findById", query = "SELECT c FROM Campaign c WHERE c.id = :id"),
    @NamedQuery(name = "Campaign.findByCampaign", query = "SELECT c FROM Campaign c WHERE c.campaign = :campaign")})
public class Campaign implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "campaign")
    private String campaign;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "campaignId")
    private Collection<CampaignList> campaignListCollection;

    public Campaign() {
    }

    public Campaign(Integer id) {
        this.id = id;
    }

    public Campaign(Integer id, String campaign) {
        this.id = id;
        this.campaign = campaign;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCampaign() {
        return campaign;
    }

    public void setCampaign(String campaign) {
        this.campaign = campaign;
    }

    @XmlTransient
    public Collection<CampaignList> getCampaignListCollection() {
        return campaignListCollection;
    }

    public void setCampaignListCollection(Collection<CampaignList> campaignListCollection) {
        this.campaignListCollection = campaignListCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Campaign)) {
            return false;
        }
        Campaign other = (Campaign) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lk.gov.health.charity.Campaign[ id=" + id + " ]";
    }
    
}
