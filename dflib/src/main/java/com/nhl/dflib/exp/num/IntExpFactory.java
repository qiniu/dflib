package com.nhl.dflib.exp.num;

import com.nhl.dflib.exp.Exp;
import com.nhl.dflib.exp.NumericExp;
import com.nhl.dflib.exp.UnaryExp;

public class IntExpFactory extends ArithmeticExpFactory {

    protected static <N extends Number> Exp<Integer> castToInteger(Exp<N> exp) {
        return exp.getType().equals(Integer.class)
                ? (Exp<Integer>) exp
                : new UnaryExp<>(exp, Integer.class, (N n) -> n != null ? n.intValue() : null);
    }

    @Override
    public NumericExp<?> plus(Exp<? extends Number> left, Exp<? extends Number> right) {
        return new IntBinaryExp(left.getName() + "+" + right.getName(),
                castToInteger(left),
                castToInteger(right),
                (n1, n2) -> n1 != null && n2 != null ? n1.intValue() + n2.intValue() : null,
                (s1, s2) -> s1.plus(s2));
    }

    @Override
    public NumericExp<?> minus(Exp<? extends Number> left, Exp<? extends Number> right) {
        return new IntBinaryExp(left.getName() + "-" + right.getName(),
                castToInteger(left),
                castToInteger(right),
                (n1, n2) -> n1 != null && n2 != null ? n1.intValue() - n2.intValue() : null,
                (s1, s2) -> s1.minus(s2));
    }

    @Override
    public NumericExp<?> multiply(Exp<? extends Number> left, Exp<? extends Number> right) {
        return new IntBinaryExp(left.getName() + "*" + right.getName(),
                castToInteger(left),
                castToInteger(right),
                (n1, n2) -> n1 != null && n2 != null ? n1.intValue() * n2.intValue() : null,
                (s1, s2) -> s1.multiply(s2));
    }

    @Override
    public NumericExp<?> divide(Exp<? extends Number> left, Exp<? extends Number> right) {
        return new IntBinaryExp(left.getName() + "/" + right.getName(),
                castToInteger(left),
                castToInteger(right),
                (n1, n2) -> n1 != null && n2 != null ? n1.intValue() / n2.intValue() : null,
                (s1, s2) -> s1.divide(s2));
    }
}
