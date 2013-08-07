package com.inktomi.cirrus.forecast;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Text;

@Element
public class City {

    @Text
    public String value;

    @Attribute(name = "state", required = true)
    public String state;

    @Attribute(name = "summarization")
    public String summarization;
}
