package com.example.springboot.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "products")
public class SmartPhone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(min = 2, message = "Product name should have at least 2 characters")
	@Column(name = "product_name")
	private String name;
	
	@NotNull
	@Column(name = "product_price")
	private float price;
	
	@NotNull
	@Column(name = "product_status")
	private boolean status;
	
	public SmartPhone() {
		super();
	};

	public SmartPhone(String name, float price, boolean status) {
		super();
		this.name = name;
		this.price = price;
		this.status = status;
	};

	public long getId() {
		return id;
	};

	public void setId(long id) {
		this.id = id;
	};

	public String getName() {
		return name;
	};

	public void setName(String name) {
		this.name = name;
	};

	public float getPrice() {
		return price;
	};

	public void setPrice(float price) {
		this.price = price;
	};

	public boolean isStatus() {
		return status;
	};

	public void setStatus(boolean status) {
		this.status = status;
	};
}
