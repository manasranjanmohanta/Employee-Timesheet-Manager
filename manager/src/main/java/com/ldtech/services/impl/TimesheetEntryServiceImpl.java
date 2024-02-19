package com.ldtech.services.impl;

import com.ldtech.entities.TimesheetEntry;
import com.ldtech.entities.TimesheetEntryId;
import com.ldtech.repositories.TimesheetEntryRepository;
import com.ldtech.services.TimesheetEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.List;

@Service
public class TimesheetEntryServiceImpl implements TimesheetEntryService {

    @Autowired
    private TimesheetEntryRepository timesheetEntryRepository;

    @Override
    public TimesheetEntry saveTimesheetEntry(TimesheetEntry timesheetEntry) {
        return timesheetEntryRepository.save(timesheetEntry);
    }

    @Override
    public TimesheetEntry getTimesheetEntryById(String employeeId, LocalDate logDate) {
        TimesheetEntryId id = new TimesheetEntryId();
        id.setEmployeeId(employeeId);
        id.setLogDate(logDate);
        return timesheetEntryRepository.findById(id).orElse(null);
    }

    @Override
    public List<TimesheetEntry> getAllTimesheetEntries() {
        return timesheetEntryRepository.findAll();
    }

    @Override
    public void deleteTimesheetEntry(String employeeId, LocalDate logDate) {
        TimesheetEntryId id = new TimesheetEntryId();
        id.setEmployeeId(employeeId);
        id.setLogDate(logDate);
        timesheetEntryRepository.deleteById(id);
    }
}
