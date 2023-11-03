package com.auth.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.auth.dto.LocationDetailsResponse;

@RestController
@RequestMapping("/location")
public class LocationController {
    private final String apiBaseUrl = "https://api.postalpincode.in/pincode/";

    
    
    @GetMapping("/{pincode}")
    public LocationDetailsResponse getLocationDetails(@PathVariable String pincode) {
    	RestTemplate restTemplate = new RestTemplate();
        
      
         ResponseEntity<LocationDetailsResponse[]> response = restTemplate.getForEntity(apiBaseUrl + Integer.parseInt(pincode),  LocationDetailsResponse[].class);

        if (response.getStatusCode().is2xxSuccessful()) {
            LocationDetailsResponse[] details = response.getBody();
            System.out.println(details[0]);
            if (details != null && details.length > 0) {
                return details[0];
            }
        }

        return null; // 
    }
}
