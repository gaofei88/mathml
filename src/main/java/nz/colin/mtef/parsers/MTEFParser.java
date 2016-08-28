package nz.colin.mtef.parsers;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import nz.colin.mtef.exceptions.ParseException;
import nz.colin.mtef.records.MTEF;
import nz.colin.mtef.records.Record;

import java.io.PushbackInputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by colin on 26/08/16.
 */
public class MTEFParser extends Parser<MTEF>{
    private final static Map<Integer, String> platformMap;
    private final static Map<Integer, String> productMap;

    static {
        Map<Integer, String> _platformMap = Maps.newHashMap();
        _platformMap.put(0, "Macintosh");
        _platformMap.put(1, "Windows");
        platformMap = ImmutableMap.copyOf(_platformMap);

        Map<Integer, String> _productMap = Maps.newHashMap();
        _productMap.put(0, "MathType");
        _productMap.put(1, "Equation Editor");
        productMap = ImmutableMap.copyOf(_productMap);
    }

    protected MTEF doParse(PushbackInputStream in) throws ParseException{
        int platform = get(readByte(in), platformMap);
        int product = get(readByte(in), productMap);
        int productVersion = readByte(in);
        int productSubversion = readByte(in);
        String applicationKey = readNullTerminatedString(in);
        int equationOptions = readByte(in);

        List<Record> records = Lists.newArrayList();

        int type;
        while((type = nextType(in)) != -1){
            Parser next = ParserRegistry.get(type);
            records.add(next.parse(in));
        }
        return new MTEF(ParserRegistry.MTEF, platform, product, productVersion, productSubversion, applicationKey, equationOptions, records);
    }

    private int get(int key, Map map) throws ParseException {
        if (map.containsKey(key)) {
            return key;
        }
        throw new ParseException("Key " + key + " wasn't in map");
    }

    protected int getInitalByte() {
        return ParserRegistry.MTEF;
    }
}
