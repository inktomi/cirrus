package com.inktomi.cirrus.nws.forecast;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Text;

import java.math.BigDecimal;

@Element
public class Uncertainty {

    @Text
    public Uncertainty.Error error;

    @Attribute(name = "type", required = true)
    public String type;

    @Element
    public static class Error {

        @Text
        public BigDecimal value;

        @Attribute(name = "qualifier")
        public String qualifier;
    }

}
