package com.inktomi.cirrus.nws.forecast;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;

import java.util.List;

@Element
public class ProductionCenter {
    @ElementListUnion({
            @ElementList(entry="sub-center", type = String.class, inline = true, required = false),
    })
    public List<Object> values;
}
