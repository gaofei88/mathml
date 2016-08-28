package nz.colin.mtef.records;

import nz.colin.mtef.RecordVisitor;

import java.util.List;

/**
 * Created by colin on 27/08/16.
 */
public class CHAR extends Record{
    private final int options;
    private final Nudge nudge;
    private final int typeface;
    private final Integer mtCodeValue;
    private final Integer fontPosition;
    private final List<Record> embellishments;

    public CHAR(int options, Nudge nudge, int typeface, Integer mtCodeValue, Integer fontPosition, List<Record> embellishments) {
        this.options = options;
        this.nudge = nudge;
        this.typeface = typeface;
        this.mtCodeValue = mtCodeValue;
        this.fontPosition = fontPosition;
        this.embellishments = embellishments;
    }

    public int getOptions() {
        return options;
    }

    public Nudge getNudge() {
        return nudge;
    }

    public String getVariation(){
        switch(typeface){
            case 1:
            case 9:
            case 10:
                return "textmode";
            default:
                return "mathmode";
        }
    }
    public int getTypeface() {
        return typeface;
    }

    public String getMtCodeValue() {
        return String.format("0x%04X", mtCodeValue);
    }

    public Integer getFontPosition() {
        return fontPosition;
    }

    public List<Record> getEmbellishments() {
        return embellishments;
    }

    //refer char.rb, font_position not fully recognized
    @Override
    public void accept(RecordVisitor visitor) {
        visitor.visit(this);
    }
}
