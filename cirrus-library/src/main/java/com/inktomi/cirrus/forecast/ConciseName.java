package com.inktomi.cirrus.forecast;

import org.simpleframework.xml.Element;

@Element
public enum ConciseName {

    TIME_SERIES("time-series"),
    GLANCE("glance"),
    TABULAR_DIGITAL("tabular-digital"),
    DIGITAL_ZONE("digital-zone"),
    DWML_BY_DAY("dwmlByDay");

    private final String value;

    ConciseName(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ConciseName fromValue(String v) {
        for (ConciseName c: ConciseName.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
