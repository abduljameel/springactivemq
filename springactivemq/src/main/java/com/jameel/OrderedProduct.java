package com.jameel;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
//import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;


@Entity
@Table(name="ORDER_PRODUCTS")
public class OrderedProduct {
	

  @Id 
  @Column(name="id")
  private Long id;
  
  @Column(name="product_name", nullable=false, length=200)
  private String productName;
  
  @Column(name="product_count")
  private int prdCount;
  
  @Column(name="price")
  private int price;

	
//	  public OrderedProduct(Long id, String productName, int prdCount, int price) { 
//		  this.id = id;
//		  this.productName = productName; 
//		  this.prdCount = prdCount;
//		  this.price = price; 
//		  }
	 
  
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getPrdCount() {
		return prdCount;
	}
	public void setPrdCount(int prdCount) {
		this.prdCount = prdCount;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
	public String toString()
	{
		
		return "Ordered Product [id= | " + getId() + " | " + getProductName() + " | " + "No. of Items : " + getPrdCount() + " | " + "Price : " + getPrice();
		
	}
	
	/* public OrderedProduct() {} */
}

