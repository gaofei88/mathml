package nz.colin.mtef.records;

import nz.colin.mtef.RecordVisitor;

import java.util.List;

/**
 * Created by colin on 27/08/16.
 */
public class SLOT extends Record{
    private final int options;
    private final Nudge nudge;
    private final int lineSpacing;
    private final RULER ruler;

    private final List<Record> records;


    public SLOT(int options, Nudge nudge, int lineSpacing, RULER ruler, List<Record> records) {
        this.options = options;
        this.nudge = nudge;
        this.lineSpacing = lineSpacing;
        this.ruler = ruler;
        this.records = records;
    }

    public int getOptions() {
        return options;
    }

    public Nudge getNudge() {
        return nudge;
    }

    public int getLineSpacing() {
        return lineSpacing;
    }

    public RULER getRuler() {
        return ruler;
    }

    public List<Record> getRecords() {
        return records;
    }

    @Override
    public void accept(RecordVisitor visitor) {
        visitor.visit(this);
    }
}
