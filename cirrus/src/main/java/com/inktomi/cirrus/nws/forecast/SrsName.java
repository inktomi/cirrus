package com.inktomi.cirrus.nws.forecast;

import org.simpleframework.xml.Element;

@Element
public enum SrsName {

    WGS_1984("WGS 1984");

    private final String value;

    SrsName(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SrsName fromValue(String v) {
        for (SrsName c: SrsName.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
