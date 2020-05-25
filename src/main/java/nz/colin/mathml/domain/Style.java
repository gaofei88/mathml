package nz.colin.mathml.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Style {
    List<FontStyle> fontStyles;

    List<String> styleNames = Arrays.asList(
            "text",
            "function",
            "variable",
            "lcGreek",
            "ucGreek",
            "symbol",
            "vectorMatrix",
            "number",
            "extraMath",
            "user1",
            "user2"
    );

    public Style() {
        fontStyles = new ArrayList<>(styleNames.size());
    }

    public void setFontStyle(int idx, int value) {
        fontStyles.add(idx, new FontStyle((value & 1) > 0, (value & 2) > 0));
    }

    public FontStyle getFontStyle(String key) {
        return fontStyles.get(styleNames.indexOf(key));
    }

    public FontStyle getFontStyle(int idx) {
        if (idx >= fontStyles.size()) {
            return null;
        }
        return fontStyles.get(idx);
    }

    public static class FontStyle {
        private boolean bold;
        private boolean italic;

        public FontStyle(boolean bold, boolean italic) {
            this.bold = bold;
            this.italic = italic;
        }

        public boolean isBold() {
            return bold;
        }

        public boolean isItalic() {
            return italic;
        }
    }
}
