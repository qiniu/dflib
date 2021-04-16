package com.nhl.dflib.exp;

/**
 * @since 0.11
 */
public class IntColumn extends ColumnExp<Integer> implements NumericExp<Integer> {

    public IntColumn(String name) {
        super(name, Integer.class);
    }
}
