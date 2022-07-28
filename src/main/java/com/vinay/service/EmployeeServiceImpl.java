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


/**
 * EmployeeServiceImpl class contains business logic of different employee maintenance 
 * services
 *
 * @version 1.00    27th AUG 2022 
 * @author 			Vinay Nikam
 *
 */
@Service(value = "employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;

	
	/**
	 * Method to fetch all the employees information from all departments
	 * 
	 * @param  pageable Contains pageNo, pageSize and sort parameter for pagination and sorting 
	 * @return List<EmployeeDTO> object containing all employees information
	 * @throws com.vinay.exception.EmployeeMaintenanceException
	 */
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

	
	/**
	 * Method to fetch information of single employee.
	 * 
	 * @param  id Long Employee Id of employee who's information to be fetched
	 * @return EmployeeDTO object containing employee information
	 * @throws com.vinay.exception.EmployeeMaintenanceException
	 */
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

	
	/**
	 * Method to fetch all the employees with name similar to given pattern
	 * 
	 * @param  employeeName String object containing pattern from name of employee
	 * @param  pageable Contains pageNo, pageSize and sort parameter for pagination and sorting
	 * @return List<EmployeeDTO> object containing employee information
	 * @throws com.vinay.exception.EmployeeMaintenanceException
	 */
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
	
	
	/**
	 * Method to fetch all the employees working under the manager having id as managerId
	 * 
	 * @param  managerId Long object containing manager Id
	 * @param  pageable Contains pageNo, pageSize and sort parameter for pagination and sorting
	 * @return List<EmployeeDTO> object containing employee information
	 * @throws com.vinay.exception.EmployeeMaintenanceException
	 */
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
	
	
	/**
	 * Method to add employee in database
	 * 
	 * @param  EmployeeDTO object which contains information of employee to be added in database
	 * @return employeeId Long Id of added employee
	 * @throws com.vinay.exception.DepartmentMaintenanceException
	 */
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
	
	
	/**
	 * Method to delete employee from database
	 * 
	 * @param  employeeId Long Employee Id of employee who's information to be deleted
	 * @return Nothing
	 * @throws com.vinay.exception.EmployeeMaintenanceException
	 */
	@Override
	public void deleteEmployee(Long employeeId) throws EmployeeMaintenanceException {
		Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
		optionalEmployee.orElseThrow(() -> new EmployeeMaintenanceException("EmployeeService.EMPLOYEE_NOT_FOUND"));
		employeeRepository.deleteById(employeeId);
	}
	
	
	/**
	 * Method to update employee information
	 * 
	 * @param  employeeId Long Employee Id of employee who's information to be updated
	 * @param  EmployeeDTO object which contains updated information of employee
	 * @return Nothing
	 * @throws com.vinay.exception.EmployeeMaintenanceException
	 * @throws com.vinay.exception.DepartmentMaintenanceException
	 */
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
	
	
	/**
	 * Helper function to create list of EmployeeDTO from Page<Employee>
	 * 
	 * @param  Page<Employee> Object from which employeeDTO list to be created
	 * @return List<EmployeeDTO>
	 * @throws Nothing
	 */
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
