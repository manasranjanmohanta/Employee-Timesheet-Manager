package com.ldtech.controllers;

import com.ldtech.dtos.DateRangeDTO;
import com.ldtech.entities.TimesheetEntry;
import com.ldtech.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadEmployeesXLS(@RequestBody DateRangeDTO dateRangeDTO) {

        List<TimesheetEntry> timesheetList = reportService.getEmployeeData(dateRangeDTO);
//        return ResponseEntity.ok(timesheetList);
        // Generate XLS file using Apache POI
        byte[] xlsData = reportService.generateXLS(timesheetList);

        ByteArrayResource resource = new ByteArrayResource(xlsData);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=employee.xls")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(xlsData.length)
                .body(resource);
    }


}
