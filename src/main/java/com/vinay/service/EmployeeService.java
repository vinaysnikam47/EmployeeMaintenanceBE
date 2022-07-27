package com.vinay.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.vinay.exception.DepartmentMaintenanceException;
import com.vinay.exception.EmployeeMaintenanceException;
import com.vinay.model.EmployeeDTO;

public interface EmployeeService {

	public List<EmployeeDTO> getAllEmployees(Pageable pageable) throws EmployeeMaintenanceException;
	public EmployeeDTO getEmployeeById(Long id) throws EmployeeMaintenanceException;
	public List<EmployeeDTO> getAllEmployeesWithNameContaining(String employeeName, Pageable pageable) throws EmployeeMaintenanceException;
	public List<EmployeeDTO> getAllEmployeesByManagerId(Long managerId, Pageable pageable) throws EmployeeMaintenanceException;
	public Long addEmployee(EmployeeDTO employeeDTO) throws DepartmentMaintenanceException;
	public void deleteEmployee(Long employeeId) throws EmployeeMaintenanceException;
	public void updateEmployee(Long employeeId, EmployeeDTO employeeDTO) throws EmployeeMaintenanceException, DepartmentMaintenanceException;
}
