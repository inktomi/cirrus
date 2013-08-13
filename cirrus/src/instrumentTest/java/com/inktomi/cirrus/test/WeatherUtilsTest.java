package com.inktomi.cirrus.test;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import com.inktomi.cirrus.DateTransform;
import com.inktomi.cirrus.EnumTransform;
import com.inktomi.cirrus.WeatherUtils;
import com.inktomi.cirrus.forecast.DWML;
import com.inktomi.cirrus.forecast.TemperatureValue;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.transform.Matcher;
import org.simpleframework.xml.transform.Transform;

import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by mruno on 8/12/13.
 */
public class WeatherUtilsTest extends AndroidTestCase {
    private static final String TAG = WeatherUtilsTest.class.getName();

    private DWML oneDay;
    private DWML beginDate;

    private Serializer mSerializer = new Persister(new Matcher() {
        public Transform match(Class type) throws Exception {
            if (type.isEnum()) {
                return new EnumTransform(type);
            }

            if( type == Date.class ){
                return new DateTransform();
            }

            return null;
        }
    });

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        InputStream oneDayXml = getContext().getResources().getAssets().open("ndfd-1day.xml");
        InputStream beginDateXml = getContext().getResources().getAssets().open("ndfd-2013-08-12.xml");

        beginDate = mSerializer.read(DWML.class, beginDateXml);
        oneDay = mSerializer.read(DWML.class, oneDayXml);

        assertNotNull("Failed to read in one day XML", oneDay);
        assertNotNull("Failed to read in begin date XML", beginDate);
    }

    @SmallTest
    public void testGetForecastMaxTemperature(){

        Calendar when = new GregorianCalendar();
        when.set(Calendar.DAY_OF_MONTH, 12);
        when.set(Calendar.MONTH, Calendar.AUGUST);
        when.set(Calendar.YEAR, 2013);
        when.set(Calendar.HOUR, 8);
        when.set(Calendar.MINUTE, 0);
        when.set(Calendar.SECOND, 0);

        TemperatureValue value = WeatherUtils.getForecastMaximumTemperature(oneDay, when.getTime());

        assertNotNull("Temperature value should not be null for input.", value);
        assertEquals("Temperature value did not have the proper temperature.", 74, value.value.intValue());
    }

    @SmallTest
    public void testGetForecastMinTemperature(){

        Calendar when = new GregorianCalendar();
        when.set(Calendar.DAY_OF_MONTH, 12);
        when.set(Calendar.MONTH, Calendar.AUGUST);
        when.set(Calendar.YEAR, 2013);
        when.set(Calendar.HOUR, 23);
        when.set(Calendar.MINUTE, 0);
        when.set(Calendar.SECOND, 0);

        TemperatureValue value = WeatherUtils.getForecastMinimumTemperature(oneDay, when.getTime());

        assertNotNull("Temperature value should not be null for input.", value);
        assertEquals("Temperature value did not have the proper temperature.", 62, value.value.intValue());
    }

    @SmallTest
    public void testGetForecastHourlyTemperature(){

        Calendar when = new GregorianCalendar();
        when.set(Calendar.DAY_OF_MONTH, 12);
        when.set(Calendar.MONTH, Calendar.AUGUST);
        when.set(Calendar.YEAR, 2013);
        when.set(Calendar.HOUR, 8);
        when.set(Calendar.MINUTE, 0);
        when.set(Calendar.SECOND, 0);

        TemperatureValue value = WeatherUtils.getForecastHourlyTemperature(oneDay, when.getTime());

        assertNotNull("Temperature value should not be null for input.", value);
        assertEquals("Temperature value did not have the proper temperature.", 63, value.value.intValue());
    }
}
