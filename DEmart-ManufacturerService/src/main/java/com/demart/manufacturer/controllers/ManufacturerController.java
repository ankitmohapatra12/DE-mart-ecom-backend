package com.demart.manufacturer.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demart.manufacturer.entity.Item;
import com.demart.manufacturer.entity.Manufacturer;
import com.demart.manufacturer.services.ManufacturerService;

@RequestMapping("/manufacturers")
@RestController
public class ManufacturerController {
	
	@Autowired
	private ManufacturerService manufacturerService;

	
	@PostMapping(value={"/add"},consumes = {"multipart/form-data",MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Manufacturer> addManufacturer(@RequestPart("files") MultipartFile[] images,@RequestPart("manufacturer") Manufacturer manufacturer) throws Exception {
		try {
			List<Item> list = new ArrayList<>();
			if(manufacturer.getManufacturingItems().size()>0) {
				//List<Item> list = new ArrayList<>();
				for(Item item :manufacturer.getManufacturingItems()){
					item.setManufacturer_item(manufacturer);
					list.add(item);
				}
				
			}
			manufacturer.setManufacturingItems(list);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
		return ResponseEntity.ok(manufacturerService.addManufacturers(manufacturer,images));
	}
	
	@GetMapping("/all/view")
	public ResponseEntity<List<Manufacturer>> viewManufacturers() throws Exception  {
		return ResponseEntity.ok(manufacturerService.viewManufacturers());
	}
	
	
	@GetMapping("/view/{manufacturerId}")
	public ResponseEntity<Manufacturer> viewManufacturer(@PathVariable String manufacturerId) throws Exception  {
		return ResponseEntity.ok(manufacturerService.viewManufacturersById(Long.parseLong(manufacturerId)));
	}
	
}
