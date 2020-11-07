package pl.setblack.calculator;

public class ImmutableCalculator {
    private final long total;


    public ImmutableCalculator(long total) {
        this.total = total;
    }

    public ImmutableCalculator add(long v) {
        return new ImmutableCalculator(this.total + v);
    }

    public long get() {
        return total;
    }
}
