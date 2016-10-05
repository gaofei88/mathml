package nz.colin.mtef.parsers;

import com.google.common.collect.Lists;
import nz.colin.mtef.exceptions.ParseException;
import nz.colin.mtef.records.*;

import java.io.PushbackInputStream;
import java.util.List;

/**
 * Created by colin on 27/08/16.
 */
public class SLOTParser extends Parser<SLOT> {
    protected SLOT doParse(PushbackInputStream in) throws ParseException {
        int options = readByte(in);
        Record.Nudge nudge = (options & Record.Options.NUDGE) > 0 ? readNudge(in) : new Record.Nudge(0,0);
        int lineSpacing = (options & Record.Options.LINE_LSPACE) > 0 ? readSimple16BitInteger(in) : -1;
        RULER ruler = (options & Record.Options.LP_RULER) > 0 ? (RULER) ParserRegistry.get(ParserRegistry.RULER).parse(in) : RULER.NO_RULER;
        List<Record> records = Lists.newArrayList();
        if((options & Record.Options.LINE_NULL) <= 0){
            readRecordsToEnd(in, records);
        }
        return new SLOT(options, nudge, lineSpacing, ruler, records);
    }

    protected int getInitalByte() {
        return ParserRegistry.SLOT;
    }
}
