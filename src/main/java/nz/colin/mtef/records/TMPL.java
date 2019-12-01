package nz.colin.mtef.records;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import nz.colin.mtef.RecordVisitor;
import nz.colin.mtef.exceptions.ParseException;

import java.util.List;
import java.util.Map;

/**
 * Created by colin on 27/08/16.
 */
public class TMPL extends Record{
    private final int options;
    private final Nudge nudge;
    private final Template template;
    private final int variation;
    private final int templateOptions;
    private final List<Record> records;

    private static final Map<Integer, Template> templates;

    public static final int ANGLE = 0;
    public static final int PAREN = 1;
    public static final int BRACE = 2;
    public static final int BRACK = 3;
    public static final int BAR = 4;
    public static final int DBAR = 5;
    public static final int FLOOR = 6;
    public static final int CEILING = 7;
    public static final int OBRACK = 8;
    public static final int INTERVAL = 9;
    public static final int ROOT = 10;
    public static final int FRACT = 11;
    public static final int UBAR = 12;
    public static final int OBAR = 13;
    public static final int ARROW = 14;
    public static final int INTEG = 15;
    public static final int SUM = 16;
    public static final int PROD = 17;
    public static final int COPROD = 18;
    public static final int UNION = 19;
    public static final int INTER = 20;
    public static final int INTOP = 21;
    public static final int SUMOP = 22;
    public static final int LIM = 23;
    public static final int HBRACE = 24;
    public static final int HBRACK = 25;
    public static final int LDIV = 26;
    public static final int SUB = 27;
    public static final int SUP = 28;
    public static final int SUBSUP = 29;
    public static final int DIRAC = 30;
    public static final int VEC = 31;
    public static final int TILDE = 32;
    public static final int HAT = 33;
    public static final int ARC = 34;
    public static final int JSTATUS = 35;
    public static final int STRIKE = 36;
    public static final int BOX = 37;

    static {
        Map<Integer, Template> _templates = Maps.newHashMap();
        _templates.put(ANGLE, new Fence(ANGLE));
        _templates.put(PAREN, new Fence(PAREN));
        _templates.put(BRACE, new Fence(BRACE));
        _templates.put(BRACK, new Fence(BRACK));
        _templates.put(BAR, new Fence(BAR));
        _templates.put(DBAR, new Fence(DBAR));
        _templates.put(FLOOR, new Fence(FLOOR));
        _templates.put(CEILING, new Fence(CEILING));
        _templates.put(OBRACK, new Fence(OBRACK));
        _templates.put(INTERVAL, new Interval(INTERVAL));
        _templates.put(ROOT, new Radical(ROOT));
        _templates.put(FRACT, new Fraction(FRACT));
        _templates.put(UBAR, new OverAndUnderBar(UBAR));
        _templates.put(OBAR, new OverAndUnderBar(OBAR));
        _templates.put(ARROW, new Arrow(ARROW));
        _templates.put(INTEG, new Integral(INTEG));
        _templates.put(SUM, new SumProductUnionIntersection(SUM));
        _templates.put(PROD, new SumProductUnionIntersection(PROD));
        _templates.put(COPROD, new SumProductUnionIntersection(COPROD));
        _templates.put(UNION, new SumProductUnionIntersection(UNION));
        _templates.put(INTER, new SumProductUnionIntersection(INTER));
        _templates.put(INTOP, new SumProductUnionIntersection(INTOP));
        _templates.put(SUMOP, new SumProductUnionIntersection(SUMOP));
        _templates.put(LIM, new Limit(LIM));
        _templates.put(HBRACE, new HorizontalBraceBracket(HBRACE));
        _templates.put(HBRACK, new HorizontalBraceBracket(HBRACK));
        _templates.put(LDIV, new LongDivision(LDIV));
        _templates.put(SUB, new SubscriptSuperscript(SUB));
        _templates.put(SUP, new SubscriptSuperscript(SUP));
        _templates.put(SUBSUP, new SubscriptSuperscript(SUBSUP));
        _templates.put(DIRAC, new DiracBraket(DIRAC));
        _templates.put(VEC, new Vector(VEC));
        _templates.put(TILDE, new HatArcTildeJoint(TILDE));
        _templates.put(HAT, new HatArcTildeJoint(HAT));
        _templates.put(ARC, new HatArcTildeJoint(ARC));
        _templates.put(JSTATUS, new HatArcTildeJoint(JSTATUS));
        _templates.put(STRIKE, new Overstrike(STRIKE));
        _templates.put(BOX, new Box(BOX));
        templates = ImmutableMap.copyOf(_templates);
    }

    public TMPL(int options, Nudge nudge, Template template, int variation, int templateOptions, List<Record> records) {
        this.options = options;
        this.nudge = nudge;
        this.template = template;
        this.variation = variation;
        this.templateOptions = templateOptions;
        this.records = records;
    }

    public int getOptions() {
        return options;
    }

    public Nudge getNudge() {
        return nudge;
    }

    public Template getTemplate() {
        return template;
    }

    public int getVariation() {
        return variation;
    }

    public int getTemplateOptions() {
        return templateOptions;
    }

    public List<Record> getRecords() {
        return records;
    }

    public static Map<Integer, Template> getTemplates() {
        return templates;
    }

    public static Template get(int i) throws ParseException {
        if (templates.containsKey(i)) {
            return templates.get(i);
        }
        throw new ParseException("No template for type " + i);
    }

    public enum TemplateClass {
        ParBox,
        RootBox,
        FracBox,
        BarBox,
        ArroBox,
        BigOpBox,
        LimBox,
        HFenceBox,
        LDivBox,
        ScrBox,
        DiracBox,
        HatBox,
        StrikeBox,
        TBoxBox
    }

    public abstract static class Template {
        public abstract TemplateClass getTemplateClass();

        private static final Map<Integer, String> templateTypeNames;

        static{
            Map<Integer, String> _templateTypeNames = Maps.newHashMap();
            _templateTypeNames.put(ANGLE, "tmANGLE");
            _templateTypeNames.put(PAREN, "tmPAREN");
            _templateTypeNames.put(BRACE, "tmBRACE");
            _templateTypeNames.put(BRACK, "tmBRACK");
            _templateTypeNames.put(BAR, "tmBAR");
            _templateTypeNames.put(DBAR, "tmDBAR");
            _templateTypeNames.put(FLOOR, "tmFLOOR");
            _templateTypeNames.put(CEILING, "tmCEILING");
            _templateTypeNames.put(OBRACK, "tmOBRACK");
            _templateTypeNames.put(INTERVAL, "tmINTERVAL");
            _templateTypeNames.put(ROOT, "tmROOT");
            _templateTypeNames.put(FRACT, "tmFRACT");
            _templateTypeNames.put(UBAR, "tmUBAR");
            _templateTypeNames.put(OBAR, "tmOBAR");
            _templateTypeNames.put(ARROW, "tmARROW");
            _templateTypeNames.put(INTEG, "tmINTEG");
            _templateTypeNames.put(SUM, "tmSUM");
            _templateTypeNames.put(PROD, "tmPROD");
            _templateTypeNames.put(COPROD, "tmCOPROD");
            _templateTypeNames.put(UNION, "tmUNION");
            _templateTypeNames.put(INTER, "tmINTER");
            _templateTypeNames.put(INTOP, "tmINTOP");
            _templateTypeNames.put(SUMOP, "tmSUMOP");
            _templateTypeNames.put(LIM, "tmLIM");
            _templateTypeNames.put(HBRACE, "tmHBRACE");
            _templateTypeNames.put(HBRACK, "tmHBRACK");
            _templateTypeNames.put(LDIV, "tmLDIV");
            _templateTypeNames.put(SUB, "tmSUB");
            _templateTypeNames.put(SUP, "tmSUP");
            _templateTypeNames.put(SUBSUP, "tmSUBSUP");
            _templateTypeNames.put(DIRAC, "tmDIRAC");
            _templateTypeNames.put(VEC, "tmVEC");
            _templateTypeNames.put(TILDE, "tmTILDE");
            _templateTypeNames.put(HAT, "tmHAT");
            _templateTypeNames.put(ARC, "tmARC");
            _templateTypeNames.put(JSTATUS, "tmJSTATUS");
            _templateTypeNames.put(STRIKE, "tmSTRIKE");
            _templateTypeNames.put(BOX, "tmBOX");
            templateTypeNames = ImmutableMap.copyOf(_templateTypeNames);
        }

        private final int type;

        protected Template(int type) {
            this.type = type;
        }

        public String getTypeName(){
            return templateTypeNames.get(type);
        }
        public int getType() {
            return type;
        }

        public boolean hasOptions() {
            return false;
        }

        public abstract String getVariation(int variation);
    }

    public static class Fence extends Template {
        private static final Map<Integer, String> variationMaps;

        static{
            Map<Integer, String> _variationMaps = Maps.newHashMap();
            _variationMaps.put(0x01, "tvFENCE_L");
            _variationMaps.put(0x02, "tvFENCE_R");
            variationMaps = ImmutableMap.copyOf(_variationMaps);
        }

        protected Fence(int type) {
            super(type);
        }

        public TemplateClass getTemplateClass() {
            return TemplateClass.ParBox;
        }

        @Override
        public boolean hasOptions() {
            return true;
        }

        @Override
        public String getVariation(int variation) {
            return variationMaps.get(variation);
        }
    }

    public static class Interval extends Template {
        public static final int INTV_LEFT_LP = 0x00;
        public static final int INTV_LEFT_RP = 0x01;
        public static final int INTV_LEFT_LB = 0x02;
        public static final int INTV_LEFT_RB = 0x03;
        public static final int INTV_RIGHT_LP = 0x00;
        public static final int INTV_RIGHT_RP = 0x10;
        public static final int INTV_RIGHT_LB = 0x20;
        public static final int INTV_RIGHT_RB = 0x30;

        private static final Map<Integer, String> variationMaps;

        static{
            Map<Integer, String> _variationMaps = Maps.newHashMap();
            _variationMaps.put(INTV_LEFT_LP + INTV_RIGHT_LP, "tvINTV_LPLP");
            _variationMaps.put(INTV_LEFT_LP + INTV_RIGHT_RP, "tvINTV_LPRP");
            _variationMaps.put(INTV_LEFT_LP + INTV_RIGHT_LB, "tvINTV_LPLB");
            _variationMaps.put(INTV_LEFT_LP + INTV_RIGHT_RB, "tvINTV_LPRB");
            _variationMaps.put(INTV_LEFT_RP + INTV_RIGHT_LP, "tvINTV_RPLP");
            _variationMaps.put(INTV_LEFT_RP + INTV_RIGHT_RP, "tvINTV_RPRP");
            _variationMaps.put(INTV_LEFT_RP + INTV_RIGHT_LB, "tvINTV_RPLB");
            _variationMaps.put(INTV_LEFT_RP + INTV_RIGHT_RB, "tvINTV_RPRB");
            _variationMaps.put(INTV_LEFT_LB + INTV_RIGHT_LP, "tvINTV_LBLP");
            _variationMaps.put(INTV_LEFT_LB + INTV_RIGHT_RP, "tvINTV_LBRP");
            _variationMaps.put(INTV_LEFT_LB + INTV_RIGHT_LB, "tvINTV_LBLB");
            _variationMaps.put(INTV_LEFT_LB + INTV_RIGHT_RB, "tvINTV_LBRB");
            _variationMaps.put(INTV_LEFT_RB + INTV_RIGHT_LP, "tvINTV_RBLP");
            _variationMaps.put(INTV_LEFT_RB + INTV_RIGHT_RP, "tvINTV_RBRP");
            _variationMaps.put(INTV_LEFT_RB + INTV_RIGHT_LB, "tvINTV_RBLB");
            _variationMaps.put(INTV_LEFT_RB + INTV_RIGHT_RB, "tvINTV_RBRB");
            variationMaps = _variationMaps;
        }

        public Interval(int type) {
            super(type);
        }

        @Override
        public TemplateClass getTemplateClass() {
            return TemplateClass.ParBox;
        }

        @Override
        public String getVariation(int variation) {
            //TO-DO: need to further study
            String variat =  variationMaps.get(variation);
            if( variat == null ){
                System.out.println("cannot parse intervals, sorry " + variation);
            }
            return variat;
            //return variationMaps.get(variation);
        }
    }

    public static class Radical extends Template {
        private static final Map<Integer, String> variationMaps;

        static{
            Map<Integer, String> _variationMaps = Maps.newHashMap();
            _variationMaps.put(0, "tvROOT_SQ");
            _variationMaps.put(1, "tvROOT_NTH");
            variationMaps = ImmutableMap.copyOf(_variationMaps);
        }

        public Radical(int type) {
            super(type);
        }

        @Override
        public TemplateClass getTemplateClass() {
            return TemplateClass.RootBox;
        }

        @Override
        public String getVariation(int variation) {
            return variationMaps.get(variation);
        }
    }

    public static class Fraction extends Template {
        public Fraction(int type) {
            super(type);
        }

        private static final Map<Integer, String> variationMaps;

        static{
            Map<Integer, String> _variationMaps = Maps.newHashMap();
            _variationMaps.put(0x01, "tvFR_SMALL");
            _variationMaps.put(0x02, "tvFR_SLASH");
            _variationMaps.put(0x04, "tvFR_BASE");
            variationMaps = ImmutableMap.copyOf(_variationMaps);
        }

        public String getVariation(int variation){
            return variationMaps.get(variation);
        }

        @Override
        public TemplateClass getTemplateClass() {
            return TemplateClass.FracBox;
        }
    }

    public static class OverAndUnderBar extends Template {
        public static final int BAR_DOUBLE = 0x01;

        public OverAndUnderBar(int type) {
            super(type);
        }
        @Override
        public String getVariation(int variation) {
            if(variation != BAR_DOUBLE){
                return null;
            }else{
                return "tvBAR_DOUBLE";
            }
        }
        @Override
        public TemplateClass getTemplateClass() {
            return TemplateClass.BarBox;
        }
    }

    public static class Arrow extends Template {

        public Arrow(int type) {
            super(type);
        }

        @Override
        public String getVariation(int variation) {
            String vName = null;
            if(variation == 0x00){
                vName = "tvAR_SINGLE";
            }
            if(variation == 0x01){
                vName = "tvAR_DOUBLE";
            }
            if((variation & 0x10) > 0){
                if((variation & 0x01) > 0 || (variation & 0x02) > 0){
                    vName = "tvAR_LOS";
                }else{
                    vName = "tvAR_LEFT";
                }
            }
            if((variation & 0x20) > 0){
                if((variation & 0x01) > 0 || (variation & 0x02) > 0){
                    vName = "tvAR_SOL";
                }else{
                    vName = "tvAR_RIGHT";
                }
            }
            if(variation == 0x02){
                vName = "tvAR_HARPOON";
            }
            if((variation & 0x04) > 0){
                if((variation & 0x08) > 0){
                    vName = "tvAR_TOPBOTTOM";
                }else if(variation == 0x04){
                    vName = "tvAR_TOP";
                }
            }
            if((variation & 0x08) > 0){
                if((variation & 0x04) > 0){
                    vName = "tvAR_TOPBOTTOM";
                }else if(variation == 0x08){
                    vName = "tvAR_BOTTOM";
                }
            }

            return vName;

        }

        @Override
        public TemplateClass getTemplateClass() {
            return TemplateClass.ArroBox;
        }
    }

    public static class Integral extends Template {
        public static final int INT_1 = 0x01;
        public static final int INT_2 = 0x02;
        public static final int INT_3 = 0x03;
        public static final int INT_LOOP = 0x04;
        public static final int INT_CW_LOOP = 0x08;
        public static final int INT_CCW_LOOP = 0x0C;
        public static final int INT_EXPAND = 0x0100;

        public Integral(int type) {
            super(type);
        }

        @Override
        public TemplateClass getTemplateClass() {
            return TemplateClass.BigOpBox;
        }

        @Override
        public boolean hasOptions() {
            return true;
        }

        @Override
        public String getVariation(int variation) {
            return null;
            //return variationMaps.get(variation);
        }
    }

    public static class SumProductUnionIntersection extends Template {
        public SumProductUnionIntersection(int type) {
            super(type);
        }

        @Override
        public TemplateClass getTemplateClass() {
            return TemplateClass.BigOpBox;
        }
        @Override
        public String getVariation(int variation) {
            return null;
            //return variationMaps.get(variation);
        }
    }

    public static class Limit extends Template {
        public static final int SUBAR = 0;
        public static final int DUBAR = 1;

        public Limit(int type) {
            super(type);
        }

        @Override
        public TemplateClass getTemplateClass() {
            return TemplateClass.LimBox;
        }
        @Override
        public String getVariation(int variation) {
            return null;
            //return variationMaps.get(variation);
        }
    }

    public static class HorizontalBraceBracket extends Template {
        public static final int HB_TOP = 0x01;

        public HorizontalBraceBracket(int type) {
            super(type);
        }

        @Override
        public TemplateClass getTemplateClass() {
            return TemplateClass.HFenceBox;
        }
        @Override
        public String getVariation(int variation) {
            return null;
            //return variationMaps.get(variation);
        }
    }

    public static class LongDivision extends Template {
        public static final int LD_UPPER = 0x01;

        public LongDivision(int type) {
            super(type);
        }

        @Override
        public TemplateClass getTemplateClass() {
            return TemplateClass.LDivBox;
        }
        @Override
        public String getVariation(int variation) {
            return null;
            //return variationMaps.get(variation);
        }
    }

    public static class SubscriptSuperscript extends Template {
        public static final int SU_PRECEDES = 0x01;

        public SubscriptSuperscript(int type) {
            super(type);
        }

        @Override
        public TemplateClass getTemplateClass() {
            return TemplateClass.ScrBox;
        }
        @Override
        public String getVariation(int variation) {
            if(SU_PRECEDES == variation){
                return "tvSU_PRECEDES";
            }
            return null;
        }
    }

    public static class DiracBraket extends Template {
        public static final int DI_LEFT = 0x01;
        public static final int DI_RIGHT = 0x02;

        public DiracBraket(int type) {
            super(type);
        }

        @Override
        public TemplateClass getTemplateClass() {
            return TemplateClass.DiracBox;
        }
        @Override
        public String getVariation(int variation) {
            return null;
            //return variationMaps.get(variation);
        }
    }

    public static class Vector extends Template {
        public static final int VE_LEFT = 0x01;
        public static final int VE_RIGHT = 0x02;
        public static final int VE_UNDER = 0x04;
        public static final int VE_HARPOON = 0x08;

        public Vector(int type) {
            super(type);
        }

        @Override
        public TemplateClass getTemplateClass() {
            return TemplateClass.HatBox;
        }
        @Override
        public String getVariation(int variation) {
            return null;
            //return variationMaps.get(variation);
        }
    }

    public static class HatArcTildeJoint extends Template {

        public HatArcTildeJoint(int type) {
            super(type);
        }

        @Override
        public TemplateClass getTemplateClass() {
            return TemplateClass.HatBox;
        }
        @Override
        public String getVariation(int variation) {
            return null;
            //return variationMaps.get(variation);
        }
    }

    public static class Overstrike extends Template {

        public static final int ST_HORIZ = 0x01;
        public static final int ST_UP = 0x02;
        public static final int ST_DOWN = 0x04;

        public Overstrike(int type) {
            super(type);
        }

        @Override
        public TemplateClass getTemplateClass() {
            return TemplateClass.StrikeBox;
        }
        @Override
        public String getVariation(int variation) {
            return null;
            //return variationMaps.get(variation);
        }
    }

    public static class Box extends Template {
        public static final int BX_ROUND = 0x01;
        public static final int BX_LEFT = 0x02;
        public static final int BX_RIGHT = 0x04;
        public static final int BX_TOP = 0x08;
        public static final int BX_BOTTOM = 0x10;

        public Box(int type) {
            super(type);
        }

        @Override
        public TemplateClass getTemplateClass() {
            return TemplateClass.TBoxBox;
        }
        @Override
        public String getVariation(int variation) {
            return null;
            //return variationMaps.get(variation);
        }
    }

    @Override
    public void accept(RecordVisitor visitor) {
        visitor.visit(this);
    }
}
