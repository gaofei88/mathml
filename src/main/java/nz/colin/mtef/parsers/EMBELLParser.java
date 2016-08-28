package nz.colin.mtef.parsers;

import nz.colin.mtef.exceptions.ParseException;
import nz.colin.mtef.records.EMBELL;
import nz.colin.mtef.records.Record;

import java.io.PushbackInputStream;

/**
 * Created by colin on 27/08/16.
 */
public class EMBELLParser extends Parser<EMBELL> {
    protected EMBELL doParse(PushbackInputStream in) throws ParseException {
        int options = readByte(in);
        Record.Nudge nudge = (options & Record.Options.NUDGE) > 0 ? readNudge(in) : new Record.Nudge(0,0);
        int embell = readByte(in);

        return new EMBELL(options, nudge, embell);
    }

    protected int getInitalByte() {
        return ParserRegistry.EMBELL;
    }
}
