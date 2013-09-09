/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ncm_sub")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NcmSub.findAll", query = "SELECT n FROM NcmSub n"),
    @NamedQuery(name = "NcmSub.findByNcmSubCode", query = "SELECT n FROM NcmSub n WHERE n.ncmSubCode = :ncmSubCode"),
    @NamedQuery(name = "NcmSub.findByDescription", query = "SELECT n FROM NcmSub n WHERE n.description = :description")})
public class NcmSub implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ncm_sub_code")
    private Integer ncmSubCode;
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ncmSubCode")
    private List<Ncm> ncmList;
    @JoinColumn(name = "ncm_group_code", referencedColumnName = "ncm_group_code")
    @ManyToOne(optional = false)
    private NcmGroup ncmGroupCode;

    public NcmSub() {
    }

    public NcmSub(Integer ncmSubCode) {
        this.ncmSubCode = ncmSubCode;
    }

    public Integer getNcmSubCode() {
        return ncmSubCode;
    }

    public void setNcmSubCode(Integer ncmSubCode) {
        this.ncmSubCode = ncmSubCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public List<Ncm> getNcmList() {
        return ncmList;
    }

    public void setNcmList(List<Ncm> ncmList) {
        this.ncmList = ncmList;
    }

    public NcmGroup getNcmGroupCode() {
        return ncmGroupCode;
    }

    public void setNcmGroupCode(NcmGroup ncmGroupCode) {
        this.ncmGroupCode = ncmGroupCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ncmSubCode != null ? ncmSubCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NcmSub)) {
            return false;
        }
        NcmSub other = (NcmSub) object;
        if ((this.ncmSubCode == null && other.ncmSubCode != null) || (this.ncmSubCode != null && !this.ncmSubCode.equals(other.ncmSubCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.NcmSub[ ncmSubCode=" + ncmSubCode + " ]";
    }
    
}
