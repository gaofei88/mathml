package nz.colin.mtef.records;

import nz.colin.mtef.RecordVisitor;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by colin on 26/08/16.
 */
public class MTEF extends Record{
    private final int mtefVersion;
    private final int platform;
    private final int product;
    private final int productVersion;
    private final int productSubversion;
    private final String applicationKey;
    private final int equationOptions;
    private final List<Record> records;


    public MTEF(int mtefVersion, int platform, int product, int productVersion, int productSubversion, String applicationKey, int equationOptions, List<Record> records) {
        this.mtefVersion = mtefVersion;
        this.platform = platform;
        this.product = product;
        this.productVersion = productVersion;
        this.productSubversion = productSubversion;
        this.applicationKey = applicationKey;
        this.equationOptions = equationOptions;
        this.records = records;
    }

    public int getMtefVersion() {
        return mtefVersion;
    }

    public int getPlatform() {
        return platform;
    }

    public int getProduct() {
        return product;
    }

    public int getProductVersion() {
        return productVersion;
    }

    public int getProductSubversion() {
        return productSubversion;
    }

    public String getApplicationKey() {
        return applicationKey;
    }

    public String getEquationOptions() {
        return equationOptions == 1 ? "inline" : "block";
    }

    public List<Record> getRecords() {
        return records;
    }

    @Override
    public void accept(RecordVisitor visitor) {
        visitor.visit(this);
    }
}
