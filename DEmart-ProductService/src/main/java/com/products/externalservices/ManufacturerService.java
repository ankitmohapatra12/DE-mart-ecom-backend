package com.products.externalservices;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.products.dto.Manufacturer;

@FeignClient(name="DEmart-ManufacturerService")
public interface ManufacturerService {

	@GetMapping("/manufacturers/all/view")
	List<Manufacturer> getManufacturers();
	
	@GetMapping("/manufacturers/view/{manufacturerId}")
	Manufacturer getManufacturer(@PathVariable("manufacturerId") String manufacturerId);
}
