package com.ldtech.services;

import com.ldtech.payloads.HistoryResponse;

import java.util.List;

public interface HistoryService {
    List<HistoryResponse> getAllHistory();
}
