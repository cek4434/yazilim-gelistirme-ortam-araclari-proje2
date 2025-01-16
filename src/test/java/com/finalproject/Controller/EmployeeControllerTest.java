package com.finalproject.Controller;

import com.finalproject.controllers.EmployeeController;
import com.finalproject.dtos.ContractEmployeeDTO;
import com.finalproject.dtos.PermanentEmployeeDTO;
import com.finalproject.entities.Employee;
import com.finalproject.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @InjectMocks
    EmployeeController employeeController;

    @Mock
    EmployeeService employeeService;

    @Mock
    PermanentEmployeeDTO permanentEmployeeDTO;

    @Mock
    ContractEmployeeDTO contractEmployeeDTO;

    @Mock
    Employee permanentEmployee;

    @Mock
    Employee contractEmployee;

    @Mock
    List<Employee> employeeList;


    @Test
    void testAddPermanentEmployeeSuccessfully() {
        // Mock: EmployeeService'den bir çalışan dönmesini sağla
        when(employeeService.addPermanentEmployee(permanentEmployeeDTO)).thenReturn(permanentEmployee);

        // Controller metodunu çağır
        ResponseEntity<Employee> response = employeeController.addPermanentEmployee(permanentEmployeeDTO);

        // Yanıtı doğrula
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(permanentEmployee, response.getBody());

        // EmployeeService metodunun bir kez çağrıldığını doğrula
        verify(employeeService, times(1)).addPermanentEmployee(permanentEmployeeDTO);
    }

    @Test
    void testAddPermanentEmployeeFailure() {
        // Mock: EmployeeService'de bir hata fırlatılacağını belirt
        when(employeeService.addPermanentEmployee(permanentEmployeeDTO))
                .thenThrow(new RuntimeException("Çalışan eklenirken bir hata oluştu"));

        // Controller metodunu çağır ve hata fırlatmasını doğrula
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            employeeController.addPermanentEmployee(permanentEmployeeDTO);
        });

        // Hatanın mesajını doğrula
        assertEquals("Çalışan eklenirken bir hata oluştu", exception.getMessage());

        // EmployeeService metodunun bir kez çağrıldığını doğrula
        verify(employeeService, times(1)).addPermanentEmployee(permanentEmployeeDTO);
    }


    @Test
    void testAddContractEmployeeSuccessfully() {
        // Mock: EmployeeService'den bir çalışan dönmesini sağla
        when(employeeService.addContractEmployee(contractEmployeeDTO)).thenReturn(contractEmployee);

        // Controller metodunu çağır
        ResponseEntity<Employee> response = employeeController.addContractEmployee(contractEmployeeDTO);

        // Yanıtı doğrula
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(contractEmployee, response.getBody());

        // Başarılı test mesajı
        System.out.println("Test başarılı: Sözleşmeli çalışan başarıyla eklendi.");

        // EmployeeService metodunun bir kez çağrıldığını doğrula
        verify(employeeService, times(1)).addContractEmployee(contractEmployeeDTO);
    }

    @Test
    void testAddContractEmployeeFailure() {
        // Mock: EmployeeService'de bir hata fırlatılacağını belirt
        when(employeeService.addContractEmployee(contractEmployeeDTO))
                .thenThrow(new RuntimeException("Sözleşmeli çalışan eklenirken bir hata oluştu"));

        // Controller metodunu çağır ve hata fırlatmasını doğrula
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            employeeController.addContractEmployee(contractEmployeeDTO);
        });

        // Hatanın mesajını doğrula
        assertEquals("Sözleşmeli çalışan eklenirken bir hata oluştu", exception.getMessage());

        // EmployeeService metodunun bir kez çağrıldığını doğrula
        verify(employeeService, times(1)).addContractEmployee(contractEmployeeDTO);
    }


    @Test
    void testListEmployees() {
        // Mock: EmployeeService'den çalışan listesi döndürmesini sağla
        when(employeeService.listEmployees()).thenReturn(Arrays.asList(permanentEmployee, contractEmployee));

        // Controller metodunu çağır
        List<Employee> employees = employeeController.listEmployees();

        // Yanıtı doğrula
        assertEquals(2, employees.size());
        assertTrue(employees.contains(permanentEmployee));
        assertTrue(employees.contains(contractEmployee));

        // Başarılı test mesajı
        System.out.println("Test başarılı: Çalışanlar başarıyla listelendi.");

        // EmployeeService metodunun bir kez çağrıldığını doğrula
        verify(employeeService, times(1)).listEmployees();
    }
}
