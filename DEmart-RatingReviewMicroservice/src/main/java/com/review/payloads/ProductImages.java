package com.review.payloads;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductImages {

	
	private long productImageId;
	
	private String productImageName;
	
	private long imageSize;
	

	private Date uploadDate;

	private Product product;

	private String imgUrl;
}
