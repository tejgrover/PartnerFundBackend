package com.PartnersFunds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PartnersFunds.Entities.AttrEntity;
import com.PartnersFunds.Repo.AttrRepo;

@Service
public class AttrServiceImpl implements AttrService{
	
	@Autowired
	AttrRepo repo;

	@Override
	public List<AttrEntity> getAttrDetails(){
		return repo.findAll();
	}

	@Override
	public String saveData(AttrEntity data) {
		try {
			repo.save(data);
			System.out.println(data);
			
		}catch(Exception e) {
			return "Getting error  : " + e;
		}
		return "";
	}
	
	@Override
	public List<AttrEntity> searchByType(String type){
		try {
			return repo.findByAttrType(type);
		}catch(Exception e) {
			System.out.println("Getting error  : " + e);
		}
		return null;
	}

}
