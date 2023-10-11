package com.seller.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.seller.entity.Sellers;



@Service
public interface SellerService {

	Sellers addSeller(Sellers seller) throws Exception;

	List<Sellers> viewSellers() throws Exception;

}
