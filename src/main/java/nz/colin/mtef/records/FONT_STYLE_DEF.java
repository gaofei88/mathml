package nz.colin.mtef.records;

import nz.colin.mtef.RecordVisitor;

/**
 * Created by colin on 27/08/16.
 */
public class FONT_STYLE_DEF extends Record{
    private final int fontDefIndex;
    private final int charStyle;

    public FONT_STYLE_DEF(int fontDefIndex, int charStyle) {
        this.fontDefIndex = fontDefIndex;
        this.charStyle = charStyle;
    }

    @Override
    public void accept(RecordVisitor visitor) {
        visitor.visit(this);
    }
}
