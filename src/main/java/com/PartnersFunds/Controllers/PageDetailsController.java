package com.PartnersFunds.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PartnersFunds.Entities.pageAttrPropertiesEntity;
import com.PartnersFunds.Entities.pageAttributesEntity;
import com.PartnersFunds.Entities.pagesEntity;
import com.PartnersFunds.service.PageDetailsService;

@RestController
@RequestMapping("/page")
public class PageDetailsController {

	
	@Autowired
	PageDetailsService pageService;
	
	@GetMapping("/getAllPages")
	public List<Object[]> getDetails(){
		return pageService.getAllDetails();
	}
	
	@PostMapping("/addPage")
	public String addPage(@RequestBody pagesEntity page) {
	pageService.savePageDetails(page);
	return "Added Page details successfully";
	}

	@PostMapping("/addPageProperties")
	public String addPageProperties(@RequestBody pageAttributesEntity pageAttribute) {
	pageService.savePageAttributeDetails(pageAttribute);
	return "Added Page Properties details successfully";
	}

	@PostMapping("/addPageAttrProperties")
	public String addPage(@RequestBody pageAttrPropertiesEntity pageAttrProperties) {
	pageService.savePageAttributePropertiesDetails(pageAttrProperties);
	return "Added Page Attribute properties details successfully";
	}

	
}
