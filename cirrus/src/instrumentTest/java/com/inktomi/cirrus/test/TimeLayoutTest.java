package com.inktomi.cirrus.test;

import android.test.ActivityTestCase;

import com.inktomi.cirrus.forecast.TimeLayout;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by mruno on 8/12/13.
 */
public class TimeLayoutTest extends TestCase {

    public void testOnlyStartDates(){
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

        TimeLayout layout = new TimeLayout();
        layout.startValidTime = startDates;

        Calendar find = new GregorianCalendar();
        find.set(2013, Calendar.AUGUST, 13, 6, 0, 0);

        int index = layout.getIndexForTime(find.getTime());

        assertEquals("Did not return proper index", 0, index);
    }

    public void testStartAndEndDates(){
        List<Date> startDates = new ArrayList<Date>(5);
        List<Date> endDates = new ArrayList<Date>(5);

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

        TimeLayout layout = new TimeLayout();
        layout.startValidTime = startDates;

        Calendar find = new GregorianCalendar();
        find.set(2013, Calendar.AUGUST, 13, 6, 0, 0);

        int index = layout.getIndexForTime(find.getTime());

        assertEquals("Did not return proper index", 0, index);
    }
}
