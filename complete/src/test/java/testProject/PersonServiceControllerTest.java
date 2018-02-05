package testProject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import mockit.Expectations;
import mockit.FullVerifications;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;


@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@AutoConfigureMockMvc
public class PersonServiceControllerTest {


	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private MockMvc mockMvc;

	
    @Before
    public void setUp()
    {
        mockMvc = MockMvcBuilders.standaloneSetup(personRepository)
                .build();
    }
	 

	@Before
	public void deleteAllBeforeTests() throws Exception {
		personRepository.deleteAll();
		log.info("DB cleaned up before tests are run");
	}

	// Testing the controller and persistence layers for adding data to the database by mocking the MVC
	//@Ignore
	@Test
	public void addPeopleViaRESTService() throws Exception{

	

		this.mockMvc.perform(post("/persons").contentType(MediaType.APPLICATION_JSON).content("{\"firstName\": \"Alex\", \"lastName\":\"Jones\"}"))
		.andDo(print())
		.andExpect(content().string("Success!"));



	}

	


}

