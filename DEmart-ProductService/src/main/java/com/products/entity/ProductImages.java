package com.products.entity;

import java.util.Date;
import java.util.List;

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
@NoArgsConstructor
@AllArgsConstructor
public class ProductImages {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productImageId;
	
	private String productImageName;
	
	@Transient
	private MultipartFile productImage;
	
	private long imageSize;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date uploadDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	private Product product;
	
	@Transient
	private String imgUrl;
	
}
