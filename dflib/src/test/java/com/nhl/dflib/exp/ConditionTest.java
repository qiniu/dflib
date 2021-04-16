package com.nhl.dflib.exp;

import com.nhl.dflib.BooleanSeries;
import com.nhl.dflib.DataFrame;
import com.nhl.dflib.unit.BooleanSeriesAsserts;
import org.junit.jupiter.api.Test;

import static com.nhl.dflib.exp.Exp.*;

public class ConditionTest {

    @Test
    public void testEq() {
        DataFrame df = DataFrame.newFrame("a", "b").foldByRow(
                "1", "1",
                "4", "5");

        BooleanSeries s = $str("b").eq($str("a")).eval(df);
        new BooleanSeriesAsserts(s).expectData(true, false);
    }

    @Test
    public void testNe() {
        DataFrame df = DataFrame.newFrame("a", "b").foldByRow(
                "1", "1",
                "4", "5");

        BooleanSeries s = $str("b").ne($str("a")).eval(df);
        new BooleanSeriesAsserts(s).expectData(false, true);
    }

    @Test
    public void testAnd() {
        DataFrame df = DataFrame.newFrame("a", "b", "c").foldByRow(
                "1", "1", false,
                "2", "2", true,
                "4", "5", true);

        BooleanSeries s = $str("b").eq($str("a")).and($bool("c")).eval(df);
        new BooleanSeriesAsserts(s).expectData(false, true, false);
    }

    @Test
    public void testOr() {
        DataFrame df = DataFrame.newFrame("a", "b", "c").foldByRow(
                "1", "1", false,
                "2", "2", true,
                "4", "5", false);

        BooleanSeries s = $str("b").eq($str("a")).or($bool("c")).eval(df);
        new BooleanSeriesAsserts(s).expectData(true, true, false);
    }

    @Test
    public void testOr_Multiple() {
        DataFrame df = DataFrame.newFrame("a", "b", "c").foldByRow(
                false, false, false,
                true, true, true,
                true, false, false);

        BooleanSeries s = $or($bool("a"), $bool("b"), $bool("c")).eval(df);
        new BooleanSeriesAsserts(s).expectData(false, true, true);
    }
}
