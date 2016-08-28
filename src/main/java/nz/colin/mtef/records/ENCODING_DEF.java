package nz.colin.mtef.records;

import nz.colin.mtef.RecordVisitor;

/**
 * Created by colin on 27/08/16.
 */
public class ENCODING_DEF extends Record{
    private final String name;

    public ENCODING_DEF(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(RecordVisitor visitor) {
        visitor.visit(this);
    }
}
