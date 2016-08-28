package nz.colin.mtef.parsers;

import nz.colin.mtef.exceptions.ParseException;
import nz.colin.mtef.records.FULL;

import java.io.PushbackInputStream;

/**
 * Created by colin on 27/08/16.
 */
public class FULLParser extends Parser<FULL>{

    protected FULL doParse(PushbackInputStream in) throws ParseException {
        return FULL.inst;
    }

    protected int getInitalByte() {
        return ParserRegistry.FULL;
    }
}
