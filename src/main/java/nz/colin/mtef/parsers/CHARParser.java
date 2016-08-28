package nz.colin.mtef.parsers;

import com.google.common.collect.Lists;
import nz.colin.mtef.exceptions.ParseException;
import nz.colin.mtef.records.CHAR;
import nz.colin.mtef.records.Record;

import java.io.PushbackInputStream;
import java.util.List;


/**
 * Created by colin on 27/08/16.
 */
public class CHARParser extends Parser<CHAR> {
    protected CHAR doParse(PushbackInputStream in) throws ParseException {
        int options = readByte(in);
        Record.Nudge nudge = (options & Record.Options.NUDGE) > 0 ? readNudge(in) : new Record.Nudge(0,0);
        int typeFace = readUnsignedInt(in);

        Integer mtCodeValue = (options & Record.Options.CHAR_ENC_NO_MTCODE) > 0 ? null : readSimple16BitInteger(in);
        Integer fontPosition = null;
        if((options & Record.Options.CHAR_ENC_CHAR_8) > 0){
            fontPosition = readByte(in);
        }else if((options & Record.Options.CHAR_ENC_CHAR_16) > 0){
            fontPosition = readSimple16BitInteger(in);
        }

        if(fontPosition == null && mtCodeValue == null){
            throw new ParseException("Unable to read char code");
        }

        List<Record> embellishments = Lists.newArrayList();
        if((options & Record.Options.CHAR_EMBELL) > 0){
            readRecordsToEnd(in, embellishments);
        }

        return new CHAR(options, nudge, typeFace, mtCodeValue, fontPosition, embellishments);
    }

    protected int getInitalByte() {
        return ParserRegistry.CHAR;
    }
}
