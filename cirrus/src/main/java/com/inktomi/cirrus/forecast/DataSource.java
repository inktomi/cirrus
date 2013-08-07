package com.inktomi.cirrus.forecast;

import org.simpleframework.xml.Element;

@Element
public enum DataSource {
    NDFD,
    RTMA;

    public String value() {
        return name();
    }

    public static DataSource fromValue(String v) {
        return valueOf(v);
    }

}
