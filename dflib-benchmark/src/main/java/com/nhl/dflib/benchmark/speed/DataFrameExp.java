package com.nhl.dflib.benchmark.speed;

import com.nhl.dflib.*;
import com.nhl.dflib.benchmark.DataGenerator;
import com.nhl.dflib.benchmark.ValueMaker;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

import static com.nhl.dflib.Exp.*;

@Warmup(iterations = 2, time = 1)
@Measurement(iterations = 3, time = 1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(2)
@State(Scope.Thread)
public class DataFrameExp {

    @Param("5000000")
    public int rows;

    private DataFrame df;

    @Setup
    public void setUp() {

        IntSeries c0 = DataGenerator.intSeries(rows, ValueMaker.intSeq());
        IntSeries c1 = DataGenerator.intSeries(rows, ValueMaker.randomIntSeq(rows / 2));
        Series<Integer> c2 = Series.forData(c1);
        Series<String> c3 = ValueMaker.stringSeq().series(rows);
        Series<String> c4 = ValueMaker.constStringSeq("abc").series(rows);
        DoubleSeries c5 = DataGenerator.doubleSeries(rows, ValueMaker.doubleSeq());
        DoubleSeries c6 = DataGenerator.doubleSeries(rows, ValueMaker.randomDoubleSeq());

        df = DataFrame.newFrame("c0", "c1", "c2", "c3", "c4", "c5", "c6")
                .columns(c0, c1, c2, c3, c4, c5, c6);
    }

    @Benchmark
    public Object mapIntViaLambda() {
        return df.mapColumn(r -> ((Integer) r.get("c0")) + ((Integer) r.get("c1")))
                .materialize()
                .iterator();
    }

    @Benchmark
    public Object mapIntViaColumn() {
        Exp<?> plus = $int("c0").plus($int("c1"));
        return plus.eval(df).materialize().iterator();
    }

    @Benchmark
    public Object mapIntegerViaLambda() {
        return df.mapColumn(r -> ((Integer) r.get("c0")) + ((Integer) r.get("c2")))
                .materialize()
                .iterator();
    }

    @Benchmark
    public Object mapIntegerViaColumn() {
        Exp<?> plus = $int("c0").plus($int("c2"));
        return plus.eval(df).materialize().iterator();
    }

    @Benchmark
    public Object mapStringViaLambda() {
        return df.mapColumn(r -> r.get("c3") + ((String) r.get("c4")))
                .materialize()
                .iterator();
    }

    @Benchmark
    public Object mapStringViaColumn() {
        Exp<String> concat = $str("c3").concat($str("c4"));
        return concat.eval(df).materialize().iterator();
    }

    @Benchmark
    public Object mapDoubleViaLambda() {
        return df.mapColumn(r -> ((Double) r.get("c5")) + ((Double) r.get("c6")))
                .materialize()
                .iterator();
    }

    @Benchmark
    public Object mapDoubleViaColumn() {
        Exp<?> plus = $double("c5").plus($double("c6"));
        return plus.eval(df).materialize().iterator();
    }
}
