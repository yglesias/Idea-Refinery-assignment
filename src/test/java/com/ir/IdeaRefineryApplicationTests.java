package com.ir;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ir.controller.TaskController;

@SpringBootTest
class IdeaRefineryApplicationTests {

	@Autowired
	private TaskController taskController;

	@Test
	void contextLoads() {
		
		assertThat(taskController).isNotNull();
	}

}
