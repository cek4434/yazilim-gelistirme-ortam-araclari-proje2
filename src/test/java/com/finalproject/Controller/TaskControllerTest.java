package com.finalproject.Controller;

import com.finalproject.controllers.TaskController;
import com.finalproject.dtos.PermanentTaskDTO;
import com.finalproject.dtos.TemporaryTaskDTO;
import com.finalproject.entities.Task;
import com.finalproject.exceptions.APIError;
import com.finalproject.services.TaskService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @Test
    void testAddTemporaryTaskSuccess() {
        // Mock: TemporaryTaskDTO nesnesini oluştur
        TemporaryTaskDTO temporaryTaskDTO = mock(TemporaryTaskDTO.class);
        lenient().when(temporaryTaskDTO.getGorevAdi()).thenReturn("Sample Task"); // lenient kullanıldı

        // Mock: Abstract Task nesnesini oluştur
        Task task = mock(Task.class);
        lenient().when(task.getGorevAdi()).thenReturn("Sample Task"); // lenient kullanıldı

        // Mock: TaskService'den bir görev dönmesini sağla
        when(taskService.addTemporaryTask(temporaryTaskDTO)).thenReturn(task);

        // Controller metodunu çağır
        Task response = taskController.addTemporaryTask(temporaryTaskDTO);

        // Yanıtı doğrula
        assertNotNull(response, "Dönen görev null olmamalı!");
        assertEquals(task, response, "Dönen görev mock nesnesiyle aynı olmalı!");

        // TaskService'in bir kez çağrıldığını doğrula
        verify(taskService, times(1)).addTemporaryTask(temporaryTaskDTO);
    }

    @Test
    void testAddTemporaryTaskFailure() {
        // Mock: TemporaryTaskDTO nesnesini oluştur
        TemporaryTaskDTO temporaryTaskDTO = mock(TemporaryTaskDTO.class);
        lenient().when(temporaryTaskDTO.getGorevAdi()).thenReturn("Sample Task"); // lenient kullanıldı

        // Mock: Abstract Task nesnesini oluştur
        Task task = mock(Task.class);
        lenient().when(task.getGorevAdi()).thenReturn("Sample Task"); // lenient kullanıldı

        // Mock: TaskService'den görev beklenen şekilde dönmüyor (null dönsün)
        when(taskService.addTemporaryTask(temporaryTaskDTO)).thenReturn(null); // null dönmesi sağlanıyor

        // Controller metodunu çağır
        Task response = taskController.addTemporaryTask(temporaryTaskDTO);

        // Yanıtı doğrula: response null olmalı
        assertNull(response, "Dönen görev null olmalı!");

        // TaskService'in bir kez çağrıldığını doğrula
        verify(taskService, times(1)).addTemporaryTask(temporaryTaskDTO);
    }

    @Test
    void testAddPermanentTaskSuccess() {
        // Mock: PermanentTaskDTO nesnesini oluştur
        PermanentTaskDTO permanentTaskDTO = mock(PermanentTaskDTO.class);
        lenient().when(permanentTaskDTO.getGorevAdi()).thenReturn("Permanent Task");

        // Mock: Abstract Task nesnesini oluştur
        Task task = mock(Task.class);
        lenient().when(task.getGorevAdi()).thenReturn("Permanent Task");

        // Mock: TaskService'den bir görev dönmesini sağla
        when(taskService.addPermanentTask(permanentTaskDTO)).thenReturn(task);

        // Controller metodunu çağır
        Task response = taskController.addPermanentTask(permanentTaskDTO);

        // Yanıtı doğrula
        assertNotNull(response, "Dönen görev null olmamalı!");
        assertEquals(task, response, "Dönen görev mock nesnesiyle aynı olmalı!");

        // TaskService'in bir kez çağrıldığını doğrula
        verify(taskService, times(1)).addPermanentTask(permanentTaskDTO);
    }

    @Test
    void testAddPermanentTaskFailure() {
        // Mock: PermanentTaskDTO nesnesini oluştur
        PermanentTaskDTO permanentTaskDTO = mock(PermanentTaskDTO.class);
        lenient().when(permanentTaskDTO.getGorevAdi()).thenReturn("Permanent Task");

        // Mock: Abstract Task nesnesini oluştur
        Task task = mock(Task.class);
        lenient().when(task.getGorevAdi()).thenReturn("Permanent Task");

        // Mock: TaskService'den görev beklenen şekilde dönmüyor (null dönsün)
        when(taskService.addPermanentTask(permanentTaskDTO)).thenReturn(null);

        // Controller metodunu çağır
        Task response = taskController.addPermanentTask(permanentTaskDTO);

        // Yanıtı doğrula: response null olmalı
        assertNull(response, "Dönen görev null olmalı!");

        // TaskService'in bir kez çağrıldığını doğrula
        verify(taskService, times(1)).addPermanentTask(permanentTaskDTO);
    }

    @Test
    void testGetAllTasksSuccess() {
        // Mock: Task listesi oluştur
        Task task1 = mock(Task.class);
        Task task2 = mock(Task.class);
        List<Task> taskList = Arrays.asList(task1, task2);

        // Mock: TaskService'den görev listesi dönmesini sağla
        when(taskService.getAllTasks()).thenReturn(taskList);

        // Controller metodunu çağır
        List<Task> response = taskController.getAllTasks();

        // Yanıtı doğrula
        assertNotNull(response, "Dönen görev listesi null olmamalı!");
        assertEquals(taskList, response, "Dönen görev listesi mock listesiyle aynı olmalı!");

        // TaskService'in bir kez çağrıldığını doğrula
        verify(taskService, times(1)).getAllTasks();
    }

    @Test
    void testGetTasksByEmployeeIdSuccess() {
        // Mock: Employee ID ve Task listesi oluştur
        Long employeeId = 1L;
        Task task1 = mock(Task.class);
        Task task2 = mock(Task.class);
        List<Task> taskList = Arrays.asList(task1, task2);

        // Mock: TaskService'den belirli bir çalışan için görev listesi dönmesini sağla
        when(taskService.getTasksByEmployeeId(employeeId)).thenReturn(taskList);

        // Controller metodunu çağır
        List<Task> response = taskController.getTasksByEmployeeId(employeeId);

        // Yanıtı doğrula
        assertNotNull(response, "Dönen görev listesi null olmamalı!");
        assertEquals(taskList, response, "Dönen görev listesi mock listesiyle aynı olmalı!");

        // TaskService'in bir kez çağrıldığını doğrula
        verify(taskService, times(1)).getTasksByEmployeeId(employeeId);
    }

    @Test
    void testGetTasksByEmployeeIdFailure() {
        // Mock: Employee ID ve boş Task listesi oluştur
        Long employeeId = 1L;
        List<Task> emptyTaskList = Arrays.asList();

        // Mock: TaskService'den belirli bir çalışan için boş görev listesi dönmesini sağla
        when(taskService.getTasksByEmployeeId(employeeId)).thenReturn(emptyTaskList);

        // Controller metodunu çağır
        List<Task> response = taskController.getTasksByEmployeeId(employeeId);

        // Yanıtı doğrula: response boş olmalı
        assertTrue(response.isEmpty(), "Dönen görev listesi boş olmalı!");

        // TaskService'in bir kez çağrıldığını doğrula
        verify(taskService, times(1)).getTasksByEmployeeId(employeeId);
    }




}
