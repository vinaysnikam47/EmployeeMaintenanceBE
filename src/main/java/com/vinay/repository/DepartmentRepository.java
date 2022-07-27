package com.vinay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinay.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{

}
