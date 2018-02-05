package testProject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class ExternalRESTSvcController {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	/*
	 * This REST controller uses RestTemplate to invoke an external REST service to get a JSON array as string and returns it to the client
	 */
	@RequestMapping(value = "/getTypicodes", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<String> getTypicodes(RestTemplate restTemplate) {

		String result;

		// Call external REST service to receive a JSON array as string
		result = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", String.class);	
		
		ResponseEntity <String> resp = new ResponseEntity<String>(result, HttpStatus.OK);
		
		return resp;
	}

}
