package com.inktomi.cirrus.nws.forecast;

import org.simpleframework.xml.Element;

@Element
public enum OperationalMode {

    OFFICIAL("official"),
    DEVELOPMENTAL("developmental"),
    EXPERIMENTAL("experimental"),
    TEST("test");

    private final String value;

    OperationalMode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static OperationalMode fromValue(String v) {
        for (OperationalMode c: OperationalMode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
