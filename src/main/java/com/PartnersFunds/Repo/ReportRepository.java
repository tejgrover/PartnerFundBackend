package com.PartnersFunds.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PartnersFunds.Entities.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
}