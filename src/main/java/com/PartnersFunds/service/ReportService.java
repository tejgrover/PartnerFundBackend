package com.PartnersFunds.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.PartnersFunds.Entities.Report;
import com.PartnersFunds.Entities.ReportParameter;
import com.PartnersFunds.Repo.ReportParameterRepository;
import com.PartnersFunds.Repo.ReportRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ReportParameterRepository reportParameterRepository;
    
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public Optional<Report> getReportById(Long reportId) {
        return reportRepository.findById(reportId);
    }

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public List<ReportParameter> getParametersByReportId(Long reportId) {
    	System.out.println(reportParameterRepository.findByReportId(reportId));
        return reportParameterRepository.findByReportId(reportId);
    }
    
    public List<Map<String, String>> getDefaultValue(Long parameterId, Map<String, Object> formData) {
        // Retrieve the default_sql based on parameterId
        String sql = reportParameterRepository.findByParameterId(parameterId)
                .map(reportParameter -> {
                    String defaultSql = reportParameter.getDefaultSql();
                    System.out.print("Original SQL: " + defaultSql);

                    // Replace each placeholder in the SQL with the corresponding value from formData
                    for (Map.Entry<String, Object> entry : formData.entrySet()) {
                        String placeholder = "$flex$." + entry.getKey();
                        String value = entry.getValue().toString();
                        defaultSql = defaultSql.replace(placeholder, value);
                    }

                    System.out.print("Modified SQL: " + defaultSql);

                    // Execute the SQL and return the result
                    return defaultSql;
                })
                .orElseThrow(() -> new IllegalArgumentException("Parameter not found"));

        // Execute the SQL and retrieve the result
        List<Map<String, String>> dataList = getQueryData(sql);

        // Log the key-value pairs for debugging
        for (Map<String, String> data : dataList) {
            System.out.println("Key: " + data.get("key") + ", Value: " + data.get("value"));
        }

        return dataList;
    };

    public List<Map<String, String>> getOptions(Long parameterId, @RequestBody Map<String, Object> formData) {
        // Retrieve the parameterWhereClause SQL based on parameterId
        String sql = reportParameterRepository.findByParameterId(parameterId)
                .map(reportParameter -> {
                    String optionsSql = reportParameter.getParameterWhereClause();
                    System.out.print("Original SQL: " + optionsSql);

                    // Replace each placeholder in the SQL with the corresponding value from formData
                    for (Map.Entry<String, Object> entry : formData.entrySet()) {
                        String placeholder = "$flex$." + entry.getKey();  // Assuming the placeholder follows this pattern
                        String value = entry.getValue().toString();
                        optionsSql = optionsSql.replace(placeholder, value);
                    }

                    System.out.print("Modified SQL: " + optionsSql);

                    // Return the modified SQL
                    return optionsSql;
                })
                .orElseThrow(() -> new IllegalArgumentException("Parameter not found"));

        // Execute the SQL and retrieve the options
        List<Map<String, String>> dataList = getQueryData(sql);

        // Log the key-value pairs for debugging
        for (Map<String, String> data : dataList) {
            System.out.println("Key: " + data.get("key") + ", Value: " + data.get("value"));
        }

        return dataList;
    }

    
    public List<Map<String, String>> getQueryData(String sql) {

        return jdbcTemplate.query(sql, new RowMapper<Map<String, String>>() {
            @Override
            public Map<String, String> mapRow(ResultSet rs, int rowNum) throws SQLException {
                Map<String, String> map = new HashMap<>();
                map.put("key", rs.getString("key"));
                map.put("value", rs.getString("value"));
                return map;
            }
        });
    }
    
    

    public Long saveReportAndParams(Map<String, Object> formData) {
        // Extract program ID
        Long programId = ((Number) formData.get("programId")).longValue();

        List<Map<String, Object>> parameters = null;
        
        // Type check to ensure the cast is safe
        Object parametersObj = formData.get("parameters");
        if (parametersObj instanceof List<?>) {
            parameters = ((List<?>) parametersObj).stream()
                        .filter(Map.class::isInstance)
                        .map(Map.class::cast)
                        .collect(Collectors.toList());
        } else {
            throw new IllegalArgumentException("Invalid data format for parameters");
        }

        // Generate new request_id from sequence
        String requestIdQuery = "SELECT XXPF_SCHEDULED_REPORTS_S.NEXTVAL FROM DUAL";
        Long requestId = jdbcTemplate.queryForObject(requestIdQuery, Long.class);

        // Insert into XXPF_SCHEDULED_REPORTS
        String insertReportQuery = "INSERT INTO XXPF_SCHEDULED_REPORTS (REQUEST_ID, REPORT_ID, PROGRAM_ID, STATUS_CODE, PHASE_CODE, REQUESTED_START_DATE, REQUESTED_BY, CREATION_DATE, LAST_UPDATE_DATE) " +
                                    "VALUES (?, ?, ?, ?, ?, SYSDATE, ?, SYSDATE, SYSDATE)";
        jdbcTemplate.update(insertReportQuery, requestId, programId, programId, "P", "R", 1111); // Adjust '1111' for REQUESTED_BY

        // Insert parameters into XXPF_REPORTING_REQUEST_PARAMS
        String insertParamQuery = "INSERT INTO XXPF_REPORTING_REQUEST_PARAMS (REQUEST_PARAM_ID, REQUEST_ID, PARAMETER_ID, PARAMETER_VALUE, PARAM_DISPLAY_VALUE) " +
                                   "VALUES (?, ?, ?, ?, ?)";
        for (Map<String, Object> param : parameters) {
            Long parameterId = ((Number) param.get("parameterId")).longValue();

            // Handle paramValue: join if it's a list, otherwise cast to String
            Object paramValueObj = param.get("paramValue");
            String paramValue;
            if (paramValueObj instanceof List<?>) {
                paramValue = String.join(", ", ((List<?>) paramValueObj).stream().map(Object::toString).collect(Collectors.toList()));
            } else {
                paramValue = paramValueObj != null ? paramValueObj.toString() : null;
            }

            // Handle displayName similarly
            Object displayNameObj = param.get("displayName");
            String displayValue;
            if (displayNameObj instanceof List<?>) {
                displayValue = String.join(", ", ((List<?>) displayNameObj).stream().map(Object::toString).collect(Collectors.toList()));
            } else {
                displayValue = displayNameObj != null ? displayNameObj.toString() : null;
            }

            // Generate new request_param_id from sequence
            String requestParamIdQuery = "SELECT XXPF_REPORTING_REQUEST_PARAMS_S.NEXTVAL FROM DUAL";
            Long requestParamId = jdbcTemplate.queryForObject(requestParamIdQuery, Long.class);

            jdbcTemplate.update(insertParamQuery, requestParamId, requestId, parameterId, paramValue, displayValue);
        }

        // Fetch reporting_query from XXPF_REPORTS
        String fetchReportQuery = "SELECT reporting_query FROM XXPF_REPORTS WHERE REPORT_ID = :programId";
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("programId", programId);
        String reportingQuery = namedParameterJdbcTemplate.queryForObject(fetchReportQuery, params, String.class);

        // Execute the reporting_query and get the result
        jdbcTemplate.query(reportingQuery, (ResultSet rs) -> {
            try {
                // Create CSV file with requestId as name
                String csvFilePath = "C:/Users/tegrover/Downloads/UploadExcel/" + requestId + ".csv";
                FileWriter fileWriter = new FileWriter(csvFilePath);

                // CSVPrinter from Apache Commons CSV to write data
                CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT);

                // Write the column headers
                int columnCount = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    csvPrinter.print(rs.getMetaData().getColumnName(i));
                }
                csvPrinter.println();

                // Write each row of data
                while (rs.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        csvPrinter.print(rs.getString(i));
                    }
                    csvPrinter.println();
                }

                // Close CSVPrinter and FileWriter
                csvPrinter.flush();
                csvPrinter.close();
            } catch (IOException | SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error writing CSV file", e);
            }
        });

        return requestId;
    }

    
    
//    
//    public Long saveReportAndParams(Map<String, Object> formData) {
//        // Extract program ID
//        Long programId = ((Number) formData.get("programId")).longValue();
//
//        List<Map<String, Object>> parameters = null;
//        
//        // Type check to ensure the cast is safe
//        Object parametersObj = formData.get("parameters");
//        if (parametersObj instanceof List<?>) {
//            parameters = ((List<?>) parametersObj).stream()
//                        .filter(Map.class::isInstance)
//                        .map(Map.class::cast)
//                        .collect(Collectors.toList());
//        } else {
//            throw new IllegalArgumentException("Invalid data format for parameters");
//        }
//
//        // Generate new request_id from sequence
//        String requestIdQuery = "SELECT XXPF_SCHEDULED_REPORTS_S.NEXTVAL FROM DUAL";
//        Long requestId = jdbcTemplate.queryForObject(requestIdQuery, Long.class);
//
//        // Insert into XXPF_SCHEDULED_REPORTS
//        String insertReportQuery = "INSERT INTO XXPF_SCHEDULED_REPORTS (REQUEST_ID, REPORT_ID, PROGRAM_ID, STATUS_CODE, PHASE_CODE, REQUESTED_START_DATE, REQUESTED_BY, CREATION_DATE, LAST_UPDATE_DATE) " +
//                                    "VALUES (?, ?, ?, ?, ?, SYSDATE, ?, SYSDATE, SYSDATE)";
//        jdbcTemplate.update(insertReportQuery, requestId, programId, programId, "P", "R", 1111); // Adjust '1111' for REQUESTED_BY
//
//        // Insert parameters into XXPF_REPORTING_REQUEST_PARAMS
//        String insertParamQuery = "INSERT INTO XXPF_REPORTING_REQUEST_PARAMS (REQUEST_PARAM_ID, REQUEST_ID, PARAMETER_ID, PARAMETER_VALUE, PARAM_DISPLAY_VALUE) " +
//                                   "VALUES (?, ?, ?, ?, ?)";
//        for (Map<String, Object> param : parameters) {
//            Long parameterId = ((Number) param.get("parameterId")).longValue();
//
//            // Handle paramValue: join if it's a list, otherwise cast to String
//            Object paramValueObj = param.get("paramValue");
//            String paramValue;
//            if (paramValueObj instanceof List<?>) {
//                paramValue = String.join(", ", ((List<?>) paramValueObj).stream().map(Object::toString).collect(Collectors.toList()));
//            } else {
//                paramValue = paramValueObj != null ? paramValueObj.toString() : null;
//            }
//
//            // Handle displayName similarly
//            Object displayNameObj = param.get("displayName");
//            String displayValue;
//            if (displayNameObj instanceof List<?>) {
//                displayValue = String.join(", ", ((List<?>) displayNameObj).stream().map(Object::toString).collect(Collectors.toList()));
//            } else {
//                displayValue = displayNameObj != null ? displayNameObj.toString() : null;
//            }
//
//            // Generate new request_param_id from sequence
//            String requestParamIdQuery = "SELECT XXPF_REPORTING_REQUEST_PARAMS_S.NEXTVAL FROM DUAL";
//            Long requestParamId = jdbcTemplate.queryForObject(requestParamIdQuery, Long.class);
//
//            jdbcTemplate.update(insertParamQuery, requestParamId, requestId, parameterId, paramValue, displayValue);
//        }
//
//        return requestId;
//    }
//

//    private Long generateRequestId() {
//        return jdbcTemplate.queryForObject("SELECT XXPF_SCHEDULED_REPORTS_SEQ.NEXTVAL FROM DUAL", Long.class);
//    }
//
//    private Long generateRequestParamId() {
//        return jdbcTemplate.queryForObject("SELECT XXPF_REPORTING_REQUEST_PARAMS_SEQ.NEXTVAL FROM DUAL", Long.class);
//    }
}