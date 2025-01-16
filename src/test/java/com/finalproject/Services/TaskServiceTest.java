package com.finalproject.Services;

import com.finalproject.dtos.PermanentTaskDTO;
import com.finalproject.dtos.TemporaryTaskDTO;
import com.finalproject.entities.Employee;
import com.finalproject.entities.Task;
import com.finalproject.exceptions.APIError;
import com.finalproject.repositories.TaskRepository;
import com.finalproject.services.EmployeeService;
import com.finalproject.services.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @InjectMocks
    TaskService taskService;

    @Mock
    TaskRepository taskRepository;

    @Mock
    EmployeeService employeeService;

    @Test
    void testAddTemporaryTaskSuccessfully() {
        // Test verisini oluştur
        TemporaryTaskDTO temporaryTaskDTO = mock(TemporaryTaskDTO.class);
        Long calisanId = temporaryTaskDTO.getCalisanId();

        Task newTask = mock(Task.class);
        Employee employee = mock(Employee.class);

        // Mock: Çalışanın varlığını kontrol et
        lenient().when(employeeService.findByEmployeId(calisanId)).thenReturn(employee);  // lenient() kullanarak esneklik sağla

        // Mock: DTO'yu Entity'ye dönüştür
        when(temporaryTaskDTO.toEntity()).thenReturn(newTask);

        // Mock: Task'i veritabanına kaydet
        when(taskRepository.save(newTask)).thenReturn(newTask);

        // Servis çağrısı
        Task savedTask = taskService.addTemporaryTask(temporaryTaskDTO);

        // Doğrulama: Kaydedilen task objesinin döndüğünü kontrol et
        assertNotNull(savedTask);
        verify(taskRepository, times(1)).save(newTask);
    }


    @Test
    void testAddPermanentTaskSuccessfully() {
        // Test verisini oluştur

        PermanentTaskDTO permanentTaskDTO = mock(PermanentTaskDTO.class);
        Long calisanId = permanentTaskDTO.getCalisanId();
        Task newTask = mock(Task.class);
        Employee employee = mock(Employee.class);

        // Mock: Çalışanın varlığını kontrol et
        lenient().when(employeeService.findByEmployeId(calisanId)).thenReturn(employee); // lenient() kullanarak esneklik sağla

        // Mock: DTO'yu Entity'ye dönüştür
        when(permanentTaskDTO.toEntity()).thenReturn(newTask);

        // Mock: Task'i veritabanına kaydet
        when(taskRepository.save(newTask)).thenReturn(newTask);

        // Servis çağrısı
        Task savedTask = taskService.addPermanentTask(permanentTaskDTO);

        // Doğrulama: Kaydedilen task objesinin döndüğünü kontrol et
        assertNotNull(savedTask);
        verify(taskRepository, times(1)).save(newTask);
    }


    @Test
    void testAddTemporaryTaskFailure() {

        TemporaryTaskDTO temporaryTaskDTO = mock(TemporaryTaskDTO.class);
        Long calisanId = temporaryTaskDTO.getCalisanId();

        // Mock: Çalışan verisi bulunamadığında hata fırlatması bekleniyor
        lenient().when(employeeService.findByEmployeId(calisanId)).thenReturn(null);  // lenient() kullanarak esneklik sağla

        // Servis çağrısı ve hata kontrolü
        assertThrows(APIError.class, () -> {
            taskService.addTemporaryTask(temporaryTaskDTO);
        });
    }

    @Test
    void testAddPermanentTaskFailure() {
        PermanentTaskDTO permanentTaskDTO = mock(PermanentTaskDTO.class);
        Long calisanId = permanentTaskDTO.getCalisanId();


        // Mock: Çalışan verisi bulunamadığında hata fırlatması bekleniyor
        lenient().when(employeeService.findByEmployeId(calisanId)).thenReturn(null);  // lenient() kullanarak esneklik sağla

        // Servis çağrısı ve hata kontrolü
        assertThrows(APIError.class, () -> {
            taskService.addPermanentTask(permanentTaskDTO);
        });
    }




    @Test
    void testGetAllTasks() {
        // Test verilerini oluştur
        Task task1 = mock(Task.class);
        Task task2 = mock(Task.class);
        List<Task> taskList = List.of(task1, task2);

        // Mock: Veritabanında görevlerin olduğunu simüle ediyoruz
        when(taskRepository.findAll()).thenReturn(taskList);

        // Servis çağrısı
        List<Task> tasks = taskService.getAllTasks();

        // Doğrulama: Görevler listesi doğru dönüyor
        assertNotNull(tasks);
        assertEquals(2, tasks.size());
    }

    @Test
    void testGetTasksByEmployeeIdSuccesfully() {
        Long employeeId = 1L;
        Task task1 = mock(Task.class);
        Task task2 = mock(Task.class);
        List<Task> taskList = List.of(task1, task2);
        Employee employee = mock(Employee.class);

        // Mock: Çalışanın var olduğunu ve görevlerin alınabileceğini simüle et
        when(employeeService.findByEmployeId(employeeId)).thenReturn(employee);
        when(employee.getId()).thenReturn(employeeId);  // employee.getId() mocklama ekleyin
        lenient().when(taskRepository.findByEmployeeId(employeeId)).thenReturn(taskList);

        // Servis çağrısı
        List<Task> tasks = taskService.getTasksByEmployeeId(employeeId);

        // Doğrulama: Görevler listesi doğru dönüyor
        assertNotNull(tasks);
        assertEquals(2, tasks.size());
    }

    @Test
    void testGetTasksByEmployeeIdFailure() {
        Long employeeId = 1L;

        // Mock: Çalışanın var olmadığını simüle et
        when(employeeService.findByEmployeId(employeeId)).thenReturn(null);  // Employee null dönüyor

        // Servis çağrısı ve exception kontrolü
        assertThrows(APIError.class, () -> taskService.getTasksByEmployeeId(employeeId));
    }


    @Test
    void testGetTasksByNonExistingEmployeeId() {
        // Test verilerini oluştur
        Long employeeId = 1L;

        // Mock: Çalışanın bulunamadığını simüle et
        when(employeeService.findByEmployeId(employeeId)).thenThrow(new APIError(404, "Çalışan bulunamadı"));

        // APIError bekliyoruz çünkü çalışan bulunamadı
        assertThrows(APIError.class, () -> {
            taskService.getTasksByEmployeeId(employeeId);
        });
    }
}
