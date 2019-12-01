package nz.colin.mathml.domain;

/**
 * Created by colin on 28/08/16.
 */
public class TextMap {
    private static final String DEFAULT_TEXTMODE = "<mi>(Char)</mi>";

    private String mathmode;
    private String textmode;
    private String number;

    public TextMap(String mathmode) {
        this.mathmode = mathmode;
        this.textmode = DEFAULT_TEXTMODE;
    }

    public TextMap(String mathmode, String textmode) {
        this.mathmode = mathmode;
        this.textmode = textmode;
    }

    public TextMap(String mathmode, String textmode, String number) {
        this.mathmode = mathmode;
        this.textmode = textmode;
        this.number = number;
    }

    public String getMathmode() {
        return mathmode;
    }

    public String getTextmode() {
        return textmode;
    }

    public String getNumber() {
        return number;
    }
}
