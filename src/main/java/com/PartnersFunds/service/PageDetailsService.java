package com.PartnersFunds.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.PartnersFunds.Entities.pageAttrPropertiesEntity;
import com.PartnersFunds.Entities.pageAttributesEntity;
import com.PartnersFunds.Entities.pagesEntity;

@Service
public interface PageDetailsService {
	
	public List<Object[]> getAllDetails();
	public String savePageAttributeDetails(pageAttributesEntity pageAttribute);
	public String savePageDetails(pagesEntity page);
	public String savePageAttributePropertiesDetails(pageAttrPropertiesEntity pageAttrProperties);
}
