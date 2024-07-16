package com.PartnersFunds.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PartnersFunds.Entities.pagesEntity;

@Repository
public interface pagesRepo extends JpaRepository<pagesEntity, Integer>{

}
