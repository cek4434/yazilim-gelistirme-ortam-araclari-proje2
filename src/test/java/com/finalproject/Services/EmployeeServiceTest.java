package com.finalproject.Services;

import com.finalproject.dtos.ContractEmployeeDTO;
import com.finalproject.entities.Employee;
import com.finalproject.dtos.PermanentEmployeeDTO;
import com.finalproject.exceptions.APIError;
import com.finalproject.repositories.EmployeeRepository;
import com.finalproject.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class) // MockitoExtension ekleyin
public class EmployeeServiceTest {

    @InjectMocks
    EmployeeService employeeService;

    @Mock
    EmployeeRepository employeeRepository;

    @Test
    void testAddPermanentEmployeeSuccessfully() {
        // Test verisini oluştur
        String sicilNo = "78965713"; // Sicil numarasını dışarıdan belirliyoruz
        PermanentEmployeeDTO permanentEmployeeDTO = mock(PermanentEmployeeDTO.class);
        Employee newEmployee = mock(Employee.class);

        // Mock: Sicil numarasını DTO'dan alıyoruz
        when(permanentEmployeeDTO.getSicilNo()).thenReturn(sicilNo);

        // Mock: Veritabanında bu sicil numarasına sahip bir çalışan olmadığını simüle ediyoruz
        when(employeeRepository.findBySicilNo(sicilNo)).thenReturn(Optional.empty());

        // Mock: DTO'yu Entity'ye dönüştür
        when(permanentEmployeeDTO.toEntity()).thenReturn(newEmployee);

        // Mock: Çalışan kaydedilecek
        when(employeeRepository.save(newEmployee)).thenReturn(newEmployee);

        // Servis çağrısı
        Employee savedEmployee = employeeService.addPermanentEmployee(permanentEmployeeDTO);

        // Doğrulama: Kaydedilen çalışanın döndüğünü kontrol et
        assertNotNull(savedEmployee);
        verify(employeeRepository, times(1)).save(newEmployee);
    }

    @Test
    void testAddContractEmployeeSuccessfully() {
        // Test verisini oluştur
        String sicilNo = "78965713"; // Sicil numarasını dışarıdan belirliyoruz
        ContractEmployeeDTO contractEmployeeDTO = mock(ContractEmployeeDTO.class);
        Employee newEmployee = mock(Employee.class);

        // Mock: Sicil numarasını DTO'dan alıyoruz
        when(contractEmployeeDTO.getSicilNo()).thenReturn(sicilNo);

        // Mock: Veritabanında bu sicil numarasına sahip bir çalışan olmadığını simüle ediyoruz
        when(employeeRepository.findBySicilNo(sicilNo)).thenReturn(Optional.empty());

        // Mock: DTO'yu Entity'ye dönüştür
        when(contractEmployeeDTO.toEntity()).thenReturn(newEmployee);

        // Mock: Çalışan kaydedilecek
        when(employeeRepository.save(newEmployee)).thenReturn(newEmployee);

        // Servis çağrısı
        Employee savedEmployee = employeeService.addContractEmployee(contractEmployeeDTO);

        // Doğrulama: Kaydedilen çalışanın döndüğünü kontrol et
        assertNotNull(savedEmployee);
        verify(employeeRepository, times(1)).save(newEmployee);
    }


    @Test
    void testAddPermanentEmployeeWithExistingSicilNo() {
        // Test verisini oluştur
        String sicilNo = "78965713"; // Sicil numarasını dışarıdan belirliyoruz
        PermanentEmployeeDTO permanentEmployeeDTO = mock(PermanentEmployeeDTO.class);
        Employee existingEmployee = mock(Employee.class);

        // Mock: Sicil numarasını DTO'dan alıyoruz
        when(permanentEmployeeDTO.getSicilNo()).thenReturn(sicilNo);

        // Mock: Veritabanında bu sicil numarasına sahip bir çalışan olduğunu simüle ediyoruz
        when(employeeRepository.findBySicilNo(sicilNo)).thenReturn(Optional.of(existingEmployee));

        // APIError bekliyoruz çünkü sicil numarası zaten mevcut
        assertThrows(APIError.class, () -> {
            employeeService.addPermanentEmployee(permanentEmployeeDTO);
        });
    }

    @Test
    void testAddContractEmployeeWithExistingSicilNo() {
        // Test verisini oluştur
        String sicilNo = "78965713"; // Sicil numarasını dışarıdan belirliyoruz
        ContractEmployeeDTO contractEmployeeDTO = mock(ContractEmployeeDTO.class);
        Employee existingEmployee = mock(Employee.class);

        // Mock: Sicil numarasını DTO'dan alıyoruz
        when(contractEmployeeDTO.getSicilNo()).thenReturn(sicilNo);

        // Mock: Veritabanında bu sicil numarasına sahip bir çalışan olduğunu simüle ediyoruz
        when(employeeRepository.findBySicilNo(sicilNo)).thenReturn(Optional.of(existingEmployee));

        // APIError bekliyoruz çünkü sicil numarası zaten mevcut
        assertThrows(APIError.class, () -> {
            employeeService.addContractEmployee(contractEmployeeDTO);
        });
    }


    @Test
    void testListEmployees() {
        // Test verilerini oluştur
        Employee employee1 = mock(Employee.class);
        Employee employee2 = mock(Employee.class);
        List<Employee> employeeList = List.of(employee1, employee2);

        // Mock: Veritabanında çalışanlar olduğunu simüle ediyoruz
        when(employeeRepository.findAll()).thenReturn(employeeList);

        // Servis çağrısı
        List<Employee> employees = employeeService.listEmployees();

        // Doğrulama: Çalışanlar listesi doğru dönüyor
        assertNotNull(employees);
        assertEquals(2, employees.size());
    }

    @Test
    void testFindEmployeeByEmployeIdNotFound() {
        Long invalidEmployeeId = 123L; // Geçersiz çalışan ID'si

        // Mock: Çalışan bulunamadığını simüle ediyoruz
        when(employeeRepository.findById(invalidEmployeeId)).thenReturn(Optional.empty());

        // APIError bekliyoruz çünkü çalışan bulunamadı
        assertThrows(APIError.class, () -> {
            employeeService.findByEmployeId(invalidEmployeeId);
        });
    }














}
