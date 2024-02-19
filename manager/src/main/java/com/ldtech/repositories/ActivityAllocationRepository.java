package com.ldtech.repositories;

import com.ldtech.entities.ActivityAllocation;
import com.ldtech.entities.ActivityAllocationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityAllocationRepository extends JpaRepository<ActivityAllocation, ActivityAllocationId> {
    // You can add custom query methods here if needed
}