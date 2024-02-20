package com.ldtech.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "activity_allocation")
public class ActivityAllocation {

    @EmbeddedId
    private ActivityAllocationId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id", insertable = false, updatable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id", referencedColumnName = "project_id", insertable = false, updatable = false)
    private Project project;

    @Column(name = "activity_end_time")
    @NotNull
    private LocalTime activityEndTime;

       @Column(name = "activity_allocation_date")
    @Temporal(TemporalType.DATE)
    @NotNull
    private LocalDate activityAllocationDate;

}


