package nz.colin.mtef.parsers;

import com.google.common.collect.Lists;
import nz.colin.mtef.exceptions.ParseException;
import nz.colin.mtef.records.COLOR_DEF;
import nz.colin.mtef.records.Record;

import java.io.PushbackInputStream;
import java.util.List;

/**
 * Created by colin on 27/08/16.
 */
public class COLOR_DEFParser extends Parser<COLOR_DEF>{

    protected COLOR_DEF doParse(PushbackInputStream in) throws ParseException {
        int options = readByte(in);
        int s = (options & Record.Options.COLOR_CMYK) > 0 ? 4 : 3;
        List<Integer> colorValues = Lists.newArrayListWithCapacity(s);
        for(int i = 0; i < s; i++){
            colorValues.set(i, readSimple16BitInteger(in));
        }

        String name = (options & Record.Options.COLOR_NAME) > 0 ? readNullTerminatedString(in) : "";
        return new COLOR_DEF(options, colorValues, name);
    }

    protected int getInitalByte() {
        return ParserRegistry.COLOR_DEF;
    }
}
