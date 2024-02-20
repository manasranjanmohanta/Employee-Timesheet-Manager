package com.ldtech.services;

import com.ldtech.dtos.DateRangeDTO;
import com.ldtech.entities.TimesheetEntry;
import com.ldtech.payloads.EmployeeDashboardResponse;

import java.util.List;

public interface DashboardService {
    List<EmployeeDashboardResponse> getDashboard();

    List<EmployeeDashboardResponse> searchByEmployeeId(String employeeId, DateRangeDTO dateRangeDTO);
}
