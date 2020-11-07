package pl.setblack.calculator;

public class MutableCalculator {
    private long total = 0L;
    public void add(long v) {
        total += v;
    }

    public long get() {
        return total;
    }
}
