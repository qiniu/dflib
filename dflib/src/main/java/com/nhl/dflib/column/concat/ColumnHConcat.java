package com.nhl.dflib.column.concat;

import com.nhl.dflib.DataFrame;
import com.nhl.dflib.Index;
import com.nhl.dflib.Series;
import com.nhl.dflib.column.ColumnDataFrame;
import com.nhl.dflib.join.JoinType;
import com.nhl.dflib.series.ArraySeries;

import java.util.function.UnaryOperator;

public class ColumnHConcat {

    private JoinType semantics;

    public ColumnHConcat(JoinType semantics) {
        this.semantics = semantics;
    }

    public DataFrame concat(Index joinedColumns, DataFrame lf, DataFrame rf) {
        int lh = lf.height();
        int rh = rf.height();

        int h = concatHeight(lh, rh);

        UnaryOperator<Series<?>> lt = seriesTrimmer(lh, h);
        UnaryOperator<Series<?>> rt = seriesTrimmer(rh, h);

        int w = joinedColumns.size();
        Series<?>[] newData = new Series[w];
        int i = 0;

        for (Series<?> s : lf.getDataColumns()) {
            newData[i++] = lt.apply(s);
        }

        for (Series<?> s : rf.getDataColumns()) {
            newData[i++] = rt.apply(s);
        }

        return new ColumnDataFrame(joinedColumns, newData);
    }

    private UnaryOperator<Series<?>> seriesTrimmer(int seriesHeight, int desiredHeight) {
        if (seriesHeight < desiredHeight) {
            return s -> padSeries(s, desiredHeight - seriesHeight);
        } else if (seriesHeight > desiredHeight) {
            return s -> s.openClosedRange(0, desiredHeight);
        } else {
            return UnaryOperator.identity();
        }
    }

    private <T> Series<T> padSeries(Series<T> series, int paddingSize) {
        Object[] padded = new Object[series.size() + paddingSize];
        series.copyTo(padded, 0, 0, series.size());
        return new ArraySeries(padded);
    }

    private int concatHeight(int lh, int rh) {
        switch (semantics) {
            case full:
                return Math.max(lh, rh);
            case right:
                return rh;
            case left:
                return lh;
            case inner:
                return Math.min(lh, rh);
            default:
                throw new IllegalStateException("Unsupported join type: " + semantics);
        }
    }


}
