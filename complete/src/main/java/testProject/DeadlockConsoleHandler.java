package testProject;

import java.lang.management.ThreadInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeadlockConsoleHandler implements DeadlockHandler {

	private static final Logger log = LoggerFactory.getLogger(Application.class);
	private String blockedThreads = "";

	/* (non-Javadoc)
	 * @see testProject.DeadlockHandler#handleDeadlock(java.lang.management.ThreadInfo[])
	 * This method from DeadlockHandler interface implements checking and extracting the information on the deadlocked threads
	 * Information gathered are: name, id, priority, resource object being locked, and the owner thread name
	 */
	@Override
	public String handleDeadlock(final ThreadInfo[] deadlockedThreads) {
		if (deadlockedThreads != null) {
			//System.err.println("Deadlock detected!");
			log.error("Deadlock detected!");


			for (ThreadInfo threadInfo : deadlockedThreads) {

				if (threadInfo != null) {

					for (Thread thread : Thread.getAllStackTraces().keySet()) {

						if (thread.getId() == threadInfo.getThreadId()) {

							//System.err.println(threadInfo.toString().trim());
							log.error(threadInfo.toString().trim());
							blockedThreads = blockedThreads+threadInfo.toString().trim()+"\n";

							for (StackTraceElement ste : thread.getStackTrace()) {
								//System.err.println("\t" + ste.toString().trim());
								log.error("\t" + ste.toString().trim());
								//blockedThreads = blockedThreads+"\t"+ste.toString().trim()+"\n";
							}
						}
					}
				}
			}
		}
		return blockedThreads;
	}

}