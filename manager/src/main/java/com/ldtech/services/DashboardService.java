package com.ldtech.services;

import com.ldtech.entities.TimesheetEntry;
import com.ldtech.payloads.EmployeeDashboardResponse;

import java.util.List;

public interface DashboardService {
    List<EmployeeDashboardResponse> getDashboard();
}
