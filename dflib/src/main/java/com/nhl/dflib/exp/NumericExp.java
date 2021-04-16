package com.nhl.dflib.exp;

import com.nhl.dflib.exp.num.ArithmeticExpFactory;

/**
 * @since 0.11
 */
public interface NumericExp<N extends Number> extends ValueExp<N> {

    default NumericExp<?> plus(Exp<? extends Number> exp) {
        return ArithmeticExpFactory.factory(this, exp).plus(this, exp);
    }

    default NumericExp<?> minus(Exp<? extends Number> exp) {
        return ArithmeticExpFactory.factory(this, exp).minus(this, exp);
    }

    default NumericExp<?> multiply(Exp<? extends Number> exp) {
        return ArithmeticExpFactory.factory(this, exp).multiply(this, exp);
    }

    default NumericExp<?> divide(Exp<? extends Number> exp) {
        return ArithmeticExpFactory.factory(this, exp).divide(this, exp);
    }
}
