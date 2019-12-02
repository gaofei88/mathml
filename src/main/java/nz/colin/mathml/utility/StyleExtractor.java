package nz.colin.mathml.utility;

import nu.xom.Element;
import nu.xom.Nodes;
import nz.colin.mathml.domain.Style;

public class StyleExtractor {

    public Style extract(Element root) {
        Style style = new Style();
        Nodes styles = root.query("//eqn_prefs/styles");

        for(int i = 0; i < styles.size(); i++) {
            int styleValue = Integer.parseInt(styles.get(i).query("font_style").get(0).getValue());
            style.setFontStyle(i, styleValue);
        }
        return style;
    }
}
