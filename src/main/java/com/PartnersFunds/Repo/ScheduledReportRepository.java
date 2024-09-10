package com.PartnersFunds.Repo;


import com.PartnersFunds.Entities.ScheduledReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduledReportRepository extends JpaRepository<ScheduledReport, Long> {
}
