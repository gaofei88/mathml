package nz.colin.mtef.records;

import nz.colin.mtef.RecordVisitor;

/**
 * Created by colin on 27/08/16.
 */
public class SUB extends Record{
    public final static SUB inst = new SUB();

    private SUB(){}

    @Override
    public void accept(RecordVisitor visitor) {
        visitor.visit(this);
    }
}
