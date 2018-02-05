package testProject;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@WebMvcTest(TextParserController.class)
public class TextParserControllerTest {

	private static final Logger log = LoggerFactory.getLogger(Application.class);
	@Autowired
	private MockMvc mockMvc;



	@Test
	public void getUniqueWords() throws Exception {


		String textJson = "{\"para\":"+"\"eight times 8  =  8 x Eight. \"}";
		log.info("Input text in JSON:"+textJson);

		final String expText = "[{\"word\":\"8\",\"count\":2},{\"word\":\"=\",\"count\":1},{\"word\":\"Eight\",\"count\":2},{\"word\":\"times\",\"count\":1},{\"word\":\"x\",\"count\":1}]";

		this.mockMvc.perform(post("/uniqueWords").content(textJson).contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(expText));

	}

}
