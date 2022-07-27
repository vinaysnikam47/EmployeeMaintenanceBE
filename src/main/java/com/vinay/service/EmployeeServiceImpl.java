package com.vinay.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.vinay.entity.Department;
import com.vinay.entity.Employee;
import com.vinay.exception.DepartmentMaintenanceException;
import com.vinay.exception.EmployeeMaintenanceException;
import com.vinay.model.EmployeeDTO;
import com.vinay.repository.DepartmentRepository;
import com.vinay.repository.EmployeeRepository;

@Service(value = "employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public List<EmployeeDTO> getAllEmployees(Pageable pageable) throws EmployeeMaintenanceException {
		Page<Employee> employeeList = employeeRepository.findAll(pageable);
		if(!employeeList.isEmpty()) {
			return getEmployeeDTOList(employeeList);
		}
		else {
			throw new EmployeeMaintenanceException("EmployeeService.EMPLOYEE_NOT_FOUND");
		}
	}

	@Override
	public EmployeeDTO getEmployeeById(Long id) throws EmployeeMaintenanceException {
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		Employee employee = optionalEmployee.orElseThrow(() -> new EmployeeMaintenanceException("EmployeeService.EMPLOYEE_NOT_FOUND"));
		EmployeeDTO empDTO = new EmployeeDTO();
		empDTO.setEmployeeId(employee.getEmployeeId());
		empDTO.setEmployeeName(employee.getEmployeeName());
		empDTO.setJobDescription(employee.getJobDescription());
		empDTO.setManagerId(employee.getManagerId());
		empDTO.setSalary(employee.getSalary());
		empDTO.setDepartmentId(employee.getDepartmentId());
		empDTO.setCreatedAt(employee.getCreatedAt());
		empDTO.setUpdatedAt(employee.getUpdatedAt());
		return empDTO;
	}

	@Override
	public List<EmployeeDTO> getAllEmployeesWithNameContaining(String employeeName, Pageable pageable) throws EmployeeMaintenanceException {
		Page<Employee> employeeList = employeeRepository.findByEmployeeNameContainingIgnoreCase(employeeName, pageable);
		if(!employeeList.isEmpty()) {
			return getEmployeeDTOList(employeeList);
		}
		else {
			throw new EmployeeMaintenanceException("EmployeeService.EMPLOYEE_NOT_FOUND");
		}
	}
	
	@Override
	public List<EmployeeDTO> getAllEmployeesByManagerId(Long managerId, Pageable pageable) throws EmployeeMaintenanceException {
		Page<Employee> employeeList = employeeRepository.findByManagerId(managerId, pageable);
		if(!employeeList.isEmpty()) {
			return getEmployeeDTOList(employeeList);
		}
		else {
			throw new EmployeeMaintenanceException("EmployeeService.EMPLOYEE_NOT_FOUND");
		}
	}
	
	@Override
	public Long addEmployee(EmployeeDTO employeeDTO) throws DepartmentMaintenanceException {
		Optional<Department> optionalDepartment = departmentRepository.findById(employeeDTO.getDepartmentId());
		optionalDepartment.orElseThrow(() -> new DepartmentMaintenanceException("DepartmentService.DEPARTMENT_NOT_FOUND"));
		
		Employee employee = new Employee();
		employee.setEmployeeName(employeeDTO.getEmployeeName());
		employee.setDepartmentId(employeeDTO.getDepartmentId());
		employee.setJobDescription(employeeDTO.getJobDescription());
		employee.setManagerId(employeeDTO.getManagerId());
		employee.setSalary(employeeDTO.getSalary());
		
		Employee employeeAfterInsert = employeeRepository.save(employee);
		return employeeAfterInsert.getEmployeeId();
	}
	
	@Override
	public void deleteEmployee(Long employeeId) throws EmployeeMaintenanceException {
		Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
		optionalEmployee.orElseThrow(() -> new EmployeeMaintenanceException("EmployeeService.EMPLOYEE_NOT_FOUND"));
		employeeRepository.deleteById(employeeId);
	}
	
	@Override
	public void updateEmployee(Long employeeId, EmployeeDTO employeeDTO)
			throws EmployeeMaintenanceException, DepartmentMaintenanceException {
		Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
		optionalEmployee.orElseThrow(() -> new EmployeeMaintenanceException("EmployeeService.EMPLOYEE_NOT_FOUND"));
		Employee employee = optionalEmployee.get();
		if(employeeDTO.getEmployeeName() != null) employee.setEmployeeName(employeeDTO.getEmployeeName());
		if(employeeDTO.getJobDescription() != null) employee.setJobDescription(employeeDTO.getJobDescription());
		if(employeeDTO.getSalary() != null) employee.setSalary(employeeDTO.getSalary());
		if(employeeDTO.getManagerId() != null) employee.setManagerId(employeeDTO.getManagerId());
		
		if(employeeDTO.getDepartmentId() != null) {
			Optional<Department> optionalDepartment = departmentRepository.findById(employeeDTO.getDepartmentId());
			optionalDepartment.orElseThrow(() -> new DepartmentMaintenanceException("DepartmentService.DEPARTMENT_NOT_FOUND"));
			employee.setDepartmentId(employeeDTO.getDepartmentId());
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
