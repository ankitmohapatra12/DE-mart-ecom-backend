package com.demart.manufacturer.servicesImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.demart.manufacturer.entity.Manufacturer;
import com.demart.manufacturer.entity.ManufacturerImages;
import com.demart.manufacturer.repositories.ManufacturerRepository;
import com.demart.manufacturer.services.ManufacturerService;





@Service
public class ManufacturerServiceImpl implements ManufacturerService {
	
	@Autowired
	private ManufacturerRepository manufactuererRepository;
	
	@Value("${file.demart.manufacturer}")
	private String manufacturerPdfPath;
	
	
	@Override
	public Manufacturer addManufacturers(Manufacturer manufacturer,MultipartFile[] images) throws Exception {
		// TODO Auto-generated method stub
		
		String imgName = "";
		
		try {
			
			manufacturer = convertAndStoreDataInFileSystem(manufacturerPdfPath,images,manufacturer,imgName);
			manufacturer =  manufactuererRepository.saveAndFlush(manufacturer);
		}catch (Exception e) {
			System.out.println("addManufacturers() : ManufacturerServiceImpl ::"+e);
			throw new Exception("Failed to add manufacturer to database !");
		}
		return manufacturer;
	}
	
	
	public Manufacturer convertAndStoreDataInFileSystem(String path,MultipartFile[] images,Manufacturer manufacturer,String imgName) throws IOException {
		boolean flag= true;
		File destPath = null;
		String extension = "";
		//String manufacturerPdfName="";
		
		List<ManufacturerImages> pdfList = new ArrayList<>();
		
		if(images.length!=0) {
			
			
				//User user = libraryService.addBook(book)
				destPath=new File(path+File.separator+manufacturer.getManufacturerName());
				if(!destPath.exists()){
					destPath.mkdirs();
				}
				int ct = 1;
				for(MultipartFile image : images) {
					ManufacturerImages manufacturerPdf =  new ManufacturerImages();
					extension=getFileExtension(image.getOriginalFilename());
					//System.out.println(extension);
					imgName = manufacturer.getManufacturerName()+"_"+ct+"."+extension;  /* imgName provided as function parameter*/
					//System.out.println(subCategoryImagename);
					FileCopyUtils.copy(image.getBytes(),new File(destPath + File.separator + imgName));
					ct++;
					manufacturerPdf.setImageName(imgName);;
					manufacturerPdf.setManufacturer(manufacturer);
					manufacturerPdf.setImageSize(image.getSize());
					manufacturerPdf.setUploadDate(new Date());
					
					pdfList.add(manufacturerPdf);
					
					
					
				}
				
				manufacturer.setAuthenticationProofImages(pdfList);
			    
//				categoryImageService.addAllCategoryImages(categoryImageList);
				
				
		} 
		
		
		
		return manufacturer;
	}

	
	public static String getFileExtension(String fileName) {
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            // Extract the substring after the last dot
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            // No file extension found
            return "";
        }
    }
	

	@Override
	public List<Manufacturer> viewManufacturers() throws Exception {
		// TODO Auto-generated method stub
		List<Manufacturer> manufacturers = new ArrayList<Manufacturer>();
		try {
			manufacturers = manufactuererRepository.findAll();
			
			manufacturers = setPdfData(manufacturers);
		}catch (Exception e) {
			System.out.println("viewManufacturers() : ManufacturerServiceImpl :: Failed to fetch sellers");
			throw new Exception("Unable to fetch sellers !!");
		}
		
		
		return manufacturers;
	}
	
	
	public List<Manufacturer> setPdfData(List<Manufacturer> manufacturers) throws Exception{
		
		String fileSize="";
		
		
		if(!manufacturers.isEmpty()) {
			for(Manufacturer manufacturer : manufacturers) {
				File path = new File(manufacturerPdfPath+File.separator+manufacturer.getManufacturerName());
				List<ManufacturerImages> images = new ArrayList<>();
							//JSONObject jsonObj =  new JSONObject();
				
				if(path.exists()) {
					File files[]  =  path.listFiles(File::isFile);
					
					
					for(File file : files) {
						fileSize = String.valueOf(file.length()/1024);
						ManufacturerImages image = new ManufacturerImages();
						image.setImageSize(Integer.parseInt(fileSize));
						String imgUrl = file.toString();
					    
						String extension = imgUrl.substring(imgUrl.lastIndexOf('.') + 1);
						if(file.exists()) {
							String  extnValue = "application/pdf";
							String encodstring = encodeFileToBase64Binary(file);
							String base64Image = "data:" + extnValue + ";base64," + encodstring;
							image.setImgUrl(base64Image);
							image.setImageName(file.getName());
						
						}
						
						images.add(image);
					}
					
					
					
					
					
				}
				manufacturer.setAuthenticationProofImages(images);
				images = null;
				
			}
			
			
		}
		
		return manufacturers;
	}
	
	private static String encodeFileToBase64Binary(File file) throws Exception {
		FileInputStream fileInputStreamReader = new FileInputStream(file);
		byte[] bytes = new byte[(int) file.length()];
		fileInputStreamReader.read(bytes);
		fileInputStreamReader.close();
		return new String(java.util.Base64.getEncoder().encodeToString(bytes));
	}


	@Override
	public Manufacturer viewManufacturersById(long manufacturerId) {
		// TODO Auto-generated method stub
		return manufactuererRepository.findByManufacturerId(manufacturerId);
	}


}
