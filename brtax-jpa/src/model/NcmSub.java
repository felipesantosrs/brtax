package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ncm_sub database table.
 * 
 */
@Entity
@Table(name="ncm_sub")
public class NcmSub implements Serializable {
	private static final long serialVersionUID = 1L;
	private int ncmSubCode;
	private String description;
	private List<Ncm> ncms;
	private NcmGroup ncmGroup;

	public NcmSub() {
	}


	@Id
	@SequenceGenerator(name="NCM_SUB_NCMSUBCODE_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NCM_SUB_NCMSUBCODE_GENERATOR")
	@Column(name="ncm_sub_code", unique=true, nullable=false)
	public int getNcmSubCode() {
		return this.ncmSubCode;
	}

	public void setNcmSubCode(int ncmSubCode) {
		this.ncmSubCode = ncmSubCode;
	}


	@Column(length=100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	//bi-directional many-to-one association to Ncm
	@OneToMany(mappedBy="ncmSub")
	public List<Ncm> getNcms() {
		return this.ncms;
	}

	public void setNcms(List<Ncm> ncms) {
		this.ncms = ncms;
	}


	//bi-directional many-to-one association to NcmGroup
	@ManyToOne
	@JoinColumn(name="ncm_group_code", nullable=false)
	public NcmGroup getNcmGroup() {
		return this.ncmGroup;
	}

	public void setNcmGroup(NcmGroup ncmGroup) {
		this.ncmGroup = ncmGroup;
	}

}