package Driver;

import Locks.Lock;

public class IncrementThread implements Runnable {
    
    public Lock lock;
    public pocketInt knob;
    public int knobTurns;
    public int threadID;

    public IncrementThread(Lock lock, pocketInt knob, int knobTurns, int threadID) {
        this.lock = lock;
        this.knob = knob;
        this.knobTurns = knobTurns;
        this.threadID = threadID;
    }

    public void run() {
        while(knob.getInteger() < knobTurns) {
            lock.lock(threadID);
            if(knob.getInteger() < knobTurns) knob.increment();
            lock.unlock(threadID);
        }
    }

}