package com.demart.manufacturer.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.demart.manufacturer.entity.Manufacturer;

@Service
public interface ManufacturerService {

	Manufacturer addManufacturers(Manufacturer manufacturer,MultipartFile[] images) throws Exception;

	List<Manufacturer> viewManufacturers() throws Exception;

}
