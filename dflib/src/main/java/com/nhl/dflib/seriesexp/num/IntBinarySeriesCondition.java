package com.nhl.dflib.seriesexp.num;

import com.nhl.dflib.BooleanSeries;
import com.nhl.dflib.IntSeries;
import com.nhl.dflib.Series;
import com.nhl.dflib.SeriesExp;
import com.nhl.dflib.seriesexp.condition.BinarySeriesCondition;

import java.util.function.BiFunction;

/**
 * @since 0.11
 */
public class IntBinarySeriesCondition extends BinarySeriesCondition<Integer, Integer> {

    private final BiFunction<IntSeries, IntSeries, BooleanSeries> primitiveOp;

    public IntBinarySeriesCondition(
            String opName,
            SeriesExp<Integer> left,
            SeriesExp<Integer> right,
            BiFunction<Series<Integer>, Series<Integer>, BooleanSeries> op,
            BiFunction<IntSeries, IntSeries, BooleanSeries> primitiveOp) {

        super(opName, left, right, op);
        this.primitiveOp = primitiveOp;
    }

    @Override
    protected BooleanSeries eval(Series<Integer> ls, Series<Integer> rs) {
        return (ls instanceof IntSeries && rs instanceof IntSeries)
                ? primitiveOp.apply((IntSeries) ls, (IntSeries) rs)
                : super.eval(ls, rs);
    }
}
