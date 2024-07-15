package com.PartnersFunds.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
}
