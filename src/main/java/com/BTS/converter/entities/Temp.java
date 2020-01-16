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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Reza
 */
@Entity
@Table(schema = "TBSEBG",name = "CONVERT_TEMP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Temp.findAll", query = "SELECT t FROM Temp t"),
    @NamedQuery(name = "Temp.findByField1", query = "SELECT t FROM Temp t WHERE t.field1 = :field1"),
    @NamedQuery(name = "Temp.findByField2", query = "SELECT t FROM Temp t WHERE t.field2 = :field2"),
    @NamedQuery(name = "Temp.findByField3", query = "SELECT t FROM Temp t WHERE t.field3 = :field3"),
    @NamedQuery(name = "Temp.findByField4", query = "SELECT t FROM Temp t WHERE t.field4 = :field4"),
    @NamedQuery(name = "Temp.findByField5", query = "SELECT t FROM Temp t WHERE t.field5 = :field5"),
    @NamedQuery(name = "Temp.findByField6", query = "SELECT t FROM Temp t WHERE t.field6 = :field6"),
    @NamedQuery(name = "Temp.findByField7", query = "SELECT t FROM Temp t WHERE t.field7 = :field7"),
    @NamedQuery(name = "Temp.findByField8", query = "SELECT t FROM Temp t WHERE t.field8 = :field8"),
    @NamedQuery(name = "Temp.findByField9", query = "SELECT t FROM Temp t WHERE t.field9 = :field9"),
    @NamedQuery(name = "Temp.findByField10", query = "SELECT t FROM Temp t WHERE t.field10 = :field10"),
    @NamedQuery(name = "Temp.findByFileName", query = "SELECT t FROM Temp t WHERE t.fileName = :fileName"),
    @NamedQuery(name = "Temp.findById", query = "SELECT t FROM Temp t WHERE t.id = :id")})
public class Temp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 100)
    @Column(name = "FIELD_1")
    private String field1;
    @Size(max = 100)
    @Column(name = "FIELD_2")
    private String field2;
    @Size(max = 100)
    @Column(name = "FIELD_3")
    private String field3;
    @Size(max = 100)
    @Column(name = "FIELD_4")
    private String field4;
    @Size(max = 100)
    @Column(name = "FIELD_5")
    private String field5;
    @Size(max = 100)
    @Column(name = "FIELD_6")
    private String field6;
    @Size(max = 100)
    @Column(name = "FIELD_7")
    private String field7;
    @Size(max = 100)
    @Column(name = "FIELD_8")
    private String field8;
    @Size(max = 100)
    @Column(name = "FIELD_9")
    private String field9;
    @Size(max = 100)
    @Column(name = "FIELD_10")
    private String field10;
    @Size(max = 100)
    @Column(name = "FILE_NAME")
    private String fileName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @JoinColumn(name = "CLIENT", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private ClientPartner client;

    public Temp() {
    }

    public Temp(BigDecimal id) {
        this.id = id;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

    public String getField4() {
        return field4;
    }

    public void setField4(String field4) {
        this.field4 = field4;
    }

    public String getField5() {
        return field5;
    }

    public void setField5(String field5) {
        this.field5 = field5;
    }

    public String getField6() {
        return field6;
    }

    public void setField6(String field6) {
        this.field6 = field6;
    }

    public String getField7() {
        return field7;
    }

    public void setField7(String field7) {
        this.field7 = field7;
    }

    public String getField8() {
        return field8;
    }

    public void setField8(String field8) {
        this.field8 = field8;
    }

    public String getField9() {
        return field9;
    }

    public void setField9(String field9) {
        this.field9 = field9;
    }

    public String getField10() {
        return field10;
    }

    public void setField10(String field10) {
        this.field10 = field10;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
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
        if (!(object instanceof Temp)) {
            return false;
        }
        Temp other = (Temp) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.BTS.converter.entities.Temp[ id=" + id + " ]";
    }
    
}
