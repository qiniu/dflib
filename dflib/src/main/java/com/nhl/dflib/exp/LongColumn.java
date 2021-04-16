package com.nhl.dflib.exp;

/**
 * @since 0.11
 */
public class LongColumn extends ColumnExp<Long> implements NumericExp<Long> {

    public LongColumn(String name) {
        super(name, Long.class);
    }
}
