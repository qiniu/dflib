package com.nhl.dflib.exp.date;

import com.nhl.dflib.DataFrame;
import com.nhl.dflib.DateExp;
import com.nhl.dflib.DateTimeExp;
import com.nhl.dflib.Series;
import com.nhl.dflib.unit.SeriesAsserts;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.nhl.dflib.Exp.$date;
import static com.nhl.dflib.Exp.$datetime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class DateTimeColumnTest {

    @Test
    public void testGetColumnName() {
        assertEquals("a", $datetime("a").getColumnName());
        assertEquals("$date(0)", $datetime(0).getColumnName());
    }

    @Test
    public void testGetColumnName_DataFrame() {
        DataFrame df = DataFrame.newFrame("a", "b").foldByRow();
        assertEquals("b", $datetime("b").getColumnName(df));
        assertEquals("a", $datetime(0).getColumnName(df));
    }

    @Test
    public void testEval() {
        DateTimeExp exp = $datetime("b");

        DataFrame df = DataFrame.newFrame("a", "b", "c").foldByRow(
                "1", LocalDateTime.of(2007, 1, 8,10,10,10), LocalDate.of(209, 2, 2),
                "4", LocalDateTime.of(2011, 11, 9,11,11,11), LocalDate.of(2005, 3, 5));

        new SeriesAsserts(exp.eval(df)).expectData(LocalDateTime.of(2007, 1, 8,10,10,10), LocalDateTime.of(2011, 11, 9,11,11,11));
    }

    @Test
    public void testAs() {
        DateTimeExp exp = $datetime("b");
        assertEquals("b", exp.getColumnName(mock(DataFrame.class)));
        assertEquals("c", exp.as("c").getColumnName(mock(DataFrame.class)));
    }

    @Test
    public void testPlusSeconds() {
        DateTimeExp exp = $datetime(0).plusSeconds(4);

        Series<LocalDateTime> s = Series.forData(LocalDateTime.of(2007, 1, 8,1,1,1), LocalDateTime.of(2011, 12, 31,1,1,1));
        new SeriesAsserts(exp.eval(s)).expectData(LocalDateTime.of(2007, 1, 8,1,1,5), LocalDateTime.of(2011, 12, 31,1,1,5));
    }

    @Test
    public void testPlusMinutes() {
        DateTimeExp exp = $datetime(0).plusMinutes(4);

        Series<LocalDateTime> s = Series.forData(LocalDateTime.of(2007, 1, 8,1,1,1), LocalDateTime.of(2011, 12, 31,1,1,1));
        new SeriesAsserts(exp.eval(s)).expectData(LocalDateTime.of(2007, 1, 8,1,5,1), LocalDateTime.of(2011, 12, 31,1,5,1));
    }

    @Test
    public void testPlusHours() {
        DateTimeExp exp = $datetime(0).plusHours(4);

        Series<LocalDateTime> s = Series.forData(LocalDateTime.of(2007, 1, 8,1,1,1), LocalDateTime.of(2011, 12, 31,1,1,1));
        new SeriesAsserts(exp.eval(s)).expectData(LocalDateTime.of(2007, 1, 8,5,1,1), LocalDateTime.of(2011, 12, 31,5,1,1));
    }

    @Test
    public void testPlusDays() {
        DateTimeExp exp = $datetime(0).plusDays(4);

        Series<LocalDateTime> s = Series.forData(LocalDateTime.of(2007, 1, 8,1,1,1), LocalDateTime.of(2011, 12, 31,1,1,1));
        new SeriesAsserts(exp.eval(s)).expectData(LocalDateTime.of(2007, 1, 12,1,1,1), LocalDateTime.of(2012, 1, 4,1,1,1));
    }

    @Test
    public void testOpsChain() {
        DateTimeExp exp = $datetime(0).plusDays(4).plusWeeks(1).plusHours(2).plusMinutes(2).plusSeconds(3);

        Series<LocalDateTime> s = Series.forData(LocalDateTime.of(2007, 1, 8,1,1,1), LocalDateTime.of(2011, 12, 31,2,2,2));
        new SeriesAsserts(exp.eval(s)).expectData(LocalDateTime.of(2007, 1, 19,3,3,4), LocalDateTime.of(2012, 1, 11,4,4,5));
    }
}
