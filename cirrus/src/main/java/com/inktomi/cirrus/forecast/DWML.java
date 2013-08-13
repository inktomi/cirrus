package com.inktomi.cirrus.forecast;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root
public class DWML {

    @Element(name = "head")
    public Head head;

    @ElementList(inline = true, name = "data")
    public List<Data> data;

    @Attribute(required = false)
    public float version;

    @Attribute(required = false)
    public String noNamespaceSchemaLocation;
}
