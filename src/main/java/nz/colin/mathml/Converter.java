package nz.colin.mathml;

import nu.xom.*;
import nz.colin.mathml.domain.Style;
import nz.colin.mathml.utility.CharReplacer;
import nz.colin.mathml.utility.Mover;
import nz.colin.mathml.utility.StyleExtractor;


import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.logging.Logger;

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

        StyleExtractor extractor = new StyleExtractor();
        Style style = extractor.extract(root);

        CharReplacer replacer = new CharReplacer(style);
        replacer.replace(root);

        try (InputStream input = new ByteArrayInputStream(root.toXML().getBytes())){
            Builder builder = new Builder();
            StringWriter sw = new StringWriter();
            StreamResult rst = new StreamResult(sw);
            cachedXSLT.newTransformer().transform(new StreamSource(input),rst);

            ByteArrayInputStream inputStream = new ByteArrayInputStream(sw.toString().getBytes("UTF-8"));
            Document result = builder.build(inputStream);
            inputStream.close();

            this.prettify(result.getRootElement());

            return result;

        } catch (ParsingException | IOException | TransformerException e) {
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
            int index = parent.indexOf(element);
            element.detach();

            Element child = childElements.get(0);
            child.detach();
            parent.insertChild(child, index);
            
            prettify(parent);
        } else {
            if (element.getLocalName().equals("mrow")) {
                merge(element, "mn");
                merge(element, "mtext");
            }
            for(int i = 0; i < childElements.size(); i++) {
                prettify(childElements.get(i));
            }
        }
    }

    private void merge(Element element, String node) {
        int size = element.getChildCount();
        int i = 0;
        while(i < size - 1) {
           Node n = element.getChild(i);
           if (n instanceof Text) {
               i++;
           } else {
               Element child = (Element) n;
               Element nextChild = (Element) element.getChild(i + 1);
               if (child.getLocalName().equals(node) && nextChild.getLocalName().equals(node)) {
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
}
