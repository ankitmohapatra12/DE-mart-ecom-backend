package com.seller.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seller.entity.Sellers;


@Repository
public interface SellerRepository extends JpaRepository<Sellers, Long> {

}
