package com.nhl.dflib.exp;

/**
 * @since 0.11
 */
public class StringColumn extends ColumnExp<String> {

    public StringColumn(String name) {
        super(name, String.class);
    }

    public Exp<String> concat(Exp<String> c) {
        return new BinaryExp<>(
                getName() + "+" + c.getName(),
                String.class,
                this,
                c,
                (s1, s2) -> s1 + s2);
    }
}
