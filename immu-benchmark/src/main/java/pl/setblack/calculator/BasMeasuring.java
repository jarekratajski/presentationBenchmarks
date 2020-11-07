package pl.setblack.calculator;

public class BasMeasuring {
    public static void main(String[] args) {
        //TOLLY BROKEN
        ImmutableCalculator calc = new ImmutableCalculator(0L);
        long startTime = System.nanoTime();
        ImmutableCalculator newCalc = calc.add(100L);
        long totalTime = System.nanoTime() - startTime;
        System.out.println("Total time:"+ totalTime);
    }
}
