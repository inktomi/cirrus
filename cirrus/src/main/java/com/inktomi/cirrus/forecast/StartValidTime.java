package com.inktomi.cirrus.forecast;


import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Text;

import java.util.Date;

@Element
public class StartValidTime {

    @Text
    public Date value;

    @Attribute(name = "period-name")
    public String periodName;

}
