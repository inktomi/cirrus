package com.inktomi.cirrus.forecast;

import org.simpleframework.xml.Element;

@Element
public class Head {
    @Element(name = "product")
    public Product product;

    @Element(name = "source")
    public Source source;
}
