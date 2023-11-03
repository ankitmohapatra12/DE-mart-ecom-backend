package com.auth.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
public class UserPresonal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userPersonalDataId;
	private String userFullName;
	private String gender;
	private String birthDay;
	private long pincode;
	
	@Transient
	private String userEmail;
	
	@Transient
	private String mobile;
	
	
	@Transient
	private long userId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JsonBackReference
	private UserCreds userData;
	
	
	
}
