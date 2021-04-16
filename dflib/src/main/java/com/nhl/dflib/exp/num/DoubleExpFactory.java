package com.nhl.dflib.exp.num;

import com.nhl.dflib.exp.Exp;
import com.nhl.dflib.exp.NumericExp;
import com.nhl.dflib.exp.UnaryExp;

public class DoubleExpFactory extends ArithmeticExpFactory {

    protected static <N extends Number> Exp<Double> castToDouble(Exp<N> exp) {
        return exp.getType().equals(Double.class)
                ? (Exp<Double>) exp
                : new UnaryExp<>(exp, Double.class, (N n) -> n != null ? n.doubleValue() : null);
    }

    @Override
    public NumericExp<?> plus(Exp<? extends Number> left, Exp<? extends Number> right) {
        return new DoubleBinaryExp(left.getName() + "+" + right.getName(),
                castToDouble(left),
                castToDouble(right),
                (n1, n2) -> n1 != null && n2 != null ? n1.doubleValue() + n2.doubleValue() : null,
                (s1, s2) -> s1.plus(s2));
    }

    @Override
    public NumericExp<?> minus(Exp<? extends Number> left, Exp<? extends Number> right) {
        return new DoubleBinaryExp(left.getName() + "-" + right.getName(),
                castToDouble(left),
                castToDouble(right),
                (n1, n2) -> n1 != null && n2 != null ? n1.doubleValue() - n2.doubleValue() : null,
                (s1, s2) -> s1.minus(s2));
    }
}
