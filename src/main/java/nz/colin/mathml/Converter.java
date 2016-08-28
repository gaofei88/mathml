package nz.colin.mathml;

import nu.xom.*;
import nu.xom.xslt.XSLException;
import nu.xom.xslt.XSLTransform;
import nz.colin.mathml.utility.CharReplacer;
import nz.colin.mathml.utility.Mover;
import nz.colin.mtef.records.CHAR;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

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
            File stylesheet = new File(Converter.class.getResource("../../").getPath(), "../xslt/transform.xsl");
            Builder builder = new Builder();
            Document stylesheetDoc = builder.build(stylesheet);
            Document input = builder.build(new ByteArrayInputStream(root.toXML().getBytes()));
            XSLTransform xform = new XSLTransform(stylesheetDoc);
            Nodes result = xform.transform(input);
            Document actualResult = XSLTransform.toDocument(result);
            return actualResult;
        } catch (ParsingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XSLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
