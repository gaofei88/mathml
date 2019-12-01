package nz.colin.mathml;

import nu.xom.*;
import nz.colin.mathml.utility.CharReplacer;
import nz.colin.mathml.utility.Mover;


import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

/**
 * Created by colin on 28/08/16.
 */
public class Converter {

    private static StreamSource xsltSource;
    private static Templates cachedXSLT;

    public Converter (){
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        xsltSource = new StreamSource(Converter.class.getClassLoader().getResourceAsStream("xslt/transform.xsl"));
        transformerFactory.setURIResolver(new ClasspathResourceURIResolver());
        try {
            cachedXSLT = transformerFactory.newTemplates(xsltSource);
        } catch (TransformerConfigurationException e) {
            System.out.println("cannot load xsl files");
            e.printStackTrace();
        }
    }

    public Document doConvert(Element root){
        Mover mover = new Mover();
        mover.move(root);

        CharReplacer replacer = new CharReplacer();
        replacer.replace(root);

        try {
            Builder builder = new Builder();
            StringWriter sw = new StringWriter();
            StreamResult rst = new StreamResult(sw);
            cachedXSLT.newTransformer().transform(new StreamSource(new ByteArrayInputStream(root.toXML().getBytes())),rst);

            Document result =  builder.build(new ByteArrayInputStream(sw.toString().getBytes("UTF-8")));
            this.prettify(result.getRootElement());

            return result;

        } catch (ParsingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }   catch (TransformerException e) {
            e.printStackTrace();
        }
        return null;
    }

    static class ClasspathResourceURIResolver implements URIResolver{
        public Source resolve(String href, String base) throws TransformerException {
            return new StreamSource(Converter.class.getClassLoader().getResourceAsStream("xslt/"+href));
        }
    }

    private void prettify(Element element) {
        Elements childElements = element.getChildElements();
        if (childElements.size() == 1 && element.getLocalName().equals("mrow")) {
            Element parent = (Element) element.getParent();
            element.detach();

            for(int i = 0; i < childElements.size(); i++) {
                Element child = childElements.get(i);
                child.detach();
                parent.appendChild(child);
            }
            prettify(parent);
        } else {
            if (element.getLocalName().equals("mrow")) {
                mergeMN(element);
            }
            for(int i = 0; i < childElements.size(); i++) {
                prettify(childElements.get(i));
            }
        }
    }

    private void mergeMN(Element element) {
        int size = element.getChildCount();
        int i = 0;
        while(i < size - 1) {
           Element child = (Element) element.getChild(i);
           Element nextChild = (Element) element.getChild(i+1);
           if (child.getLocalName().equals("mn") && nextChild.getLocalName().equals("mn")) {
               String newValue = child.getValue() + nextChild.getValue();
               child.getChild(0).detach();
               child.appendChild(newValue);
               nextChild.detach();
               size--;
           } else {
               i++;
           }
        }
    }
}
