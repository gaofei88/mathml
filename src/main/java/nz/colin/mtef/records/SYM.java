package nz.colin.mtef.records;

import nz.colin.mtef.RecordVisitor;

/**
 * Created by colin on 27/08/16.
 */
public class SYM extends Record{
    public final static SYM inst = new SYM();

    private SYM(){}

    @Override
    public void accept(RecordVisitor visitor) {
        visitor.visit(this);
    }
}
