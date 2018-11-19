package Locks;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class AndersonAdaptive implements Lock {

    private AtomicIntegerArray x;
    private boolean[] b;
    private AtomicBoolean[] y;
    private AtomicBoolean[] z;
    private AtomicInteger globalLevel;
    private int[] myLevel;

    public AndersonAdaptive(int numThreads, int knobTurns) {
        x = new AtomicIntegerArray(knobTurns*2);
        b = new boolean[knobTurns*2];
        y = new AtomicBoolean[knobTurns*2];
        z = new AtomicBoolean[knobTurns*2];
        for(int i = 0; i < knobTurns*2; i++) {
            y[i] = new AtomicBoolean(false);
            z[i] = new AtomicBoolean(false);
        }
        globalLevel = new AtomicInteger(0);
        myLevel = new int[numThreads+1];
    }

    public void lock(int threadID) {
        myLevel[threadID] = globalLevel.get();
        while(true) {
            x.set(myLevel[threadID], threadID);
            if(y[myLevel[threadID]].get()) {
                b[myLevel[threadID]] = true;
                while(!(myLevel[threadID] < globalLevel.get()));
                myLevel[threadID] = globalLevel.get();
                continue;
            }
            y[myLevel[threadID]].set(true);
            if(x.get(myLevel[threadID]) != threadID) {
                while(!(b[myLevel[threadID]] || z[myLevel[threadID]].get()));
                if(z[myLevel[threadID]].get()) {
                    while(!(myLevel[threadID] < globalLevel.get()));
                    myLevel[threadID] = globalLevel.get();
                    continue;
                } else {
                    myLevel[threadID] = myLevel[threadID] + 1;
                    continue;
                }
            } else {
                z[myLevel[threadID]].set(true);
                if(!b[myLevel[threadID]]) {
                    return;
                } else {
                    myLevel[threadID] = myLevel[threadID] + 1;
                    continue;
                }
            }
        }
    }

    public void unlock(int threadID) {
        globalLevel.set(myLevel[threadID] + 1);
    }

}