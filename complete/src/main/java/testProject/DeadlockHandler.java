package testProject;

import java.lang.management.ThreadInfo;

public interface DeadlockHandler {
	String handleDeadlock(final ThreadInfo[] deadlockedThreads);
}