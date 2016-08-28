package nz.colin.mtef.records;

import nz.colin.mtef.RecordVisitor;

/**
 * Created by colin on 27/08/16.
 */
public class EMBELL extends Record{
    private final int opetions;
    private final Nudge nudge;
    private final int embell;

    public EMBELL(int opetions, Nudge nudge, int embell) {
        this.opetions = opetions;
        this.nudge = nudge;
        this.embell = embell;
    }

    @Override
    public void accept(RecordVisitor visitor) {
        visitor.visit(this);
    }


//    public static final int emb1DOT = 2;
//    public static final int emb2DOT = 3;
//    public static final int emb3DOT = 4;
//    public static final int emb1PRIME = 5;
//    public static final int emb2PRIME = 6;
//    public static final int embBPRIME = 7;
//    public static final int embTILDE = 8;
//    public static final int embHAT = 9;
//    public static final int embNOT = 10;
//    public static final int embRARROW = 11;
//    public static final int embLARROW = 12;
//    public static final int embBARROW = 13;
//    public static final int embR1ARROW = 14;
//    public static final int embL1ARROW = 15;
//    public static final int embMBAR = 16;
//    public static final int emb0BAR = 17;
//    public static final int emb3PRIME = 18;
//    public static final int embFROWN = 19;
//    public static final int embSMILE = 20;
//    public static final int embX_BARS = 21;
//    public static final int embUP_BAR = 22;
//    public static final int embDOWN_BAR = 23;
//    public static final int emb4DOT = 24;
//    public static final int embU_1DOT = 25;
//    public static final int embU_2DOT = 26;
//    public static final int embU_3DOT = 27;
//    public static final int embU_4DOT = 28;
//    public static final int embU_BAR = 29;
//    public static final int embU_TILDE = 30;
//    public static final int embU_FROWN = 31;
//    public static final int embU_SMILE = 32;
//    public static final int embU_FARROW = 33;
//    public static final int embU_LARROW = 34;
//    public static final int embU_BARROW = 35;
//    public static final int embU_R1ARROW = 36;
//    public static final int embU_L1ARROW = 37;


}
