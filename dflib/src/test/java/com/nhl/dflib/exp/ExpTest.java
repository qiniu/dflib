package com.nhl.dflib.exp;

import com.nhl.dflib.DataFrame;
import com.nhl.dflib.DoubleSeries;
import com.nhl.dflib.IntSeries;
import com.nhl.dflib.Series;
import com.nhl.dflib.unit.SeriesAsserts;
import org.junit.jupiter.api.Test;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import static com.nhl.dflib.exp.Exp.*;
import static org.junit.jupiter.api.Assertions.*;

public class ExpTest {

    @Test
    public void testStr_ReadColumn() {
        DataFrame df = DataFrame.newFrame("a", "b", "c").foldByRow(
                "1", "2", "3",
                "4", "5", "6");

        Series<String> s = $str("b").eval(df);
        new SeriesAsserts(s).expectData("2", "5");
    }

    @Test
    public void testStr_Named() {
        Exp<String> e = $str("b");
        assertEquals("b", e.getName());
        assertEquals("c", e.named("c").getName());
    }

    @Test
    public void testStr_Concat() {
        DataFrame df = DataFrame.newFrame("a", "b", "c").foldByRow(
                "1", "2", "3",
                "4", "5", "6");

        Series<String> s = $str("b").concat($str("a")).eval(df);
        new SeriesAsserts(s).expectData("21", "54");
    }

    @Test
    public void testInt_PlusInt() {
        DataFrame df = DataFrame.newFrame("a", "b").foldByRow(
                1, 2,
                3, 4);

        Series<Integer> s = $int("b").plusInt($int("a")).eval(df);
        assertFalse(s instanceof IntSeries);
        new SeriesAsserts(s).expectData(3, 7);
    }

    @Test
    public void testInt_PlusInt_IntSeries() {
        DataFrame df = DataFrame.newFrame("a", "b").foldIntStreamByRow(IntStream.of(1, 2, 3, 4));

        // sanity check of the test DataFrame
        Series<Integer> a = df.getColumn("a");
        assertTrue(a instanceof IntSeries);

        Series<Integer> b = df.getColumn("b");
        assertTrue(b instanceof IntSeries);

        // run and verify the calculation
        Series<Integer> s = $int("b").plusInt($int("a")).eval(df);
        assertTrue(s instanceof IntSeries);
        new SeriesAsserts(s).expectData(3, 7);
    }

    @Test
    public void testDouble_PlusDouble() {
        DataFrame df = DataFrame.newFrame("a", "b").foldByRow(
                1.01, 2.,
                3., 4.5);

        Series<Double> s = $double("b").plusDouble($double("a")).eval(df);
        assertFalse(s instanceof DoubleSeries);
        new SeriesAsserts(s).expectData(3.01, 7.5);
    }

    @Test
    public void testDouble_PlusDouble_DoubleSeries() {
        DataFrame df = DataFrame.newFrame("a", "b").foldDoubleStreamByRow(DoubleStream.of(1.01, 2., 3., 4.5));

        // sanity check of the test DataFrame
        Series<Double> a = df.getColumn("a");
        assertTrue(a instanceof DoubleSeries);

        Series<Double> b = df.getColumn("b");
        assertTrue(b instanceof DoubleSeries);

        // run and verify the calculation
        Series<Double> s = $double("b").plusDouble($double("a")).eval(df);
        assertTrue(s instanceof DoubleSeries);
        new SeriesAsserts(s).expectData(3.01, 7.5);
    }
}
