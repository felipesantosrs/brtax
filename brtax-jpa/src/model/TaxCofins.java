/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Felipe
 */
@Entity
@Table(name = "tax_cofins")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TaxCofins.findAll", query = "SELECT t FROM TaxCofins t"),
    @NamedQuery(name = "TaxCofins.findByIdTaxCofins", query = "SELECT t FROM TaxCofins t WHERE t.idTaxCofins = :idTaxCofins"),
    @NamedQuery(name = "TaxCofins.findByAliquot", query = "SELECT t FROM TaxCofins t WHERE t.aliquot = :aliquot"),
    @NamedQuery(name = "TaxCofins.findByDecree", query = "SELECT t FROM TaxCofins t WHERE t.decree = :decree")})
public class TaxCofins implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_tax_cofins")
    private Integer idTaxCofins;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "aliquot")
    private BigDecimal aliquot;
    @Column(name = "decree")
    private String decree;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTaxCofins")
    private List<Ncm> ncmList;

    public TaxCofins() {
    }

    public TaxCofins(Integer idTaxCofins) {
        this.idTaxCofins = idTaxCofins;
    }

    public Integer getIdTaxCofins() {
        return idTaxCofins;
    }

    public void setIdTaxCofins(Integer idTaxCofins) {
        this.idTaxCofins = idTaxCofins;
    }

    public BigDecimal getAliquot() {
        return aliquot;
    }

    public void setAliquot(BigDecimal aliquot) {
        this.aliquot = aliquot;
    }

    public String getDecree() {
        return decree;
    }

    public void setDecree(String decree) {
        this.decree = decree;
    }

    @XmlTransient
    public List<Ncm> getNcmList() {
        return ncmList;
    }

    public void setNcmList(List<Ncm> ncmList) {
        this.ncmList = ncmList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTaxCofins != null ? idTaxCofins.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TaxCofins)) {
            return false;
        }
        TaxCofins other = (TaxCofins) object;
        if ((this.idTaxCofins == null && other.idTaxCofins != null) || (this.idTaxCofins != null && !this.idTaxCofins.equals(other.idTaxCofins))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TaxCofins[ idTaxCofins=" + idTaxCofins + " ]";
    }
    
}
