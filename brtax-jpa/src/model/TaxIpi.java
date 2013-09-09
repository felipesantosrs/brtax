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
@Table(name = "tax_ipi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TaxIpi.findAll", query = "SELECT t FROM TaxIpi t"),
    @NamedQuery(name = "TaxIpi.findByIdTaxIpi", query = "SELECT t FROM TaxIpi t WHERE t.idTaxIpi = :idTaxIpi"),
    @NamedQuery(name = "TaxIpi.findByAliquot", query = "SELECT t FROM TaxIpi t WHERE t.aliquot = :aliquot"),
    @NamedQuery(name = "TaxIpi.findByDecree", query = "SELECT t FROM TaxIpi t WHERE t.decree = :decree")})
public class TaxIpi implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_tax_ipi")
    private Integer idTaxIpi;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "aliquot")
    private BigDecimal aliquot;
    @Column(name = "decree")
    private String decree;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTaxIpi")
    private List<Ncm> ncmList;

    public TaxIpi() {
    }

    public TaxIpi(Integer idTaxIpi) {
        this.idTaxIpi = idTaxIpi;
    }

    public Integer getIdTaxIpi() {
        return idTaxIpi;
    }

    public void setIdTaxIpi(Integer idTaxIpi) {
        this.idTaxIpi = idTaxIpi;
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
        hash += (idTaxIpi != null ? idTaxIpi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TaxIpi)) {
            return false;
        }
        TaxIpi other = (TaxIpi) object;
        if ((this.idTaxIpi == null && other.idTaxIpi != null) || (this.idTaxIpi != null && !this.idTaxIpi.equals(other.idTaxIpi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TaxIpi[ idTaxIpi=" + idTaxIpi + " ]";
    }
    
}
