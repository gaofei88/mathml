package nz.colin.mtef.records;

import nz.colin.mtef.RecordVisitor;

/**
 * Created by colin on 27/08/16.
 */
public class SUBSYM extends Record{
    public final static SUBSYM inst = new SUBSYM();

    private SUBSYM(){}

    @Override
    public void accept(RecordVisitor visitor) {
        visitor.visit(this);
    }
}
