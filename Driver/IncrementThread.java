package Driver;

import Locks.Lock;

public class IncrementThread implements Runnable {
    
    public Lock lock;
    public pocketInt knob;
    public int knobTurns;
    public int threadID;
    public static volatile boolean entered;

    public IncrementThread(Lock lock, pocketInt knob, int knobTurns, int threadID) {
        this.lock = lock;
        this.knob = knob;
        this.knobTurns = knobTurns;
        this.threadID = threadID;
    }

    public void run() {
        while(knob.getInteger() < knobTurns) {
            lock.lock(threadID);
            if(entered) {
                System.out.print(".");
            }
            entered = true;
            if(knob.getInteger() < knobTurns) knob.increment();
            entered = false;
            lock.unlock(threadID);
        }
    }

}