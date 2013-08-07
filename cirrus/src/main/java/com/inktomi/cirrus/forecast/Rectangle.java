package com.inktomi.cirrus.forecast;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

@Element
public class Rectangle {

    @ElementList(required = true, inline = true)
    public List<Point> point;

    @Attribute(name = "summarization")
    public String summarization;
}
