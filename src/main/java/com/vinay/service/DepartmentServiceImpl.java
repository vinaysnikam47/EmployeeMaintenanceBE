package com.vinay.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.vinay.entity.Department;
import com.vinay.entity.Employee;
import com.vinay.exception.DepartmentMaintenanceException;
import com.vinay.model.EmployeeDTO;
import com.vinay.repository.DepartmentRepository;

@Service(value = "departmentService")
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public List<EmployeeDTO> getAllEmployeesOfDepartment(Long departmentId, Pageable pageable) throws DepartmentMaintenanceException{
		Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);
		optionalDepartment.orElseThrow(() -> new DepartmentMaintenanceException("DepartmentService.DEPARTMENT_NOT_FOUND"));
		Department department = optionalDepartment.get();
	
		if(!department.getEmployeeList().isEmpty()) {
			List<Employee> allEmployeelist =  department.getEmployeeList();
			int start = (int)pageable.getOffset();
			int end = Math.min(start + (int)pageable.getPageSize(), allEmployeelist.size());
			
			if(start >= end) {
				throw new DepartmentMaintenanceException("DepartmentService.EMPLOYEE_NOT_FOUND");
			}
			Page<Employee> employeeList = new PageImpl<>(allEmployeelist.subList(start, end), pageable, allEmployeelist.size());
			return getEmployeeDTOList(employeeList);
			
		}
		else {
			throw new DepartmentMaintenanceException("DepartmentService.EMPLOYEE_NOT_FOUND");
		}
	}
	
	private List<EmployeeDTO> getEmployeeDTOList(Page<Employee> employeeList){
		List<EmployeeDTO> employeeDTOList = new ArrayList<>();
		for(Employee emp: employeeList) {
			EmployeeDTO empDTO = new EmployeeDTO();
			empDTO.setEmployeeId(emp.getEmployeeId());
			empDTO.setEmployeeName(emp.getEmployeeName());
			empDTO.setJobDescription(emp.getJobDescription());
			empDTO.setManagerId(emp.getManagerId());
			empDTO.setSalary(emp.getSalary());
			empDTO.setDepartmentId(emp.getDepartmentId());
			empDTO.setCreatedAt(emp.getCreatedAt());
			empDTO.setUpdatedAt(emp.getUpdatedAt());
			employeeDTOList.add(empDTO);
		}
		return employeeDTOList;
	}
}
