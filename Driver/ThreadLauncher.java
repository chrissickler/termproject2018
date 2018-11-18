package Driver;

import java.util.ArrayList;
import Locks.Lock;

public class ThreadLauncher {

    public Lock lock;
    public pocketInt knob;
    public int knobTurns;
    public int numThreads;
    public Result result;

    public ThreadLauncher(Lock lock, pocketInt knob, int knobTurns, int numThreads) {
        this.lock = lock;
        this.knob = knob;
        this.knobTurns = knobTurns;
        this.numThreads = numThreads;
    }

    public void fireThreads() {
        ArrayList<Thread> team = new ArrayList<Thread>();
        for(int i = 1; i < numThreads+1; i++) {
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