package com.ir.dao;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Component;

import com.ir.model.Task;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter @Setter
public class TaskDataStore {
	
	ConcurrentMap<Long, Task> tasks;
	
	public TaskDataStore() {
		this.tasks = new ConcurrentHashMap<>();
	}
	
}



