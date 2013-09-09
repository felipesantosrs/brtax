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
@Table(name = "ncm_group")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NcmGroup.findAll", query = "SELECT n FROM NcmGroup n"),
    @NamedQuery(name = "NcmGroup.findByNcmGroupCode", query = "SELECT n FROM NcmGroup n WHERE n.ncmGroupCode = :ncmGroupCode"),
    @NamedQuery(name = "NcmGroup.findByDescription", query = "SELECT n FROM NcmGroup n WHERE n.description = :description")})
public class NcmGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ncm_group_code")
    private Integer ncmGroupCode;
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ncmGroupCode")
    private List<NcmSub> ncmSubList;

    public NcmGroup() {
    }

    public NcmGroup(Integer ncmGroupCode) {
        this.ncmGroupCode = ncmGroupCode;
    }

    public Integer getNcmGroupCode() {
        return ncmGroupCode;
    }

    public void setNcmGroupCode(Integer ncmGroupCode) {
        this.ncmGroupCode = ncmGroupCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public List<NcmSub> getNcmSubList() {
        return ncmSubList;
    }

    public void setNcmSubList(List<NcmSub> ncmSubList) {
        this.ncmSubList = ncmSubList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ncmGroupCode != null ? ncmGroupCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NcmGroup)) {
            return false;
        }
        NcmGroup other = (NcmGroup) object;
        if ((this.ncmGroupCode == null && other.ncmGroupCode != null) || (this.ncmGroupCode != null && !this.ncmGroupCode.equals(other.ncmGroupCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.NcmGroup[ ncmGroupCode=" + ncmGroupCode + " ]";
    }
    
}
