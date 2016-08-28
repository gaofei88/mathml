package nz.colin.mathml.domain;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by colin on 28/08/16.
 */
public class Replacement {
    private static final String UNSUPPORTED = "Unsupported (Char)";
    private static final String DEFAULT_TEXTMODE = "(Char)";
    private static final String DEFAULT_MATHMODE = "<mi>(Char)<mi>";

    private static final Range[] ranges = new Range[]{
            new Range(0x0021), new Range(0x0028), new Range(0x0029),new Range(0x002A),
            new Range(0x002B), new Range(0x002C), new Range(0x002D), new Range(0x002E),
            new Range(0x002F), new Range(0x003D), new Range(0x003F), new Range(0x005B),
            new Range(0x005D), new Range(0x007E),
            new Range(0x0000,0x0008), new Range(0x000B,0x001F), new Range(0x0030,0x0039),
            new Range(0x003A,0x003B), new Range(0x0041,0x005A), new Range(0x0061,0x007A),
            new Range(0x0080,0x009F), new Range(0x00A0,0x00B0), new Range(0x00B2,0x00BB),
            new Range(0x00BC,0x00BE), new Range(0x02C6,0x02FF), new Range(0x0300,0x036F),
            new Range(0x2000,0x200B), new Range(0x200C,0x200F), new Range(0x2010,0x2027),
            new Range(0x2028,0x202F), new Range(0x2030,0x2069), new Range(0x206A,0x206F),
            new Range(0x2070,0x209F), new Range(0x20A0,0x20CF), new Range(0x20D0,0x20FF),
            new Range(0x2100,0x2101), new Range(0x2103,0x210A), new Range(0x2116,0x2117),
            new Range(0x213C,0x2146), new Range(0x2150,0x218F), new Range(0x2190,0x21FF),
            new Range(0x2200,0x2211), new Range(0x2213,0x221D), new Range(0x221F,0x22FF),
            new Range(0x2300,0x23FF), new Range(0x2400,0x243F), new Range(0x2500,0x257F),
            new Range(0x2580,0x259F), new Range(0x25A0,0x25FF), new Range(0x2600,0x267F),
            new Range(0x2700,0x27BF), new Range(0x27F0,0x27FF), new Range(0x2900,0x297F),
            new Range(0x2980,0x29AF), new Range(0x29B1,0x29DB), new Range(0x29DD,0x29FF),
            new Range(0x2A00,0x2AFF), new Range(0x3000,0x303F), new Range(0xE000,0xE900),
            new Range(0xE905,0xE90A), new Range(0xE90D,0xE921), new Range(0xE926,0xE92C),
            new Range(0xE92E,0xE931), new Range(0xE934,0xE939), new Range(0xE93C,0xE98E),
            new Range(0xE990,0xEA05), new Range(0xEA08,0xEA0A), new Range(0xEA0D,0xEA31),
            new Range(0xEA36,0xEA39), new Range(0xEA3C,0xEA3F), new Range(0xEA46,0xEB00),
            new Range(0xEB03,0xEB04), new Range(0xEB07,0xED09), new Range(0xED14,0xED15),
            new Range(0xED17,0xEE03), new Range(0xEE04,0xEE0C), new Range(0xEE0D,0xEE18),
            new Range(0xEE1A,0xEEFF), new Range(0xEF09,0xEFFF), new Range(0xF000,0xF033),
            new Range(0xF034,0xF07F), new Range(0xF080,0xF0B3), new Range(0xF0B4,0xF0BF),
            new Range(0xF0C0,0xF0C9), new Range(0xF0CA,0xF0FF), new Range(0xF100,0xF133),
            new Range(0xF134,0xF8FF), new Range(0xFB00,0xFB4F), new Range(0xFE35,0xFE4F)
    };

    private static final TextMap[] textMaps = new TextMap[]{
            new TextMap("<mo>(Char)</mo>"), new TextMap("<mo stretchy='false'>(Char)</mo>"), new TextMap("<mo stretchy='false'>(Char)</mo>"), new TextMap("<mo>(Char)</mo>"),
            new TextMap("<mo>(Char)</mo>"), new TextMap("<mo>(Char)</mo>"), new TextMap("<mo>(Char)</mo>"), new TextMap("<mo>(Char)</mo>"),
            new TextMap("<mo>(Char)</mo>"), new TextMap("<mo>(Char)</mo>"), new TextMap("<mo>(Char)</mo>"), new TextMap("<mo stretchy='false'>(Char)</mo>"),
            new TextMap("<mo stretchy='false'>(Char)</mo>"), new TextMap("<mo>(Char)</mo>"),
            new TextMap(UNSUPPORTED, UNSUPPORTED), new TextMap(UNSUPPORTED, UNSUPPORTED), new TextMap("<mn>(Char)</mn>", "(Char)", "(Char)"),
            new TextMap("<mo>(Char)</mo>", "(Char)"), new TextMap("<mi>(Char)</mi>", "(Char)"), new TextMap("<mi>(Char)</mi>", "(Char)"),
            new TextMap(UNSUPPORTED, UNSUPPORTED), new TextMap("<mo>(CharHex)</mo>"), new TextMap("<mo>(CharHex)</mo>"),
            new TextMap("<mn>(CharHex)</mn>"), new TextMap("<mo>(CharHex)</mo>"), new TextMap("<mo>(CharHex)</mo>"),
            new TextMap("<mtext>(CharHex)</mtext>"), new TextMap(UNSUPPORTED, UNSUPPORTED), new TextMap("<mo>(CharHex)</mo>"),
            new TextMap(UNSUPPORTED, UNSUPPORTED), new TextMap("<mo>(CharHex)</mo>"), new TextMap(UNSUPPORTED, UNSUPPORTED),
            new TextMap("<mo>(CharHex)</mo>"), new TextMap("<mi>(CharHex)</mi>"), new TextMap("<mo>(CharHex)</mo>"),
            new TextMap("<mo>(CharHex)</mo>"), new TextMap("<mo>(CharHex)</mo>"), new TextMap("<mo>(CharHex)</mo>"),
            new TextMap("<mo>(CharHex)</mo>"), new TextMap("<mn>(CharHex)</mn>"), new TextMap("<mo>(CharHex)</mo>"),
            new TextMap("<mo>(CharHex)</mo>"), new TextMap("<mo>(CharHex)</mo>"), new TextMap("<mo>(CharHex)</mo>"),
            new TextMap("<mo>(CharHex)</mo>"), new TextMap("<mo>(CharHex)</mo>"), new TextMap("<mo>(CharHex)</mo>"),
            new TextMap("<mo>(CharHex)</mo>"), new TextMap("<mo>(CharHex)</mo>"), new TextMap("<mo>(CharHex)</mo>"),
            new TextMap("<mo>(CharHex)</mo>"), new TextMap("<mo>(CharHex)</mo>"), new TextMap("<mo>(CharHex)</mo>"),
            new TextMap("<mo>(CharHex)</mo>"), new TextMap("<mo>(CharHex)</mo>"), new TextMap("<mo>(CharHex)</mo>"),
            new TextMap("<mo>(CharHex)</mo>"), new TextMap("<mo>(CharHex)</mo>"), new TextMap(UNSUPPORTED, UNSUPPORTED),
            new TextMap(UNSUPPORTED, UNSUPPORTED), new TextMap(UNSUPPORTED, UNSUPPORTED), new TextMap(UNSUPPORTED, UNSUPPORTED),
            new TextMap(UNSUPPORTED, UNSUPPORTED), new TextMap(UNSUPPORTED, UNSUPPORTED), new TextMap(UNSUPPORTED, UNSUPPORTED),
            new TextMap(UNSUPPORTED, UNSUPPORTED), new TextMap(UNSUPPORTED, UNSUPPORTED), new TextMap(UNSUPPORTED, UNSUPPORTED),
            new TextMap(UNSUPPORTED, UNSUPPORTED), new TextMap(UNSUPPORTED, UNSUPPORTED), new TextMap(UNSUPPORTED, UNSUPPORTED),
            new TextMap(UNSUPPORTED, UNSUPPORTED), new TextMap(UNSUPPORTED, UNSUPPORTED), new TextMap(UNSUPPORTED, UNSUPPORTED),
            new TextMap(UNSUPPORTED, UNSUPPORTED), new TextMap(UNSUPPORTED), new TextMap(UNSUPPORTED,UNSUPPORTED),
            new TextMap(UNSUPPORTED, UNSUPPORTED), new TextMap(UNSUPPORTED, UNSUPPORTED), new TextMap(UNSUPPORTED),
            new TextMap(UNSUPPORTED, UNSUPPORTED), new TextMap(UNSUPPORTED), new TextMap(UNSUPPORTED, UNSUPPORTED),
            new TextMap(UNSUPPORTED), new TextMap(UNSUPPORTED,UNSUPPORTED), new TextMap(UNSUPPORTED),
            new TextMap(UNSUPPORTED, UNSUPPORTED), new TextMap("<mtext>(CharHex)</mtext>"), new TextMap("<mo>(CharHex)</mo>")
    };

    public TextMap testRange(String mtCode){
        int i = Integer.parseInt(mtCode.substring(2),16);
        int index = -1;
        for(int x = 0; x < ranges.length; x++){
            if(ranges[x].withRange(i)){
                index = x; break;
            }
        }
        if(index >= 0){
            return textMaps[index];
        }else{
            return null;
        }
    }
}
