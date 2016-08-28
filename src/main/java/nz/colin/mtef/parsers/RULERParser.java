package nz.colin.mtef.parsers;

import nz.colin.mtef.exceptions.ParseException;
import nz.colin.mtef.records.RULER;

import java.io.PushbackInputStream;

/**
 * Created by colin on 27/08/16.
 */
public class RULERParser extends Parser<RULER> {
    protected RULER doParse(PushbackInputStream in) throws ParseException {
        int nStops = readByte(in);
        RULER.TabStop[] tabStops = new RULER.TabStop[nStops];
        for (int i=0; i< nStops; i++) {
            int type = readByte(in);
            int offset = readSimple16BitInteger(in);
            tabStops[i] = new RULER.TabStop(type, offset);
        }
        return new RULER(nStops, tabStops);
    }

    protected int getInitalByte() {
        return ParserRegistry.RULER;
    }
}
