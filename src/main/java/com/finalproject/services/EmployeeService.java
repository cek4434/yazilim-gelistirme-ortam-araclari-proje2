package com.finalproject.services;

import com.finalproject.dtos.ContractEmployeeDTO;
import com.finalproject.dtos.PermanentEmployeeDTO;
import com.finalproject.entities.Employee;
import com.finalproject.exceptions.APIError;
import com.finalproject.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
    }

    public Employee addPermanentEmployee(PermanentEmployeeDTO permanentEmployeeDTO) {

        Optional isEmployee = employeeRepository.findBySicilNo(permanentEmployeeDTO.getSicilNo());
        if(isEmployee.isPresent()) {
            throw new APIError(409, "bu sicil no ile kayıtlı zaten bir çalışan var.");
        }else {
            Employee employee = permanentEmployeeDTO.toEntity();

            employee.setIstenCikisTarihi(null);
            Employee savedEmployee = employeeRepository.save(employee);

            return savedEmployee;
        }

    }

    public Employee addContractEmployee(ContractEmployeeDTO contractEmployeeDTO) {
        Optional isEmployee = employeeRepository.findBySicilNo(contractEmployeeDTO.getSicilNo());
        if(isEmployee.isPresent()) {
            throw new APIError(409, "bu sicil no ile kayıtlı zaten bir çalışan var.");
        } else {

            Employee employee = contractEmployeeDTO.toEntity();
            employee.setIstenCikisTarihi(contractEmployeeDTO.getSozlesmeBitisTarihi());

            Employee savedEmploye = employeeRepository.save(employee);

            return savedEmploye;

        }

    }

    public List<Employee> listEmployees() {
        return employeeRepository.findAll();
    }

    public Employee findByEmployeId(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new APIError(404, "calisan id bulunamadı"));
        return employee;
    }









}
