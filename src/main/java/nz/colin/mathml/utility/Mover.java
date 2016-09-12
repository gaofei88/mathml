package nz.colin.mathml.utility;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import nu.xom.*;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

    private static final List<String> OPEN_PAREN;
    static {
        List<String> _OPEN_PAREN = Lists.newArrayList();
        _OPEN_PAREN.add("0x0028");
        _OPEN_PAREN.add("0x005B");
        _OPEN_PAREN.add("0x007B");
        OPEN_PAREN = ImmutableList.copyOf(_OPEN_PAREN);
    }

    private static final List<String> CLOSE_PAREN;
    static {
        List<String> _CLOSE_PAREN = Lists.newArrayList();
        _CLOSE_PAREN.add("0x0029");
        _CLOSE_PAREN.add("0x005D");
        _CLOSE_PAREN.add("0x007D");
        CLOSE_PAREN = ImmutableList.copyOf(_CLOSE_PAREN);
    }

    private static final Map<String, String> OPEN_CLOSE_PAIRS;
    static{
        Map<String,String> _OPEN_CLOSE_PAIRS = Maps.newHashMap();
        _OPEN_CLOSE_PAIRS.put("0x0028","0x0029");
        _OPEN_CLOSE_PAIRS.put("0x005B","0x005D");
        _OPEN_CLOSE_PAIRS.put("0x007B","0x007D");
        OPEN_CLOSE_PAIRS = ImmutableMap.copyOf(_OPEN_CLOSE_PAIRS);
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
        Nodes els = el.query(String.format("//tmpl[(%s) and %s]", SUBSUP_SELECTOR, PRE));
       // System.out.println(els.size());
    }

    private void moveFollowingSubSup(Element el){
        Nodes els = el.query(String.format("//tmpl[(%s) and not(%s)]", SUBSUP_SELECTOR, PRE));
       // System.out.println(els.size());
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

                if(hasCloseParen){
                    //System.out.println(siblings.size());
                    for(int j = siblings.size()-1; j >= 0; j--){
                        Node next = getNext(siblings.get(j));
                        boolean hasOpenParen = false;
                        Nodes tMtCodes = next.query("//mt_code_value");
                        for(int k = 0; k < tMtCodes.size(); k++){
                            if(OPEN_PAREN.contains(tMtCodes.get(k).getValue())){
                                hasOpenParen = true;
                                break;
                            }
                        }
                        if(hasOpenParen){
                           siblings.get(j).detach();
                        }
                    }
                    moveParent(siblings, e);
                    //System.out.println(siblings.size());
                    //TO-DO: refer mover.rb 73-75
                }else{
                    e.appendChild(siblingLast.copy());
                    siblingLast.detach();
                }
            }
            Elements children = n.getChildElements();
            for(int j = 0; j < children.size(); j++){
               // System.out.println(children.get(j).getLocalName());
                if(children.get(j).getLocalName().equals("slot")){
                    n.insertChild(e, j);
                    break;
                }
            }
        }
    }

    private void moveParent(Nodes siblings, Node n){
        Iterator<String> iter = OPEN_CLOSE_PAIRS.keySet().iterator();
        while(iter.hasNext()){
            String open = iter.next();
            String close = OPEN_CLOSE_PAIRS.get(open);
            Nodes nodes = siblings.get(0).query("//mt_code_value");
            for(int i = 0; i < nodes.size(); i++){
                if(nodes.get(i).getValue().equals(open)){
                    moveUntilMtCode(siblings, close, n);
                    break;
                }
            }
        }
    }

    private void moveUntilMtCode(Nodes elements, String mtCodeValue, Node parent){
        for(int i = 0; i < elements.size(); i++){
            Nodes mtCodes = elements.get(i).query("//mt_code_value");
            boolean canBreak = false;
            for(int j = 0; j < mtCodes.size(); j++){
                if(mtCodes.get(j).getValue().equals(mtCodeValue)){
                    canBreak = true;
                    break;
                }
            }
            if(canBreak){
                break;
            }else{
                ((Element) parent).appendChild(elements.get(i));
                elements.get(i).detach();
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

    public Node getNext(Node current) {
        ParentNode parent = current.getParent();
        if (parent == null) return null;
        int index = parent.indexOf(current);
        if (index+1 == parent.getChildCount()) return getNext(parent);
        return parent.getChild(index+1);
    }
}
