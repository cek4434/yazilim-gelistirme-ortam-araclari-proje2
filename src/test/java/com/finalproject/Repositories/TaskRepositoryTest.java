package com.finalproject.repositories;

import com.finalproject.entities.Employee;
import com.finalproject.entities.Task;
import com.finalproject.repositories.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskRepositoryTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private Task task1;

    @Mock
    private Task task2;

    @Mock
    private Employee employee;



    @Test
    void testFindByEmployeeId() {
        // Mock repository'yi doğru şekilde yapılandırıyoruz
        when(taskRepository.findByEmployeeId(1L)).thenReturn(Arrays.asList(task1, task2));

        // findByEmployeeId metodunu test ediyoruz
        List<Task> tasks = taskRepository.findByEmployeeId(1L);

        // Sonuçları doğruluyoruz
        assertNotNull(tasks, "Görev listesi boş olmamalıdır");
        assertTrue(tasks.size() == 2, "Verilen çalışan ID'si için 2 görev olmalıdır");
        assertTrue(tasks.contains(task1), "Görev listesi task1'i içermelidir");
        assertTrue(tasks.contains(task2), "Görev listesi task2'yi içermelidir");
    }

    @Test
    void testFindByEmployeeIdNoTasks() {
        // Mock repository'yi doğru şekilde yapılandırıyoruz, çalışan için hiç görev yok
        when(taskRepository.findByEmployeeId(1L)).thenReturn(Arrays.asList());

        // findByEmployeeId metodunu test ediyoruz
        List<Task> tasks = taskRepository.findByEmployeeId(1L);

        // Sonuçları doğruluyoruz
        assertNotNull(tasks, "Görev listesi boş olmamalıdır");
        assertTrue(tasks.isEmpty(), "Verilen çalışan ID'si için hiç görev olmamalıdır");
    }
}
