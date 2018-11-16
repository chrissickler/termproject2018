package Locks;

public class OneBit implements Lock {

	private boolean[] b;
	private int numThreads;
	
	public OneBit(int numThreads) {
		this.numThreads = numThreads;
		b = new boolean[numThreads];
		for(int i = 0; i < numThreads; i++) {
			b[i] = false;
		}
	}
	
	public void lock(int threadID) {
		int j;
		while(b[threadID] != true) {
			b[threadID] = true;
			j = 0;
			while((b[threadID] == true) && (j < threadID)) {
				if(b[j] == true) {
					b[threadID] = false;
				}
				while(b[j] == true);
				j++;
			}
		}
		for(j = threadID + 1; j < numThreads; j++) {
			while(b[j] == true);
		}
	}
	
	public void unlock(int threadID) {
		b[threadID] = false;
	}
	
}