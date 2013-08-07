package com.inktomi.cirrus.nws.forecast;

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

    @Attribute
    public float version;

    @Attribute(required = false)
    public String noNamespaceSchemaLocation;
}
