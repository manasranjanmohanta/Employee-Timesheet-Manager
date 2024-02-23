package com.ldtech.controllers;

import com.ldtech.entities.TimesheetEntry;
import com.ldtech.payloads.TimesheetResponse;
import com.ldtech.services.TimesheetEntryService;
import com.ldtech.services.TimesheetValidateService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/validate")
public class TimesheetValidateController {

    @Autowired
    private TimesheetValidateService timesheetValidateService;

    @GetMapping("/{employeeId}/{logDate}")
    public ResponseEntity<TimesheetResponse> getTimesheetEntryById(@PathVariable String employeeId, @PathVariable LocalDate logDate) {
        TimesheetResponse response = timesheetValidateService.getTimesheetEntryByEmployeeIdAndLogDate(employeeId, logDate);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
