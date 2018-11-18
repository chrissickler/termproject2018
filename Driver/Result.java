package Driver;

public class Result {

    private Long startTime;
    private Long endTime;
    private Long nanoSeconds;
    private Long microSeconds;
    private Long milliSeconds;
    private Long seconds;
    private Long minutes;
    private String totalTime;

    public Result(long startTime, long endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        calculateTime();
    }

    private void calculateTime() {
        Long raw = endTime - startTime;
        nanoSeconds = raw % 1000;
        raw /= 1000;
        microSeconds = raw % 1000;
        raw /= 1000;
        milliSeconds = raw % 1000;
        raw /= 1000;
        seconds = raw % 6000;
        raw /= 60;
        minutes = raw;
        StringBuilder sb1 = new StringBuilder();
        sb1.append(minutes.toString() + "min, ");
        sb1.append(seconds.toString() + "sec, ");
        sb1.append(milliSeconds.toString() + "ms, ");
        sb1.append(microSeconds.toString() + "us, ");
        sb1.append(nanoSeconds.toString() + "ns");
        totalTime = sb1.toString();
    }

    public String getTotalTime() {
        return totalTime;
    }

}