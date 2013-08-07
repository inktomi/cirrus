package com.inktomi.cirrus.nws.forecast;

import java.util.Date;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Text;

@Element
public class CreationDate {
    @Text
    public Date value;

    @Attribute(name = "refresh-frequency", required = true)
    public String refreshFrequency;
}
