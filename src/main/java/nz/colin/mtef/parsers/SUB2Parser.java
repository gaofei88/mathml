package nz.colin.mtef.parsers;

import nz.colin.mtef.exceptions.ParseException;
import nz.colin.mtef.records.SUB2;

import java.io.PushbackInputStream;

/**
 * Created by colin on 27/08/16.
 */
public class SUB2Parser extends Parser<SUB2>{
    protected SUB2 doParse(PushbackInputStream in) throws ParseException {
        return SUB2.inst;
    }

    protected int getInitalByte() {
        return ParserRegistry.SUB2;
    }
}
