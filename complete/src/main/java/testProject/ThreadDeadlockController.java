package testProject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ThreadDeadlockController {

	/**
	 * @return String - Text indicating the two threads are in deadlock
	 */
	@RequestMapping ("/createThreads")
	public ResponseEntity<String> createThreadDeadlock () {
		Object obj1 = new Object();
		Object obj2 = new Object();


		Thread t1 = new Thread(new UnsafeThread(obj1, obj2), "Thread1");
		Thread t2 = new Thread(new UnsafeThread(obj2, obj1), "Thread2");

		t1.start();
		t2.start();

		work(5000L); // allow some time for the threads to get into deadlock
		return new ResponseEntity<String> ("Two threads "+t1.getName()+" and "+t2.getName()+" are in deadlock.", HttpStatus.OK);
	}

	/*
	 * @return String - If the threads are in deadlock, return details of the threads to REST client
	 */
	@RequestMapping ("/monitorThreads")
	public ResponseEntity <String> monitorDeadlockedThreads() {
		final String threadDetails;
		work(1000L);
		DeadlockDetector deadlockDetector = new DeadlockDetector();
		threadDetails = deadlockDetector.deadlockCheck();

		return new ResponseEntity<String> (threadDetails, HttpStatus.OK);
	}

	private void work(long t) {
		try {
			Thread.sleep(t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
