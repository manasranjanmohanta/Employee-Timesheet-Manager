package com.ldtech.services.impl;

import com.ldtech.entities.ActivityAllocation;
import com.ldtech.entities.ActivityAllocationId;
import com.ldtech.entities.Employee;
import com.ldtech.entities.Project;
import com.ldtech.payloads.EmployeeData;
import com.ldtech.repositories.ActivityAllocationRepository;
import com.ldtech.repositories.EmployeeRepository;
import com.ldtech.repositories.ProjectRepository;
import com.ldtech.services.ActivityAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ActivityAllocationServiceImpl implements ActivityAllocationService {

    @Autowired
    private ActivityAllocationRepository activityAllocationRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public ActivityAllocation saveActivityAllocation(ActivityAllocation activityAllocation) {
        return activityAllocationRepository.save(activityAllocation);
    }

    @Override
    public ActivityAllocation getActivityAllocationById(String projectId, String employeeId) {
        ActivityAllocationId id = new ActivityAllocationId();
        id.setProjectId(projectId);
        id.setEmployeeId(employeeId);
        return activityAllocationRepository.findById(id).orElse(null);
    }
    @Override
    public List<ActivityAllocation> getAllActivityAllocations() {
        return activityAllocationRepository.findAll();
    }


    @Override
    public EmployeeData searchEmployeeByEmployeeId(String employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);

        EmployeeData employeeData = new EmployeeData();
        employeeData.setEmployeeId(employee.getEmployeeId());
        employeeData.setEmployeeName(employee.getEmployeeName());

        return employeeData;
    }

    @Override
    public EmployeeData searchEmployeeByEmployeeName(String employeeName) {
        Employee employee = employeeRepository.findByEmployeeName(employeeName);

        EmployeeData employeeData = new EmployeeData();
        employeeData.setEmployeeId(employee.getEmployeeId());
        employeeData.setEmployeeName(employee.getEmployeeName());

        return employeeData;
    }

    @Override
    public List<String> getAllProjectNames(String projectManager) {
//        List<Project> projects = projectRepository.findAll();
////        return projects.stream()
////                .map(project -> project.getProjectName()).collect(Collectors.toList());
        List<Project> projects = projectRepository.findProjectsByProjectManager(projectManager);
        return projects.stream()
                .map(Project::getProjectName)
                .collect(Collectors.toList());
    }

}
