/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BTS.converter.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(schema = "TBSEBG", name = "CONVERT_M_CLIENT_PARTNER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClientPartner.findAll", query = "SELECT c FROM ClientPartner c"),
    @NamedQuery(name = "ClientPartner.findById", query = "SELECT c FROM ClientPartner c WHERE c.id = :id"),
    @NamedQuery(name = "ClientPartner.findByClientId", query = "SELECT c FROM ClientPartner c WHERE c.clientId = :clientId"),
    @NamedQuery(name = "ClientPartner.findByName", query = "SELECT c FROM ClientPartner c WHERE c.name = :name"),
    @NamedQuery(name = "ClientPartner.findByIncomingPath", query = "SELECT c FROM ClientPartner c WHERE c.incomingPath = :incomingPath"),
    @NamedQuery(name = "ClientPartner.findByOutgoingPath", query = "SELECT c FROM ClientPartner c WHERE c.outgoingPath = :outgoingPath"),
    @NamedQuery(name = "ClientPartner.findByParameter2", query = "SELECT c FROM ClientPartner c WHERE c.parameter2 = :parameter2")})
public class ClientPartner implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Size(max = 25)
    @Column(name = "CLIENT_ID")
    private String clientId;
    @Size(max = 35)
    @Column(name = "NAME")
    private String name;
    @Size(max = 100)
    @Column(name = "INCOMING_PATH")
    private String incomingPath;
    @Size(max = 100)
    @Column(name = "OUTGOING_PATH")
    private String outgoingPath;
    @Size(max = 5)
    @Column(name = "PARAMETER2")
    private String parameter2;
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<HistoryFile> historyFileList;
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Temp> tempList;
    @OneToMany(mappedBy = "clientId", fetch = FetchType.LAZY)
    private List<TrExtension> trExtensionList;
    @JoinColumn(name = "TYPE", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private CorporateType type;
    @JoinColumn(name = "PARAMETER", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Parameter parameter;
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<DetailData> detailDataList;

    public ClientPartner() {
    }

    public ClientPartner(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIncomingPath() {
        return incomingPath;
    }

    public void setIncomingPath(String incomingPath) {
        this.incomingPath = incomingPath;
    }

    public String getOutgoingPath() {
        return outgoingPath;
    }

    public void setOutgoingPath(String outgoingPath) {
        this.outgoingPath = outgoingPath;
    }

    public String getParameter2() {
        return parameter2;
    }

    public void setParameter2(String parameter2) {
        this.parameter2 = parameter2;
    }

    @XmlTransient
    public List<HistoryFile> getHistoryFileList() {
        return historyFileList;
    }

    public void setHistoryFileList(List<HistoryFile> historyFileList) {
        this.historyFileList = historyFileList;
    }

    @XmlTransient
    public List<Temp> getTempList() {
        return tempList;
    }

    public void setTempList(List<Temp> tempList) {
        this.tempList = tempList;
    }

    @XmlTransient
    public List<TrExtension> getTrExtensionList() {
        return trExtensionList;
    }

    public void setTrExtensionList(List<TrExtension> trExtensionList) {
        this.trExtensionList = trExtensionList;
    }

    public CorporateType getType() {
        return type;
    }

    public void setType(CorporateType type) {
        this.type = type;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    @XmlTransient
    public List<DetailData> getDetailDataList() {
        return detailDataList;
    }

    public void setDetailDataList(List<DetailData> detailDataList) {
        this.detailDataList = detailDataList;
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
        if (!(object instanceof ClientPartner)) {
            return false;
        }
        ClientPartner other = (ClientPartner) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.BTS.converter.entities.ClientPartner[ id=" + id + " ]";
    }
    
}
