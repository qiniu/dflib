package com.nhl.dflib.exp.num;

import com.nhl.dflib.DataFrame;
import com.nhl.dflib.IntSeries;
import com.nhl.dflib.exp.NumericExp;
import com.nhl.dflib.series.IntSingleValueSeries;

/**
 * @since 0.11
 */
public class IntSingleValueExp implements NumericExp<Integer> {

    private final int value;

    public IntSingleValueExp(int value) {
        this.value = value;
    }

    @Override
    public String getName() {
        return "$val";
    }

    @Override
    public Class<Integer> getType() {
        return Integer.TYPE;
    }

    @Override
    public IntSeries eval(DataFrame df) {
        return new IntSingleValueSeries(value, df.height());
    }
}
