package Locks;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class ColoredBakery implements Lock {

	private static final boolean Black = true;
	private static final boolean White = false;
	private int numThreads;
	private volatile AtomicBoolean color; // could be atomic
	private AtomicBoolean[] choosing;
	private AtomicIntegerArray numbers;
	private AtomicBoolean[] colors;
	
	public ColoredBakery(int numThreads) {
		this.numThreads = numThreads;
		color = new AtomicBoolean(Black);
		choosing = new AtomicBoolean[numThreads+1];
		colors = new AtomicBoolean[numThreads+1];
		numbers = new AtomicIntegerArray(numThreads+1);
		for(int i = 1; i < numThreads+1; i++) {
			choosing[i] = new AtomicBoolean(false);
			colors[i] = new AtomicBoolean(White);
			numbers.set(i, 0);
		}
	}
	
	public void lock(int threadID) {
		choosing[threadID].set(true); 
		colors[threadID].set(color.get());
		int max = Integer.MIN_VALUE;
		boolean myColor = colors[threadID].get();
		for(int j = 1; j < numThreads+1; j++) {
			if((colors[j].get() == myColor) && (numbers.get(j) > max)) {
				max = numbers.get(j);
			}
		}
		max = max + 1;
		numbers.set(threadID, max);
		choosing[threadID].set(false);
		
		for(int j = 1; j < numThreads+1; j++) {
			if(j == threadID) continue;
			while(choosing[j].get());
			if(colors[j].get() == colors[threadID].get()) {
				while(true) {
					 if(numbers.get(j) == 0) break;
					 if(numbers.get(j) > numbers.get(threadID)) break;
					 if((numbers.get(j) == numbers.get(threadID)) && (j > threadID)) break;
					 if(colors[j].get() != colors[threadID].get()) break;
				}
			} else {
				while(true) {
					  if(numbers.get(j) == 0) break;
					  if(colors[threadID].get() != color.get()) break;
					  if(colors[j].get() == colors[threadID].get()) break;
				}
			}
		}
	}

	public void unlock(int threadID) {
		if(colors[threadID].get() == Black) {
			color.set(White);
		} else {
			color.set(Black);
		}
		numbers.set(threadID, 0);
	}
	
}
