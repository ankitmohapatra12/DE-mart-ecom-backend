package com.order.payload;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand {

	private Long id;

	   
    private String name;

    
    private String logoUrl;

  
    private String description;


    private String website;

  
    private String countryOfOrigin;

  
    private Integer yearFounded;

   
    private String founder;
    
    
    private String brandDealerName;


   
    private String contactEmail;

    
    private String contactPhone;

   
    private String facebookUrl;

    
    private String twitterUrl;

    
    private String instagramUrl;

    
    private double averageRating;

    
    private boolean isActive;
    
    
    private Date partnershipDate;
}
