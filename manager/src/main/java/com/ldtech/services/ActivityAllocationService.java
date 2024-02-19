package com.ldtech.services;

import com.ldtech.entities.ActivityAllocation;

import java.util.List;

public interface ActivityAllocationService {
    ActivityAllocation saveActivityAllocation(ActivityAllocation activityAllocation);
    ActivityAllocation getActivityAllocationById(String projectId, String employeeId);
    List<ActivityAllocation> getAllActivityAllocations();
    void deleteActivityAllocation(String projectId, String employeeId);
}

