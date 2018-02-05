package testProject;

import static org.junit.Assert.*;

import org.junit.Test;

public class FibonacciTest {


	@Test
	public void testGetFibonacciSeries() {
		int n = 5;
		long [] expSeq = {0,1,1,2,3,5};
		Fibonacci fib = new Fibonacci(5);
		long [] seq = fib.getFibonacciSeries(n);
		assertTrue(seq.length==expSeq.length);
		
		for(int i=0; i<seq.length; i++) {
			assertTrue(seq[i]==expSeq[i]);
		}
	}

}
