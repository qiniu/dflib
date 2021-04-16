package com.nhl.dflib.exp;

import com.nhl.dflib.DoubleSeries;
import com.nhl.dflib.Series;

import java.util.function.BinaryOperator;

/**
 * @since 0.11
 */
public class DoubleBinaryExp extends BinaryExp<Double> implements NumericExp<Double> {

    protected static DoubleBinaryExp plus(Exp<? extends Number> left, Exp<? extends Number> right) {
        return new DoubleBinaryExp(left.getName() + "+" + right.getName(),
                castToDouble(left),
                castToDouble(right),
                (n1, n2) -> n1 != null && n2 != null ? n1.doubleValue() + n2.doubleValue() : null,
                (s1, s2) -> s1.plus(s2));
    }

    protected static <N extends Number> Exp<Double> castToDouble(Exp<N> exp) {
        return exp.getType().equals(Double.class)
                ? (Exp<Double>) exp
                : new UnaryExp<>(exp, Double.class, (N n) -> n != null ? n.doubleValue() : null);
    }

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
