package nz.colin.mtef.parsers;

import com.google.common.collect.Lists;
import nz.colin.mtef.exceptions.ParseException;
import nz.colin.mtef.records.PILE;
import nz.colin.mtef.records.RULER;
import nz.colin.mtef.records.Record;

import java.io.PushbackInputStream;
import java.util.List;

/**
 * Created by colin on 27/08/16.
 */
public class PILEParser extends Parser<PILE> {
    protected PILE doParse(PushbackInputStream in) throws ParseException {
        int options = readByte(in);
        Record.Nudge nudge = (options & Record.Options.NUDGE) > 0 ? readNudge(in) :  new Record.Nudge(0,0);
        int hAlign = readByte(in);
        int vAlign = readByte(in);
        RULER ruler = (options & Record.Options.LP_RULER) > 0 ? (RULER) ParserRegistry.get(ParserRegistry.RULER).parse(in) : RULER.NO_RULER;
        List<Record> records = Lists.newArrayList();
        readRecordsToEnd(in, records);
        return new PILE(options, nudge, hAlign, vAlign, ruler, records);
    }

    protected int getInitalByte() {
        return ParserRegistry.PILE;
    }
}
