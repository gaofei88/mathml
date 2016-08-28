package nz.colin.mtef.parsers;

import nz.colin.mtef.exceptions.ParseException;
import nz.colin.mtef.records.SUBSYM;

import java.io.PushbackInputStream;

/**
 * Created by colin on 27/08/16.
 */
public class SUBSYMParser extends Parser<SUBSYM> {
    protected SUBSYM doParse(PushbackInputStream in) throws ParseException {
        return SUBSYM.inst;
    }

    protected int getInitalByte() {
        return ParserRegistry.SUBSYM;
    }
}
