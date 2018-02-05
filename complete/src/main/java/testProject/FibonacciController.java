package testProject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 * This REST controller method fibNumber gets a parameter N=size of Fibonacci sequence and returns an array of integers
 * as Fibonacci sequence
 * Assumption: N should be less than 93 since the long integers will overflow after fib(92)
 * 
 */
@RestController
public class FibonacciController {

	@RequestMapping (value="/fibonacci", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<long []> fibNumber (@RequestParam String N) {
		int num = Integer.parseInt(N);
		long [] fibSeq;
		Fibonacci fib = new Fibonacci (num);
		fibSeq = fib.getFibonacciSeries(num);
		return new ResponseEntity<long []> (fibSeq, HttpStatus.OK);
	}

}
