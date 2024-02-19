package com.ldtech.repositories;

import com.ldtech.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    // You can add custom query methods here if needed
}
