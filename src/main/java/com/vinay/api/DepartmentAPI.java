package com.vinay.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vinay.exception.DepartmentMaintenanceException;
import com.vinay.model.EmployeeDTO;
import com.vinay.service.DepartmentService;

@RestController
@RequestMapping(value = "/deptMaint")
public class DepartmentAPI {
	
	@Autowired
	private DepartmentService departmentService;

	@GetMapping(value = "/departments/{departmentId}/{pageNo}/{pageSize}")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployeesOfDepartment(@PathVariable Long departmentId, @PathVariable int pageNo, @PathVariable int pageSize) throws DepartmentMaintenanceException{
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		List<EmployeeDTO> list = departmentService.getAllEmployeesOfDepartment(departmentId, pageable);
		ResponseEntity<List<EmployeeDTO>> response = new ResponseEntity<List<EmployeeDTO>>(list, HttpStatus.OK);
		return response;
		
	}
}
