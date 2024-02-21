package com.ldtech.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllocateData {
    private String projectName;
    private String projectType;
    private String activityType;
    private LocalTime activityStartTime;
    private LocalTime activityEndTime;
}
