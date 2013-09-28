/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "ncm_tax")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NcmTax.findAll", query = "SELECT n FROM NcmTax n"),
    @NamedQuery(name = "NcmTax.findByIdNcmTax", query = "SELECT n FROM NcmTax n WHERE n.idNcmTax = :idNcmTax"),
    @NamedQuery(name = "NcmTax.findByAliquot", query = "SELECT n FROM NcmTax n WHERE n.aliquot = :aliquot")})
public class NcmTax implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ncm_tax")
    private Integer idNcmTax;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "aliquot")
    private Float aliquot;
    @JoinColumn(name = "ncm_code", referencedColumnName = "ncm_code")
    @ManyToOne
    private Ncm ncmCode;

    public NcmTax() {
    }

    public NcmTax(Integer idNcmTax) {
        this.idNcmTax = idNcmTax;
    }

    public Integer getIdNcmTax() {
        return idNcmTax;
    }

    public void setIdNcmTax(Integer idNcmTax) {
        this.idNcmTax = idNcmTax;
    }

    public Float getAliquot() {
        return aliquot;
    }

    public void setAliquot(Float aliquot) {
        this.aliquot = aliquot;
    }

    public Ncm getNcmCode() {
        return ncmCode;
    }

    public void setNcmCode(Ncm ncmCode) {
        this.ncmCode = ncmCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNcmTax != null ? idNcmTax.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NcmTax)) {
            return false;
        }
        NcmTax other = (NcmTax) object;
        if ((this.idNcmTax == null && other.idNcmTax != null) || (this.idNcmTax != null && !this.idNcmTax.equals(other.idNcmTax))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.NcmTax[ idNcmTax=" + idNcmTax + " ]";
    }
    
}
