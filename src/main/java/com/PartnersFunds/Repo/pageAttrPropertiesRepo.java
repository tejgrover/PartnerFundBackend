package com.PartnersFunds.Repo;

import com.PartnersFunds.Entities.pageAttrPropertiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface pageAttrPropertiesRepo extends JpaRepository<pageAttrPropertiesEntity, Integer> {
   @Query(
      value = "select property_value from xxpf_page_attr_properties where property_name = 'ONCLICK' AND attribute_id = :attr_id ",
      nativeQuery = true
   )
   String dBFuncName(@Param("attr_id") int attr_id);
}
