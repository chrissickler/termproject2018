package Locks;

public class OneBitAdaptive implements Lock {

    private volatile boolean[] b;
    private int numThreads;

    public OneBitAdaptive(int numThreads) {
        this.numThreads = numThreads;
        b = new boolean[numThreads+1];
        for(int i = 0; i < numThreads+1; i++) {
            b[i] = false;
        }
    }

    public void lock(int threadID) {
        int j;
        do {
            b[threadID] = true;
            j = 1;
            while((b[threadID] == true) && (j < threadID)) {
                if(b[j] == true) {
                    b[threadID] = false;
                    do {
                        if(b[threadID] == true) {
                            b[threadID] = false;
                        }
                    } while(b[j] == true);
                }
                j++;
            }
        } while(b[threadID] != true);
        for(j = threadID + 1; j < numThreads+1; j++) {
            do {
                if(b[threadID] == true) {
                    b[threadID] = false;
                }
            } while(b[j] == true);
        }
    }

    public void unlock(int threadID) {
        b[threadID] = false;
    }

}