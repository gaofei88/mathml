package nz.colin.mtef.parsers;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import nz.colin.mtef.exceptions.ParseException;
import nz.colin.mtef.records.EQN_PREFS;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by colin on 27/08/16.
 */
public class EQN_PREFSParser extends Parser<EQN_PREFS> {
    private final static Map<Integer, String> map;

    static {
        Map<Integer, String> _map = Maps.newHashMap();
        _map.put(0, "inches");
        _map.put(1, "centimeters");
        _map.put(2, "points");
        _map.put(3, "picas");
        _map.put(4, "percentage");
        map = ImmutableMap.copyOf(_map);
    }

    protected EQN_PREFS doParse(PushbackInputStream in) throws ParseException {
        int options = readByte(in);
        if(options != 0){
            throw new ParseException("Options should have been 0.");
        }

        int sizesCount = readByte(in);
        NibbleReader sizeReader = new NibbleReader(in);
        List<EQN_PREFS.Nibbles> sizes = Lists.newArrayList();
        for(int x = 0; x < sizesCount; x++){
            int  sizeUnit = sizeReader.nextNibble();
            List<Integer> sizeNibbles = Lists.newArrayList();
            sizeReader.readNibbles(sizeNibbles);
            sizes.add(new EQN_PREFS.Nibbles(sizeUnit,sizeNibbles));
        }

        int spacesCount = readByte(in);
        NibbleReader spaceReader = new NibbleReader(in);
        List<EQN_PREFS.Nibbles> spaces = Lists.newArrayList();
        for(int y = 0; y < spacesCount; y++){
            int spaceUnit = spaceReader.nextNibble();
            List<Integer> spaceNibbles = Lists.newArrayList();
            spaceReader.readNibbles(spaceNibbles);
            spaces.add(new EQN_PREFS.Nibbles(spaceUnit, spaceNibbles));
        }

        int stylesCount = readByte(in);
        List<EQN_PREFS.Style> styles = Lists.newArrayList();
        for(int z = 0; z < stylesCount; z++){
            int fontDef = readUnsignedInt(in);
            if(fontDef != 0){
                styles.add(new EQN_PREFS.Style(fontDef, readByte(in)));
            }else{
                styles.add(new EQN_PREFS.Style(0,0));
            }
        }
        return new EQN_PREFS(options, sizesCount,spacesCount,stylesCount,sizes,spaces,styles);
    }

    protected int getInitalByte() {
        return ParserRegistry.EQN_PREFS;
    }

    public class NibbleReader {
        private final InputStream in;
        int currentByte = -1;

        public NibbleReader(InputStream in) {
            this.in = in;
        }

        public int nextNibble() throws ParseException {
            try {
                int res = -1;
                if (currentByte == -1) {
                    currentByte = in.read();
                    if (currentByte == -1) {
                        throw new ParseException("Ran out of nibbles");
                    }
                    res = (currentByte & 0xf0) >> 4;
                } else {
                    res = currentByte & 0xf;
                    currentByte = -1;
                }
                return res;
            } catch (IOException e) {
                throw new ParseException("Couldn't read from stream", e);
            }
        }

        public void readNibbles(List nibbles) throws ParseException {
            int nextNibble;
            while ((nextNibble = nextNibble()) != 0xf) {
                switch (nextNibble) {
                    case 0xc:
                    case 0xd:
                    case 0xe:
                        throw new ParseException("Illegal nibble: " + nextNibble);
                    default:
                        nibbles.add(nextNibble);
                        break;
                }
            }
        }


    }

}
