package com.PartnersFunds.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PartnersFunds.Entities.pageAttributesEntity;

@Repository
public interface pageAttributesRepo extends JpaRepository<pageAttributesEntity, Integer> {

}
