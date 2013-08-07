package com.inktomi.cirrus.nws.forecast;

import org.simpleframework.xml.Element;

@Element
public enum HeightUnits {

    FEET("feet"),
    METERS("meters");

    private final String value;

    HeightUnits(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static HeightUnits fromValue(String v) {
        for (HeightUnits c: HeightUnits.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
