package com.nhl.dflib.exp.datetime;

import com.nhl.dflib.Exp;
import com.nhl.dflib.Series;
import com.nhl.dflib.TimeExp;
import com.nhl.dflib.exp.map.MapExp2;

import java.time.LocalTime;
import java.util.function.BiFunction;

/**
 * @since 0.11
 */
public class TimeExp2<L, R> extends MapExp2<L, R, LocalTime> implements TimeExp {

    public static <L, R> TimeExp2<L, R> mapVal(String opName, Exp<L> left, Exp<R> right, BiFunction<L, R, LocalTime> op) {
        return new TimeExp2<>(opName, left, right, valToSeries(op, LocalTime.class));
    }

    public TimeExp2(String opName, Exp<L> left, Exp<R> right, BiFunction<Series<L>, Series<R>, Series<LocalTime>> op) {
        super(opName, LocalTime.class, left, right, op);
    }
}
