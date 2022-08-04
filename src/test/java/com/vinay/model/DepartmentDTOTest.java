package com.vinay.model;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DepartmentDTOTest {
	
	DepartmentDTO departmentDTO = null;
	
	@BeforeEach
	public void createDepartment() {	
		departmentDTO = new DepartmentDTO();
		departmentDTO.setDepartmentName("FNSP");
		departmentDTO.setDepartmentId(1002L);
		LocalDateTime dateTime = LocalDateTime.of(2022, 8, 3, 19, 2);
		departmentDTO.setCreatedAt(dateTime);
		departmentDTO.setUpdatedAt(dateTime);
	}
	
	@Test
	public void getDepartmentIdValidTest() {
		Long departmentId = 1002L;
		Assertions.assertEquals(departmentId, departmentDTO.getDepartmentId());
	}
	
	@Test
	public void setDepartmentIdValidTest() {
		Long departmentId = 1004L;
		departmentDTO.setDepartmentId(departmentId);
		Assertions.assertEquals(departmentId, departmentDTO.getDepartmentId());
	}
	
	@Test
	public void getDepartmentNameValidTest() {
		String departmentName = "FNSP";
		Assertions.assertEquals(departmentName, departmentDTO.getDepartmentName());
	}
	
	@Test
	public void setDepartmentNameValidTest() {
		String departmentName = "FNPR";
		departmentDTO.setDepartmentName(departmentName);
		Assertions.assertEquals(departmentName, departmentDTO.getDepartmentName());
	}
	
	@Test
	public void getCreatedAtValidTest() {
		LocalDateTime dateTime = LocalDateTime.of(2022, 8, 3, 19, 2);
		Assertions.assertEquals(dateTime, departmentDTO.getCreatedAt());
	}
	
	@Test
	public void setCreatedAtValidTest() {
		LocalDateTime dateTime = LocalDateTime.of(2022, 7, 3, 19, 2);
		departmentDTO.setCreatedAt(dateTime);
		LocalDateTime dateTimeExpected = LocalDateTime.of(2022, 7, 3, 19, 2);
		Assertions.assertEquals(dateTimeExpected, departmentDTO.getCreatedAt());
	}
	
	@Test
	public void getUpdatedAtValidTest() {
		LocalDateTime dateTime = LocalDateTime.of(2022, 8, 3, 19, 2);
		Assertions.assertEquals(dateTime, departmentDTO.getUpdatedAt());
	}
	
	@Test
	public void setUpdatedAtValidTest() {
		LocalDateTime dateTime = LocalDateTime.of(2022, 7, 3, 19, 2);
		departmentDTO.setUpdatedAt(dateTime);
		LocalDateTime dateTimeExpected = LocalDateTime.of(2022, 7, 3, 19, 2);
		Assertions.assertEquals(dateTimeExpected, departmentDTO.getUpdatedAt());
	}
}
