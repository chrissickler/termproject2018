package Locks;

import java.util.concurrent.atomic.AtomicBoolean;

public class OneBit implements Lock {

    private AtomicBoolean[] b;
    private int numThreads;

    public OneBit(int numThreads) {
        this.numThreads = numThreads;
        b = new AtomicBoolean[numThreads+1];
        for(int i = 0; i < numThreads+1; i++) {
            b[i] = new AtomicBoolean(false);
        }
    }

    public void lock(int threadID) {
        int j;
        do {
            b[threadID].set(true);
            j = 1;
            while((b[threadID].get() == true) && (j < threadID)) {
                if(b[j].get() == true) {
                    b[threadID].set(false);
                    while(b[j].get() == true);
                }
                j++;
            }
        } while(b[threadID].get() != true);
        for(j = threadID + 1; j < numThreads+1; j++) {
            while(b[j].get() == true);
        }
    }

    public void unlock(int threadID) {
        b[threadID].set(false);
    }

}