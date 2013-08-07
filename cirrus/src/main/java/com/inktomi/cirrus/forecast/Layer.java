package com.inktomi.cirrus.forecast;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Text;

import java.math.BigInteger;

@Element
public class Layer {

    @Text
    public BigInteger value;

    @Attribute(name = "vertical-coordinate")
    public String verticalCoordinate;
}
