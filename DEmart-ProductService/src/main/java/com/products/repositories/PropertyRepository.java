package com.products.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.products.entity.Property;
import com.products.entity.Product;


@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

	List<Property> findByProductProperty(Product productId);

}
