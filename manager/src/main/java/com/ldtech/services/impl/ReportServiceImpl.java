package com.ldtech.services.impl;

import com.ldtech.dtos.DateRangeDTO;
import com.ldtech.entities.TimesheetEntry;
import com.ldtech.repositories.TimesheetEntryRepository;
import com.ldtech.services.ReportService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private TimesheetEntryRepository timesheetEntryRepository;

    @Override
    public List<TimesheetEntry> getEmployeeData(DateRangeDTO dateRangeDTO) {
        LocalDate startDate = dateRangeDTO.getStartDate();
        LocalDate endDate = dateRangeDTO.getEndDate();

        System.out.println(startDate + " " + endDate);

        List<TimesheetEntry> timesheetEntries = new ArrayList<>();
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            List<TimesheetEntry> entryList = timesheetEntryRepository.findByLogDate(date);

            timesheetEntries.addAll(entryList);

        }
        return timesheetEntries;
    }

    @Override
    public byte[] generateXLS(List<TimesheetEntry> timesheetList) {
        try (Workbook workbook = new HSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Employees");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Log Date");
            headerRow.createCell(1).setCellValue("Employee ID");
            headerRow.createCell(2).setCellValue("Employee Name");
            headerRow.createCell(3).setCellValue("Project Name");
            headerRow.createCell(4).setCellValue("Activity Type");
            headerRow.createCell(5).setCellValue("Start Time");
            headerRow.createCell(6).setCellValue("End Time");
            headerRow.createCell(7).setCellValue("Manager");
            headerRow.createCell(8).setCellValue("Client");
            headerRow.createCell(9).setCellValue("Department");
            headerRow.createCell(10).setCellValue("Billing");
            headerRow.createCell(11).setCellValue("Status");


            // Create data rows
            int rowNum = 1;
            for (TimesheetEntry employee : timesheetList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(employee.getId().getLogDate().toString());
                row.createCell(1).setCellValue(employee.getId().getEmployeeId());
                row.createCell(2).setCellValue(employee.getEmployee().getEmployeeName());
                row.createCell(3).setCellValue(employee.getProject().getProjectName());
                row.createCell(4).setCellValue(employee.getId().getActivityType());
                row.createCell(5).setCellValue(employee.getId().getStartTime().toString());
                row.createCell(6).setCellValue(employee.getEndTime().toString());
                row.createCell(7).setCellValue(employee.getProject().getProjectManager());
                row.createCell(8).setCellValue(employee.getProject().getClient());
                row.createCell(9).setCellValue(employee.getEmployee().getDepartment());
                row.createCell(10).setCellValue(employee.getProject().getBillingType());
                row.createCell(11).setCellValue(employee.getApprovalStatus());

            }

            // Write the workbook to a ByteArrayOutputStream
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception
            return new byte[0];
        }
    }
}
