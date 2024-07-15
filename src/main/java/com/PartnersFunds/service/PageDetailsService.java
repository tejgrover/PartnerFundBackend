package com.PartnersFunds.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface PageDetailsService {
	
	public List<Object[]> getAllDetails();	
}
