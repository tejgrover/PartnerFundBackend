package com.PartnersFunds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PartnersFunds.Entities.pageAttrPropertiesEntity;
import com.PartnersFunds.Entities.pageAttributesEntity;
import com.PartnersFunds.Entities.pagesEntity;
import com.PartnersFunds.Repo.PageDetailsRepo;
import com.PartnersFunds.Repo.pageAttrPropertiesRepo;
import com.PartnersFunds.Repo.pageAttributesRepo;
import com.PartnersFunds.Repo.pagesRepo;

@Service
public class PageDetailsServiceImpl implements  PageDetailsService{

	
	@Autowired
	PageDetailsRepo pageRepo;
	
	@Autowired
	pageAttributesRepo pageAttributeRepo;
	
	@Autowired
	pageAttrPropertiesRepo pageAttrPropertiesRepo;
	
	@Autowired
	pagesRepo pagesRepo;
	
	@Override
	public List<Object[]> getAllDetails() {
		
		return pageRepo.allDetails();
	}

	@Override
	public Integer savePageDetails(pagesEntity page) {
		 try {
	            pagesEntity savedData = pageRepo.save(page);
	            System.out.println(savedData.getPage_id());
//	            return savedData;
	            return savedData.getPage_id();
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	}

	@Override
	public String savePageAttributeDetails(pageAttributesEntity pageAttribute) {

	try {
	pageAttributeRepo.save(pageAttribute);

	}catch(Exception e) {
	return "Error : " + e ;
	}
	return null;
	}

	@Override
	public String savePageAttributePropertiesDetails(List<pageAttrPropertiesEntity> pageAttrPropertiesList) {


	try {
	for (pageAttrPropertiesEntity pageAttrProperties : pageAttrPropertiesList) {
	pageAttrPropertiesRepo.save(pageAttrProperties);
	}
	} catch (Exception e) {
	return "Error: " + e.getMessage();
	}
	return "Success";
	}

	
	
}
