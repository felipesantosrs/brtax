package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the tax_pis database table.
 * 
 */
@Entity
@Table(name="tax_pis")
public class TaxPis implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idTaxPis;
	private BigDecimal aliquot;
	private String decree;
	private List<Ncm> ncms;

	public TaxPis() {
	}


	@Id
	@SequenceGenerator(name="TAX_PIS_IDTAXPIS_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TAX_PIS_IDTAXPIS_GENERATOR")
	@Column(name="id_tax_pis", unique=true, nullable=false)
	public int getIdTaxPis() {
		return this.idTaxPis;
	}

	public void setIdTaxPis(int idTaxPis) {
		this.idTaxPis = idTaxPis;
	}


	@Column(precision=10, scale=2)
	public BigDecimal getAliquot() {
		return this.aliquot;
	}

	public void setAliquot(BigDecimal aliquot) {
		this.aliquot = aliquot;
	}


	@Column(length=255)
	public String getDecree() {
		return this.decree;
	}

	public void setDecree(String decree) {
		this.decree = decree;
	}


	//bi-directional many-to-one association to Ncm
	@OneToMany(mappedBy="taxPi")
	public List<Ncm> getNcms() {
		return this.ncms;
	}

	public void setNcms(List<Ncm> ncms) {
		this.ncms = ncms;
	}

}