package com.PartnersFunds.Repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PartnersFunds.Entities.Report;
import com.PartnersFunds.Entities.ReportParameter;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportParameterRepository extends JpaRepository<ReportParameter, Long> {
    List<ReportParameter> findByReportId(Long reportId);
    
    Optional<ReportParameter> findByParameterId(Long parameterId);
    
    
}