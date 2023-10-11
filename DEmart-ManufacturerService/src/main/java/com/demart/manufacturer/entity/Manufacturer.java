package com.demart.manufacturer.entity;

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
@Table(name="manufacturer")
public class Manufacturer {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long manufacturerId;
	
	private String manufacturerName;
	
	private String manufacturerEmail;
	
	private String manufacturerPhone;
	
	private String manufacturerAddress;
	
	private String manufacturerWebsite;
	
	private String manufacturerDescription;
	
	private String founderName;
	
	private long yearFounded;
	
	private String logoUrl;
	
	private String manufacturerEmployeeSize;
	
	
	@OneToMany(mappedBy = "manufacturer_item",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Item> manufacturingItems;
	
	private String rating;
	
	private boolean manufacturerVerificationStatus = true;
	
	private String manufacturerTermsConditions;
	
	
	@OneToMany(mappedBy = "manufacturer",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<ManufacturerImages> authenticationProofImages;
    
    
}
