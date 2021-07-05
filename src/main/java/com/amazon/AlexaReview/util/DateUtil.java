package com.amazon.AlexaReview.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class DateUtil {

    public static String formartDate(String strDate) throws ParseException {
        if (strDate != null && !strDate.isEmpty()) {
            SimpleDateFormat sdf;
            SimpleDateFormat sdf1;

            sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            return (sdf1.format(sdf.parse(strDate)));
        }
        return null;
    }
}
