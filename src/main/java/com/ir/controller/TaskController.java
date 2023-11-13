package com.ir.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ir.dao.TaskDataStore;
import com.ir.model.TaskRequest;
import com.ir.model.TaskResponse;
import com.ir.model.Task;
import com.ir.util.IdCache;

import jakarta.validation.Valid;

@RestController
public class TaskController {
	
	@Autowired
	private TaskDataStore taskDataStore;
	
	@PostMapping(value = "/tasks", produces = "application/json")
	public TaskResponse createTask(@Valid @RequestBody Task task) throws UnsupportedEncodingException {
		
		TaskResponse taskResponse = new TaskResponse();
		
		long nextId = IdCache.getNextId();
		if(task.getCompleted()) {
			task.setCompletedDate(new Date());
		} 
		taskDataStore.getTasks().put(nextId, task);
		
		taskResponse.setId(nextId);
		taskResponse.setTask(task);

		return taskResponse;
	}
	
	@PutMapping(value = "/tasks/:id", produces = "application/json")
	public TaskResponse updateTask(@RequestBody TaskRequest request) throws UnsupportedEncodingException {

		TaskResponse taskResponse = new TaskResponse();
		
		Task origTask = taskDataStore.getTasks().get(request.getId());
		
		if(origTask != null) {
			if(request.getTask().getCompleted()) {
				request.getTask().setCompletedDate(new Date());
			} else {
				request.getTask().setCompletedDate(null);
			}
			taskDataStore.getTasks().replace(request.getId(), request.getTask());
			taskResponse.setId(request.getId());
			taskResponse.setTask(request.getTask());
		} else {
			taskResponse.setMessage("Task ID: " + request.getId() + " NOT Found");
		}
		
		return taskResponse;
	}
	
	@GetMapping(value = "/tasks", produces = "application/json")
	public Map<Long, Task> getAllTasks() {

		return taskDataStore.getTasks();
	}
	
	@GetMapping(value = "/tasks/:id", produces = "application/json")
	public TaskResponse getTasks(@RequestBody TaskRequest request) throws UnsupportedEncodingException {

		TaskResponse taskResponse = new TaskResponse();
		
		Task task = taskDataStore.getTasks().get(request.getId());
		
		if(task != null) {
			taskResponse.setId(request.getId());
			taskResponse.setTask(task);
		} else {
			taskResponse.setMessage("Task ID: " + request.getId() + " NOT Found");
		}
		
		return taskResponse;
	}
	
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
	
}
