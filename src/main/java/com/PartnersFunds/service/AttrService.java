package com.PartnersFunds.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.PartnersFunds.Entities.AttrEntity;

@Service
public interface AttrService {

	public List<AttrEntity> getAttrDetails();
	public String saveData(AttrEntity data);
	public List<AttrEntity> searchByType(String type);
}
