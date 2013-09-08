package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ncm database table.
 * 
 */
@Entity
@Table(name="ncm")
public class Ncm implements Serializable {
	private static final long serialVersionUID = 1L;
	private int ncmCode;
	private String description;
	private NcmSub ncmSub;
	private TaxCofin taxCofin;
	private TaxIpi taxIpi;
	private TaxPis taxPis;

	public Ncm() {
	}


	@Id
	@SequenceGenerator(name="NCM_NCMCODE_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NCM_NCMCODE_GENERATOR")
	@Column(name="ncm_code", unique=true, nullable=false)
	public int getNcmCode() {
		return this.ncmCode;
	}

	public void setNcmCode(int ncmCode) {
		this.ncmCode = ncmCode;
	}


	@Column(length=100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	//bi-directional many-to-one association to NcmSub
	@ManyToOne
	@JoinColumn(name="ncm_sub_code", nullable=false)
	public NcmSub getNcmSub() {
		return this.ncmSub;
	}

	public void setNcmSub(NcmSub ncmSub) {
		this.ncmSub = ncmSub;
	}


	//bi-directional many-to-one association to TaxCofin
	@ManyToOne
	@JoinColumn(name="id_tax_cofins", nullable=false)
	public TaxCofin getTaxCofin() {
		return this.taxCofin;
	}

	public void setTaxCofin(TaxCofin taxCofin) {
		this.taxCofin = taxCofin;
	}


	//bi-directional many-to-one association to TaxIpi
	@ManyToOne
	@JoinColumn(name="id_tax_ipi", nullable=false)
	public TaxIpi getTaxIpi() {
		return this.taxIpi;
	}

	public void setTaxIpi(TaxIpi taxIpi) {
		this.taxIpi = taxIpi;
	}


	//bi-directional many-to-one association to TaxPis
	@ManyToOne
	@JoinColumn(name="id_tax_pis", nullable=false)
	public TaxPis getTaxPis() {
		return this.taxPis;
	}

	public void setTaxPis(TaxPis taxPis) {
		this.taxPis = taxPis;
	}

}