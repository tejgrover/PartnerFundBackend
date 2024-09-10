package com.PartnersFunds.Entities;


import jakarta.persistence.*;

@Entity
@Table(name = "XXPF_SCHEDULED_REPORTS")
public class ScheduledReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REQUEST_ID")
    private Long requestId;

    @Column(name = "PROGRAM_ID")
    private String programName;

    @Column(name = "STATUS_CODE")
    private String status;

    @Column(name = "PHASE_CODE")
    private String phase;

    @Column(name = "REQUESTED_START_DATE")
    private String requestedStartDate;

    @Column(name = "ACTUAL_COMPLETION_DATE")
    private String actualCompletionDate;

    @Column(name = "REQUESTED_BY")
    private String requestedBy;

    // Getters and Setters

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getRequestedStartDate() {
        return requestedStartDate;
    }

    public void setRequestedStartDate(String requestedStartDate) {
        this.requestedStartDate = requestedStartDate;
    }

    public String getActualCompletionDate() {
        return actualCompletionDate;
    }

    public void setActualCompletionDate(String actualCompletionDate) {
        this.actualCompletionDate = actualCompletionDate;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }
}
