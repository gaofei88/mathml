package nz.colin.mtef.records;

import nz.colin.mtef.RecordVisitor;

/**
 * Created by colin on 26/08/16.
 */
public class FUTURE extends Record {
    private final int skip;

    public FUTURE(int skip) {
        this.skip = skip;
    }

    public void accept(RecordVisitor visitor) {
        visitor.visit(this);
    }
}
