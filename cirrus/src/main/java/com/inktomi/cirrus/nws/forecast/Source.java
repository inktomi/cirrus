package com.inktomi.cirrus.nws.forecast;

import org.simpleframework.xml.Element;

@Element
public class Source {

    @Element(name = "more-information", required = true)
    public String moreInformation;

    @Element(name = "production-center")
    public ProductionCenter productionCenter;

    @Element
    public String disclaimer;

    @Element
    public String credit;

    @Element(name = "credit-logo")
    public String creditLogo;

    @Element
    public String feedback;
}
