package com.ldtech.services.impl;

import com.ldtech.dtos.DateRangeDTO;
import com.ldtech.entities.History;
import com.ldtech.payloads.HistoryResponse;
import com.ldtech.repositories.HistoryRepository;
import com.ldtech.services.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public List<HistoryResponse> getAllHistory() {
        LocalDate currentDate = LocalDate.now();
        // Find the start and end dates of the current week
        LocalDate startDate = currentDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate endDate = startDate.plusDays(4);

        List<History> histories = new ArrayList<>();
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            List<History> historyList = historyRepository.findByLogDate(date);
            histories.addAll(historyList);

        }
        List<HistoryResponse> historyResponses = histories.stream().map(this::mapToHistoryResponse).collect(Collectors.toList());

        return historyResponses;
    }

    @Override
    public List<HistoryResponse> getAllHistoryWithRangedDate(DateRangeDTO dateRangeDTO) {
        LocalDate startDate = dateRangeDTO.getStartDate();
        LocalDate endDate = dateRangeDTO.getEndDate();

        List<History> histories = new ArrayList<>();
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            List<History> historyList = historyRepository.findByLogDate(date);
            histories.addAll(historyList);

        }
        List<HistoryResponse> historyResponses = histories.stream().map(this::mapToHistoryResponse).collect(Collectors.toList());

        return historyResponses;
    }

    @Override
    public List<HistoryResponse> getAllHistoryByEmployeeId(String employeeId, DateRangeDTO dateRangeDTO) {
        LocalDate startDate = dateRangeDTO.getStartDate();
        LocalDate endDate = dateRangeDTO.getEndDate();

        List<History> histories = new ArrayList<>();
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            List<History> historyList = historyRepository.findByEmployeeIdAndLogDate(employeeId, date);

            histories.addAll(historyList);

        }
        List<HistoryResponse> historyResponses = histories.stream().map(this::mapToHistoryResponse).collect(Collectors.toList());

        return historyResponses;
    }

    @Override
    public List<HistoryResponse> getAllHistoryByEmployeeName(String employeeName, DateRangeDTO dateRangeDTO) {
        LocalDate startDate = dateRangeDTO.getStartDate();
        LocalDate endDate = dateRangeDTO.getEndDate();

        List<History> histories = new ArrayList<>();
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            List<History> historyList = historyRepository.findByEmployeeNameAndLogDate(employeeName, date);

            histories.addAll(historyList);

        }
        List<HistoryResponse> historyResponses = histories.stream().map(this::mapToHistoryResponse).collect(Collectors.toList());

        return historyResponses;
    }

    private HistoryResponse mapToHistoryResponse(History history) {
        HistoryResponse response = new HistoryResponse();
        response.setEmployeeId(history.getEmployeeId());
        response.setEmployeeName(history.getEmployeeName());
        response.setLogDate(history.getLogDate());
        response.setApprovalStatus(history.getApprovalStatus());
        response.setModifiedBy(history.getModifiedBy());
        response.setModifiedDate(history.getModifiedDate());
        return response;
    }
}
