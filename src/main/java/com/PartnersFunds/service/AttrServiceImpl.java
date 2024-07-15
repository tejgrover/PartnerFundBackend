package com.PartnersFunds.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PartnersFunds.Entities.pageAttrPropertiesEntity;
import com.PartnersFunds.Entities.pageAttributesEntity;
import com.PartnersFunds.Entities.pagesEntity;
import com.PartnersFunds.Repo.pageAttrPropertiesRepo;
import com.PartnersFunds.Repo.pageAttributesRepo;
import com.PartnersFunds.Repo.pagesRepo;


@Service
public class AttrServiceImpl implements AttrService{
	
<<<<<<< Updated upstream
//	@Autowired
//	AttrRepo repo;
//
//	@Override
//	public List<AttrEntity> getAttrDetails(){
//		return repo.findAll();
//	}
//
//	@Override
//	public String saveData(AttrEntity data) {
//		try {
//			repo.save(data);
//			System.out.println(data);
//			
//		}catch(Exception e) {
//			return "Getting error  : " + e;
//		}
//		return "";
//	}
//	
//	@Override
//	public List<AttrEntity> searchByType(String type){
//		try {
//			return repo.findByAttrType(type);
//		}catch(Exception e) {
//			System.out.println("Getting error  : " + e);
//		}
//		return null;
//	}
=======
	@Autowired
	pagesRepo Pagerepo;
	@Autowired
	pageAttributesRepo Attrrepo;
	@Autowired
	pageAttrPropertiesRepo AttrPropertiesrepo;
	
	public List<pagesEntity> getPages(){
		try {
			return Pagerepo.findAll();
		}catch(Exception e) {
			System.out.println("Exception Error "+e);
		}
		return null;
		
	}
	
	public List<pageAttributesEntity> getPageAttributes(){
		try {
			return Attrrepo.findAll();
		}catch(Exception e) {
			System.out.println("Exception Error "+e);
		}
		return null;
		
	}
	
	public List<pageAttrPropertiesEntity> getPageProperties(){
		try {
			return AttrPropertiesrepo.findAll();
		}catch(Exception e) {
			System.out.println("Exception Error "+e);
		}
		return null;
		
	}
>>>>>>> Stashed changes


}
