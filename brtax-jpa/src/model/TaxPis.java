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
@Table(name = "tax_pis")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TaxPis.findAll", query = "SELECT t FROM TaxPis t"),
    @NamedQuery(name = "TaxPis.findByIdTaxPis", query = "SELECT t FROM TaxPis t WHERE t.idTaxPis = :idTaxPis"),
    @NamedQuery(name = "TaxPis.findByAliquot", query = "SELECT t FROM TaxPis t WHERE t.aliquot = :aliquot"),
    @NamedQuery(name = "TaxPis.findByDecree", query = "SELECT t FROM TaxPis t WHERE t.decree = :decree")})
public class TaxPis implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_tax_pis")
    private Integer idTaxPis;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "aliquot")
    private BigDecimal aliquot;
    @Column(name = "decree")
    private String decree;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTaxPis")
    private List<Ncm> ncmList;

    public TaxPis() {
    }

    public TaxPis(Integer idTaxPis) {
        this.idTaxPis = idTaxPis;
    }

    public Integer getIdTaxPis() {
        return idTaxPis;
    }

    public void setIdTaxPis(Integer idTaxPis) {
        this.idTaxPis = idTaxPis;
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
        hash += (idTaxPis != null ? idTaxPis.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TaxPis)) {
            return false;
        }
        TaxPis other = (TaxPis) object;
        if ((this.idTaxPis == null && other.idTaxPis != null) || (this.idTaxPis != null && !this.idTaxPis.equals(other.idTaxPis))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TaxPis[ idTaxPis=" + idTaxPis + " ]";
    }
    
}
