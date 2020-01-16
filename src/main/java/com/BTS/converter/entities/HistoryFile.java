/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BTS.converter.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Reza
 */
@Entity
@Table(schema = "TBSEBG",name = "CONVERT_M_HISTORY_FILE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistoryFile.findAll", query = "SELECT h FROM HistoryFile h"),
    @NamedQuery(name = "HistoryFile.findById", query = "SELECT h FROM HistoryFile h WHERE h.id = :id"),
    @NamedQuery(name = "HistoryFile.findByOldPath", query = "SELECT h FROM HistoryFile h WHERE h.oldPath = :oldPath"),
    @NamedQuery(name = "HistoryFile.findByNewPath", query = "SELECT h FROM HistoryFile h WHERE h.newPath = :newPath"),
    @NamedQuery(name = "HistoryFile.findByOldFilename", query = "SELECT h FROM HistoryFile h WHERE h.oldFilename = :oldFilename"),
    @NamedQuery(name = "HistoryFile.findByNewFilename", query = "SELECT h FROM HistoryFile h WHERE h.newFilename = :newFilename"),
    @NamedQuery(name = "HistoryFile.findByDate", query = "SELECT h FROM HistoryFile h WHERE h.date = :date")})
public class HistoryFile implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Size(max = 100)
    @Column(name = "OLD_PATH")
    private String oldPath;
    @Size(max = 100)
    @Column(name = "NEW_PATH")
    private String newPath;
    @Size(max = 100)
    @Column(name = "OLD_FILENAME")
    private String oldFilename;
    @Size(max = 100)
    @Column(name = "NEW_FILENAME")
    private String newFilename;
    @Column(name = "DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @JoinColumn(name = "CLIENT", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private ClientPartner client;

    public HistoryFile() {
    }

    public HistoryFile(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getOldPath() {
        return oldPath;
    }

    public void setOldPath(String oldPath) {
        this.oldPath = oldPath;
    }

    public String getNewPath() {
        return newPath;
    }

    public void setNewPath(String newPath) {
        this.newPath = newPath;
    }

    public String getOldFilename() {
        return oldFilename;
    }

    public void setOldFilename(String oldFilename) {
        this.oldFilename = oldFilename;
    }

    public String getNewFilename() {
        return newFilename;
    }

    public void setNewFilename(String newFilename) {
        this.newFilename = newFilename;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ClientPartner getClient() {
        return client;
    }

    public void setClient(ClientPartner client) {
        this.client = client;
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
        if (!(object instanceof HistoryFile)) {
            return false;
        }
        HistoryFile other = (HistoryFile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.BTS.converter.entities.HistoryFile[ id=" + id + " ]";
    }
    
}
