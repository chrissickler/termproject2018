package Driver;

import java.util.ArrayList;

import Locks.Lock;

public class ThreadLauncher {
	
	public Lock lock;
	public Integer knob;
	public int knobTurns;
	public int numThreads;
	public Result result;
	
	public ThreadLauncher(Lock lock, Integer knob, int knobTurns, int numThreads) {
		this.lock = lock;
		this.knob = knob;
		this.knobTurns = knobTurns;
		this.numThreads = numThreads;
	}
	
	public void fireThreads() {
		ArrayList<Thread> team = new ArrayList<Thread>();
		for(int i = 0; i < numThreads; i++) {
			Thread worker = new Thread(new IncrementThread(lock, knob, knobTurns, i));
			team.add(worker);
		}
    	long startTime = System.nanoTime();
		for(Thread worker : team) {
			worker.start();
		}
		for(Thread worker : team) {
			try {
				worker.join();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		long endTime = System.nanoTime();
		result = new Result(startTime, endTime);
	}
	
	public String getResult() {
		return(result.getTotalTime());
	}
}
