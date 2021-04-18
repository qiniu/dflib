package com.nhl.dflib;

import com.nhl.dflib.exp.*;
import com.nhl.dflib.exp.condition.AndCondition;
import com.nhl.dflib.exp.condition.BooleanColumn;
import com.nhl.dflib.exp.condition.OrCondition;
import com.nhl.dflib.exp.num.DoubleColumn;
import com.nhl.dflib.exp.num.IntColumn;
import com.nhl.dflib.exp.num.LongColumn;
import com.nhl.dflib.exp.str.StringColumn;

import java.util.Objects;

/**
 * @since 0.11
 */
public interface Exp<V> {

    static ValueExp<?> $col(String name) {
        return new ColumnExp(name, Object.class);
    }

    static <V> ValueExp<V> $val(V value) {

        if (value == null) {
            return (ValueExp<V>) new SingleValueExp<>(null, Object.class);
        }

        // note that wrapping the value in primitive-optimized series has only very small effects on performance
        // (slightly improves comparisons with primitive series, and slows down comparisons with object-wrapped numbers).
        // So using the same "exp" for all values.

        // TODO: explore possible performance improvement by not converting scalars to Series at all, and providing a
        //   separate evaluation path instead.

        return new SingleValueExp(value, value.getClass());
    }

    static StringColumn $str(String name) {
        return new StringColumn(name);
    }

    static NumericExp<Integer> $int(String name) {
        return new IntColumn(name);
    }

    static NumericExp<Long> $long(String name) {
        return new LongColumn(name);
    }

    static NumericExp<Double> $double(String name) {
        return new DoubleColumn(name);
    }

    // TODO: inconsistency - unlike numeric columns that support nulls, BooleanColumn is a "Condition",
    //  that can have no nulls, and will internally convert all nulls to "false"..
    //  Perhaps we need a distinction between a "condition" and a "boolean value expression"?
    static Condition $bool(String name) {
        return new BooleanColumn(name);
    }

    static Condition $or(Condition... conditions) {
        return conditions.length == 1
                ? conditions[0] : new OrCondition(conditions);
    }

    static Condition $and(Condition... conditions) {
        return conditions.length == 1
                ? conditions[0] : new AndCondition(conditions);
    }

    String getName();

    Class<V> getType();

    Series<V> eval(DataFrame df);

    /**
     * Creates a new expression by renaming the current expression.
     */
    default Exp<V> named(String name) {
        Objects.requireNonNull(name, "Null 'name'");
        return name.equals(getName()) ? this : new RenamedExp<>(name, this);
    }
}
