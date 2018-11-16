package Locks;

public interface Lock {
	
    public void lock(int threadID);
    
    public void unlock(int threadID);
    
}
