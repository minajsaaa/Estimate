package com.owed.nobug.util;

import java.text.DecimalFormat;

public class StringUtil {

    public static String toNumFormat(int num) {
        DecimalFormat decimal = new DecimalFormat("#,###");
        return decimal.format(num);
    }
}
