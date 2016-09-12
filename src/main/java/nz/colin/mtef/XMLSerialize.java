package nz.colin.mtef;

import com.google.common.collect.Maps;
import nu.xom.Element;
import nz.colin.mtef.records.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Stack;

/**
 * Created by colin on 27/08/16.
 */
public class XMLSerialize implements RecordVisitor{
    private final Element root;
    public final Stack<Element> current = new Stack<Element>();

    public XMLSerialize(){
        root = new Element("root");
        current.push(root);
    }

    public Element getRoot() {
        return root;
    }

    public void visit(CHAR aChar) {
        Element el = new Element("char");
        String[] attributes = { "options", "font_position", "typeface", "variation", "mt_code_value"};
        append(el, attributes, aChar);
        if(el.getChildElements().size() == 0){
            System.out.println(el.toXML().toString());
        }
        current.peek().appendChild(el);
    }

    public void visit(COLOR aColor) {
        Element el = new Element("color");
        String[] attributes = { "color_def_index" };
        append(el,attributes,aColor);
        current.peek().appendChild(el);
    }

    public void visit(COLOR_DEF aColorDef) {
        Element el = new Element("color_def");
        el.appendChild("have not implement yet.");
        current.peek().appendChild(el);
    }

    public void visit(EMBELL anEmbell) {
        Element el = new Element("embell");
        el.appendChild("have not implement yet.");
        current.peek().appendChild(el);
    }

    public void visit(ENCODING_DEF anEncodingDef) {
        Element el = new Element("encodingDef");
        String[] attributes = { "name" };
        append(el, attributes, anEncodingDef);
        current.peek().appendChild(el);
    }

    public void visit(END anEnd) {
        Element el = new Element("end");
        current.peek().appendChild(el);

    }

    public void visit(EQN_PREFS anEqnPrefs) {
        Element el = new Element("eqn_prefs");
        String[] attributes = { "options", "sizes_count", "spaces_count", "styles_count"};
        append(el, attributes, anEqnPrefs);

        for(EQN_PREFS.Nibbles size : anEqnPrefs.getSizes()){
            Element s = new Element("sizes");
            Element unit = new Element("unit");
            unit.appendChild("" + size.getUnit());
            s.appendChild(unit);
            for(Integer i : size.getNibble()){
                Element n = new Element("nibbles");
                n.appendChild("" + i);
                s.appendChild(n);
            }
            el.appendChild(s);
        }

        for(EQN_PREFS.Nibbles space : anEqnPrefs.getSpaces()){
            Element s = new Element("spaces");
            Element unit = new Element("unit");
            unit.appendChild("" + space.getUnit());
            s.appendChild(unit);
            for(Integer i : space.getNibble()){
                Element n = new Element("nibbles");
                n.appendChild("" + i);
                s.appendChild(n);
            }
            el.appendChild(s);
        }

        for(EQN_PREFS.Style style : anEqnPrefs.getStyles()){
            Element s = new Element("styles");

            Element fontDef = new Element("font_def");
            fontDef.appendChild("" + style.getFontDef());
            s.appendChild(fontDef);

            Element fontStyle = new Element("font_style");
            fontStyle.appendChild("" + style.getFontStyle());
            s.appendChild(fontStyle);

            el.appendChild(s);
        }

        current.peek().appendChild(el);
    }

    public void visit(FONT_DEF aFontDef) {
        Element el = new Element("font_def");
        String[] attributes = { "enc_def_index", "font_name" };
        append(el, attributes, aFontDef);
        current.peek().appendChild(el);
    }

    public void visit(FONT_STYLE_DEF aFontStyleDef) {
        Element el = new Element("font_style_def");
        el.appendChild("have not implement yet.");
        current.peek().appendChild(el);

    }

    public void visit(FULL aFull) {
        Element el = new Element("full");
        current.peek().appendChild(el);
    }

    public void visit(SLOT aSlot) {
        Element el = new Element("slot");
        Element opt = new Element("options");
        opt.appendChild("" + aSlot.getOptions());
        el.appendChild(opt);
        current.push(el);

        for(Record record : aSlot.getRecords()){
            record.accept(this);
        }

        current.pop();
        current.peek().appendChild(el);
    }

    public void visit(MATRIX aMatrix) {
        Element el = new Element("matrix");
        el.appendChild("have not implement yet.");
        current.peek().appendChild(el);
    }

    public void visit(MTEF aMtef) {
        String[] attributes = {
                "mtef_version",
                "platform",
                "product",
                "product_version",
                "product_subversion",
                "application_key",
                "equation_options"
        };
        Element mtef = new Element("mtef");
        append(mtef, attributes, aMtef);
        current.push(mtef);

        for(Record record: aMtef.getRecords()){
            record.accept(this);
        }

        current.pop();
        current.peek().appendChild(mtef);
    }



    public void visit(PILE aPile) {
        Element el = new Element("pile");
        el.appendChild("have not implemented yet");
        current.peek().appendChild(el);
    }



    public void visit(SIZE aSize) {
        Element el = new Element("size");
        String[] attributes = { "lsize", "dsize" };
        append(el, attributes, aSize);
        current.peek().appendChild(el);
    }

    public void visit(SUB aSub) {
        Element el = new Element("sub");
        current.peek().appendChild(el);
    }

    public void visit(SUB2 aSub2) {
        Element el = new Element("sub2");
        current.peek().appendChild(el);
    }

    public void visit(RULER aRuler) {
        //not required
    }

    public void visit(SUBSYM aSubSym) {
        Element el = new Element("sub_sym");
        current.peek().appendChild(el);
    }

    public void visit(SYM aSym) {
        Element el = new Element("sym");
        current.peek().appendChild(el);
    }

    public void visit(TMPL aTmpl) {
        Element el = new Element("tmpl");

        Element selector = new Element("selector");
        selector.appendChild(aTmpl.getTemplate().getTypeName());
        el.appendChild(selector);


        //System.out.println(aTmpl.getTemplate().getTypeName() + "has options " + aTmpl.getTemplate().hasOptions());
        Element variation = new Element("variation");
        String v = aTmpl.getTemplate().getVariation(aTmpl.getVariation());
        if(v != null) {
            variation.appendChild(v);
            el.appendChild(variation);
        }

        Element templateOptions = new Element("template_specific_options");
        templateOptions.appendChild("" + aTmpl.getTemplateOptions());
        el.appendChild(templateOptions);

        current.push(el);
        for(Record record: aTmpl.getRecords()){
            record.accept(this);
        }
        current.pop();
        current.peek().appendChild(el);
    }

    public void visit(FUTURE aFuture) {

    }

    public void append(Element el, String[] attributes, Record obj){
        Method[] methods = obj.getClass().getMethods();
        Map<String, Method> methodsMap = Maps.newHashMap();
        for(int i = 0; i < methods.length; i++){
            if(methods[i].getName().startsWith("get")){
                methodsMap.put(methods[i].getName(), methods[i]);
            }
        }
        try {
            for(int j = 0; j < attributes.length; j++){
                Element e = new Element(attributes[j]);
                Method method = methodsMap.get(getMethodName(attributes[j]));
                e.appendChild("" + method.invoke(obj));
                el.appendChild(e);
            }
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (InvocationTargetException e1) {
            e1.printStackTrace();
        }
    }

    public String getMethodName(String attrName){
        String[] slice = attrName.split("_");
        String name = "get";
        for(int i = 0; i < slice.length; i++){
            name += ("" + slice[i].charAt(0)).toUpperCase();
            name += slice[i].substring(1);
        }
        return name;
    }
}
