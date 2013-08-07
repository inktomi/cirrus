package com.inktomi.cirrus.nws;

import org.simpleframework.xml.transform.Transform;

/**
 * Created by mruno on 8/6/13.
 */
public class EnumTransform implements Transform<Enum> {
    private final Class type;

    public EnumTransform(Class type) {
        this.type = type;
    }

    public Enum read(String value) throws Exception {
        for (Object o : type.getEnumConstants()) {
            String test = o.toString();
            test = test.replaceAll(" ", "_");

            if (test.equalsIgnoreCase(value.replaceAll(" ", "_"))) {
                return (Enum) o;
            }
        }
        return null;
    }

    public String write(Enum value) throws Exception {
        return value.toString();
    }
}