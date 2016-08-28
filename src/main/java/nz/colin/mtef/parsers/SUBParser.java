package nz.colin.mtef.parsers;

import nz.colin.mtef.exceptions.ParseException;
import nz.colin.mtef.records.SUB;

import java.io.PushbackInputStream;

/**
 * Created by colin on 27/08/16.
 */
public class SUBParser extends Parser<SUB> {
    protected SUB doParse(PushbackInputStream in) throws ParseException {
        return SUB.inst;
    }

    protected int getInitalByte() {
        return ParserRegistry.SUB;
    }
}
