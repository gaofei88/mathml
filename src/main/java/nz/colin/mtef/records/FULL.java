package nz.colin.mtef.records;

import nz.colin.mtef.RecordVisitor;

/**
 * Created by colin on 27/08/16.
 */
public class FULL extends Record{
    public final static FULL inst = new FULL();
    private FULL(){}

    @Override
    public void accept(RecordVisitor visitor) {
        visitor.visit(this);
    }
}
