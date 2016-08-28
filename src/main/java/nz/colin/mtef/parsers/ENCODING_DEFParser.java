package nz.colin.mtef.parsers;

import nz.colin.mtef.exceptions.ParseException;
import nz.colin.mtef.records.ENCODING_DEF;

import java.io.PushbackInputStream;

/**
 * Created by colin on 27/08/16.
 */
public class ENCODING_DEFParser extends Parser<ENCODING_DEF> {
    protected ENCODING_DEF doParse(PushbackInputStream in) throws ParseException {
        String name = readNullTerminatedString(in);
        return new ENCODING_DEF(name);
    }

    protected int getInitalByte() {
        return ParserRegistry.ENCODING_DEF;
    }
}
