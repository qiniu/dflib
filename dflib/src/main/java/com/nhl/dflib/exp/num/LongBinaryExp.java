package com.nhl.dflib.exp.num;

import com.nhl.dflib.LongSeries;
import com.nhl.dflib.Series;
import com.nhl.dflib.exp.BinaryExp;
import com.nhl.dflib.exp.Exp;
import com.nhl.dflib.exp.NumericExp;

import java.util.function.BinaryOperator;

/**
 * @since 0.11
 */
public class LongBinaryExp extends BinaryExp<Long> implements NumericExp<Long> {

    private final BinaryOperator<LongSeries> longOp;

    protected LongBinaryExp(
            String name,
            Exp<Long> left,
            Exp<Long> right,
            BinaryOperator<Long> op,
            BinaryOperator<LongSeries> longOp) {

        super(name, Long.class, left, right, op);
        this.longOp = longOp;
    }

    @Override
    protected Series<Long> eval(Series<Long> ls, Series<Long> rs) {
        return (ls instanceof LongSeries && rs instanceof LongSeries)
                ? longOp.apply((LongSeries) ls, (LongSeries) rs)
                : super.eval(ls, rs);
    }
}
