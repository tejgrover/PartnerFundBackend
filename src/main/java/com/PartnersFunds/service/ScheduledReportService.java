package com.PartnersFunds.service;

import org.apache.commons.csv.CSVFormat;
import java.io.InputStream;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PartnersFunds.Entities.ScheduledReport;
import com.PartnersFunds.Repo.ScheduledReportRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScheduledReportService {

    @Autowired
    private ScheduledReportRepository scheduledReportRepository;

    public List<ScheduledReport> getAllReports() {
        return scheduledReportRepository.findAll();
    }

    public String ViewRequestTable(Long id) {
        String csvFilePath = "C:\\Users\\tegrover\\Downloads\\UploadExcel\\" + id + ".csv";

        try {
            // Read the CSV file and convert to JSON
            Map<String, Object> result = readCsvAndConvertToJson(csvFilePath);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);

        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
            throw new RuntimeException("Failed to read CSV file and convert to JSON", e);
        }
    }

    private Map<String, Object> readCsvAndConvertToJson(String filePath) throws IOException {
        Map<String, Object> result = new LinkedHashMap<>();
        List<Map<String, String>> data = new ArrayList<>();
        List<String> columns = new ArrayList<>();

        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + filePath);
        }

        try (CSVParser parser = new CSVParser(new FileReader(filePath), CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            // Extract header columns
            columns.addAll(parser.getHeaderMap().keySet());

            for (CSVRecord record : parser) {
                Map<String, String> rowData = new LinkedHashMap<>();
                for (String column : columns) {
                    rowData.put(column, record.get(column));
                }
                data.add(rowData);
            }
        }

        result.put("data", data);
        result.put("columns", columns);
        return result;
    }
    
    public byte[] getCsvData(Long id) throws IOException {
        String csvFilePath = "C:\\Users\\tegrover\\Downloads\\UploadExcel\\" + id + ".csv";
        File file = new File(csvFilePath);
        
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + csvFilePath);
        }

        try (InputStream in = new FileInputStream(file);
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

            return out.toByteArray();
        }
    }
}
