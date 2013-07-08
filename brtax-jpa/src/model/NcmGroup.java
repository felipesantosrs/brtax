package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ncm_group database table.
 * 
 */
@Entity
@Table(name="ncm_group")
public class NcmGroup implements Serializable {
	private static final long serialVersionUID = 1L;
	private int ncmGroupCode;
	private String description;
	private List<NcmSub> ncmSubs;

	public NcmGroup() {
	}


	@Id
	@SequenceGenerator(name="NCM_GROUP_NCMGROUPCODE_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NCM_GROUP_NCMGROUPCODE_GENERATOR")
	@Column(name="ncm_group_code", unique=true, nullable=false)
	public int getNcmGroupCode() {
		return this.ncmGroupCode;
	}

	public void setNcmGroupCode(int ncmGroupCode) {
		this.ncmGroupCode = ncmGroupCode;
	}


	@Column(length=100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	//bi-directional many-to-one association to NcmSub
	@OneToMany(mappedBy="ncmGroup")
	public List<NcmSub> getNcmSubs() {
		return this.ncmSubs;
	}

	public void setNcmSubs(List<NcmSub> ncmSubs) {
		this.ncmSubs = ncmSubs;
	}

}