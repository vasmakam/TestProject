package testProject;


public class Fibonacci {
	private static long fibRecord[];

	/*
	 * Constructor initializes the array with the size info (Fibonacci sequence size + 1). 
	 * Assumption: The parameter n is for 1 origin. It means fib(0) = 0 is always returned.
	 * It also calls the recursive function to generate the Fibonacci sequence
	 */
	public Fibonacci(int n) {

		Fibonacci.fibRecord = new long[n+1];

		fibRecMemo(n);
	}

	/*
	 * This recursive function returns the fib(n)-highest Fibonacci number.
	 * It uses recursion as well as memoization to reduce the computation time. fibRecord array stores the intermediate and final results
	 * The order of time complexity is O(N).
	 */
	private  long fibRecMemo(int num){

		if(num <= 0){
			fibRecord[0] = 0;
			return 0;
		}
		if(num == 1){
			fibRecord[1] = 1;
			return 1;
		}
		if(num ==2){
			fibRecord[0] = 0;
			fibRecord[1] = fibRecord[2] = 1;
			return 1; 
		}

		if(fibRecord[num] > 0){
			return fibRecord[num];
		}
		else{
			fibRecord[num] = fibRecMemo(num-1) + fibRecMemo(num-2);
			return fibRecord[num];           
		}

	}

	/*
	 * This method returns the previously computed and stored Fibonacci sequence or calls the recursive function if the requested
	 * number is greater than the stored sequence
	 */
	public long [] getFibonacciSeries(int num) {
		if(num+1 > fibRecord.length)
			fibRecMemo(num);
		return fibRecord;
	}

}
