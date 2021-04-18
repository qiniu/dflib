package com.nhl.dflib.series;

import com.nhl.dflib.IntSeries;
import com.nhl.dflib.range.Range;

import java.util.Arrays;

/**
 * @since 0.11
 */
public class IntSingleValueSeries extends IntBaseSeries {

    private final int value;
    private final int size;

    public IntSingleValueSeries(int value, int size) {
        this.value = value;
        this.size = size;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int getInt(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        return value;
    }

    @Override
    public void copyToInt(int[] to, int fromOffset, int toOffset, int len) {
        Arrays.fill(to, toOffset, toOffset + len, value);
    }

    @Override
    public IntSeries materializeInt() {
        return this;
    }

    @Override
    public IntSeries rangeOpenClosedInt(int fromInclusive, int toExclusive) {
        if (fromInclusive == toExclusive) {
            return new IntArraySeries();
        }

        if (fromInclusive == 0 && toExclusive == size) {
            return this;
        }

        Range.checkRange(fromInclusive, toExclusive - fromInclusive, size);
        return new IntSingleValueSeries(value, toExclusive - fromInclusive);
    }

    @Override
    public IntSeries headInt(int len) {
        return len < size ? new IntSingleValueSeries(value, len) : this;
    }

    @Override
    public IntSeries tailInt(int len) {
        return len < size ? new IntSingleValueSeries(value, len) : this;
    }

    @Override
    public int max() {
        return value;
    }

    @Override
    public int min() {
        return value;
    }

    @Override
    public long sum() {
        return (long) value * size;
    }

    @Override
    public double average() {
        return value;
    }

    @Override
    public double median() {
        return value;
    }
}
