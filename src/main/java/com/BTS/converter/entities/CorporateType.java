/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BTS.converter.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @author Reza
 */
@Entity
@Table(schema = "TBSEBG",name = "CONVERT_M_CORPORATE_TYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CorporateType.findAll", query = "SELECT c FROM CorporateType c"),
    @NamedQuery(name = "CorporateType.findById", query = "SELECT c FROM CorporateType c WHERE c.id = :id"),
    @NamedQuery(name = "CorporateType.findByName", query = "SELECT c FROM CorporateType c WHERE c.name = :name")})
public class CorporateType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "ID")
    private String id;
    @Size(max = 35)
    @Column(name = "NAME")
    private String name;
    @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
    private List<ClientPartner> clientPartnerList;

    public CorporateType() {
    }

    public CorporateType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<ClientPartner> getClientPartnerList() {
        return clientPartnerList;
    }

    public void setClientPartnerList(List<ClientPartner> clientPartnerList) {
        this.clientPartnerList = clientPartnerList;
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
        if (!(object instanceof CorporateType)) {
            return false;
        }
        CorporateType other = (CorporateType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.BTS.converter.entities.CorporateType[ id=" + id + " ]";
    }
    
}
