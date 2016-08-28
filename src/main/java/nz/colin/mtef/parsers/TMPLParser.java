package nz.colin.mtef.parsers;

import com.google.common.collect.Lists;
import nz.colin.mtef.exceptions.ParseException;
import nz.colin.mtef.records.Record;
import nz.colin.mtef.records.TMPL;

import java.io.PushbackInputStream;
import java.util.List;

/**
 * Created by colin on 27/08/16.
 */
public class TMPLParser extends Parser<TMPL>{
    protected TMPL doParse(PushbackInputStream in) throws ParseException {
        int options = readByte(in);
        Record.Nudge nudge = (options & Record.Options.NUDGE) > 0 ? readNudge(in) : new Record.Nudge(0,0);
        int templateCode = readByte(in);
        int variation = readByte(in);
        if((variation & 0x80) > 0){
            variation = (variation & 0x7F) | (readByte(in) << 8);
        }

        TMPL.Template template = TMPL.get(templateCode);
        int templateOptions = 0;
        if(template.hasOptions()){
            templateOptions = readByte(in);
        }else{
            if(readByte(in) != 0){
                throw new ParseException("Expected options byte to be 0 for a template with no options");
            }
        }
        List<Record> records = Lists.newArrayList();
        if((options & Record.Options.LINE_NULL) <= 0){
            readRecordsToEnd(in, records);
        }

        return new TMPL(options,nudge,template, variation, templateOptions, records);
    }

    protected int getInitalByte() {
        return ParserRegistry.TMPL;
    }
}
