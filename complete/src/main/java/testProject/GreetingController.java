package testProject;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String hello = "HELLO WORLD";


	@RequestMapping("/")
	public String greeting() {
		return (hello);
	}
}
