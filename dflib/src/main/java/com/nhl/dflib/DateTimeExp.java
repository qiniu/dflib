package com.nhl.dflib;

import com.nhl.dflib.exp.datetime.DateTimeExpScalar2;

import java.time.LocalDateTime;

/**
 * An expression applied to datetime columns.
 *
 * @since 0.11
 */
public interface DateTimeExp extends Exp<LocalDateTime> {

    default DateTimeExp plusSeconds(int seconds) {
        return DateTimeExpScalar2.mapVal("plusSeconds", this, seconds, (ld, y) -> ld.plusSeconds(y));
    }

    default DateTimeExp plusMinutes(int minutes) {
        return DateTimeExpScalar2.mapVal("plusMinutes", this, minutes, (ld, y) -> ld.plusMinutes(y));
    }

    default DateTimeExp plusHours(int hours) {
        return DateTimeExpScalar2.mapVal("plusHours", this, hours, (ld, y) -> ld.plusHours(y));
    }

    default DateTimeExp plusDays(int days) {
        return DateTimeExpScalar2.mapVal("plusDays", this, days, (ld, d) -> ld.plusDays(days));
    }

    default DateTimeExp plusWeeks(int weeks) {
        return DateTimeExpScalar2.mapVal("plusWeeks", this, weeks, (ld, w) -> ld.plusWeeks(w));
    }

    default DateTimeExp plusMonths(int months) {
        return DateTimeExpScalar2.mapVal("plusMonths", this, months, (ld, m) -> ld.plusMonths(m));
    }

    default DateTimeExp plusYears(int years) {
        return DateTimeExpScalar2.mapVal("plusYears", this, years, (ld, y) -> ld.plusYears(y));
    }
}
