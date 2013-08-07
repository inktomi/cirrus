package com.inktomi.cirrus.nws.forecast;

import org.simpleframework.xml.Element;

@Element
public enum LikelihoodUnits {

    PERCENT("percent");

    private final String value;

    LikelihoodUnits(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static LikelihoodUnits fromValue(String v) {
        for (LikelihoodUnits c: LikelihoodUnits.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
