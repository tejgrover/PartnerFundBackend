package com.PartnersFunds.Entities;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "xxpf_reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REPORT_ID")
    private Long reportId;

    @Column(name = "REPORT_NAME")
    private String reportName;

    @Column(name = "REPORT_SHORT_NAME")
    private String reportShortName;

    @Column(name = "ENABLED_FLAG")
    private String enabledFlag;

    @Column(name = "REPORTING_TABLE_NAME")
    private String reportingTableName;

    @Lob
    @Column(name = "REPORTING_QUERY")
    private String reportingQuery;

    @Column(name = "EXECUTION_TYPE")
    private String executionType;

    @Column(name = "EXECUTION_FILE_NAME")
    private String executionFileName;

    @Lob
    @Column(name = "DISPLAY_COLUMN_NAME")
    private String displayColumnName;

    @Column(name = "PURGE_FREQUENCY_DAYS")
    private Long purgeFrequencyDays;

    @Column(name = "DELETE_FLAG")
    private String deleteFlag;

    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "LAST_UPDATE_DATE")
    private Date lastUpdateDate;

    @Column(name = "LAST_UPDATED_BY")
    private String lastUpdatedBy;

    @Column(name = "REQUEST_ID")
    private Long requestId;

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getReportShortName() {
		return reportShortName;
	}

	public void setReportShortName(String reportShortName) {
		this.reportShortName = reportShortName;
	}

	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

	public String getReportingTableName() {
		return reportingTableName;
	}

	public void setReportingTableName(String reportingTableName) {
		this.reportingTableName = reportingTableName;
	}

	public String getReportingQuery() {
		return reportingQuery;
	}

	public void setReportingQuery(String reportingQuery) {
		this.reportingQuery = reportingQuery;
	}

	public String getExecutionType() {
		return executionType;
	}

	public void setExecutionType(String executionType) {
		this.executionType = executionType;
	}

	public String getExecutionFileName() {
		return executionFileName;
	}

	public void setExecutionFileName(String executionFileName) {
		this.executionFileName = executionFileName;
	}

	public String getDisplayColumnName() {
		return displayColumnName;
	}

	public void setDisplayColumnName(String displayColumnName) {
		this.displayColumnName = displayColumnName;
	}

	public Long getPurgeFrequencyDays() {
		return purgeFrequencyDays;
	}

	public void setPurgeFrequencyDays(Long purgeFrequencyDays) {
		this.purgeFrequencyDays = purgeFrequencyDays;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}
    
}