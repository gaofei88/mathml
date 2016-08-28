package nz.colin.mtef.parsers;

import nz.colin.mtef.exceptions.ParseException;
import nz.colin.mtef.records.SYM;

import java.io.PushbackInputStream;

/**
 * Created by colin on 27/08/16.
 */
public class SYMParser extends Parser<SYM> {
    protected SYM doParse(PushbackInputStream in) throws ParseException {
        return SYM.inst;
    }

    protected int getInitalByte() {
        return ParserRegistry.SYM;
    }
}
