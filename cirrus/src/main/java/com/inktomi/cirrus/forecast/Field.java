package com.inktomi.cirrus.forecast;

import org.simpleframework.xml.Element;

@Element
public enum Field {

    METEOROLOGICAL("meteorological");

    private final String value;

    Field(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Field fromValue(String v) {
        for (Field c: Field.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
