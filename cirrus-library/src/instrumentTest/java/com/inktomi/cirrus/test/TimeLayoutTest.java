package com.inktomi.cirrus.test;

import android.test.ActivityTestCase;

import com.inktomi.cirrus.forecast.TimeLayout;

import junit.framework.TestCase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Tests that we can get the proper index out from start and end dates given an input datetime.
 *
 * Created by mruno on 8/12/13.
 */
public class TimeLayoutTest extends TestCase {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'kk:mm:ss");

    public void testNonConsecutiveTimes() throws ParseException {
        List<Date> startDates = new ArrayList<Date>(5);
        List<Date> endDates = new ArrayList<Date>(5);

        // 8-20 8/12
        startDates.add(DATE_FORMAT.parse("2013-08-12T08:00:00-07:00"));
        endDates.add(DATE_FORMAT.parse("2013-08-12T20:00:00-07:00"));

        // 8-20 8/13
        startDates.add(DATE_FORMAT.parse("2013-08-13T08:00:00-07:00"));
        endDates.add(DATE_FORMAT.parse("2013-08-13T20:00:00-07:00"));

        // 8-20 8/14
        startDates.add(DATE_FORMAT.parse("2013-08-14T08:00:00-07:00"));
        endDates.add(DATE_FORMAT.parse("2013-08-14T20:00:00-07:00"));

        // 8-20 8/15
        startDates.add(DATE_FORMAT.parse("2013-08-15T08:00:00-07:00"));
        endDates.add(DATE_FORMAT.parse("2013-08-15T20:00:00-07:00"));

        // and so on..
        startDates.add(DATE_FORMAT.parse("2013-08-16T08:00:00-07:00"));
        endDates.add(DATE_FORMAT.parse("2013-08-16T20:00:00-07:00"));

        startDates.add(DATE_FORMAT.parse("2013-08-17T08:00:00-07:00"));
        endDates.add(DATE_FORMAT.parse("2013-08-17T20:00:00-07:00"));

        startDates.add(DATE_FORMAT.parse("2013-08-18T08:00:00-07:00"));
        endDates.add(DATE_FORMAT.parse("2013-08-18T20:00:00-07:00"));

        TimeLayout layout = new TimeLayout();
        layout.startValidTime = startDates;
        layout.endValidTime = endDates;

        Calendar find = new GregorianCalendar();
        find.set(2013, Calendar.AUGUST, 12, 22, 0, 0);

        int index = layout.getIndexForTime(find.getTime());
        assertEquals("Requesting a maximum temperature in the night time should result in a -1.", -1, index);
    }

    public void testOnlyStartDates(){
        List<Date> startDates = getStartDates();

        TimeLayout layout = new TimeLayout();
        layout.startValidTime = startDates;

        Calendar find = new GregorianCalendar();
        find.set(2013, Calendar.AUGUST, 13, 6, 0, 0);

        int index = layout.getIndexForTime(find.getTime());

        assertEquals("Did not return proper index", 0, index);
    }

    public void testStartAndEndDates(){
        List<Date> startDates = getStartDates();
        List<Date> endDates = getEndDates();

        TimeLayout layout = new TimeLayout();
        layout.startValidTime = startDates;
        layout.endValidTime = endDates;

        Calendar find = new GregorianCalendar();
        find.set(2013, Calendar.AUGUST, 13, 6, 0, 0);

        int index = layout.getIndexForTime(find.getTime());

        assertEquals("Did not return proper index", 0, index);
    }

    private List<Date> getEndDates() {
        List<Date> endDates = new ArrayList<Date>(5);

        Calendar endOne = new GregorianCalendar();
        endOne.set(2013, Calendar.AUGUST, 13, 7, 0, 0);

        Calendar endTwo = new GregorianCalendar();
        endTwo.set(2013, Calendar.AUGUST, 13, 9, 0, 0);

        Calendar endThree = new GregorianCalendar();
        endThree.set(2013, Calendar.AUGUST, 13, 10, 0, 0);

        Calendar endFour = new GregorianCalendar();
        endFour.set(2013, Calendar.AUGUST, 13, 12, 0, 0);

        Calendar endFive = new GregorianCalendar();
        endFive.set(2013, Calendar.AUGUST, 13, 14, 0, 0);

        endDates.add(endOne.getTime());
        endDates.add(endTwo.getTime());
        endDates.add(endThree.getTime());
        endDates.add(endFour.getTime());
        endDates.add(endFive.getTime());

        return endDates;
    }

    private List<Date> getStartDates() {
        List<Date> startDates = new ArrayList<Date>(5);

        Calendar one = new GregorianCalendar();
        one.set(2013, Calendar.AUGUST, 13, 5, 0, 0);

        Calendar two = new GregorianCalendar();
        two.set(2013, Calendar.AUGUST, 13, 7, 0, 0);

        Calendar three = new GregorianCalendar();
        three.set(2013, Calendar.AUGUST, 13, 9, 0, 0);

        Calendar four = new GregorianCalendar();
        four.set(2013, Calendar.AUGUST, 13, 10, 0, 0);

        Calendar five = new GregorianCalendar();
        five.set(2013, Calendar.AUGUST, 13, 12, 0, 0);

        startDates.add(one.getTime());
        startDates.add(two.getTime());
        startDates.add(three.getTime());
        startDates.add(four.getTime());
        startDates.add(five.getTime());
        return startDates;
    }
}
