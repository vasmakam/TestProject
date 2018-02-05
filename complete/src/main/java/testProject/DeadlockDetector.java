package testProject;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeadlockDetector {

	private String blockedThreadsInfo;
	private final DeadlockHandler deadlockHandler = new DeadlockConsoleHandler();
	private final ThreadMXBean mbean = ManagementFactory.getThreadMXBean();

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	/**
	 * @return String - details on deadlocked threads
	 * This is a service method called from ThreadDeadlock REST Controller
	 * 
	 */
	public String deadlockCheck() {
		long[] deadlockedThreadIds = DeadlockDetector.this.mbean.findDeadlockedThreads();
		log.info("Starting Deadllock Detector.");

		if (deadlockedThreadIds != null) {
			ThreadInfo[] threadInfos = 
					DeadlockDetector.this.mbean.getThreadInfo(deadlockedThreadIds);

			blockedThreadsInfo = DeadlockDetector.this.deadlockHandler.handleDeadlock(threadInfos);
			log.info("Returning blocked threads Details.");
		}
		return blockedThreadsInfo;
	}




}