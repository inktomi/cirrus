package com.inktomi.cirrus.forecast;

import org.simpleframework.xml.Element;

@Element
public class Source {

    @Element(name = "more-information", required = true)
    public String moreInformation;

    @Element(name = "production-center", required = false)
    public ProductionCenter productionCenter;

    @Element(required = false)
    public String disclaimer;

    @Element(required = false)
    public String credit;

    @Element(name = "credit-logo", required = false)
    public String creditLogo;

    @Element(required = false)
    public String feedback;
}
