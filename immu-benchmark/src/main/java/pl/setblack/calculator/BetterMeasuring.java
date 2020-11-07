package pl.setblack.calculator;

public class BetterMeasuring {
    public static void main(String[] args) {
        //NOT TOTALLY BROKEN
        ImmutableCalculator calc = new ImmutableCalculator(0L);
        //warmup
        long v =0;
        for (int i=0;i<10000; i++) {
           v = immutableCalcTest();
        }
        System.out.println("end warmup with" +v);

        long startTime = System.nanoTime();
        for (int i=0;i<10000000; i++) {
            v = immutableCalcTest();
        }
        ImmutableCalculator newCalc = calc.add(100L);
        long totalTime = System.nanoTime() - startTime;
        System.out.println("end calc with" +v);
        System.out.println("Total time:"+ totalTime);
        System.out.println("Single time:"+ totalTime/(CalcBenchmark.Consts.numOps*10000));
    }

    public static long  immutableCalcTest() {
        ImmutableCalculator calc = new ImmutableCalculator(0L);
        for (long i = 0; i< CalcBenchmark.Consts.numOps; i++) {
            calc = calc.add(i);
        }
        return calc.get();
    }
}
