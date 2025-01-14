package com.finalproject.controllers;

import com.finalproject.dtos.ContractEmployeeDTO;
import com.finalproject.dtos.PermanentEmployeeDTO;
import com.finalproject.entities.Employee;
import com.finalproject.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    @Autowired
    public EmployeeController (EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @PostMapping("/permanent")
    public ResponseEntity<Employee> addPermanentEmployee(@Valid @RequestBody PermanentEmployeeDTO permanentEmployeeDTO) {
        Employee createdEmployee = employeeService.addPermanentEmployee(permanentEmployeeDTO);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @PostMapping("/contract")
    public ResponseEntity<Employee> addContractEmployee(@Valid @RequestBody ContractEmployeeDTO contractEmployeeDTO) {
        Employee createdEmployee = employeeService.addContractEmployee(contractEmployeeDTO);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @GetMapping("")
    public List<Employee> listEmployees() {
        return employeeService.listEmployees();
    }











}
