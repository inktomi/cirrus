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
import java.util.List;

/**
 * Created by mruno on 8/12/13.
 */
public class WeatherUtilsTest extends AndroidTestCase {
    private static final String TAG = WeatherUtilsTest.class.getName();

    private DWML cloudy;

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

        InputStream cloudyXml = getContext().getResources().getAssets().open("cloudy-day.xml");

        cloudy = mSerializer.read(DWML.class, cloudyXml);

        assertNotNull("Failed to read in cloudy day XML", cloudy);
    }

    @SmallTest
    public void testGetForecastMaxTemperature(){

        Calendar when = new GregorianCalendar();
        when.set(Calendar.DAY_OF_MONTH, 15);
        when.set(Calendar.MONTH, Calendar.AUGUST);
        when.set(Calendar.YEAR, 2013);
        when.set(Calendar.HOUR_OF_DAY, 8);
        when.set(Calendar.MINUTE, 0);
        when.set(Calendar.SECOND, 0);
        when.set(Calendar.ZONE_OFFSET, -18000000);


        TemperatureValue value = WeatherUtils.getForecastMaximumTemperature(cloudy, when.getTime());

        assertNotNull("Temperature value should not be null for input.", value);
        assertEquals("Temperature value did not have the proper temperature.", 85, value.value.intValue());
    }

    @SmallTest
    public void testGetForecastMinTemperature(){

        Calendar when = new GregorianCalendar();
        when.set(Calendar.DAY_OF_MONTH, 15);
        when.set(Calendar.MONTH, Calendar.AUGUST);
        when.set(Calendar.YEAR, 2013);
        when.set(Calendar.HOUR_OF_DAY, 23);
        when.set(Calendar.MINUTE, 0);
        when.set(Calendar.SECOND, 0);
        when.set(Calendar.ZONE_OFFSET, -18000000);


        TemperatureValue value = WeatherUtils.getForecastMinimumTemperature(cloudy, when.getTime());

        assertNotNull("Temperature value should not be null for input.", value);
        assertEquals("Temperature value did not have the proper temperature.", 63, value.value.intValue());
    }

    @SmallTest
    public void testForecastWeatherType(){
        Calendar when = new GregorianCalendar();
        when.set(Calendar.DAY_OF_MONTH, 13);
        when.set(Calendar.MONTH, Calendar.AUGUST);
        when.set(Calendar.YEAR, 2013);
        when.set(Calendar.HOUR_OF_DAY, 15);
        when.set(Calendar.MINUTE, 0);
        when.set(Calendar.SECOND, 0);
        when.set(Calendar.ZONE_OFFSET, -18000000);

        String weatherType = WeatherUtils.getForecastWeatherConditions(cloudy, when.getTime());

        assertNotNull("Weather types should not be null for input.", weatherType);
        assertEquals("Weather type was not 'Scattered Thunderstorms'", "Scattered Thunderstorms", weatherType);
    }

    @SmallTest
    public void testCurrentWeatherType(){
        String weatherType = WeatherUtils.getCurrentWeatherConditions(cloudy);

        assertNotNull("Weather types should not be null for input.", weatherType);
        assertEquals("Weather type was not 'Mostly Cloudy'", "Mostly Cloudy", weatherType);
    }
}
