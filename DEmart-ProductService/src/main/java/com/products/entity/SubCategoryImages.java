package com.products.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryImages {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long subCategoryImageId;
	
	private String subCategoryImageName;
	
	@Transient
	private MultipartFile subCategoryImage;
	
	private long imageSize;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date uploadDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	private SubCategory subCategory;
	
	@Transient
	private String imgUrl;
	
	
}
