package com.inktomi.cirrus.forecast;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Text;

import java.math.BigInteger;
import java.util.List;

@Element
public class Anomaly {

    @Element
    public String name;

    @ElementList(entry = "value", inline = true)
    public List<Value> value;

    @Attribute(name = "type", required = true)
    public String type;

    @Attribute(name = "units")
    public String units;

    @Attribute(name = "time-layout", required = true)
    public String timeLayout;

    @Attribute(name = "categorical-table", required = false)
    public String categoricalTable;

    @Attribute(name = "conversion-table", required = false)
    public String conversionTable;

    @Element
    public static class Value {

        @Text
        public BigInteger value;

        @Attribute(name = "upper-range", required = false)
        public Integer upperRange;

        @Attribute(name = "lower-range", required = false)
        public Integer lowerRange;
    }

}
