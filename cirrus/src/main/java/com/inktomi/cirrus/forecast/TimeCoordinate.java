package com.inktomi.cirrus.forecast;

import org.simpleframework.xml.Element;

@Element
public enum TimeCoordinate {

    UTC("UTC"),
    LOCAL("local");

    private final String value;

    TimeCoordinate(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TimeCoordinate fromValue(String v) {
        for (TimeCoordinate c: TimeCoordinate.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
