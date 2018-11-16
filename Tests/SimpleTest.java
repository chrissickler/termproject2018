package Tests;

import org.junit.Test;

import Driver.ThreadLauncher;
import Locks.*;

public class SimpleTest {

	private static final int knob = 0;
    private int knobTurns = 10;
    private int numThreads = 1;

    @Test
    public void TestComplete() {
    	for(int i = 10; i < 10000000; i*=10) {
    		knobTurns = i;
    		for(int j = 1; j < 9; j++) {
    			numThreads = j;
    			System.out.println("knobTurns = " + i + " numThreads = " + j);
    			TestReentrant();
    			System.out.println("\t ->Reentrant");
    			TestSynchronized();
    			System.out.println("\t ->Synchronized");
    			TestAtomic();
    			System.out.println("\t ->Atomic");
    			TestOneBit();
    			System.out.println("\t ->OneBit");
    			TestOneBitAdaptive();
    			System.out.println("\t ->OneBitAdaptive");
    		}
    	}
    }
    
	//@Test
	public void TestReentrant() {
		Reentrant lock = new Reentrant(numThreads);
		Integer newKnob = new Integer(knob);
		ThreadLauncher tl = new ThreadLauncher(lock, newKnob, knobTurns, numThreads);
		tl.fireThreads();
		System.out.print(tl.getResult());
	}
    
	//@Test
	public void TestSynchronized() {
		Synchronized lock = new Synchronized(numThreads);
		Integer newKnob = new Integer(knob);
		ThreadLauncher tl = new ThreadLauncher(lock, newKnob, knobTurns, numThreads);
		tl.fireThreads();
		System.out.print(tl.getResult());
	}
	
	//@Test
	public void TestAtomic() {
		Atomic lock = new Atomic(numThreads);
		Integer newKnob = new Integer(knob);
		ThreadLauncher tl = new ThreadLauncher(lock, newKnob, knobTurns, numThreads);
		tl.fireThreads();
		System.out.print(tl.getResult());
	}
	
	//@Test
	public void TestOneBit() {
		OneBit lock = new OneBit(numThreads);
		Integer newKnob = new Integer(knob);
		ThreadLauncher tl = new ThreadLauncher(lock, newKnob, knobTurns, numThreads);
		tl.fireThreads();
		System.out.print(tl.getResult());
	}
	
	//@Test
	public void TestOneBitAdaptive() {
		OneBitAdaptive lock = new OneBitAdaptive(numThreads);
		Integer newKnob = new Integer(knob);
		ThreadLauncher tl = new ThreadLauncher(lock, newKnob, knobTurns, numThreads);
		tl.fireThreads();
		System.out.print(tl.getResult());
	}
    
}
