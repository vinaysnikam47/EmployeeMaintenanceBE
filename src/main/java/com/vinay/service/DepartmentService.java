package com.vinay.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.vinay.exception.DepartmentMaintenanceException;
import com.vinay.model.EmployeeDTO;

public interface DepartmentService {
	
	public List<EmployeeDTO> getAllEmployeesOfDepartment(Long departmentId, Pageable pageable) throws DepartmentMaintenanceException;
}
