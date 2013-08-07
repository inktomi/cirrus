package com.inktomi.cirrus.nws.forecast;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Text;

import java.math.BigDecimal;

@Element
public class DecimalVal {

    @Text(required = false)
    public BigDecimal value;

    @Attribute(name = "nil", required = false, empty = "false")
    public String nil;

    @Attribute(name = "upper-range", required = false)
    public Integer upperRange;

    @Attribute(name = "lower-range", required = false)
    public Integer lowerRange;

    @Attribute(name = "type", required = false)
    public DataSource type;
}
