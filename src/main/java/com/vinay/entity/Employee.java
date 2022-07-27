package com.vinay.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employeeId;
	
	private String employeeName;
	private String jobDescription;
	private Long managerId;
	private Long salary;
	
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	private Long departmentId;
	
	public Long getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	
	public String getEmployeeName() {
		return employeeName;
	}
	
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
	public String getJobDescription() {
		return jobDescription;
	}
	
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	
	public Long getManagerId() {
		return managerId;
	}
	
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}
	
	public Long getSalary() {
		return salary;
	}
	
	public void setSalary(Long salary) {
		this.salary = salary;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public Long getDepartmentId() {
		return departmentId;
	}
	
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	
	
}
