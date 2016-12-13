package com.inktomi.cirrus.forecast;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Text;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Element
public class TemperatureValue {
    private static final Pattern ALL_DIGITS = Pattern.compile("^\\d*$");

    public BigInteger value;

    @Attribute(name = "upper-range", required = false)
    public BigInteger upperRange;

    @Attribute(name = "lower-range", required = false)
    public BigInteger lowerRange;

    @Attribute(name = "type", required = false)
    public DataSource type;

    @Text(required = false)
    public void setValue(String value){
        Matcher allDigitMatcher = ALL_DIGITS.matcher(value);

        if( allDigitMatcher.matches() ){
            this.value = new BigInteger(value);
        }
    }

    @Text(required = false)
    public String getValue() {
        return value.toString();
    }
}
