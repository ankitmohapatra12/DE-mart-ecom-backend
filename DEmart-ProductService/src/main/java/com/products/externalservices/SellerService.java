package com.products.externalservices;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.products.dto.Brand;
import com.products.dto.Seller;

@FeignClient(name="DEmart-SellerSubService")
public interface SellerService {

	
	@GetMapping("/sellers/all/view")
	List<Seller> getSellers();
	
	@GetMapping("/brands/{id}")
	Brand getBrand(@PathVariable("id") String id);
}
