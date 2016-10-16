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
    public Document doConvert(Element root){
        Mover mover = new Mover();
        mover.move(root);

        CharReplacer replacer = new CharReplacer();
        replacer.replace(root);

        try {
            Builder builder = new Builder();

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            StreamSource xsltSource = new StreamSource(this.getClass().getClassLoader().getResourceAsStream("xslt/transform.xsl"));

            transformerFactory.setURIResolver(new ClasspathResourceURIResolver());

            Templates cachedXSLT = transformerFactory.newTemplates(xsltSource);
            Transformer transformer = cachedXSLT.newTransformer();

            StringWriter sw = new StringWriter();
            StreamResult rst = new StreamResult(sw);
            transformer.transform(new StreamSource(new ByteArrayInputStream(root.toXML().getBytes())),rst);

            return builder.build(new ByteArrayInputStream(sw.toString().getBytes()));

        } catch (ParsingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return null;
    }

    class ClasspathResourceURIResolver implements URIResolver{
        public Source resolve(String href, String base) throws TransformerException {
            return new StreamSource(Converter.class.getClassLoader().getResourceAsStream("xslt/"+href));
        }
    }
}
