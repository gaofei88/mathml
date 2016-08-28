package nz.colin.mtef.records;

import nz.colin.mtef.RecordVisitor;

import java.util.List;

/**
 * Created by colin on 27/08/16.
 */
public class EQN_PREFS extends Record{
    private final int options;
    private final int sizesCount;
    private final int spacesCount;
    private final int stylesCount;

    private final List<Nibbles> sizes;
    private final List<Nibbles> spaces;
    private final List<Style> styles;

    public EQN_PREFS(int options, int sizesCount, int spacesCount, int stylesCount, List<Nibbles> sizes, List<Nibbles> spaces, List<Style> styles) {
        this.options = options;
        this.sizesCount = sizesCount;
        this.spacesCount = spacesCount;
        this.stylesCount = stylesCount;
        this.sizes = sizes;
        this.spaces = spaces;
        this.styles = styles;
    }

    public static class Nibbles{
        private final int unit;
        private final List<Integer> nibble;

        public Nibbles(int unit, List<Integer> nibble) {
            this.unit = unit;
            this.nibble = nibble;
        }

        public int getUnit() {
            return unit;
        }

        public List<Integer> getNibble() {
            return nibble;
        }
    }

    public static class Style{
        private final int fontDef;
        private final int fontStyle;

        public Style(int fontDef, int fontStyle) {
            this.fontDef = fontDef;
            this.fontStyle = fontStyle;
        }

        public int getFontDef() {
            return fontDef;
        }

        public int getFontStyle() {
            return fontStyle;
        }
    }

    public int getOptions() {
        return options;
    }

    public int getSizesCount() {
        return sizesCount;
    }

    public int getSpacesCount() {
        return spacesCount;
    }

    public int getStylesCount() {
        return stylesCount;
    }

    public List<Nibbles> getSizes() {
        return sizes;
    }

    public List<Nibbles> getSpaces() {
        return spaces;
    }

    public List<Style> getStyles() {
        return styles;
    }

    @Override
    public void accept(RecordVisitor visitor) {
        visitor.visit(this);
    }
}