/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.charity.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author buddhika
 */
@Entity
@Table(name = "chief_occupant")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChiefOccupant.findAll", query = "SELECT c FROM ChiefOccupant c"),
    @NamedQuery(name = "ChiefOccupant.findByMemberNic", query = "SELECT c FROM ChiefOccupant c WHERE c.memberNic = :memberNic")})
public class ChiefOccupant implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "member_nic")
    private String memberNic;
    @JoinColumn(name = "member_nic", referencedColumnName = "nic", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Members members;
    @JoinColumn(name = "co_nic", referencedColumnName = "nic")
    @ManyToOne(optional = false)
    private Members coNic;

    public ChiefOccupant() {
    }

    public ChiefOccupant(String memberNic) {
        this.memberNic = memberNic;
    }

    public String getMemberNic() {
        return memberNic;
    }

    public void setMemberNic(String memberNic) {
        this.memberNic = memberNic;
    }

    public Members getMembers() {
        return members;
    }

    public void setMembers(Members members) {
        this.members = members;
    }

    public Members getCoNic() {
        return coNic;
    }

    public void setCoNic(Members coNic) {
        this.coNic = coNic;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (memberNic != null ? memberNic.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChiefOccupant)) {
            return false;
        }
        ChiefOccupant other = (ChiefOccupant) object;
        if ((this.memberNic == null && other.memberNic != null) || (this.memberNic != null && !this.memberNic.equals(other.memberNic))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lk.gov.health.charity.ChiefOccupant[ memberNic=" + memberNic + " ]";
    }
    
}
