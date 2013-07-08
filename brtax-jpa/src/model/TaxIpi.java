package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the tax_ipi database table.
 * 
 */
@Entity
@Table(name="tax_ipi")
public class TaxIpi implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idTaxIpi;
	private BigDecimal aliquot;
	private String decree;
	private List<Ncm> ncms;

	public TaxIpi() {
	}


	@Id
	@SequenceGenerator(name="TAX_IPI_IDTAXIPI_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TAX_IPI_IDTAXIPI_GENERATOR")
	@Column(name="id_tax_ipi", unique=true, nullable=false)
	public int getIdTaxIpi() {
		return this.idTaxIpi;
	}

	public void setIdTaxIpi(int idTaxIpi) {
		this.idTaxIpi = idTaxIpi;
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
	@OneToMany(mappedBy="taxIpi")
	public List<Ncm> getNcms() {
		return this.ncms;
	}

	public void setNcms(List<Ncm> ncms) {
		this.ncms = ncms;
	}

}