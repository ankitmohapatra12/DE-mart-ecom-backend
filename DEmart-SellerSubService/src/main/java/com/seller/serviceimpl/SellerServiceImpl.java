package com.seller.serviceimpl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.seller.entity.SellerImages;
import com.seller.entity.Sellers;
import com.seller.repository.SellerRepository;
import com.seller.service.SellerService;






@Service
public class SellerServiceImpl implements SellerService {
	
	@Autowired
	private SellerRepository sellerRepository;
	
	
	@Value("${file.seller.images}")
	private String sellerImagePath;
	
	
	@Value("${file.seller.imageData}")
	private String sellerImageDataPath;
	
	
	@Override
	public Sellers addSeller(Sellers seller) throws Exception {
		// TODO Auto-generated method stub
		
		try {
			seller =  sellerRepository.saveAndFlush(seller);
		}catch (Exception e) {
			System.out.println("addSeller() : SellerServiceImpl"+e);
			throw new Exception("Failed to add seller to database !");
		}
		return seller;
	}


	@Override
	public List<Sellers> viewSellers() throws Exception {
		// TODO Auto-generated method stub
		List<Sellers> sellers = new ArrayList<Sellers>();
		try {
			String fileSize = "";
			sellers = sellerRepository.findAll();
			
			if(!sellers.isEmpty()) {
				for(Sellers seller : sellers) {
					
					File path2 = new File(sellerImageDataPath+File.separator+File.separator+seller.getSellerName());
					File path = new File(sellerImagePath+File.separator+File.separator+seller.getSellerName());
					
					List<SellerImages> sellerDataImages = new ArrayList<>();
//					sellerDataImages = seller.getImages().stream().filter(x -> x.getSellerPictureType().equals("Data")).toList();
//								//JSONObject jsonObj =  new JSONObject();
//					
//					SellerImages sellerProfileImage = seller.getImages().stream().filter(x -> x.getSellerPictureType().equals("B")).toList().get(0);

					
					if(path.exists()) {
						File files[]  =  path.listFiles(File::isFile);
						
						
						for(File file : files) {
							
							fileSize = String.valueOf(file.length()/1024);
							SellerImages image = new SellerImages();
							image.setImageSize(Integer.parseInt(fileSize));
							String imgUrl = file.toString();
						    
							String extension = imgUrl.substring(imgUrl.lastIndexOf('.') + 1);
							if(file.exists()) {
								String  extnValue = "image/" + extension;
								String encodstring = encodeFileToBase64Binary(file);
								String base64Image = "data:" + extnValue + ";base64," + encodstring;
								image.setImgUrl(base64Image);
								image.setSellerImageName(file.getName());
							
							}
							
							sellerDataImages.add(image);
						}

					}
					
					
					if(path2.exists()) {
						File files[]  =  path2.listFiles(File::isFile);
						
						
						File file = files[0];
							
							fileSize = String.valueOf(file.length()/1024);
							SellerImages image = new SellerImages();
							image.setImageSize(Integer.parseInt(fileSize));
							String imgUrl = file.toString();
						    
							String extension = imgUrl.substring(imgUrl.lastIndexOf('.') + 1);
							if(file.exists()) {
								String  extnValue = "image/" + extension;
								String encodstring = encodeFileToBase64Binary(file);
								String base64Image = "data:" + extnValue + ";base64," + encodstring;
								image.setImgUrl(base64Image);
								image.setSellerImageName(file.getName());
							
							}
							
							sellerDataImages.add(image);
						

					}
					seller.setImages(sellerDataImages);
					sellerDataImages = null;
					
				}
				
				
			}
			
			
		}catch (Exception e) {
			System.out.println("viewSellers() : SellerServiceImpl :: Failed to fetch sellers");
			throw new Exception("Unable to fetch sellers !!");
		}
		
		
		return sellers;
	}
	
	
	
	@Override
	public Sellers getSeller(String sellerEmail) throws Exception {
		// TODO Auto-generated method stub
		Sellers seller = new Sellers();
		try {
			String fileSize = "";
			seller = sellerRepository.findBySellerEmail(sellerEmail);
			
			if(seller!=null) {
				
					
					File path2 = new File(sellerImageDataPath+File.separator+File.separator+seller.getSellerName());
					File path = new File(sellerImagePath+File.separator+File.separator+seller.getSellerName());
					
					List<SellerImages> sellerDataImages = new ArrayList<>();
//					sellerDataImages = seller.getImages().stream().filter(x -> x.getSellerPictureType().equals("Data")).toList();
//								//JSONObject jsonObj =  new JSONObject();
//					
//					SellerImages sellerProfileImage = seller.getImages().stream().filter(x -> x.getSellerPictureType().equals("B")).toList().get(0);

					
					if(path.exists()) {
						File files[]  =  path.listFiles(File::isFile);
						
						
						for(File file : files) {
							
							fileSize = String.valueOf(file.length()/1024);
							SellerImages image = new SellerImages();
							image.setImageSize(Integer.parseInt(fileSize));
							String imgUrl = file.toString();
						    
							String extension = imgUrl.substring(imgUrl.lastIndexOf('.') + 1);
							if(file.exists()) {
								String  extnValue = "image/" + extension;
								String encodstring = encodeFileToBase64Binary(file);
								String base64Image = "data:" + extnValue + ";base64," + encodstring;
								image.setImgUrl(base64Image);
								image.setSellerImageName(file.getName());
							
							}
							
							sellerDataImages.add(image);
						}

					}
					
					
					if(path2.exists()) {
						File files[]  =  path2.listFiles(File::isFile);
						
						
						File file = files[0];
							
							fileSize = String.valueOf(file.length()/1024);
							SellerImages image = new SellerImages();
							image.setImageSize(Integer.parseInt(fileSize));
							String imgUrl = file.toString();
						    
							String extension = imgUrl.substring(imgUrl.lastIndexOf('.') + 1);
							if(file.exists()) {
								String  extnValue = "image/" + extension;
								String encodstring = encodeFileToBase64Binary(file);
								String base64Image = "data:" + extnValue + ";base64," + encodstring;
								image.setImgUrl(base64Image);
								image.setSellerImageName(file.getName());
							
							}
							
							sellerDataImages.add(image);
						

					}
					seller.setImages(sellerDataImages);
					sellerDataImages = null;
					
				}
				
				
		
			
			
		}catch (Exception e) {
			System.out.println("viewSellers() : SellerServiceImpl :: Failed to fetch sellers");
			throw new Exception("Unable to fetch sellers !!");
		}
		
		
		return seller;
	}
	
	
	
	private static String encodeFileToBase64Binary(File file) throws Exception {
		FileInputStream fileInputStreamReader = new FileInputStream(file);
		byte[] bytes = new byte[(int) file.length()];
		fileInputStreamReader.read(bytes);
		fileInputStreamReader.close();
		return new String(java.util.Base64.getEncoder().encodeToString(bytes));
	}


	@Override
	public Sellers getSellerById(long sellerId) throws Exception {
		Sellers seller = new Sellers();
		try {
			String fileSize = "";
			seller = sellerRepository.findBySellerId(sellerId);
			
			if(seller!=null) {
				
					
					File path2 = new File(sellerImageDataPath+File.separator+File.separator+seller.getSellerName());
					File path = new File(sellerImagePath+File.separator+File.separator+seller.getSellerName());
					
					List<SellerImages> sellerDataImages = new ArrayList<>();
//					sellerDataImages = seller.getImages().stream().filter(x -> x.getSellerPictureType().equals("Data")).toList();
//								//JSONObject jsonObj =  new JSONObject();
//					
//					SellerImages sellerProfileImage = seller.getImages().stream().filter(x -> x.getSellerPictureType().equals("B")).toList().get(0);

					
					if(path.exists()) {
						File files[]  =  path.listFiles(File::isFile);
						
						
						for(File file : files) {
							
							fileSize = String.valueOf(file.length()/1024);
							SellerImages image = new SellerImages();
							image.setImageSize(Integer.parseInt(fileSize));
							String imgUrl = file.toString();
						    
							String extension = imgUrl.substring(imgUrl.lastIndexOf('.') + 1);
							if(file.exists()) {
								String  extnValue = "image/" + extension;
								String encodstring = encodeFileToBase64Binary(file);
								String base64Image = "data:" + extnValue + ";base64," + encodstring;
								image.setImgUrl(base64Image);
								image.setSellerImageName(file.getName());
							
							}
							
							sellerDataImages.add(image);
						}

					}
					
					
					if(path2.exists()) {
						File files[]  =  path2.listFiles(File::isFile);
						
						
						File file = files[0];
							
							fileSize = String.valueOf(file.length()/1024);
							SellerImages image = new SellerImages();
							image.setImageSize(Integer.parseInt(fileSize));
							String imgUrl = file.toString();
						    
							String extension = imgUrl.substring(imgUrl.lastIndexOf('.') + 1);
							if(file.exists()) {
								String  extnValue = "image/" + extension;
								String encodstring = encodeFileToBase64Binary(file);
								String base64Image = "data:" + extnValue + ";base64," + encodstring;
								image.setImgUrl(base64Image);
								image.setSellerImageName(file.getName());
							
							}
							
							sellerDataImages.add(image);
						

					}
					seller.setImages(sellerDataImages);
					sellerDataImages = null;
					
				}
				
				
		
			
			
		}catch (Exception e) {
			System.out.println("viewSellers() : SellerServiceImpl :: Failed to fetch sellers");
			throw new Exception("Unable to fetch sellers !!");
		}
		
		
		return seller;
	}

}
