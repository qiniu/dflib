package com.nhl.dflib.exp.datetime;

import com.nhl.dflib.DateTimeExp;
import com.nhl.dflib.exp.GenericColumn;

import java.time.LocalDateTime;

/**
 * @since 0.11
 */
public class DateTimeColumn extends GenericColumn<LocalDateTime> implements DateTimeExp {

    public DateTimeColumn(String name) {
        super(name, LocalDateTime.class);
    }

    public DateTimeColumn(int position) {
        super(position, LocalDateTime.class);
    }

    @Override
    public String toQL() {
        return position >= 0 ? "$date(" + position + ")" : name;
    }
}
