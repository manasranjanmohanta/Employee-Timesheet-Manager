package com.ldtech.repositories;

import com.ldtech.entities.TimesheetEntry;
import com.ldtech.entities.TimesheetEntryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimesheetEntryRepository extends JpaRepository<TimesheetEntry, TimesheetEntryId> {
    // You can add custom query methods here if needed
}