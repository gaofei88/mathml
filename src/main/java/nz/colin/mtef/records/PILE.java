package nz.colin.mtef.records;

import nz.colin.mtef.RecordVisitor;

import java.util.List;

/**
 * Created by colin on 27/08/16.
 */
public class PILE extends Record{
    private final int options;
    private final Nudge nudge;
    private final int hAlign;
    private final int vAlign;
    private final RULER ruler;
    private final List<Record> records;

    public PILE(int options, Nudge nudge, int hAlign, int vAlign, RULER ruler, List<Record> records) {
        this.options = options;
        this.nudge = nudge;
        this.hAlign = hAlign;
        this.vAlign = vAlign;
        this.ruler = ruler;
        this.records = records;
    }

    public List<Record> getRecords() {
        return records;
    }

    @Override
    public void accept(RecordVisitor visitor) {
        visitor.visit(this);
    }
}
