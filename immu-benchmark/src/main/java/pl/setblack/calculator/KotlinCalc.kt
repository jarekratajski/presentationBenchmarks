package pl.setblack.calculator

import pl.setblack.calculator.CalcBenchmark.Consts

object KotlinCalc {
    fun kotlinCalc()  =
            (0..CalcBenchmark.Consts.numOps).fold(ImmutableCalculator(0L)) { calc, i ->
        calc.add(i)
    }

}
