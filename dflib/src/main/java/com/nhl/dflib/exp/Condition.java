package com.nhl.dflib.exp;

import com.nhl.dflib.BooleanSeries;
import com.nhl.dflib.DataFrame;
import com.nhl.dflib.exp.condition.AndCondition;
import com.nhl.dflib.exp.condition.OrCondition;

/**
 * @since 0.11
 */
public interface Condition extends Exp<Boolean> {

    @Override
    BooleanSeries eval(DataFrame df);

    default Condition and(Condition c) {
        return new AndCondition(this, c);
    }

    default Condition or(Condition c) {
        return new OrCondition(this, c);
    }

    @Override
    default Class<Boolean> getType() {
        return Boolean.class;
    }
}
