package com.nhl.dflib;

import com.nhl.dflib.unit.SeriesAsserts;
import org.junit.Test;

public class DataFrame_AggFilteredTest {

    @Test
    public void testSumInt() {
        DataFrame df = DataFrame.newFrame("a", "b").foldByRow(
                1, 1,
                -1, 5,
                2, 6,
                -4, 5);

        Series<?> s = df.agg(
                // filter is applied to column 0, sum is applied to column 1
                Aggregator.filterRows(0, (Integer i) -> i % 2 == 0).sumInt(1),
                // filter is applied to column 1, sum is applied to column 0
                Aggregator.filterRows("b", (Integer i) -> i % 2 == 1).sumInt("a"));

        new SeriesAsserts(s).expectData(11, -4);
    }

    @Test
    public void testSumLong() {
        DataFrame df = DataFrame.newFrame("a", "b").foldByRow(
                1, 1,
                -1, 5,
                2, 6,
                -4, 5);

        Series<?> s = df.agg(
                // filter is applied to column 0, sum is applied to column 1
                Aggregator.filterRows(0, (Integer i) -> i % 2 == 0).sumLong(1),
                // filter is applied to column 1, sum is applied to column 0
                Aggregator.filterRows("b", (Integer i) -> i % 2 == 1).sumLong("a"));

        new SeriesAsserts(s).expectData(11L, -4L);
    }

    @Test
    public void testFirst() {
        DataFrame df = DataFrame.newFrame("a", "b").foldByRow(
                7, 1,
                -1, 5,
                -4, 5);

        Series<?> s = df.agg(
                Aggregator.filterRows(0, (Integer i) -> i % 2 == 0).first(1),
                Aggregator.filterRows("b", (Integer i) -> i % 2 == 1).first("a"));

        new SeriesAsserts(s).expectData(5, 7);
    }

    @Test
    public void testCountInt_CountLong() {
        DataFrame df = DataFrame.newFrame("a", "b").foldByRow(
                7, 1,
                -1, 5,
                -4, 5);

        Series<?> s = df.agg(
                Aggregator.filterRows(0, (Integer i) -> i % 2 == 0).countInt(),
                Aggregator.filterRows("b", (Integer i) -> i % 2 == 1).countLong());

        new SeriesAsserts(s).expectData(1, 3L);
    }

    @Test
    public void test_MinMaxLong() {
        DataFrame df = DataFrame.newFrame("a", "b").foldByRow(
                1L, 1L,
                2L, 4L,
                -1L, 5L,
                8L, 2L);

        Series<?> s = df.agg(
                Aggregator.filterRows(0, (Long i) -> i % 2 == 0).maxLong(1),
                Aggregator.filterRows(0, (Long i) -> i % 2 == 0).minLong(1),
                Aggregator.filterRows("b", (Long i) -> i % 2 == 1).maxLong("a"),
                Aggregator.filterRows("b", (Long i) -> i % 2 == 1).minLong("a")
        );

        new SeriesAsserts(s).expectData(4L, 2L, 1L, -1L);
    }

}