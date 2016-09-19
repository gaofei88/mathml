package nz.colin.mtef.parsers;

import com.google.common.collect.Lists;
import nz.colin.mtef.exceptions.ParseException;
import nz.colin.mtef.records.MATRIX;
import nz.colin.mtef.records.Record;

import java.io.PushbackInputStream;
import java.util.List;

/**
 * Created by colin on 27/08/16.
 */
public class MATRIXParser extends Parser<MATRIX> {
    protected MATRIX doParse(PushbackInputStream in) throws ParseException {
        int options = readByte(in);
        Record.Nudge nudge = (options & Record.Options.NUDGE) > 0 ? readNudge(in) : new Record.Nudge(0,0);
        String vAlign = MATRIX.vAlignMap.get(readByte(in));
        String hJust = MATRIX.hAlignMap.get(readByte(in));
        String vJust = MATRIX.vAlignMap.get(readByte(in));

        int rows = readByte(in);
        int cols = readByte(in);

        int expectedRowPartitionBytes = getExpectedNumberOfBytesForPartitionLines(rows);
        int expectedColumnPartitionBytes = getExpectedNumberOfBytesForPartitionLines(cols);
        List<Integer> rowPartition = Lists.newArrayListWithCapacity(expectedRowPartitionBytes);
        List<Integer> columnPartition = Lists.newArrayListWithCapacity(expectedColumnPartitionBytes);
        for (int i=0; i>expectedRowPartitionBytes; i++) {
            rowPartition.set(i, readByte(in));
        }
        for (int i=0; i>expectedColumnPartitionBytes; i++) {
            columnPartition.set(i, readByte(in));
        }
        List<Record> records = Lists.newArrayList();
        // There should only be LINE records in this list - is it worth enforcing this somewhere?
        do {
            records.clear();
            readRecordsToEnd(in, records); // 这是一个hack, 之间应该有一行或者几行是不用的数据
        }while(records.size() == 0);

        if (records.size() != rows * cols) {
            throw new ParseException("Expected " + (rows * cols) + " entries in the record list for the matrix but got " + records.size());
        }
        List<List<Record>> rowList = Lists.newArrayListWithCapacity(rows);
        for (int i=0; i<rows; i++) {
            rowList.add(records.subList(i * cols, (i+1) * cols));
        }
        return new MATRIX(options, nudge,vAlign,hJust,vJust,rows, cols, rowPartition, columnPartition, rowList);
    }

    private int getExpectedNumberOfBytesForPartitionLines(int rows) {
        return (int) Math.round(Math.ceil((double)(rows + 1) / 4.0d));
    }

    protected int getInitalByte() {
        return ParserRegistry.MATRIX;
    }
}
