package com.ldtech.repositories;

import com.ldtech.entities.History;
import com.ldtech.entities.TimesheetEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface HistoryRepository extends JpaRepository<History, String> {
    @Query("SELECT h FROM History h WHERE h.logDate = :logDate")
    List<History> findByLogDate(@Param("logDate") LocalDate logDate);

    List<History> findByEmployeeIdAndLogDate(String employeeId, LocalDate date);

    List<History> findByEmployeeNameAndLogDate(String employeeName, LocalDate date);

    List<History> findByApprovalStatusAndLogDate(String approvalStatus, LocalDate date);
}
