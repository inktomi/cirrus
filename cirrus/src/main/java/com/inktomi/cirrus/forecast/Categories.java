package com.inktomi.cirrus.forecast;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

@Element
public class Categories {

    @Element
    public String name;

    @Element(name = "categories-key", required = true)
    public String categoriesKey;

    @Element
    public ValueList valueList;

    @Attribute(name = "type", required = true)
    public String type;

    @Attribute(name = "probability-type")
    public Probability probabilityType;
}
