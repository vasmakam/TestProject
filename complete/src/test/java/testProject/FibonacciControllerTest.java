package testProject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FibonacciControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void fibonacciTest() throws Exception {

		String N = "{\"N\":\"7\"}";

		System.out.println(N);
		String fibSeq = "[0,1,1,2,3,5,8,13]";

		this.mockMvc.perform(get("/fibonacci").param("N","7"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(fibSeq));

	}


}
