package nz.colin.mathml.domain;

/**
 * Created by colin on 28/08/16.
 */
public class Range {
    private int start;
    private int end;

    public Range(int start) {
        this.start = start;
        this.end = start;
    }

    public Range(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public boolean withRange(int i){
        return i >= start && i <= end;
    }
}
