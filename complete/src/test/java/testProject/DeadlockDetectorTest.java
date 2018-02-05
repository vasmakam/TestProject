package testProject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DeadlockDetectorTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void createDeadlock() throws Exception {
		this.mockMvc.perform(get("/createThreads"))
		.andDo(print())
		.andExpect(status().isOk());
	}

	@Test
	public void detectDeadlock() throws Exception {
		this.mockMvc.perform(get("/monitorThreads"))
		.andDo(print())
		.andExpect(status().isOk());


	}
}
