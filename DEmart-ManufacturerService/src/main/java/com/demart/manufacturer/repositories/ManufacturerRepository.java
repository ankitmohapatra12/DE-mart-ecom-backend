package com.demart.manufacturer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demart.manufacturer.entity.Manufacturer;


@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

	Manufacturer findByManufacturerId(long manufacturerId);

}
