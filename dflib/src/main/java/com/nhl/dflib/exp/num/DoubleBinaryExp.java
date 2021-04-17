package com.nhl.dflib.exp.num;

import com.nhl.dflib.DoubleSeries;
import com.nhl.dflib.Series;
import com.nhl.dflib.exp.BinaryExp;
import com.nhl.dflib.exp.Exp;
import com.nhl.dflib.exp.NumericExp;

import java.util.function.BinaryOperator;

/**
 * @since 0.11
 */
public class DoubleBinaryExp extends BinaryExp<Double, Double, Double> implements NumericExp<Double> {

    private final BinaryOperator<DoubleSeries> doubleOp;

    protected DoubleBinaryExp(
            String name,
            Exp<Double> left,
            Exp<Double> right,
            BinaryOperator<Double> op,
            BinaryOperator<DoubleSeries> doubleOp) {

        super(name, Double.class, left, right, op);
        this.doubleOp = doubleOp;
    }

    @Override
    protected Series<Double> eval(Series<Double> ls, Series<Double> rs) {
        return (ls instanceof DoubleSeries && rs instanceof DoubleSeries)
                ? doubleOp.apply((DoubleSeries) ls, (DoubleSeries) rs)
                : super.eval(ls, rs);
    }
}
