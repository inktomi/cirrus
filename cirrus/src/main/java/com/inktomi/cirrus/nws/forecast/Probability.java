package com.inktomi.cirrus.nws.forecast;

import org.simpleframework.xml.Element;

@Element
public enum Probability {

    NONEXCEEDANCE("nonexceedance"),
    EXCEEDANCE("exceedance");

    private final String value;

    Probability(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Probability fromValue(String v) {
        for (Probability c: Probability.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
