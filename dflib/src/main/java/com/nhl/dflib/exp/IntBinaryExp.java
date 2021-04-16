package com.nhl.dflib.exp;

import com.nhl.dflib.IntSeries;
import com.nhl.dflib.Series;

import java.util.function.BinaryOperator;

/**
 * @since 0.11
 */
public class IntBinaryExp extends BinaryExp<Integer> implements NumericExp<Integer> {

    protected static IntBinaryExp plus(Exp<? extends Number> left, Exp<? extends Number> right) {
        return new IntBinaryExp(left.getName() + "+" + right.getName(),
                castToInt(left),
                castToInt(right),
                (n1, n2) -> n1 != null && n2 != null ? n1.intValue() + n2.intValue() : null,
                (s1, s2) -> s1.plus(s2));
    }

    protected static <N extends Number> Exp<Integer> castToInt(Exp<N> exp) {
        return exp.getType().equals(Integer.class)
                ? (Exp<Integer>) exp
                : new UnaryExp<>(exp, Integer.class, (N n) -> n != null ? n.intValue() : null);
    }

    private final BinaryOperator<IntSeries> intOp;

    protected IntBinaryExp(
            String name,
            Exp<Integer> left,
            Exp<Integer> right,
            BinaryOperator<Integer> op,
            BinaryOperator<IntSeries> intOp) {

        super(name, Integer.class, left, right, op);
        this.intOp = intOp;
    }

    @Override
    protected Series<Integer> eval(Series<Integer> ls, Series<Integer> rs) {
        return (ls instanceof IntSeries && rs instanceof IntSeries)
                ? intOp.apply((IntSeries) ls, (IntSeries) rs)
                : super.eval(ls, rs);
    }
}
