package com.vinay.model;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmployeeDTOTest {
	
	EmployeeDTO employeeDTO = null;
	
	@BeforeEach
	public void createEmployeeDTO() {
		employeeDTO = new EmployeeDTO();
		employeeDTO.setEmployeeId(10L);
		employeeDTO.setEmployeeName("John Cena");
		employeeDTO.setJobDescription("You can't see me");
		employeeDTO.setManagerId(12L);
		employeeDTO.setSalary(100000L);
		LocalDateTime dateTime = LocalDateTime.of(2022, 8, 3, 19, 2);
		employeeDTO.setCreatedAt(dateTime);
		employeeDTO.setUpdatedAt(dateTime);
		employeeDTO.setDepartmentId(1001L);
	}
	
	@Test
	public void getEmployeeIdValidTest() {
		Long employeeId = 10L;
		Assertions.assertEquals(employeeId, employeeDTO.getEmployeeId());
	}
	
	@Test
	public void setEmployeeIdValidTest() {
		employeeDTO.setEmployeeId(14L);
		Long employeeId = 14L;
		Assertions.assertEquals(employeeId, employeeDTO.getEmployeeId());
	}
	
	@Test
	public void getEmployeeNameValidTest() {
		String employeeName = "John Cena";
		Assertions.assertEquals(employeeName, employeeDTO.getEmployeeName());
	}
	
	@Test
	public void setEmployeeNameValidTest() {
		employeeDTO.setEmployeeName("Goldberg");
		String employeeName = "Goldberg";
		Assertions.assertEquals(employeeName, employeeDTO.getEmployeeName());
	}
	
	@Test
	public void getJobDescriptionValidTest() {
		String job = "You can't see me";
		Assertions.assertEquals(job, employeeDTO.getJobDescription());
	}
	
	@Test
	public void setJobDescriptionValidTest() {
		employeeDTO.setJobDescription("Smash");
		String job = "Smash";
		Assertions.assertEquals(job, employeeDTO.getJobDescription());
	}
	
	@Test
	public void getManagerIdValidTest() {
		Long managerId = 12L;
		Assertions.assertEquals(managerId, employeeDTO.getManagerId());
	}
	
	@Test
	public void setManagerIdValidTest() {
		employeeDTO.setManagerId(15L);
		Long managerId = 15L;
		Assertions.assertEquals(managerId, employeeDTO.getManagerId());
	}
	
	@Test
	public void getSalaryValidTest() {
		Long salary = 100000L;
		Assertions.assertEquals(salary, employeeDTO.getSalary());
	}
	
	@Test
	public void setSalaryValidTest() {
		employeeDTO.setSalary(120000L);
		Long salary = 120000L;
		Assertions.assertEquals(salary, employeeDTO.getSalary());
	}
	
	@Test
	public void getCreatedAtValidTest() {
		LocalDateTime dateTime = LocalDateTime.of(2022, 8, 3, 19, 2);
		Assertions.assertEquals(dateTime, employeeDTO.getCreatedAt());
	}
	
	@Test
	public void setCreatedAtValidTest() {
		LocalDateTime dateTime = LocalDateTime.of(2022, 7, 3, 19, 2);
		employeeDTO.setCreatedAt(dateTime);
		LocalDateTime dateTimeExpected = LocalDateTime.of(2022, 7, 3, 19, 2);
		Assertions.assertEquals(dateTimeExpected, employeeDTO.getCreatedAt());
	}
	
	@Test
	public void getUpdatedAtValidTest() {
		LocalDateTime dateTime = LocalDateTime.of(2022, 8, 3, 19, 2);
		Assertions.assertEquals(dateTime, employeeDTO.getUpdatedAt());
	}
	
	@Test
	public void setUpdatedAtValidTest() {
		LocalDateTime dateTime = LocalDateTime.of(2022, 7, 3, 19, 2);
		employeeDTO.setUpdatedAt(dateTime);
		LocalDateTime dateTimeExpected = LocalDateTime.of(2022, 7, 3, 19, 2);
		Assertions.assertEquals(dateTimeExpected, employeeDTO.getUpdatedAt());
	}
	
	@Test
	public void getDepartmentIdValidTest() {
		Long deptId = 1001L;
		Assertions.assertEquals(deptId, employeeDTO.getDepartmentId());
	}
	
	@Test
	public void setDepartmentIdValidTest() {
		employeeDTO.setDepartmentId(1002L);
		Long deptId = 1002L;
		Assertions.assertEquals(deptId, employeeDTO.getDepartmentId());
	}
}
