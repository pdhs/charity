/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.charity.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author buddhika
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "FamilyMember.findAll", query = "SELECT m FROM FamilyMember m"),
    @NamedQuery(name = "FamilyMember.findById", query = "SELECT m FROM FamilyMember m WHERE m.id = :id"),
    @NamedQuery(name = "FamilyMember.findByNic", query = "SELECT m FROM FamilyMember m WHERE m.nic = :nic"),
    @NamedQuery(name = "FamilyMember.findByNameSin", query = "SELECT m FROM FamilyMember m WHERE m.nameSin = :nameSin"),
    @NamedQuery(name = "FamilyMember.findByNameEng", query = "SELECT m FROM FamilyMember m WHERE m.nameEng = :nameEng"),
    @NamedQuery(name = "FamilyMember.findByAddressEng", query = "SELECT m FROM FamilyMember m WHERE m.addressEng = :addressEng"),
    @NamedQuery(name = "FamilyMember.findByAddressSin", query = "SELECT m FROM FamilyMember m WHERE m.addressSin = :addressSin"),
    @NamedQuery(name = "FamilyMember.findByDob", query = "SELECT m FROM FamilyMember m WHERE m.dob = :dob"),
    @NamedQuery(name = "FamilyMember.findByTel1", query = "SELECT m FROM FamilyMember m WHERE m.tel1 = :tel1"),
    @NamedQuery(name = "FamilyMember.findByTel2", query = "SELECT m FROM FamilyMember m WHERE m.tel2 = :tel2")})
public class FamilyMember implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nic;
    @Column(name = "name_sin")
    private String nameSin;
    @Column(name = "name_eng")
    private String nameEng;
    @Column(name = "address_eng")
    private String addressEng;
    @Column(name = "address_sin")
    private String addressSin;
    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Column(name = "tel1")
    private String tel1;
    @Column(name = "tel2")
    private String tel2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memberId")
    private Collection<CampaignListElements> campaignListElementsCollection;
    @JoinColumn(name = "relationship_id", referencedColumnName = "id")
    @ManyToOne
    private Relationship relationshipId;
    @JoinColumn(name = "cluster_id", referencedColumnName = "id")
    @ManyToOne
    private Cluster clusterId;
    @JoinColumn(name = "dsignation_id", referencedColumnName = "id")
    @ManyToOne
    private Designation dsignationId;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private FamilyUnit familyUnit;
    private boolean chiefHouseHolder;
    Long familyMemberOrderNo;

    public Long getFamilyMemberOrderNo() {
        return familyMemberOrderNo;
    }

    public void setFamilyMemberOrderNo(Long familyMemberOrderNo) {
        this.familyMemberOrderNo = familyMemberOrderNo;
    }

    
    

    public FamilyMember() {
    }

    public FamilyMember(Integer id) {
        this.id = id;
    }

    public FamilyMember(Integer id, String nic, String nameSin, String nameEng, String addressEng, String addressSin, Date dob, String tel1, String tel2) {
        this.id = id;
        this.nic = nic;
        this.nameSin = nameSin;
        this.nameEng = nameEng;
        this.addressEng = addressEng;
        this.addressSin = addressSin;
        this.dob = dob;
        this.tel1 = tel1;
        this.tel2 = tel2;
    }

    public FamilyUnit getFamilyUnit() {
        return familyUnit;
    }

    public void setFamilyUnit(FamilyUnit familyUnit) {
        this.familyUnit = familyUnit;
    }

    public boolean isChiefHouseHolder() {
        return chiefHouseHolder;
    }

    public void setChiefHouseHolder(boolean chiefHouseHolder) {
        this.chiefHouseHolder = chiefHouseHolder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getNameSin() {
        return nameSin;
    }

    public void setNameSin(String nameSin) {
        this.nameSin = nameSin;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public String getAddressEng() {
        return addressEng;
    }

    public void setAddressEng(String addressEng) {
        this.addressEng = addressEng;
    }

    public String getAddressSin() {
        return addressSin;
    }

    public void setAddressSin(String addressSin) {
        this.addressSin = addressSin;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    @XmlTransient
    public Collection<CampaignListElements> getCampaignListElementsCollection() {
        return campaignListElementsCollection;
    }

    public void setCampaignListElementsCollection(Collection<CampaignListElements> campaignListElementsCollection) {
        this.campaignListElementsCollection = campaignListElementsCollection;
    }

    public Relationship getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(Relationship relationshipId) {
        this.relationshipId = relationshipId;
    }

    public Cluster getClusterId() {
        return clusterId;
    }

    public void setClusterId(Cluster clusterId) {
        this.clusterId = clusterId;
    }

    public Designation getDsignationId() {
        return dsignationId;
    }

    public void setDsignationId(Designation dsignationId) {
        this.dsignationId = dsignationId;
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
        if (!(object instanceof FamilyMember)) {
            return false;
        }
        FamilyMember other = (FamilyMember) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lk.gov.health.charity.FamilyMember[ id=" + id + " ]";
    }

}
