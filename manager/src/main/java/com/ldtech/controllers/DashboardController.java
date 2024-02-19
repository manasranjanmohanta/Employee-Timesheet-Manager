package com.ldtech.controllers;

import com.ldtech.entities.TimesheetEntry;
import com.ldtech.payloads.EmployeeDashboardResponse;
import com.ldtech.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    // manager dashboard
    @GetMapping
    public ResponseEntity<List<EmployeeDashboardResponse>> getManagerDashboard(){
        List<EmployeeDashboardResponse> responses = dashboardService.getDashboard();
        return ResponseEntity.ok(responses);
//        return ResponseEntity.ok(dashboardService.getDashboard());
    }
}
