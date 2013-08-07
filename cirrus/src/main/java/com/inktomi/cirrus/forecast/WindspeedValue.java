package com.inktomi.cirrus.forecast;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Text;

import java.math.BigInteger;

@Element
public class WindspeedValue {

    @Text(required = false)
    public BigInteger value;

    @Attribute(name = "nil", required = false, empty = "false")
    public String nil;

    @Attribute(name = "upper-range", required = false)
    public BigInteger upperRange;

    @Attribute(name = "lower-range", required = false)
    public BigInteger lowerRange;

    @Attribute(name = "type", required = false)
    public DataSource type;
}
