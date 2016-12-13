package com.inktomi.cirrus.forecast;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Text;

@Element
public class MoreWeatherInformation {

    @Text(required = false)
    public String value;

    @Attribute(name = "applicable-location", required = true)
    public String applicableLocation;
}