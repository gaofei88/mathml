package nz.colin.mathml.utility;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import nu.xom.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by colin on 28/08/16.
 */
public class Mover {
    private static final String SUBSUP_SELECTOR = "selector='tmSUP' or " +
            "selector='tmSUB' or " +
            "selector='tmSUBSUP'";

    private static final String  PARENS_SELECTOR = "selector='tmPARENS' or "+
            "selector='tmBRACK' or " +
            "selector='tmBRACE' or " +
            "selector='tmOBRACK' or " +
            "selector='tmOBRACE' or " +
            "selector='tmHBRACK' or " +
            "selector='tmHBRACE'";

    private static final String PRE = "variation='tvSU_PRECEDES'";

    private static final String OPEN_PAREN = "mt_code_value = '0x0028' or " +
            "mt_code_value = '0x005B' or " +
            "mt_code_value = '0x007B'";

    private static final List<String> CLOSE_PAREN;
    static {
        List<String> _CLOSE_PAREN = Lists.newArrayList();
        _CLOSE_PAREN.add("0x0029");
        _CLOSE_PAREN.add("0x005D");
        _CLOSE_PAREN.add("0x007D");
        CLOSE_PAREN = ImmutableList.copyOf(_CLOSE_PAREN);
    }

    private Nodes last_preceding_siblings = null;

    public void move(Element root) {
        moveFollowingSubSup(root);
        movePrecedingSubSup(root);
        invertCharEmbell(root);
    }

    private void invertCharEmbell(Element el) {
        Nodes embells = el.query("//char[embell]");
        for(int i = 0; i < embells.size(); i++){
            Element embell = (Element) embells.get(i).query("embell").get(0);
            embell.detach();
            Node n = embells.get(i).copy();
            embell.appendChild(n);
            Element parentNode = (Element) embells.get(i).getParent();
            Elements children = parentNode.getChildElements();
            for(int j = 0; j < children.size(); j++){
                if(children.get(j) == embells.get(i)){
                    parentNode.insertChild(embell,j);
                    embells.get(i).detach();
                    break;
                }
            }
        }
    }

    private void movePrecedingSubSup(Element el) {
    }

    private void moveFollowingSubSup(Element el){
        Nodes els = el.query(String.format("//tmpl[(%s) and not(%s)]", SUBSUP_SELECTOR, PRE));
        for(int i = 0; i < els.size(); i++){
            Element n = (Element) els.get(i);
            Nodes siblings = newPrecedingSiblings(n);
            Element e = new Element("slot");

            if(siblings.size() > 0){
                Node siblingLast = siblings.get(siblings.size() - 1);

                boolean hasCloseParen = false;
                Nodes mtCodes = siblingLast.query("//mt_code_value");
                for(int j = 0; j < mtCodes.size(); j++){
                    if(CLOSE_PAREN.contains(mtCodes.get(j).getValue())){
                        hasCloseParen = true;
                        break;
                    }
                }

                Nodes parens = siblingLast.query(String.format("self::tmpl[%s]", PARENS_SELECTOR));
                boolean hasDefinedParen = parens.size() > 0 ? true : false;

                if(hasCloseParen){
                    //TO-DO: refer mover.rb 73-75
                }else{
                    e.appendChild(siblingLast.copy());
                    siblingLast.detach();
                }
            }
            Elements children = n.getChildElements();
            for(int j = 0; j < children.size(); j++){
                System.out.println(children.get(j).getLocalName());
                if(children.get(j).getLocalName().equals("slot")){
                    n.insertChild(e, j);
                    break;
                }
            }
        }
    }

    private Nodes newPrecedingSiblings(Element el){
        Nodes allSiblings = el.query("preceding-sibling::tmpl | preceding-sibling::char");
        Nodes siblings = new Nodes();
        for(int i = 0; i < allSiblings.size(); i++){
            boolean flag = true;
            for(int j = 0; last_preceding_siblings != null && j < last_preceding_siblings.size(); j++) {
                if (allSiblings.get(i).equals(last_preceding_siblings)) {
                    flag = false;
                    break;
                }
            }
            if(flag) siblings.append(allSiblings.get(i));
        }
        last_preceding_siblings = allSiblings;
        return siblings;
    }
}
