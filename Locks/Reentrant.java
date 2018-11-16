package Locks;

import java.util.concurrent.locks.ReentrantLock;

public class Reentrant implements Lock {
	
	private ReentrantLock lock;
	
	public Reentrant(int numThreads) {
		lock = new ReentrantLock();
	}
	
	public void lock(int threadID) {
		lock.lock();		
	}

	public void unlock(int threadID) {
		lock.unlock();		
	}
    
}