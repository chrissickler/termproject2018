package Driver;

import Driver.ThreadLauncher;
import Driver.pocketInt;
import Locks.*;

public class Tests {

	private int knobTurns;
	private int numThreads;

    public Tests() {
    	
    }

    public void TestAll() {
	    for(knobTurns = 10; knobTurns < 10000000; knobTurns*=10) {
	        for(numThreads = 1; numThreads < 9; numThreads++) {
	            System.out.println("\nknobTurns = " + knobTurns + " numThreads = " + numThreads);
	            TestOneBit();
	            System.out.println("\t ->OneBit");
	            TestAndersonAdaptive();
	            System.out.println("\t ->AndersonAdaptive");
	            if((knobTurns <= 10000) || (numThreads < 5)) {
	            	TestColoredBakery();
	            	System.out.println("\t ->ColoredBakery");
	            }
	        }
	    }
    }
    
    private void TestOneBit() {
        OneBit lock = new OneBit(numThreads);
        pocketInt newKnob = new pocketInt(0);
        ThreadLauncher tl = new ThreadLauncher(lock, newKnob, knobTurns, numThreads);
        tl.fireThreads();
        System.out.print(tl.getResult());
    }

    private void TestAndersonAdaptive() {
        AndersonAdaptive lock = new AndersonAdaptive(numThreads, knobTurns);
        pocketInt newKnob = new pocketInt(0);
        ThreadLauncher tl = new ThreadLauncher(lock, newKnob, knobTurns, numThreads);
        tl.fireThreads();
        System.out.print(tl.getResult());
    }

    private void TestColoredBakery() {
        ColoredBakery lock = new ColoredBakery(numThreads);
        pocketInt newKnob = new pocketInt(0);
        ThreadLauncher tl = new ThreadLauncher(lock, newKnob, knobTurns, numThreads);
        tl.fireThreads();
        System.out.print(tl.getResult());
    }

}