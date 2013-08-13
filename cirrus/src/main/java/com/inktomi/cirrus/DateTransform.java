package com.inktomi.cirrus;

import org.simpleframework.xml.transform.Transform;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mruno on 8/6/13.
 */
public class DateTransform implements Transform<Date> {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'kk:mm:ss");

    public DateTransform() {}

    public Date read(String value) throws Exception {
        // If we have NA, default to being valid now.
        if( value.equals("NA") ){
            return new Date();
        }

        return DATE_FORMAT.parse(value);
    }

    public String write(Date value) throws Exception {
        return DATE_FORMAT.format(value);
    }
}