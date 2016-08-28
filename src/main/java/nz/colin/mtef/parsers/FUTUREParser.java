package nz.colin.mtef.parsers;

import nz.colin.mtef.exceptions.ParseException;
import nz.colin.mtef.records.FUTURE;

import java.io.PushbackInputStream;

/**
 * Created by colin on 28/08/16.
 */
public class FUTUREParser extends Parser<FUTURE>{

    protected FUTURE doParse(PushbackInputStream in) throws ParseException {
        int skip = readByte(in);
        System.out.println(skip);
        return new FUTURE(skip);
    }

    protected int getInitalByte() {
        return ParserRegistry.FUTURE;
    }
}
