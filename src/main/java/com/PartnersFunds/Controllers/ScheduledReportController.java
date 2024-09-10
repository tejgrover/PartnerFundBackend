package com.PartnersFunds.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.PartnersFunds.Entities.ScheduledReport;
import com.PartnersFunds.service.ScheduledReportService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/viewrequests")
public class ScheduledReportController {

    @Autowired
    private ScheduledReportService scheduledReportService;

    @GetMapping
    public List<ScheduledReport> getAllReports() {
        return scheduledReportService.getAllReports();
    }

    @GetMapping("/{id}")
    public String getReportById(@PathVariable Long id) {
        return scheduledReportService.ViewRequestTable(id);
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadCsv(@RequestParam("requestId") Long requestId) {
        try {
            byte[] csvData = scheduledReportService.getCsvData(requestId);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "text/csv");
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + requestId + ".csv\"");

            return new ResponseEntity<>(csvData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
