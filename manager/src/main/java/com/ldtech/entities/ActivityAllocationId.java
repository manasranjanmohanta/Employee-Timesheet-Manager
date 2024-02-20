package com.ldtech.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ActivityAllocationId implements Serializable {

    @Column(name = "employee_id")
    @NotNull
    private String employeeId;

    @Column(name = "project_id")
    @NotNull
    private String projectId;

    @Column(name = "project_type")
    @Size(max = 20)
    private String projectType;

    @Column(name = "activity_type")
    @Size(max = 20)
    private String activityType;

    @Column(name = "activity_start_time")
    @NotNull
    private LocalTime activityStartTime;

}