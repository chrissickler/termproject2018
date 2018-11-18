package Driver;

public class pocketInt {

    public Integer i;

    public pocketInt(int i) {
        this.i = Integer.valueOf(i);
    }

    public void increment() {
        i++;
    }

    public Integer getInteger() {
        return(i);
    }

}