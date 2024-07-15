package com.PartnersFunds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PartnersFunds.Repo.PageDetailsRepo;

@Service
public class PageDetailsServiceImpl implements  PageDetailsService{

	
	@Autowired
	PageDetailsRepo pageRepo;
	
	@Override
	public List<Object[]> getAllDetails() {
		
		return pageRepo.allDetails();
	}

	
}
