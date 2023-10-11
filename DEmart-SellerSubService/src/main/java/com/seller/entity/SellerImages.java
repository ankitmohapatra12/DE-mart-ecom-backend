package com.seller.entity;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
public class SellerImages {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long sellerImageId;
	
	private String sellerImageName;
	
	@Transient
	private MultipartFile sellerImage;
	
	private long imageSize;
	
	
	private String sellerPictureType;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date uploadDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	private Sellers seller_id;
	
	@Transient
	private String imgUrl;
}
