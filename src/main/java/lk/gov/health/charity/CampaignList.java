/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.charity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author buddhika
 */
@Entity
@Table(name = "campaign_list")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CampaignList.findAll", query = "SELECT c FROM CampaignList c"),
    @NamedQuery(name = "CampaignList.findById", query = "SELECT c FROM CampaignList c WHERE c.id = :id"),
    @NamedQuery(name = "CampaignList.findByCampaignListName", query = "SELECT c FROM CampaignList c WHERE c.campaignListName = :campaignListName"),
    @NamedQuery(name = "CampaignList.findByDate", query = "SELECT c FROM CampaignList c WHERE c.date = :date")})
public class CampaignList implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "campaign_list_name")
    private String campaignListName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "campaignListId")
    private Collection<CampaignListElements> campaignListElementsCollection;
    @JoinColumn(name = "campaign_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Campaign campaignId;

    public CampaignList() {
    }

    public CampaignList(Integer id) {
        this.id = id;
    }

    public CampaignList(Integer id, String campaignListName, Date date) {
        this.id = id;
        this.campaignListName = campaignListName;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCampaignListName() {
        return campaignListName;
    }

    public void setCampaignListName(String campaignListName) {
        this.campaignListName = campaignListName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @XmlTransient
    public Collection<CampaignListElements> getCampaignListElementsCollection() {
        return campaignListElementsCollection;
    }

    public void setCampaignListElementsCollection(Collection<CampaignListElements> campaignListElementsCollection) {
        this.campaignListElementsCollection = campaignListElementsCollection;
    }

    public Campaign getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Campaign campaignId) {
        this.campaignId = campaignId;
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
        if (!(object instanceof CampaignList)) {
            return false;
        }
        CampaignList other = (CampaignList) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lk.gov.health.charity.CampaignList[ id=" + id + " ]";
    }
    
}
