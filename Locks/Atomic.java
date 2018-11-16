package Locks;

import java.util.concurrent.atomic.AtomicInteger;

public class Atomic implements Lock {
	
	private AtomicInteger isOccupied;
	
	public Atomic(int numThreads) {
		isOccupied = new AtomicInteger(0);
	}
	
	public void lock(int threadID) {
		while(!isOccupied.compareAndSet(0,1));
	}
	
	public void unlock(int threadID) {
		isOccupied.set(0);
	}
	
}