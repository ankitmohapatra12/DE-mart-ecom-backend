package com.seller.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sellers {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long sellerId;
	
	private String sellerName;
	
	private String sellerEmail;
	
	private String sellerPhone;
	
	private String sellerAddress;
	
	private String sellerWebsite;
	
	private String sellerDescription;
	
	private String sellerOfficeAddress;
	
	private String ratings;
	
	private boolean sellerVerificationStatus = true;
	
	private String sellerTermsConditions;
	
	
	@OneToMany(mappedBy = "seller_id",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<SellerImages> images;
    
    
}
