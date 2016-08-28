package nz.colin.mtef.records;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import nz.colin.mtef.RecordVisitor;

import java.util.List;
import java.util.Map;

/**
 * Created by colin on 27/08/16.
 */
public class MATRIX extends Record{
    private final int options;
    private final Nudge nudge;
    private final String vAlign;
    private final String hJust;
    private final String vJust;
    private final int rows;
    private final int cols;
    private final List<Integer> rowParts;
    private final List<Integer> colParts;
    private final List<List<Record>> objectList;


    public static final Map<Integer,String> vAlignMap;
    public static final Map<Integer,String> hAlignMap;


    static {
        Map<Integer,String> _vAlignMap = Maps.newHashMap();
        _vAlignMap.put(0, "top_baseline");
        _vAlignMap.put(1, "center_baseline");
        _vAlignMap.put(2, "bottom_baseline");
        _vAlignMap.put(3, "center");
        _vAlignMap.put(4, "axis");
        vAlignMap = ImmutableMap.copyOf(_vAlignMap);

        Map<Integer, String> _hAlignMap = Maps.newHashMap();
        _hAlignMap.put(1, "left");
        _hAlignMap.put(2, "center");
        _hAlignMap.put(3, "right");
        _hAlignMap.put(4, "al");
        _hAlignMap.put(5, "dec");
        hAlignMap = ImmutableMap.copyOf(_hAlignMap);
    }

    public MATRIX(int options, Nudge nudge, String vAlign, String hJust, String vJust, int rows, int cols, List<Integer> rowParts, List<Integer> colParts, List<List<Record>> objectList) {
        this.options = options;
        this.nudge = nudge;
        this.vAlign = vAlign;
        this.hJust = hJust;
        this.vJust = vJust;
        this.rows = rows;
        this.cols = cols;
        this.rowParts = rowParts;
        this.colParts = colParts;
        this.objectList = objectList;
    }

    @Override
    public void accept(RecordVisitor visitor) {
        visitor.visit(this);
    }
}
