package com.nhl.dflib.exp;

import com.nhl.dflib.DataFrame;
import com.nhl.dflib.Series;

import java.util.Objects;

/**
 * @since 0.11
 */
public interface Exp<V> {

    static IntColumn $int(String name) {
        return new IntColumn(name);
    }

    static LongColumn $long(String name) {
        return new LongColumn(name);
    }

    static DoubleColumn $double(String name) {
        return new DoubleColumn(name);
    }

    static BooleanColumn $bool(String name) {
        return new BooleanColumn(name);
    }

    static StringColumn $str(String name) {
        return new StringColumn(name);
    }

    String getName();

    Class<V> getType();

    Series<V> eval(DataFrame df);

    /**
     * Creates a new expression by renaming the current expression.
     */
    default Exp<V> named(String name) {
        Objects.requireNonNull(name, "Null 'name'");
        return name.equals(getName()) ? this : new RenamedExp(name, this);
    }
}
