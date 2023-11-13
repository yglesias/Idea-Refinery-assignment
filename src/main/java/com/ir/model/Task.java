package com.ir.model;

import java.util.Date;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.NotNull;


import lombok.Getter;
import lombok.Setter;

@Validated
@Getter @Setter
public class Task {
	
	@NotNull(message = "Title cannot be NULL")
	private String title;
	@NotNull(message = "Description cannot be NULL")
	private String description;
	private Boolean completed;
	private Date createDate;
	@JsonInclude(Include.NON_NULL)
	private Date completedDate;
	
	
	public Task(String title, String description) {
		
		this.title = title;
		this.description = description;
		this.completed = false;
		this.createDate = new Date();
				
	}
	

}
