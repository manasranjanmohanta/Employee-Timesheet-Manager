package com.ldtech.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "history_info")
public class History {
    @Id
    @Column(name = "employee_id")
    @Size(max = 15)
    private String employeeId;

    @Column(name = "employee_name")
    @Size(max = 30)
    private String employeeName;

    @Column(name = "log_date")
    @Temporal(TemporalType.DATE)
    private LocalDate logDate;

    @Column(name = "approval_status")
    @Size(max = 10)
    private String approvalStatus;

    @Column(name = "modified_by")
    @Size(max = 30)
    private String modifiedBy;

    @Column(name = "modified_date")
    @Temporal(TemporalType.DATE)
    private LocalDate modifiedDate;


}
