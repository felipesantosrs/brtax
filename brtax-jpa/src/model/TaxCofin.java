package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the tax_cofins database table.
 * 
 */
@Entity
@Table(name="tax_cofins")
public class TaxCofin implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idTaxCofins;
	private BigDecimal aliquot;
	private String decree;
	private List<Ncm> ncms;

	public TaxCofin() {
	}


	@Id
	@SequenceGenerator(name="TAX_COFINS_IDTAXCOFINS_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TAX_COFINS_IDTAXCOFINS_GENERATOR")
	@Column(name="id_tax_cofins", unique=true, nullable=false)
	public int getIdTaxCofins() {
		return this.idTaxCofins;
	}

	public void setIdTaxCofins(int idTaxCofins) {
		this.idTaxCofins = idTaxCofins;
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
	@OneToMany(mappedBy="taxCofin")
	public List<Ncm> getNcms() {
		return this.ncms;
	}

	public void setNcms(List<Ncm> ncms) {
		this.ncms = ncms;
	}

}