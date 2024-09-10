package com.PartnersFunds.Entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "xxpf_report_parameters")
public class ReportParameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PARAMETER_ID")
    private Long parameterId;

    @Column(name = "REPORT_ID")
    private Long reportId;

    @Column(name = "PARAMETER_NAME")
    private String parameterName;

    @Column(name = "DISPLAY_NAME")
    private String displayName;

    @Column(name = "PARAMETER_DATA_TYPE")
    private String parameterDataType;

    @Column(name = "DISPLAY_SEQ")
    private Long displaySeq;

    @Column(name = "ENABLED_FLAG")
    private String enabledFlag;

    @Column(name = "LIST_FLAG")
    private String listFlag;

    @Lob
    @Column(name = "PARAMETER_WHERE_CLAUSE")
    private String parameterWhereClause;

    @Column(name = "REQUIRED_FLAG")
    private String requiredFlag;

    @Column(name = "MULTI_SELECT_ENABLED")
    private String multiSelectEnabled;

    @Column(name = "DISPLAYED_FLAG")
    private String displayedFlag;

    @Lob
    @Column(name = "DEFAULT_SQL")
    private String defaultSql;

    @Lob
    @Column(name = "ENABLE_CONDITION")
    private String enableCondition;

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

    public Long getParameterId() {
		return parameterId;
	}

	public void setParameterId(Long parameterId) {
		this.parameterId = parameterId;
	}

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getParameterDataType() {
		return parameterDataType;
	}

	public void setParameterDataType(String parameterDataType) {
		this.parameterDataType = parameterDataType;
	}

	public Long getDisplaySeq() {
		return displaySeq;
	}

	public void setDisplaySeq(Long displaySeq) {
		this.displaySeq = displaySeq;
	}

	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

	public String getListFlag() {
		return listFlag;
	}

	public void setListFlag(String listFlag) {
		this.listFlag = listFlag;
	}

	public String getParameterWhereClause() {
		return parameterWhereClause;
	}

	public void setParameterWhereClause(String parameterWhereClause) {
		this.parameterWhereClause = parameterWhereClause;
	}

	public String getRequiredFlag() {
		return requiredFlag;
	}

	public void setRequiredFlag(String requiredFlag) {
		this.requiredFlag = requiredFlag;
	}

	public String getMultiSelectEnabled() {
		return multiSelectEnabled;
	}

	public void setMultiSelectEnabled(String multiSelectEnabled) {
		this.multiSelectEnabled = multiSelectEnabled;
	}

	public String getDisplayedFlag() {
		return displayedFlag;
	}

	public void setDisplayedFlag(String displayedFlag) {
		this.displayedFlag = displayedFlag;
	}

	public String getDefaultSql() {
		return defaultSql;
	}

	public void setDefaultSql(String defaultSql) {
		this.defaultSql = defaultSql;
	}

	public String getEnableCondition() {
		return enableCondition;
	}

	public void setEnableCondition(String enableCondition) {
		this.enableCondition = enableCondition;
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

	public String getToolTip() {
		return toolTip;
	}

	public void setToolTip(String toolTip) {
		this.toolTip = toolTip;
	}


    @Column(name = "TOOLTIP")
    private String toolTip;

    // Getters and setters
    // ...
}