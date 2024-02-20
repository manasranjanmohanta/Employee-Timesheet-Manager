package com.ldtech.controllers;

import com.ldtech.entities.ActivityAllocation;
import com.ldtech.payloads.EmployeeData;
import com.ldtech.services.ActivityAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activityAllocations")
public class ActivityAllocationController {

    @Autowired
    private ActivityAllocationService activityAllocationService;

    @PostMapping
    public ResponseEntity<ActivityAllocation> createActivityAllocation(@RequestBody ActivityAllocation activityAllocation) {
        ActivityAllocation savedAllocation = activityAllocationService.saveActivityAllocation(activityAllocation);
        return new ResponseEntity<>(savedAllocation, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}/{employeeId}")
    public ResponseEntity<ActivityAllocation> getActivityAllocationById(@PathVariable String projectId, @PathVariable String employeeId) {
        ActivityAllocation allocation = activityAllocationService.getActivityAllocationById(projectId, employeeId);
        if (allocation != null) {
            return new ResponseEntity<>(allocation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<ActivityAllocation>> getAllActivityAllocations() {
        List<ActivityAllocation> allocations = activityAllocationService.getAllActivityAllocations();
        return new ResponseEntity<>(allocations, HttpStatus.OK);
    }

    @GetMapping("/id/{employeeId}")
    public ResponseEntity<EmployeeData> searchEmployeeByEmployeeId(@PathVariable(value = "employeeId") String employeeId) {
        EmployeeData employeeData = activityAllocationService.searchEmployeeByEmployeeId(employeeId);
        return ResponseEntity.ok(employeeData);
    }

    @GetMapping("/name/{employeeName}")
    public ResponseEntity<EmployeeData> searchEmployeeByEmployeeName(@PathVariable(value = "employeeName") String employeeName) {
        EmployeeData employeeData = activityAllocationService.searchEmployeeByEmployeeName(employeeName);
        return ResponseEntity.ok(employeeData);

    }
}

