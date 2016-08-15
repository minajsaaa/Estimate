package com.owed.nobug.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    public static String getDateTime(String format) {
        SimpleDateFormat sf = new SimpleDateFormat(format, Locale.KOREA);
        Date date = new Date();
        return sf.format(date);
    }

    //  =======================================================================================

}
