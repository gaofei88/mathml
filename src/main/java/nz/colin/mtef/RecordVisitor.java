package nz.colin.mtef;

import nz.colin.mtef.records.*;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by colin on 27/08/16.
 */
public interface RecordVisitor {
    void visit(CHAR aChar);
    void visit(COLOR aColor);
    void visit(COLOR_DEF aColorDef);
    void visit(EMBELL anEmbell);
    void visit(ENCODING_DEF anEncodingDef);
    void visit(END anEnd);
    void visit(EQN_PREFS anEqnPrefs);
    void visit(FONT_DEF aFontDef);
    void visit(FONT_STYLE_DEF aFontStyleDef);
    void visit(FULL aFull);
    void visit(SLOT aSlot);
    void visit(MATRIX aMatrix);
    void visit(MTEF aMtef);
    void visit(PILE aPile);
    void visit(RULER aRuler);
    void visit(SIZE aSize);
    void visit(SUB aSub);
    void visit(SUB2 aSub2);
    void visit(SUBSYM aSubSym);
    void visit(SYM aSym);
    void visit(TMPL aTmpl);
    void visit(FUTURE aFuture);
}
