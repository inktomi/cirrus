package com.inktomi.cirrus.nws.forecast;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

@Element
public class Area {

    @Element
    public Circle circle;

    @Element
    public Rectangle rectangle;

    @Attribute(name = "area-type")
    public String areaType;
}
