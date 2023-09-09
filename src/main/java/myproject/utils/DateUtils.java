package myproject.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    public static String convertDateToString(Date date, String format){
        SimpleDateFormat convertDate = new SimpleDateFormat(format);
        return convertDate.format(date);
    }

    public static String convertLocalDateTimeToString(LocalDateTime localDateTime, String format){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(formatter);
    }

}
