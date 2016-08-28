package nz.colin.mtef.parsers;

import nz.colin.mtef.exceptions.ParseException;
import nz.colin.mtef.records.END;

import java.io.PushbackInputStream;

/**
 * Created by colin on 26/08/16.
 */
public class ENDParser extends Parser<END> {
    protected END doParse(PushbackInputStream in) throws ParseException {
        return END.inst;
    }

    protected int getInitalByte() {
        return ParserRegistry.END;
    }
}
