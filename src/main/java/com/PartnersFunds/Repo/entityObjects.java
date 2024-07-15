package com.PartnersFunds.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface entityObjects extends JpaRepository<entityObjects, Integer> {

//	@Query(value="SELECT * FROM XXPF_PAGE_CONFIGS_ATTR p WHERE p.attr_type = :type",nativeQuery=true)
//	List<AttrEntity> findByAttrType(@Param("type") String type);
//	
}
