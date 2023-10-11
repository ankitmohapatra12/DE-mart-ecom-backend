package com.products.entity;

import java.util.Date;



import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryImages {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long categoryImageId;
	
	private String categoryImageName;
	
	@Transient
	private MultipartFile categoryImage;
	
	private long imageSize;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date uploadDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	private Category category;
	
	@Transient
	private String imgUrl;
}
