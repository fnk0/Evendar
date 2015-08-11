package com.gabilheri.choresapp.utils;

import android.text.format.Time;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 8/4/15.
 */
public final class TimeUtils {

    private TimeUtils() {}

    public static final SimpleDateFormat sBasicSimpleDateFormat;

    static  {
        sBasicSimpleDateFormat = new SimpleDateFormat("EEE, MMM dd yyyy", Locale.US);
    }

    public static String getToday() {
        return sBasicSimpleDateFormat.format(new Date());
    }

    public static String formatBasicDate(long time) {
        return sBasicSimpleDateFormat.format(time);
    }

    public static String formatShortDate(long time) {
        String formattedDate = sBasicSimpleDateFormat.format(time);
        return formattedDate.substring(0, formattedDate.length() - 6);
    }

    // To make it easy to query for the exact date, we normalize all dates that go into
    // the database to the start of the the Julian day at UTC.
    public static long normalizeDate(long startDate){
        Time time = new Time();
        time.set(startDate);
        int julianDay = Time.getJulianDay(startDate, time.gmtoff);
        return time.setJulianDay(julianDay);
    }

}
