package com.vinay.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.vinay.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	public Page<Employee> findByEmployeeNameContainingIgnoreCase(String employeeName, Pageable pageable);
	public Page<Employee> findByManagerId(Long managerId, Pageable pageable);
}
