package nz.colin.mtef.parsers;

import nz.colin.mtef.exceptions.ParseException;
import nz.colin.mtef.records.FONT_STYLE_DEF;

import java.io.PushbackInputStream;

/**
 * Created by colin on 27/08/16.
 */
public class FONT_STYLE_DEFParser extends Parser<FONT_STYLE_DEF> {
    protected FONT_STYLE_DEF doParse(PushbackInputStream in) throws ParseException {
        int fontDefIndex = readUnsignedInt(in);
        int charStyle = readByte(in);
        return new FONT_STYLE_DEF(fontDefIndex, charStyle);
    }

    protected int getInitalByte() {
        return ParserRegistry.FONT_STYLE_DEF;
    }
}
