package com.finalproject.services;

import com.finalproject.dtos.PermanentTaskDTO;
import com.finalproject.dtos.TemporaryTaskDTO;
import com.finalproject.entities.Employee;
import com.finalproject.entities.Task;
import com.finalproject.exceptions.APIError;
import com.finalproject.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {


    private final TaskRepository taskRepository;
    private final EmployeeService employeeService;

    @Autowired
    public TaskService(TaskRepository taskRepository, EmployeeService employeeService) {
        this.taskRepository = taskRepository;
        this.employeeService = employeeService;
    }

    public Task addTemporaryTask(TemporaryTaskDTO temporaryTaskDTO) {

        Employee isEmployeIdExisist = employeeService.findByEmployeId(temporaryTaskDTO.getCalisanId());
        if (isEmployeIdExisist == null) {
            throw new APIError(404, "Çalışan bulunamadı");
        }

        Task task = temporaryTaskDTO.toEntity();
        task.setEmployee(isEmployeIdExisist);
        return taskRepository.save(task);


    }

    public Task addPermanentTask(PermanentTaskDTO permanentTaskDTO) {

        Employee isEmployeIdExisist = employeeService.findByEmployeId(permanentTaskDTO.getCalisanId());
        if (isEmployeIdExisist == null) {
            throw new APIError(404, "Çalışan bulunamadı");
        }
        Task task = permanentTaskDTO.toEntity();
        task.setEmployee(isEmployeIdExisist);

        return taskRepository.save(task);
    }

    public List getAllTasks() {
        return taskRepository.findAll();
    }

    public List getTasksByEmployeeId(Long id) {
        Employee isEmployeIdExisist = employeeService.findByEmployeId(id);
        if (isEmployeIdExisist == null) {
            throw new APIError(404, "Çalışan bulunamadı");
        }
        return taskRepository.findByEmployeeId(isEmployeIdExisist.getId());
    }






}
