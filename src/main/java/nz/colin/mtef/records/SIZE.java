package nz.colin.mtef.records;

import nz.colin.mtef.RecordVisitor;

/**
 * Created by colin on 27/08/16.
 */
public class SIZE extends Record{
    public enum SizeType {
        ExplicitPointSize,
        SmallDelta,
        LargeDelta
    }

    private final SizeType sizeType;
    private final int lSize;
    private final int dSize;

    public SIZE(SizeType sizeType, int lSize, int dSize) {
        this.sizeType = sizeType;
        this.lSize = lSize;
        this.dSize = dSize;
    }

    public int getlSize() {
        return lSize;
    }

    public int getdSize() {
        return dSize;
    }

    @Override
    public void accept(RecordVisitor visitor) {
        visitor.visit(this);
    }
}
