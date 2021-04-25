package com.nhl.dflib.exp.sorter;

import com.nhl.dflib.DataFrame;
import com.nhl.dflib.sort.IntComparator;

/**
 * An expression for sorting DataFrame data.
 *
 * @since 0.11
 */
public interface Sorter {

    IntComparator eval(DataFrame df);
}
