package nz.colin.mtef.records;

import nz.colin.mtef.RecordVisitor;

/**
 * Created by colin on 27/08/16.
 */
public class SUB2 extends Record{
    public final static SUB2 inst = new SUB2();

    private SUB2(){}

    @Override
    public void accept(RecordVisitor visitor) {
        visitor.visit(this);
    }
}
