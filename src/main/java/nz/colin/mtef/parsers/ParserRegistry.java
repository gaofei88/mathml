package nz.colin.mtef.parsers;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import nz.colin.mtef.exceptions.ParseException;

import java.util.Map;

/**
 * Created by colin on 26/08/16.
 */
public class ParserRegistry {
    private static final Map<Integer, Parser> parsers;

    public static final int MTEF = 5;

    public static final int END = 0;
    public static final int SLOT = 1;
    public static final int CHAR = 2;
    public static final int TMPL = 3;
    public static final int PILE = 4;
    public static final int MATRIX = 5;
    public static final int EMBELL = 6;
    public static final int RULER = 7;
    public static final int FONT_STYLE_DEF = 8;
    public static final int SIZE = 9;
    public static final int FULL = 10;
    public static final int SUB = 11;
    public static final int SUB2 = 12;
    public static final int SYM = 13;
    public static final int SUBSYM = 14;
    public static final int COLOR = 15;
    public static final int COLOR_DEF = 16;
    public static final int FONT_DEF = 17;
    public static final int EQN_PREFS = 18;
    public static final int ENCODING_DEF = 19;
    public static final int FUTURE = 100;

    static {
        Map<Integer, Parser> _parsers = Maps.newHashMap();
        _parsers.put(END, new ENDParser());
        _parsers.put(SLOT, new SLOTParser());
        _parsers.put(CHAR, new CHARParser());
        _parsers.put(TMPL, new TMPLParser());
        _parsers.put(PILE, new PILEParser());
        _parsers.put(MATRIX, new MATRIXParser());
        _parsers.put(EMBELL, new EMBELLParser());
        _parsers.put(RULER, new RULERParser());
        _parsers.put(FONT_STYLE_DEF, new FONT_STYLE_DEFParser());
        _parsers.put(SIZE, new SIZEParser());
        _parsers.put(FULL, new FULLParser());
        _parsers.put(SUB, new SUBParser());
        _parsers.put(SUB2, new SUB2Parser());
        _parsers.put(SYM, new SYMParser());
        _parsers.put(SUBSYM, new SUBSYMParser());
        _parsers.put(COLOR, new COLORParser());
        _parsers.put(COLOR_DEF, new COLOR_DEFParser());
        _parsers.put(FONT_DEF, new FONT_DEFParser());
        _parsers.put(EQN_PREFS, new EQN_PREFSParser());
        _parsers.put(ENCODING_DEF, new ENCODING_DEFParser());
        _parsers.put(FUTURE, new FUTUREParser());
        parsers = ImmutableMap.copyOf(_parsers);
    }

    public static Parser get(int i) throws ParseException {
        if(parsers.containsKey(i)){
            return parsers.get(i);
        }
        throw new ParseException("No parser for type " + i);
    }
}
