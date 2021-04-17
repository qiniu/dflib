package com.nhl.dflib.exp.condition;

import com.nhl.dflib.BooleanSeries;
import com.nhl.dflib.DataFrame;
import com.nhl.dflib.Series;
import com.nhl.dflib.exp.Condition;
import com.nhl.dflib.exp.Exp;

import java.util.function.BiFunction;

/**
 * @since 0.11
 */
public class BinaryCondition<L, R> implements Condition {

    private final String name;
    protected final Exp<L> left;
    protected final Exp<R> right;
    private final BiFunction<Series<L>, Series<R>, BooleanSeries> op;

    public BinaryCondition(String name, Exp<L> left, Exp<R> right, BiFunction<Series<L>, Series<R>, BooleanSeries> op) {
        this.name = name;
        this.left = left;
        this.right = right;
        this.op = op;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public BooleanSeries eval(DataFrame df) {
        return eval(left.eval(df), right.eval(df));
    }

    protected BooleanSeries eval(Series<L> ls, Series<R> rs) {
        return op.apply(ls, rs);
    }
}
