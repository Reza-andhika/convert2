/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BTS.converter.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Reza
 */
@Entity
@Table(schema = "TBSEBG",name = "CONVERT_TR_EXTENSION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TrExtension.findAll", query = "SELECT t FROM TrExtension t"),
    @NamedQuery(name = "TrExtension.findById", query = "SELECT t FROM TrExtension t WHERE t.id = :id")})
public class TrExtension implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private ClientPartner clientId;
    @JoinColumn(name = "EXTENSION", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Extension extension;

    public TrExtension() {
    }

    public TrExtension(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public ClientPartner getClientId() {
        return clientId;
    }

    public void setClientId(ClientPartner clientId) {
        this.clientId = clientId;
    }

    public Extension getExtension() {
        return extension;
    }

    public void setExtension(Extension extension) {
        this.extension = extension;
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
        if (!(object instanceof TrExtension)) {
            return false;
        }
        TrExtension other = (TrExtension) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.BTS.converter.entities.TrExtension[ id=" + id + " ]";
    }
    
}
