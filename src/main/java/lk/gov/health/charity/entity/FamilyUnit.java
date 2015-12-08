/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.charity.entity;

import java.io.Serializable;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author buddhika
 */
@Entity
public class FamilyUnit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    Cluster cluster;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date dateEntryDate;

    @OneToMany(mappedBy = "familyUnit", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderColumn(name = "orderNo")
    List<FamilyMember> members;

    @Transient
    FamilyMember chiefHouseHolder;

    public Cluster getCluster() {
        return cluster;
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    public Date getDateEntryDate() {
        if (dateEntryDate == null) {
            dateEntryDate = new Date();
        }
        return dateEntryDate;
    }

    public void setDateEntryDate(Date dateEntryDate) {
        this.dateEntryDate = dateEntryDate;
    }

    public List<FamilyMember> getMembers() {
        if (members == null) {
            members = new ArrayList<FamilyMember>();
            FamilyMember chh = new FamilyMember();
            chh.setChiefHouseHolder(true);
            chh.setFamilyMemberOrderNo(0l);
            members.add(chh);
        }
        return members;
    }

    public List<FamilyMember> getMembersExceptChh() {
        if (members != null && !members.isEmpty()) {
            List<FamilyMember> temFms = new ArrayList<FamilyMember>();
            temFms.addAll(members);
            temFms.remove(0);
            return temFms;
        }else{
            return null;
        }
    }

    public void setMembers(List<FamilyMember> members) {
        this.members = members;
    }

    public FamilyMember getChiefHouseHolder() {
        if (chiefHouseHolder == null) {
            try {
                chiefHouseHolder = getMembers().get(0);
            } catch (Exception e) {
                System.out.println("e = " + e);
            }
        }
        return chiefHouseHolder;
    }

    public void setChiefHouseHolder(FamilyMember chiefHouseHolder) {
        this.chiefHouseHolder = chiefHouseHolder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof FamilyUnit)) {
            return false;
        }
        FamilyUnit other = (FamilyUnit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lk.gov.health.charity.entity.FamilyUnit[ id=" + id + " ]";
    }

}
