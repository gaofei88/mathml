import com.sun.tools.doclets.internal.toolkit.util.DocFinder;
import nu.xom.Document;
import nz.colin.mathml.Converter;
import nz.colin.mtef.XMLSerialize;
import nz.colin.mtef.exceptions.ParseException;
import nz.colin.mtef.parsers.MTEFParser;
import nz.colin.mtef.records.MTEF;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.util.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

/**
 * Created by colin on 26/08/16.
 */
public class GeneralTest {

    @Test
    public void testParseQuadratic() throws IOException, ParseException {
        InputStream is = GeneralTest.class.getResourceAsStream("/ole/quadratic.bin");
        PushbackInputStream pis = new PushbackInputStream(is);

        pis.read(new byte[28]);

        MTEF mtef = new MTEFParser().parse(pis);
        XMLSerialize serializer = new XMLSerialize();

        mtef.accept(serializer);

        Converter c = new Converter();
        Document mathml = c.doConvert(serializer.getRoot());

        System.out.println(mathml.toXML().replace("&amp;", "&"));

        is.close();
    }

    @Test
    public void testParseFraction() throws IOException, ParseException {
        InputStream is = GeneralTest.class.getResourceAsStream("/ole/fraction.bin");
        PushbackInputStream pis = new PushbackInputStream(is);

        pis.read(new byte[28]);

        MTEF mtef = new MTEFParser().parse(pis);
        XMLSerialize serializer = new XMLSerialize();

        mtef.accept(serializer);

        Converter c = new Converter();
        Document mathml = c.doConvert(serializer.getRoot());

        System.out.println(mathml.toXML().replace("&amp;", "&"));

        is.close();
    }

    @Test
    public void testEquation2() throws IOException, ParseException {
        InputStream is = GeneralTest.class.getResourceAsStream("/ole/fences.bin");
        POIFSFileSystem poifs = new POIFSFileSystem(is);
        PushbackInputStream pis = new PushbackInputStream(poifs.createDocumentInputStream("Equation Native"));

        pis.read(new byte[28]);

        MTEF mtef = new MTEFParser().parse(pis);
        XMLSerialize serializer = new XMLSerialize();

        mtef.accept(serializer);

        System.out.println("MTEF is \n" + serializer.getRoot().toXML());

        Converter c = new Converter();
        Document mathml = c.doConvert(serializer.getRoot());

        System.out.println("MathML is \n" + mathml.toXML().replace("&amp;", "&"));

        is.close();
    }

}
