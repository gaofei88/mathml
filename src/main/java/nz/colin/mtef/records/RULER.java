package nz.colin.mtef.records;

import com.google.common.collect.ImmutableList;
import nz.colin.mtef.RecordVisitor;

import java.util.List;

/**
 * Created by colin on 27/08/16.
 */
public class RULER extends Record{
    public final static RULER NO_RULER = new RULER(0, new TabStop[]{});

    private final int nStops;
    private final List<TabStop> tabStops;

    public RULER(int nStops, TabStop[] tabStops) {
        this.nStops = nStops;
        this.tabStops = ImmutableList.copyOf(tabStops);
    }

    public static class TabStop {
        private final int type;
        private final int offset;

        public TabStop(int type, int offset) {
            this.type = type;
            this.offset = offset;
        }
    }

    @Override
    public void accept(RecordVisitor visitor) {
        visitor.visit(this);
    }
}
