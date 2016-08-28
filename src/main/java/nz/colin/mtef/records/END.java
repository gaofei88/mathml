package nz.colin.mtef.records;

import nz.colin.mtef.RecordVisitor;

/**
 * Created by colin on 26/08/16.
 */
public class END extends Record{
    public final static END inst = new END();

    private END(){}

    @Override
    public void accept(RecordVisitor visitor) {
        visitor.visit(this);
    }
}
