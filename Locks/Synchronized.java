package Locks;

import java.util.concurrent.Semaphore;

public class Synchronized implements Lock {

    private Semaphore sema;
    
    public Synchronized(int numThreads) {
        this.sema = new Semaphore(1);
    }

    public void lock(int threadID) {
        try {
            sema.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void unlock(int threadID) {
        sema.release();
    }

}