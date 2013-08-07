package com.inktomi.cirrus.forecast;

import org.simpleframework.xml.Element;

@Element
public enum Category {

    ANALYSIS("analysis"),
    FORECAST("forecast"),
    ANALYSIS_AND_FORECAST("analysis and forecast"),
    CURRENT_OBSERVATIONS_AND_FORECAST("current observations and forecast"),
    OBSERVATIONS("observations"),
    GUIDANCE("guidance");

    private final String value;

    Category(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Category fromValue(String v) {
        for (Category c: Category.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
