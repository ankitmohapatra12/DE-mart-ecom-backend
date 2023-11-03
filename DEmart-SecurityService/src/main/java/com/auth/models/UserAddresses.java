package com.auth.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAddresses {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userAddressId;
	
	private String name;
	
	private String mobile;
	
	private String pincode;
	
	private String country;
	
	private String state;
	
	private String city;
	
	private String locality;
	
	private String district;
	
	private String addressType;
	
	@Column(columnDefinition = "TEXT")
	private String address;
	
	private boolean defaultAddressUser;
	
	
	@Transient
	private long userId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference
	private UserCreds userAddresses;
}
