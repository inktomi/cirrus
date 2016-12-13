package com.inktomi.cirrus.forecast;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Text;

import java.math.BigDecimal;
import java.util.List;

@Element
public class ValueList {

    @Text
    public List<String> value;

    @Attribute(name = "median", required = false)
    public BigDecimal median;

    @Attribute(name = "confidenceInterval50", required = false)
    public BigDecimal confidenceInterval50;

    @Attribute(name = "confidenceInterval80", required = false)
    public BigDecimal confidenceInterval80;

    @Attribute(name = "skew80", required = false)
    public BigDecimal skew80;
}
