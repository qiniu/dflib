package com.nhl.dflib.exp.num;

import com.nhl.dflib.exp.Exp;
import com.nhl.dflib.exp.NumericExp;
import com.nhl.dflib.exp.UnaryExp;

public class LongExpFactory extends ArithmeticExpFactory {

    protected static <N extends Number> Exp<Long> castToLong(Exp<N> exp) {
        return exp.getType().equals(Long.class)
                ? (Exp<Long>) exp
                : new UnaryExp<>(exp, Long.class, (N n) -> n != null ? n.longValue() : null);
    }

    @Override
    public NumericExp<?> plus(Exp<? extends Number> left, Exp<? extends Number> right) {
        return new LongBinaryExp(left.getName() + "+" + right.getName(),
                castToLong(left),
                castToLong(right),
                (n1, n2) -> n1 != null && n2 != null ? n1.longValue() + n2.longValue() : null,
                (s1, s2) -> s1.plus(s2));
    }

    @Override
    public NumericExp<?> minus(Exp<? extends Number> left, Exp<? extends Number> right) {
        return new LongBinaryExp(left.getName() + "-" + right.getName(),
                castToLong(left),
                castToLong(right),
                (n1, n2) -> n1 != null && n2 != null ? n1.longValue() - n2.longValue() : null,
                (s1, s2) -> s1.minus(s2));
    }
}
