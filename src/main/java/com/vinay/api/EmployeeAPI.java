package com.vinay.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinay.exception.DepartmentMaintenanceException;
import com.vinay.exception.EmployeeMaintenanceException;
import com.vinay.model.EmployeeDTO;
import com.vinay.service.EmployeeService;

@RestController
@RequestMapping(value = "/empMaint")
public class EmployeeAPI {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private Environment environment;
	
	@GetMapping(value = "/employees/{pageNo}/{pageSize}")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@PathVariable int pageNo, @PathVariable int pageSize) throws EmployeeMaintenanceException{
		Sort sort = Sort.by("employeeId");
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		List<EmployeeDTO> list = employeeService.getAllEmployees(pageable);
		ResponseEntity<List<EmployeeDTO>> response = new ResponseEntity<List<EmployeeDTO>>(list, HttpStatus.OK);
		return response;
		
	}
	
	@GetMapping(value = "/employees/{employeeId}")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long employeeId) throws EmployeeMaintenanceException{
		EmployeeDTO employeeDTO = employeeService.getEmployeeById(employeeId);
		ResponseEntity<EmployeeDTO> response = new ResponseEntity<EmployeeDTO>(employeeDTO, HttpStatus.OK);
		return response;	
	}
	
	@GetMapping(value = "/nameSearch/{employeeName}/{pageNo}/{pageSize}")
	public ResponseEntity<List<EmployeeDTO>> getEmployeesByNameContaining(@PathVariable String employeeName, @PathVariable int pageNo, @PathVariable int pageSize) throws EmployeeMaintenanceException{
		Sort sort = Sort.by("employeeId");
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		List<EmployeeDTO> list = employeeService.getAllEmployeesWithNameContaining(employeeName, pageable);
		ResponseEntity<List<EmployeeDTO>> response = new ResponseEntity<List<EmployeeDTO>>(list, HttpStatus.OK);
		return response;	
	}
	
	@GetMapping(value = "/searchByMgrId/{managerId}/{pageNo}/{pageSize}")
	public ResponseEntity<List<EmployeeDTO>> getEmployeesByManagerId(@PathVariable Long managerId, @PathVariable int pageNo, @PathVariable int pageSize) throws EmployeeMaintenanceException{
		Sort sort = Sort.by("employeeId");
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		List<EmployeeDTO> list = employeeService.getAllEmployeesByManagerId(managerId, pageable);
		ResponseEntity<List<EmployeeDTO>> response = new ResponseEntity<List<EmployeeDTO>>(list, HttpStatus.OK);
		return response;	
	}
	
	@PostMapping(value = "/add")
	public ResponseEntity<String> addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) throws DepartmentMaintenanceException{
		Long employeeId = employeeService.addEmployee(employeeDTO);
		String message = environment.getProperty("EmployeeService.ADD_SUCCESS") + employeeId;
		ResponseEntity<String> response = new ResponseEntity<String>(message, HttpStatus.OK);
		return response;
	}
	
	@DeleteMapping(value = "/delete/{employeeId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Long employeeId) throws EmployeeMaintenanceException{
		employeeService.deleteEmployee(employeeId);
		String message = environment.getProperty("EmployeeService.DELETE_SUCCESS");
		ResponseEntity<String> response = new ResponseEntity<String>(message, HttpStatus.OK);
		return response;
	}
	
	@PutMapping(value = "/update/{employeeId}")
	public ResponseEntity<String> updateEmployee(@PathVariable Long employeeId, @RequestBody EmployeeDTO employeeDTO) throws EmployeeMaintenanceException, DepartmentMaintenanceException{
		employeeService.updateEmployee(employeeId, employeeDTO);
		String message = environment.getProperty("EmployeeService.UPDATE_SUCCESS");
		ResponseEntity<String> response = new ResponseEntity<String>(message, HttpStatus.OK);
		return response;
	}
}
