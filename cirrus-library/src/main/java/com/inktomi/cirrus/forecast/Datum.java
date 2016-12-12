package com.inktomi.cirrus.forecast;

import org.simpleframework.xml.Element;

@Element
public enum Datum {

    SURFACE("surface"),
    MEAN_SEA_LEVEL("mean sea level");

    private final String value;

    Datum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Datum fromValue(String v) {
        for (Datum c: Datum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
