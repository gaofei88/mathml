package nz.colin.mtef.records;

import nz.colin.mtef.RecordVisitor;

/**
 * Created by colin on 27/08/16.
 */
public class COLOR extends Record{
    private final int colorDefIndex;

    public COLOR(int colorDefIndex) { this.colorDefIndex = colorDefIndex; }

    public int getColorDefIndex() {
        return colorDefIndex;
    }

    @Override
    public void accept(RecordVisitor visitor) {
        visitor.visit(this);
    }
}
