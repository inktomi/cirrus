package com.inktomi.cirrus.nws.forecast;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

@Element
public class Circle {

    @Element(required = true)
    public Point point;

    @Element(required = true)
    public Radius radius;

    @Attribute(name = "summarization", required = false)
    public String summarization;
}
