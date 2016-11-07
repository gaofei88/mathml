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
    private static Converter instance;
    private static StreamSource xsltSource;
    private static Templates cachedXSLT;
    public static Converter getInstance(){
        if(null == instance){
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            instance = new Converter();
            xsltSource = new StreamSource(Converter.class.getClassLoader().getResourceAsStream("xslt/transform.xsl"));
            transformerFactory.setURIResolver(new ClasspathResourceURIResolver());
            try {
                cachedXSLT = transformerFactory.newTemplates(xsltSource);
            } catch (TransformerConfigurationException e) {
                System.out.println("cannot load xsl files");
                e.printStackTrace();
            }

        }
        return instance;
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

            return builder.build(new ByteArrayInputStream(sw.toString().getBytes("UTF-8")));

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
}
