package com.nhl.dflib.exp.date;

import com.nhl.dflib.DataFrame;
import com.nhl.dflib.DateTimeExp;
import com.nhl.dflib.Series;
import com.nhl.dflib.TimeExp;
import com.nhl.dflib.unit.SeriesAsserts;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.nhl.dflib.Exp.$datetime;
import static com.nhl.dflib.Exp.$time;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class TimeColumnTest {

    @Test
    public void testGetColumnName() {
        assertEquals("a", $time("a").getColumnName());
        assertEquals("$date(0)", $time(0).getColumnName());
    }

    @Test
    public void testGetColumnName_DataFrame() {
        DataFrame df = DataFrame.newFrame("a", "b").foldByRow();
        assertEquals("b", $time("b").getColumnName(df));
        assertEquals("a", $time(0).getColumnName(df));
    }

    @Test
    public void testEval() {
        TimeExp exp = $time("b");

        DataFrame df = DataFrame.newFrame("a", "b", "c").foldByRow(
                "1", LocalTime.of(10,10,10), LocalDate.of(209, 2, 2),
                "4", LocalTime.of(11,11,11), LocalDate.of(2005, 3, 5));

        new SeriesAsserts(exp.eval(df)).expectData(LocalTime.of(10,10,10), LocalTime.of(11,11,11));
    }

    @Test
    public void testAs() {
        TimeExp exp = $time("b");
        assertEquals("b", exp.getColumnName(mock(DataFrame.class)));
        assertEquals("c", exp.as("c").getColumnName(mock(DataFrame.class)));
    }

    @Test
    public void testPlusSeconds() {
        TimeExp exp = $time(0).plusSeconds(4);

        Series<LocalTime> s = Series.forData(LocalTime.of( 1,1,1), LocalTime.of(2,2,2));
        new SeriesAsserts(exp.eval(s)).expectData(LocalTime.of(1,1,5), LocalTime.of(2,2,6));
    }

    @Test
    public void testPlusMinutes() {
        TimeExp exp = $time(0).plusMinutes(4);

        Series<LocalTime> s = Series.forData(LocalTime.of( 1,1,1), LocalTime.of(2,2,2));
        new SeriesAsserts(exp.eval(s)).expectData(LocalTime.of(1,5,1), LocalTime.of(2,6,2));
    }

    @Test
    public void testPlusHours() {
        TimeExp exp = $time(0).plusHours(4);

        Series<LocalTime> s = Series.forData(LocalTime.of( 1,1,1), LocalTime.of(2,2,2));
        new SeriesAsserts(exp.eval(s)).expectData(LocalTime.of(5,1,1), LocalTime.of(6,2,2));
    }

    @Test
    public void testOpsChain() {
        TimeExp exp = $time(0).plusHours(4).plusMinutes(1).plusSeconds(3);

        Series<LocalTime> s = Series.forData(LocalTime.of( 1,1,1), LocalTime.of(2,2,2));
        new SeriesAsserts(exp.eval(s)).expectData(LocalTime.of(5,2,4), LocalTime.of(6,3,5));
    }
}
