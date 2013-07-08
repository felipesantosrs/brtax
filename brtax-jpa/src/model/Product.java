package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@Table(name="product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idProduct;
	private String brand;
	private String description;
	private int gtin;
	private String productName;

	public Product() {
	}


	@Id
	@SequenceGenerator(name="PRODUCT_IDPRODUCT_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUCT_IDPRODUCT_GENERATOR")
	@Column(name="id_product", unique=true, nullable=false)
	public int getIdProduct() {
		return this.idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}


	@Column(length=50)
	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}


	@Column(length=500)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Column(nullable=false)
	public int getGtin() {
		return this.gtin;
	}

	public void setGtin(int gtin) {
		this.gtin = gtin;
	}


	@Column(name="product_name", length=100)
	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}