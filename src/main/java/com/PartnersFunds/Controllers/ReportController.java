package com.PartnersFunds.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PartnersFunds.Entities.Report;
import com.PartnersFunds.Entities.ReportParameter;
import com.PartnersFunds.service.ReportService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "http://localhost:3000")
public class ReportController {

    @Autowired
    private ReportService reportService;
    
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
    
    
    @GetMapping()
    public Map<String, Object> getAllReportDetails() {
        Map<String, Object> response = new HashMap<>();

        // Fetch all reports
        List<Report> reports = reportService.getAllReports();

        // Prepare the main response structure
        Map<String, List<Map<String, Object>>> programDetailsListMap = new HashMap<>();
        Map<String, List<Map<String, Object>>> progParamsListMap = new HashMap<>();

        for (Report report : reports) {
            // Fetch report parameters
            List<ReportParameter> parameters = reportService.getParametersByReportId(report.getReportId());

            // Construct the program details map for each report
            Map<String, Object> programDetails = new HashMap<>();
            programDetails.put("reportId", report.getReportId());
//            programDetails.put("requestGroupId", report.getRequestId());
            programDetails.put("reportName", report.getReportName());
            programDetails.put("incortaPublishEnabled", report.getEnabledFlag());

            // Group program details by request group ID
            programDetailsListMap.computeIfAbsent(String.valueOf(report.getRequestId()), k -> new java.util.ArrayList<>()).add(programDetails);

            // Construct the parameter details for each report
            List<Map<String, Object>> parameterDetails = parameters.stream().map(parameter -> {
                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("reportId", parameter.getReportId());
                paramMap.put("parameterId", parameter.getParameterId());
                paramMap.put("parameterName", parameter.getParameterName());
                paramMap.put("displayName", parameter.getDisplayName());
                paramMap.put("parameterDataType", parameter.getParameterDataType());
                paramMap.put("displaySeq", parameter.getDisplaySeq());
                paramMap.put("enabledFlag", parameter.getEnabledFlag());
                paramMap.put("paramWhereClause", parameter.getParameterWhereClause());
                paramMap.put("paramKey", null); // As per example, keeping paramKey as null
                paramMap.put("paramValue", null); // As per example, keeping paramValue as null
                paramMap.put("requiredFlag", parameter.getRequiredFlag());
                paramMap.put("multiSelectFlag", parameter.getMultiSelectEnabled());
                if(parameter.getParameterWhereClause() != null) {
                	paramMap.put("dependentFlag", getDependentFlag(parameter.getParameterWhereClause()));
                }
                paramMap.put("displayedFlag", parameter.getDisplayedFlag());
                paramMap.put("defaultSql", parameter.getDefaultSql());
                paramMap.put("enableCondition", parameter.getEnableCondition());
                paramMap.put("defaultDependentFlag", "N");
                paramMap.put("enableDependentFlag", "N");
                paramMap.put("toolTip", parameter.getToolTip());
                paramMap.put("listFlag", parameter.getListFlag());
                return paramMap;
            }).collect(Collectors.toList());

            // Group parameter details by report ID
            progParamsListMap.put(String.valueOf(report.getReportId()), parameterDetails);
        }

        // Construct the final response
        Map<String, Object> reqGrpProgParams = new HashMap<>();
        reqGrpProgParams.put("progParamsListMap", progParamsListMap);
        reqGrpProgParams.put("programDetailsListMap", programDetailsListMap);

        response.put("errorMessage", "");
        response.put("reqGrpProgParams", reqGrpProgParams);

        return response;
    }
    
    @PostMapping("/{parameterId}/default-value")
    public List<Map<String, String>> getDefaultValue(@PathVariable Long parameterId, @RequestBody Map<String, Object> formData){
        // Process the formData as needed
        return reportService.getDefaultValue(parameterId, formData);
    }
    
    @PostMapping("/submit")
    public Map<String, Object> submitReport(@RequestBody Map<String, Object> formData) {
        Map<String, Object> response = new HashMap<>();
        try {
            Long requestId = reportService.saveReportAndParams(formData);
            response.put("requestId", requestId);
            response.put("message", "Report submitted successfully");
        } catch (Exception e) {
            response.put("message", "Error submitting report: " + e.getMessage());
        }
        return response;
    }


    
    @PostMapping("/{parameterId}/options")
    public List<Map<String, String>> getoptions(@PathVariable Long parameterId, @RequestBody Map<String, Object> formData) {
        return reportService.getOptions(parameterId, formData);
    }
    
    public String getDependentFlag(String paramWhereClause) {
        System.out.println("paramWhereClause  ===> " + paramWhereClause);
        
        if (paramWhereClause != null && paramWhereClause.contains("$flex$")) {
            System.out.println("queryResult ===> $FLEX$ found in the query");
            return "Y";
        }
        System.out.println("queryResult ===> $FLEX$ not found in the query");
        return "N";
    }


}