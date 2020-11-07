/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package pl.setblack.calculator;

import io.vavr.collection.Array;
import io.vavr.collection.List;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

import java.util.stream.LongStream;

public class CalcBenchmark {

    @Benchmark
    public void immutableCalcTest(Blackhole blackhole) {
        ImmutableCalculator calc = new ImmutableCalculator(0L);
        for (long i = 0; i<Consts.numOps; i++) {
            calc = calc.add(i);
        }
        blackhole.consume(calc.get());
    }

    @Benchmark
    public void pureImmutableCalcTest( Blackhole blackhole) {
        final ImmutableCalculator initial = new ImmutableCalculator(0);
        final long result = LongStream
                .range(0, Consts.numOps)
                .mapToObj(x -> x)
                .reduce(initial, (calc, i) -> calc.add(i), (x, y) -> {
                    throw new IllegalStateException();
                }).get();

        blackhole.consume(result);

    }

    @Benchmark
    public void vavrImmutableCalcTest( Blackhole blackhole) {
        final ImmutableCalculator initial = new ImmutableCalculator(0);
        ImmutableCalculator result = Array.range(0, Consts.numOps).foldLeft(initial, (calc, i) -> calc.add(i));

        blackhole.consume(result);
    }


    @Benchmark
    public void kotlinImmutableCalcTest( Blackhole blackhole) {
        blackhole.consume(Consts.kotlinCalc.kotlinCalc());

    }

    @Benchmark
    public void mutableCalcTest( Blackhole blackhole) {
        MutableCalculator calc = new MutableCalculator();
        for (long i = 0; i<Consts.numOps; i++) {
            calc.add(i);
        }
        blackhole.consume(calc.get());
    }

    public static final class Consts {
        public static final long numOps = 1000L;
        private static final KotlinCalc kotlinCalc =  KotlinCalc.INSTANCE;
    }
}
