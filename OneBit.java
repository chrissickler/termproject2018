package OneBit;

public class OneBit {
	
	static int count = 0;
	static myThread [] arr;
	static volatile boolean[] b;
	
    public static int parallelIncrement(int c, int numThreads){
    	//tailSlot.set(0);
    	arr = new myThread[numThreads];
    	b = new boolean[numThreads];
    	for(int i=0;i<b.length;i++)
    	{
    		b[i] = false;
    	}
    	int remainder = c % numThreads;
    	for(int i=0;i<numThreads;i++)
    	{
    		if(i<remainder)
    			arr[i] = new myThread(i,(c/numThreads) + 1);
    		else
    			arr[i] = new myThread(i,(c/numThreads));
    	}
        for(int i=0;i<arr.length;i++)
        {
        	arr[i].increment();
        }
        while(count!=c) {}
        return count;
    }
    
    static class myThread extends Thread
    {
    	int times;
    	int id;
    	public myThread(int id, int times)
    	{
    		this.id = id;
    		this.times = times;
    	}
    	public void increment()
    	{
    		while(times!=0)
    		{
    			lock();
    			CS();
    			unlock();
    		}
    	}
    	public void lock()
    	{
    		while(b[id]!=true)
    		{
    			b[id] = true;
    			int j= 0;
    			while(b[id]==true && j<id)
    			{
    				if(b[j]==true)
    				  b[id] = false;
    				while(b[j]==true){}
    				j++;
    				
    			}
    		}
    		for(int j=id+1;j<b.length;j++)
    		{
    			while(b[j]==true){}
    		}
    	}
    	public void CS()
    	{
    		count++;
    		times--;
    	}
    	public void unlock()
    	{
    		b[id] = false;
    	}
    	
    }

}