package com.nhl.dflib;

import com.nhl.dflib.exp.datetime.TimeExpScalar2;

import java.time.LocalTime;

/**
 * An expression applied to time columns.
 *
 * @since 0.11
 */
public interface TimeExp extends Exp<LocalTime> {

    default TimeExp plusSeconds(int seconds) {
        return TimeExpScalar2.mapVal("plusSeconds", this, seconds, (ld, y) -> ld.plusSeconds(y));
    }

    default TimeExp plusMinutes(int minutes) {
        return TimeExpScalar2.mapVal("plusMinutes", this, minutes, (ld, y) -> ld.plusMinutes(y));
    }

    default TimeExp plusHours(int hours) {
        return TimeExpScalar2.mapVal("plusHours", this, hours, (ld, y) -> ld.plusHours(y));
    }

}
