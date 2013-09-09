/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Felipe
 */
@Entity
@Table(name = "ncm")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ncm.findAll", query = "SELECT n FROM Ncm n"),
    @NamedQuery(name = "Ncm.findByNcmCode", query = "SELECT n FROM Ncm n WHERE n.ncmCode = :ncmCode"),
    @NamedQuery(name = "Ncm.findByDescription", query = "SELECT n FROM Ncm n WHERE n.description = :description")})
public class Ncm implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ncm_code")
    private Integer ncmCode;
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "id_tax_pis", referencedColumnName = "id_tax_pis")
    @ManyToOne(optional = false)
    private TaxPis idTaxPis;
    @JoinColumn(name = "id_tax_cofins", referencedColumnName = "id_tax_cofins")
    @ManyToOne(optional = false)
    private TaxCofins idTaxCofins;
    @JoinColumn(name = "id_tax_ipi", referencedColumnName = "id_tax_ipi")
    @ManyToOne(optional = false)
    private TaxIpi idTaxIpi;
    @JoinColumn(name = "ncm_sub_code", referencedColumnName = "ncm_sub_code")
    @ManyToOne(optional = false)
    private NcmSub ncmSubCode;

    public Ncm() {
    }

    public Ncm(Integer ncmCode) {
        this.ncmCode = ncmCode;
    }

    public Integer getNcmCode() {
        return ncmCode;
    }

    public void setNcmCode(Integer ncmCode) {
        this.ncmCode = ncmCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaxPis getIdTaxPis() {
        return idTaxPis;
    }

    public void setIdTaxPis(TaxPis idTaxPis) {
        this.idTaxPis = idTaxPis;
    }

    public TaxCofins getIdTaxCofins() {
        return idTaxCofins;
    }

    public void setIdTaxCofins(TaxCofins idTaxCofins) {
        this.idTaxCofins = idTaxCofins;
    }

    public TaxIpi getIdTaxIpi() {
        return idTaxIpi;
    }

    public void setIdTaxIpi(TaxIpi idTaxIpi) {
        this.idTaxIpi = idTaxIpi;
    }

    public NcmSub getNcmSubCode() {
        return ncmSubCode;
    }

    public void setNcmSubCode(NcmSub ncmSubCode) {
        this.ncmSubCode = ncmSubCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ncmCode != null ? ncmCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ncm)) {
            return false;
        }
        Ncm other = (Ncm) object;
        if ((this.ncmCode == null && other.ncmCode != null) || (this.ncmCode != null && !this.ncmCode.equals(other.ncmCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Ncm[ ncmCode=" + ncmCode + " ]";
    }
    
}
