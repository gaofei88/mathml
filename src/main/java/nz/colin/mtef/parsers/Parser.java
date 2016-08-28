package nz.colin.mtef.parsers;

import nz.colin.mtef.exceptions.ParseException;
import nz.colin.mtef.records.END;
import nz.colin.mtef.records.Record;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PushbackInputStream;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by colin on 26/08/16.
 */
public abstract class Parser<T extends Record> {
    private final static Charset ASCII = Charset.forName("ASCII");

    public T parse(PushbackInputStream in) throws ParseException {
        try{
            int f = in.read();
            if(f != getInitalByte()){
                throw new ParseException("First byte of record was " + f + " but expected " + getInitalByte());
            }
            return doParse(in);
        } catch (IOException e){
            throw new ParseException("Unable to read stream", e);
        }
    }

    protected int readByte(PushbackInputStream in) throws ParseException {
        try {
            int i = in.read();
            if (i == -1) {
                throw new ParseException("Ran out of stream");
            }
            return i;
        } catch (IOException e) {
            throw new ParseException("Couldn't read from stream", e);
        }
    }

    protected int readSimple16BitInteger(PushbackInputStream in) throws ParseException {
        return readTwoByteUnsignedInt(in);
    }

    protected String readNullTerminatedString(PushbackInputStream in) throws ParseException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i;
        while ((i = readByte(in)) > 0) {
            baos.write(i);
        }
        return new String(baos.toByteArray(), ASCII);
    }

    protected Record.Nudge readNudge(PushbackInputStream in) throws ParseException {
        int dx = readByte(in);
        int dy = readByte(in);
        // TODO - check
        if (dx == 255 && dy == 255) {
            dx = readTwoByteSignedInt(in);
            dy = readTwoByteSignedInt(in);
            return new Record.Nudge(dx, dy);
        } else {
            return new Record.Nudge(dx - 128, dy - 128);
        }
    }

    protected int nextType(PushbackInputStream in) throws ParseException {
        try {
            int next = in.read();
            if (next != -1) {
                in.unread(next);
            }
            return next;
        } catch (IOException e) {
            throw new ParseException("Couldn't check next type", e);
        }
    }

    protected int readTwoByteSignedInt(PushbackInputStream in) throws ParseException {
        return readTwoByteUnsignedInt(in) - 32768;
    }

    protected int readTwoByteUnsignedInt(PushbackInputStream in) throws ParseException {
        int low = readByte(in);
        int high = readByte(in);
        return (high << 8) + low;
    }

    protected int readUnsignedInt(PushbackInputStream in) throws ParseException {
        int first = readByte(in);
        if (first < 255) {
            return first;
        } else {
            return readTwoByteUnsignedInt(in);
        }
    }

    protected void readRecordsToEnd(PushbackInputStream in, List<Record> records) throws ParseException {
        while (true) {
            int type = nextType(in);
            Parser next = ParserRegistry.get(type);
            Record record = next.parse(in);
            if (record instanceof END) {
                break;
            }
            records.add(record);
        }
    }

    protected abstract T doParse(PushbackInputStream in) throws ParseException;

    protected abstract int getInitalByte();

}
