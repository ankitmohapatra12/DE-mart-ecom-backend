package com.review.entity;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewImages {

	
	
	private String reviewImageName;
	
	private long imageSize;
	
	private Date uploadDate = new Date();
	
	private String imgUrl;
}
