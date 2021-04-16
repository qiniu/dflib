package com.nhl.dflib.exp;

import com.nhl.dflib.DataFrame;
import com.nhl.dflib.Series;
import com.nhl.dflib.accumulator.ObjectAccumulator;

import java.util.function.BinaryOperator;

/**
 * @since 0.11
 */
public class BinaryExp<V> implements Exp<V> {

    private final String name;
    private final Class<V> type;
    private final Exp<V> left;
    private final Exp<V> right;
    private final BinaryOperator<V> op;

    public BinaryExp(String name, Class<V> type, Exp<V> left, Exp<V> right, BinaryOperator<V> op) {
        this.name = name;
        this.type = type;
        this.left = left;
        this.right = right;
        this.op = op;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Class<V> getType() {
        return type;
    }

    @Override
    public Series<V> eval(DataFrame df) {
        return eval(left.eval(df), right.eval(df));
    }

    protected Series<V> eval(Series<V> ls, Series<V> rs) {
        int len = ls.size();
        ObjectAccumulator<V> accum = new ObjectAccumulator<>(len);
        for (int i = 0; i < len; i++) {
            accum.add(op.apply(ls.get(i), rs.get(i)));
        }

        return accum.toSeries();
    }
}
