package nz.colin.mtef.parsers;

import nz.colin.mtef.exceptions.ParseException;
import nz.colin.mtef.records.SIZE;

import java.io.PushbackInputStream;

/**
 * Created by colin on 27/08/16.
 */
public class SIZEParser extends Parser<SIZE> {
    protected SIZE doParse(PushbackInputStream in) throws ParseException {
        int f = readByte(in);
        int lSize = 0, dSize = 0;
        SIZE.SizeType sizeType;
        switch (f){
            case 101:
                sizeType = SIZE.SizeType.ExplicitPointSize;
                lSize = readSimple16BitInteger(in);
                break;
            case 100:
                sizeType = SIZE.SizeType.LargeDelta;
                lSize = readByte(in);
                dSize = readSimple16BitInteger(in);
                break;
            default:
                sizeType = SIZE.SizeType.SmallDelta;
                lSize = f;
                dSize = readByte(in) - 128;
                break;

        }
        return new SIZE(sizeType, lSize, dSize);
    }

    protected int getInitalByte() {
        return ParserRegistry.SIZE;
    }
}
