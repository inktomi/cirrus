package com.inktomi.cirrus.forecast;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Text;

import java.math.BigInteger;

@Element
public class TemperatureValue {

    @Text
    public BigInteger value;

    @Attribute(name = "upper-range", required = false)
    public BigInteger upperRange;

    @Attribute(name = "lower-range", required = false)
    public BigInteger lowerRange;

    @Attribute(name = "type", required = false)
    public DataSource type;
}
