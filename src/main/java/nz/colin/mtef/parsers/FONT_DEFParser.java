package nz.colin.mtef.parsers;

import nz.colin.mtef.exceptions.ParseException;
import nz.colin.mtef.records.FONT_DEF;

import java.io.PushbackInputStream;

/**
 * Created by colin on 27/08/16.
 */
public class FONT_DEFParser extends Parser<FONT_DEF>{
    protected FONT_DEF doParse(PushbackInputStream in) throws ParseException {
        int encDedIndex = readUnsignedInt(in);
        String fontName = readNullTerminatedString(in);
        return new FONT_DEF(encDedIndex, fontName);
    }

    protected int getInitalByte() {
        return ParserRegistry.FONT_DEF;
    }
}
