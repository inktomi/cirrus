package com.inktomi.cirrus.nws.forecast;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

@Element
public class ValueForRange {

    @ElementList(inline = true)
    public List<PercentageValue> value;

    @ElementList(inline = true)
    public List<String> gt;

    @ElementList(inline = true)
    public List<String> ge;

    @ElementList(inline = true)
    public List<String> lt;

    @ElementList(inline = true)
    public List<String> le;

    @ElementList(inline = true)
    public List<String> eq;
}
