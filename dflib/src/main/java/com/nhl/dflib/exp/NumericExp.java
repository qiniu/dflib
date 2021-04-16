package com.nhl.dflib.exp;

/**
 * @since 0.11
 */
public interface NumericExp<N extends Number> extends Exp<N> {

    // TODO: do implicit operation type calculation based in the pargument types and standard java rules.
    //   e.g. double + int => double

    default NumericExp<Integer> plusInt(Exp<? extends Number> exp) {
        return IntBinaryExp.plus(this, exp);
    }

    default NumericExp<Double> plusDouble(Exp<? extends Number> exp) {
        return DoubleBinaryExp.plus(this, exp);
    }
}
