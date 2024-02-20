package com.ldtech.controllers;

import com.ldtech.dtos.DateRangeDTO;
import com.ldtech.entities.TimesheetEntry;
import com.ldtech.payloads.EmployeeDashboardResponse;
import com.ldtech.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    // manager dashboard (by default for current week )
    // http://localhost:8080/api/dashboard
    @GetMapping
    public ResponseEntity<List<EmployeeDashboardResponse>> getManagerDashboard(){
        List<EmployeeDashboardResponse> responses = dashboardService.getDashboard();
        return ResponseEntity.ok(responses);
    }


    // search by employeeId
    // http://localhost:8080/api/dashboard/id/L000150
    @GetMapping("/id/{employeeId}")
    public ResponseEntity<List<EmployeeDashboardResponse>> searchByEmployeeId(@PathVariable String employeeId, @RequestBody DateRangeDTO dateRangeDTO){
        List<EmployeeDashboardResponse> responses = dashboardService.searchByEmployeeId(employeeId, dateRangeDTO);
        return ResponseEntity.ok(responses);
    }

    // search by employeeName
    // http://localhost:8080/api/dashboard/name/L000150
    @GetMapping("/name/{employeeName}")
    public ResponseEntity<List<EmployeeDashboardResponse>> searchByEmployeeName(@PathVariable String employeeName, @RequestBody DateRangeDTO dateRangeDTO){
        List<EmployeeDashboardResponse> responses = dashboardService.searchByEmployeeName(employeeName, dateRangeDTO);
        return ResponseEntity.ok(responses);
    }
}
