package com.inktomi.cirrus.forecast;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

@Element
public class Radius {
    @Attribute(name = "radius-units", required = true)
    public String radiusUnits;
}
