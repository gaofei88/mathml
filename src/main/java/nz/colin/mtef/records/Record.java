package nz.colin.mtef.records;

import nz.colin.mtef.RecordVisitor;

/**
 * Created by colin on 26/08/16.
 */
public abstract class Record {
    public abstract void accept(RecordVisitor visitor);

    public static class Options {
        public final static int NUDGE = 0x08;
        public final static int CHAR_EMBELL = 0x01;
        public final static int CHAR_FUNC_START = 0x02;
        public final static int CHAR_ENC_CHAR_8 = 0x04;
        public final static int CHAR_ENC_CHAR_16 = 0x10;
        public final static int CHAR_ENC_NO_MTCODE = 0x20;

        public final static int LINE_NULL = 0x01;
        public final static int LINE_LSPACE = 0x04;
        public final static int LP_RULER = 0x02;

        public final static int COLOR_CMYK = 0x01;
        public final static int COLOR_SPOT = 0x02;
        public final static int COLOR_NAME = 0x04;


    }

    public static class Nudge {
        private final int dx;
        private final int dy;

        public Nudge(int dy, int dx) {
            this.dy = dy;
            this.dx = dx;
        }

        public int getDx() {
            return dx;
        }

        public int getDy() {
            return dy;
        }
    }
}
