package Driver;

import Locks.Lock;

public class IncrementThread implements Runnable {
	
	public Lock lock;
	public Integer knob;
	public int knobTurns;
	public int threadID;
	
	public IncrementThread(Lock lock, Integer knob, int knobTurns, int threadID) {
		this.lock = lock;
		this.knob = knob;
		this.knobTurns = knobTurns;
		this.threadID = threadID;
	}
	
	public void run() {
		while(true) {
			lock.lock(threadID);
			if(knob < knobTurns) {
				knob++;
			} else {
				lock.unlock(threadID);
				break;
			}
			lock.unlock(threadID);
		}
	}
	
}
