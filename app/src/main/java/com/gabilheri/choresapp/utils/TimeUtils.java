package com.gabilheri.choresapp.utils;

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
        sBasicSimpleDateFormat = new SimpleDateFormat("EEE, MM dd yyyy", Locale.US);
    }

    public static String getToday() {
        return sBasicSimpleDateFormat.format(new Date());
    }

    public static String formatBasicDate(long time) {
        return sBasicSimpleDateFormat.format(time);
    }
}
