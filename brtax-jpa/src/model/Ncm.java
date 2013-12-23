/*
 * BRTAX - Servidor
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
 * Representa a tabela NCM
 *
 * @author Felipe
 */
@Entity
@Table(name = "ncm")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ncm.findAll", query = "SELECT n FROM Ncm n"),
    @NamedQuery(name = "Ncm.findByNcmCode", query = "SELECT n FROM Ncm n WHERE n.ncmCode = :ncmCode"),
    @NamedQuery(name = "Ncm.findByDescription", query = "SELECT n FROM Ncm n WHERE n.description like :description")})
public class Ncm implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ncm_code")
    private Integer ncmCode;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "ncmCode")
    private List<NcmTax> ncmTaxList;

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

    @XmlTransient
    public List<NcmTax> getNcmTaxList() {
        return ncmTaxList;
    }

    public void setNcmTaxList(List<NcmTax> ncmTaxList) {
        this.ncmTaxList = ncmTaxList;
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
