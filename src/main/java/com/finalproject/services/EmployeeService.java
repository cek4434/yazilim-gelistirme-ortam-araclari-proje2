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

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee addPermanentEmployee(PermanentEmployeeDTO permanentEmployeeDTO) {
        findEmployeeBySicilNo(permanentEmployeeDTO.getSicilNo());

        Employee employee = permanentEmployeeDTO.toEntity();
        employee.setIstenCikisTarihi(null);
        Employee savedEmployee = employeeRepository.save(employee);

        return savedEmployee;
    }

    public Employee addContractEmployee(ContractEmployeeDTO contractEmployeeDTO) {
        findEmployeeBySicilNo(contractEmployeeDTO.getSicilNo());

        Employee employee = contractEmployeeDTO.toEntity();
        employee.setIstenCikisTarihi(contractEmployeeDTO.getSozlesmeBitisTarihi());

        Employee savedEmploye = employeeRepository.save(employee);

        return savedEmploye;

    }



    public List<Employee> listEmployees() {
        return employeeRepository.findAll();
    }

    public Boolean findEmployeeBySicilNo (String sicilNo) {
        Optional isEmployee = employeeRepository.findBySicilNo(sicilNo);
        if(isEmployee.isPresent()) {
            throw new APIError(409, "bu sicil no ile kayıtlı zaten bir çalışan var.");
        }else {
            return true;
        }
    }


    public Employee findByEmployeId(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new APIError(404, "calisan id bulunamadı"));
        return employee;
    }









}
