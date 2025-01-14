package com.finalproject.repositories;

import com.finalproject.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional findBySicilNo (String sicilNo);



}
