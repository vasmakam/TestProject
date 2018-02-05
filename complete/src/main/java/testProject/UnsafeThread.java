package testProject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnsafeThread implements Runnable{

	private static final Logger log = LoggerFactory.getLogger(Application.class);
	private Object obj1;
	private Object obj2;

	/*
	 * Constructor initializes its two objects
	 */
	public UnsafeThread(Object o1, Object o2){
		this.obj1=o1;
		this.obj2=o2;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 * The run() method uses nested synchronized blocks to acquire two objects one after the other after a small wait
	 * The thread is blocked waiting for each object to be available.
	 */
	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		log.info("Thread "+name + " acquiring lock on "+obj1);
		synchronized (obj1) {
			log.info("Thread "+name + " acquired lock on "+obj1);
			work();
			log.info("Thread "+name + " acquiring lock on "+obj2);
			synchronized (obj2) {
				log.info("Thread "+name + " acquired lock on "+obj2);

			}
			log.info("Thread "+name + " released lock on "+obj2);
		}
		log.info("Thread "+name + " released lock on "+obj1);
		log.info("Thread "+name + " finished execution.");
	}

	private void work() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

