package nz.colin.mathml.utility;

import nu.xom.*;
import nz.colin.mathml.domain.Replacement;
import nz.colin.mathml.domain.TextMap;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by colin on 28/08/16.
 */
public class CharReplacer {
    private final Replacement replacement;

    public CharReplacer() {
        this.replacement = new Replacement();
    }

    public void replace(Element root){
        Nodes chars = root.query("//char");
        for(int i = 0; i < chars.size(); i++){
            String mtCode = chars.get(i).query("mt_code_value").get(0).getValue();
            TextMap map = this.replacement.testRange(mtCode);
            if(map != null){
                doReplacement(map, chars.get(i));
            }
        }
    }

    private void doReplacement(TextMap map, Node node){
        String mode = node.query("variation").get(0).getValue();
        Method[] methods = map.getClass().getMethods();
        Method methodToCall = null;
        for(int i = 0; i < methods.length; i++){
            if(methods[i].getName().toLowerCase().contains(mode)){
                methodToCall = methods[i];break;
            }
        }

        String mtCode = node.query("mt_code_value").get(0).getValue().substring(2);

        String replacedStr = null;

        try {
            replacedStr = (String) methodToCall.invoke(map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        if(replacedStr.contains("(Char)")){
            replacedStr = replacedStr.replace("(Char)", "" + (char) Integer.parseInt(mtCode,16));
        }else{
            replacedStr = replacedStr.replace("(CharHex)", "&#x" + mtCode + ";");
        }

        Element m = new Element(replacedStr.substring(1,3));
        if (mode.equals("textmode")) {
            m = new Element("mtext");
        }
        m.appendChild(replacedStr.substring(4, replacedStr.length() - 5));

        Element parent = (Element) node.getParent();
        Elements children = parent.getChildElements();
        for(int j = 0; j < children.size(); j++){
            if(children.get(j) == node){
                parent.insertChild(m, j);
                node.detach();
                break;
            }
        }
    }
}
