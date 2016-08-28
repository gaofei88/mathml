package nz.colin.mtef.records;

import nz.colin.mtef.RecordVisitor;

import java.util.List;

/**
 * Created by colin on 27/08/16.
 */
public class COLOR_DEF extends Record {
    private final int options;
    private final List<Integer> colorValues;
    private final String name;

    public COLOR_DEF(int options, List<Integer> colorValues, String name) {
        this.options = options;
        this.colorValues = colorValues;
        this.name = name;
    }

    @Override
    public void accept(RecordVisitor visitor) {
        visitor.visit(this);
    }
}
