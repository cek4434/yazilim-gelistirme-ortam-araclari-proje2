package com.finalproject.controllers;

import com.finalproject.dtos.PermanentTaskDTO;
import com.finalproject.dtos.TemporaryTaskDTO;
import com.finalproject.entities.Task;
import com.finalproject.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    @Autowired
    public TaskController (TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping("/temporary")
    public Task addTemporaryTask(@Valid @RequestBody TemporaryTaskDTO temporaryTaskDTO) {
        return taskService.addTemporaryTask(temporaryTaskDTO);
    }

    @PostMapping("/permanent")
    public Task addPermanentTask(@Valid @RequestBody PermanentTaskDTO permanentTaskDTO) {
        return taskService.addPermanentTask(permanentTaskDTO);
    }

    @GetMapping()
    public List getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping(path = "/{id}")
    public List getTasksByEmployeeId(@PathVariable Long id) {
        return taskService.getTasksByEmployeeId(id);
    }



}
