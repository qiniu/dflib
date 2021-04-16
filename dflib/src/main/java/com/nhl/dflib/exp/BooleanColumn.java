package com.nhl.dflib.exp;

import com.nhl.dflib.BooleanSeries;
import com.nhl.dflib.BooleanValueMapper;
import com.nhl.dflib.DataFrame;
import com.nhl.dflib.Series;

/**
 * @since 0.11
 */
public class BooleanColumn extends ColumnExp<Boolean> implements Condition {

    public BooleanColumn(String name) {
        super(name, Boolean.class);
    }

    @Override
    public BooleanSeries eval(DataFrame df) {
        Series<?> c = df.getColumn(getName());
        return c instanceof BooleanSeries
                ? (BooleanSeries) c
                : BooleanSeries.forSeries(c, BooleanValueMapper.fromObject());
    }
}
