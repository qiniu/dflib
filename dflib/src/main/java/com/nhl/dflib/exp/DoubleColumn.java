package com.nhl.dflib.exp;

/**
 * @since 0.11
 */
public class DoubleColumn extends ColumnExp<Double> implements NumericExp<Double> {

    public DoubleColumn(String name) {
        super(name, Double.class);
    }
}
