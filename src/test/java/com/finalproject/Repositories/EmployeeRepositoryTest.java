package com.finalproject.repositories;

import com.finalproject.entities.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeRepositoryTest {

    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    Employee employee;

    @Test
    void testFindBySicilNo() {
        // Employee mock'ını oluşturuyoruz
        Employee employeeMock = mock(Employee.class);
        when(employeeMock.getSicilNo()).thenReturn("12345678");

        // Mock repository'yi doğru şekilde yapılandırıyoruz
        when(employeeRepository.findBySicilNo("12345678")).thenReturn(Optional.of(employeeMock));

        // findBySicilNo metodunu test ediyoruz
        Optional<Employee> foundEmployee = employeeRepository.findBySicilNo("12345678");

        // Sonuçları doğruluyoruz
        assertTrue(foundEmployee.isPresent(), "Çalışan bulunmalıdır");
        assertEquals("12345678", foundEmployee.get().getSicilNo(), "Sicil No eşleşmemeli");
    }

    @Test
    void testFindBySicilNoFailure() {
        // Mock repository'yi doğru şekilde yapılandırıyoruz
        when(employeeRepository.findBySicilNo("12345678")).thenReturn(Optional.empty()); // Employee bulunamaz

        // findBySicilNo metodunu test ediyoruz
        Optional<Employee> foundEmployee = employeeRepository.findBySicilNo("12345678");

        // Sonuçları doğruluyoruz
        assertFalse(foundEmployee.isPresent(), "Employee should not be found"); // Burada employee bulunmamalı, bu yüzden assertFalse kullanıyoruz.
    }




}
