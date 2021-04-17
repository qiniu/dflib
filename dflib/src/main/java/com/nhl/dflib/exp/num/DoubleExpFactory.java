package com.nhl.dflib.exp.num;

import com.nhl.dflib.exp.Exp;
import com.nhl.dflib.exp.NumericExp;
import com.nhl.dflib.exp.UnaryExp;

public class DoubleExpFactory extends NumericExpFactory {

    protected static Exp<Double> cast(Exp<?> exp) {

        // TODO: a map of casting converters

        Class<?> t = exp.getType();
        if (t.equals(Double.class)) {
            return (Exp<Double>) exp;
        }

        if (Number.class.isAssignableFrom(t)) {
            Exp<Number> nExp = (Exp<Number>) exp;
            return new UnaryExp<>(nExp, Double.class, (Number n) -> n != null ? n.doubleValue() : null);
        }

        if (t.equals(String.class)) {
            Exp<String> sExp = (Exp<String>) exp;
            return new UnaryExp<>(sExp, Double.class, (String s) -> s != null ? Double.parseDouble(s) : null);
        }

        throw new IllegalArgumentException("Expression type '" + t.getName() + "' can't be converted to Double");
    }

    @Override
    public NumericExp<?> plus(Exp<? extends Number> left, Exp<? extends Number> right) {
        return new DoubleBinaryExp(left.getName() + "+" + right.getName(),
                cast(left),
                cast(right),
                (n1, n2) -> n1 != null && n2 != null ? n1.doubleValue() + n2.doubleValue() : null,
                (s1, s2) -> s1.plus(s2));
    }

    @Override
    public NumericExp<?> minus(Exp<? extends Number> left, Exp<? extends Number> right) {
        return new DoubleBinaryExp(left.getName() + "-" + right.getName(),
                cast(left),
                cast(right),
                (n1, n2) -> n1 != null && n2 != null ? n1.doubleValue() - n2.doubleValue() : null,
                (s1, s2) -> s1.minus(s2));
    }

    @Override
    public NumericExp<?> multiply(Exp<? extends Number> left, Exp<? extends Number> right) {
        return new DoubleBinaryExp(left.getName() + "*" + right.getName(),
                cast(left),
                cast(right),
                (n1, n2) -> n1 != null && n2 != null ? n1.doubleValue() * n2.doubleValue() : null,
                (s1, s2) -> s1.multiply(s2));
    }

    @Override
    public NumericExp<?> divide(Exp<? extends Number> left, Exp<? extends Number> right) {
        return new DoubleBinaryExp(left.getName() + "/" + right.getName(),
                cast(left),
                cast(right),
                (n1, n2) -> n1 != null && n2 != null ? n1.doubleValue() / n2.doubleValue() : null,
                (s1, s2) -> s1.divide(s2));
    }
}
