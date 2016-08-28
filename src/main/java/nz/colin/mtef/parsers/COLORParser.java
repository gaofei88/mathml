package nz.colin.mtef.parsers;

import nz.colin.mtef.exceptions.ParseException;
import nz.colin.mtef.records.COLOR;

import java.io.PushbackInputStream;

/**
 * Created by colin on 27/08/16.
 */
public class COLORParser extends Parser<COLOR> {
    protected COLOR doParse(PushbackInputStream in) throws ParseException {
        int colorDefIndex = readUnsignedInt(in);
        return new COLOR(colorDefIndex);
    }

    protected int getInitalByte() {
        return ParserRegistry.COLOR;
    }
}
