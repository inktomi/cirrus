package com.inktomi.cirrus.forecast;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Text;

import java.math.BigInteger;

@Element
public class Height {

    @Text
    public BigInteger value;

    @Attribute(name = "datum", required = true)
    public Datum datum;

    @Attribute(name = "height-units", required = false)
    public HeightUnits heightUnits;
}
