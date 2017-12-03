package charles.com.milu.Utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by PIG18 on 1/25/2017.
 */

public class TimeUtility {
    public static String getCurrentTimeString(){
        try {
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            TimeZone tz1 = TimeZone.getTimeZone("UTC");
            format1.setTimeZone(tz1);

            Date date1 = new Date();

            String result = format1.format(date1);
            return result;

        } catch (Exception e) {
            return "";
        }
    }

    public static String getLocalTimeString(String timeString) {
        try {
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            TimeZone tz1 = TimeZone.getTimeZone("UTC");
            format1.setTimeZone(tz1);
            Date date1 = format1.parse(timeString);

            SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            TimeZone tz2 = TimeZone.getDefault();
            format2.setTimeZone(tz2);
            String result = format2.format(date1);
            return result;

        } catch (Exception e) {
            return timeString;
        }
    }

    public static String getLocalTimeStringForChat(String timeString){
        try {
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            TimeZone tz1 = TimeZone.getTimeZone("UTC");
            format1.setTimeZone(tz1);
            Date date1 = format1.parse(timeString);

            Date date_now = new Date();

            SimpleDateFormat format2 = new SimpleDateFormat("MM-dd");
            TimeZone tz2 = TimeZone.getDefault();
            format2.setTimeZone(tz2);

            SimpleDateFormat format3 = new SimpleDateFormat("HH:mm");
            TimeZone tz3 = TimeZone.getDefault();
            format3.setTimeZone(tz3);

            String day1 = format2.format(date1);
            String day_now = format2.format(date_now);

            SimpleDateFormat format4 = new SimpleDateFormat("HH:mm MM-dd");
            TimeZone tz4 = TimeZone.getDefault();
            format3.setTimeZone(tz4);

            String time1 = format3.format(date1);
            String time2 = format4.format(date1);

            if(day1.equals(day_now)){
                return time1;
            }else{
                return time2;
            }

        } catch (Exception e) {
            return timeString;
        }
    }

    public static String getTimeForMember(String timeString){
        try {
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            TimeZone tz1 = TimeZone.getTimeZone("UTC");
            format1.setTimeZone(tz1);
            Date date1 = format1.parse(timeString);

            Date date_now = new Date();

            SimpleDateFormat format2 = new SimpleDateFormat("MM-dd");
            TimeZone tz2 = TimeZone.getDefault();
            format2.setTimeZone(tz2);

            SimpleDateFormat format3 = new SimpleDateFormat("HH:mm");
            TimeZone tz3 = TimeZone.getDefault();
            format3.setTimeZone(tz3);

            String day1 = format2.format(date1);
            String day_now = format2.format(date_now);

            String time1 = format3.format(date1);

            if(day1.equals(day_now)){
                return time1;
            }else{
                return day1;
            }

        } catch (Exception e) {
            return timeString;
        }
    }


}
