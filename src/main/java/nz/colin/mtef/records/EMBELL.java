package nz.colin.mtef.records;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import nz.colin.mtef.RecordVisitor;

import java.util.Map;

/**
 * Created by colin on 27/08/16.
 */
public class EMBELL extends Record{
    private final int options;
    private final Nudge nudge;
    private final int embell;

    public EMBELL(int options, Nudge nudge, int embell) {
        this.options = options;
        this.nudge = nudge;
        this.embell = embell;
    }

    @Override
    public void accept(RecordVisitor visitor) {
        visitor.visit(this);
    }


    public static final int emb1DOT = 2;
    public static final int emb2DOT = 3;
    public static final int emb3DOT = 4;
    public static final int emb1PRIME = 5;
    public static final int emb2PRIME = 6;
    public static final int embBPRIME = 7;
    public static final int embTILDE = 8;
    public static final int embHAT = 9;
    public static final int embNOT = 10;
    public static final int embRARROW = 11;
    public static final int embLARROW = 12;
    public static final int embBARROW = 13;
    public static final int embR1ARROW = 14;
    public static final int embL1ARROW = 15;
    public static final int embMBAR = 16;
    public static final int emb0BAR = 17;
    public static final int emb3PRIME = 18;
    public static final int embFROWN = 19;
    public static final int embSMILE = 20;
    public static final int embX_BARS = 21;
    public static final int embUP_BAR = 22;
    public static final int embDOWN_BAR = 23;
    public static final int emb4DOT = 24;
    public static final int embU_1DOT = 25;
    public static final int embU_2DOT = 26;
    public static final int embU_3DOT = 27;
    public static final int embU_4DOT = 28;
    public static final int embU_BAR = 29;
    public static final int embU_TILDE = 30;
    public static final int embU_FROWN = 31;
    public static final int embU_SMILE = 32;
    public static final int embU_FARROW = 33;
    public static final int embU_LARROW = 34;
    public static final int embU_BARROW = 35;
    public static final int embU_R1ARROW = 36;
    public static final int embU_L1ARROW = 37;

    private static final Map<Integer, String> embellishments;

    static {
        Map<Integer,String> _embellishments = Maps.newHashMap();
        _embellishments.put(emb1DOT, "embDOT");
        _embellishments.put(emb2DOT, "emb2DOT");
        _embellishments.put(emb3DOT, "emb3DOT");
        _embellishments.put(emb1PRIME, "emb1PRIME");
        _embellishments.put(emb2PRIME, "emb2PRIME");
        _embellishments.put(embBPRIME, "embBPRIME");
        _embellishments.put(embTILDE, "embTILDE");
        _embellishments.put(embHAT, "embHAT");
        _embellishments.put(embRARROW, "embRARROW");
        _embellishments.put(embNOT, "embNOT");
        _embellishments.put(embLARROW, "embLARROW");
        _embellishments.put(embBARROW, "embBARROW");
        _embellishments.put(embR1ARROW, "embR1ARROW");
        _embellishments.put(embL1ARROW, "embL1ARROW");
        _embellishments.put(embMBAR, "embMBAR");
        _embellishments.put(emb0BAR, "emb0BAR");
        _embellishments.put(emb3PRIME, "emb3PRIME");
        _embellishments.put(embFROWN, "embFROWN");
        _embellishments.put(embSMILE, "embSMILE");
        _embellishments.put(embX_BARS, "embX_BARS");
        _embellishments.put(embUP_BAR, "embUP_BAR");
        _embellishments.put(embDOWN_BAR, "embDOWN_BAR");
        _embellishments.put(emb4DOT, "emb4DOT");
        _embellishments.put(embU_1DOT, "embU_1DOT");
        _embellishments.put(embU_2DOT, "embU_2DOT");
        _embellishments.put(embU_3DOT, "embU_3DOT");
        _embellishments.put(embU_4DOT, "embU_4DOT");
        _embellishments.put(embU_BAR, "embU_BAR");
        _embellishments.put(embU_TILDE, "embU_TILDE");
        _embellishments.put(embU_FROWN, "embU_FROWN");
        _embellishments.put(embU_SMILE, "embU_SMILE");
        _embellishments.put(embU_FARROW, "embU_FARROW");
        _embellishments.put(embU_LARROW, "embU_LARROW");
        _embellishments.put(embU_BARROW, "embU_BARROW");
        _embellishments.put(embU_R1ARROW, "embU_R1ARROW");
        _embellishments.put(embU_L1ARROW, "embU_L1ARROW");
        embellishments = ImmutableMap.copyOf(_embellishments);
    }

    public String getEmbell(){
        return embellishments.get(this.embell);
    }
}
