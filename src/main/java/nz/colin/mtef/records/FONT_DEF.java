package nz.colin.mtef.records;

import nz.colin.mtef.RecordVisitor;

/**
 * Created by colin on 27/08/16.
 */
public class FONT_DEF extends Record{
    private final int encDefIndex;
    private final String fontName;

    public FONT_DEF(int encDefIndex, String fontName) {
        this.encDefIndex = encDefIndex;
        this.fontName = fontName;
    }

    public int getEncDefIndex() {
        return encDefIndex;
    }

    public String getFontName() {
        return fontName;
    }

    @Override
    public void accept(RecordVisitor visitor) {
        visitor.visit(this);
    }
}
