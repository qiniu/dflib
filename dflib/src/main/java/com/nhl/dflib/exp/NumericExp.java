package com.nhl.dflib.exp;

import com.nhl.dflib.exp.num.NumericExpFactory;

/**
 * @since 0.11
 */
public interface NumericExp<N extends Number> extends ValueExp<N> {

    default NumericExp<?> plus(Exp<? extends Number> exp) {
        return NumericExpFactory.factory(this, exp).plus(this, exp);
    }

    default NumericExp<?> minus(Exp<? extends Number> exp) {
        return NumericExpFactory.factory(this, exp).minus(this, exp);
    }

    default NumericExp<?> multiply(Exp<? extends Number> exp) {
        return NumericExpFactory.factory(this, exp).multiply(this, exp);
    }

    default NumericExp<?> divide(Exp<? extends Number> exp) {
        return NumericExpFactory.factory(this, exp).divide(this, exp);
    }

    default Condition lt(Exp<? extends Number> exp) {
        return NumericExpFactory.factory(this, exp).lt(this, exp);
    }

    default Condition le(Exp<? extends Number> exp) {
        return NumericExpFactory.factory(this, exp).le(this, exp);
    }

    default Condition gt(Exp<? extends Number> exp) {
        return NumericExpFactory.factory(this, exp).gt(this, exp);
    }

    default Condition ge(Exp<? extends Number> exp) {
        return NumericExpFactory.factory(this, exp).ge(this, exp);
    }
}
