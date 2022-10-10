package test;

import cn.hutool.core.date.*;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class TestTimeDemo {
    @Test
    public void TestHuToolTimeIsIn(){
        DateTime dateTime = DateUtil.parse("2021-09-09 23:30", DatePattern.NORM_DATETIME_MINUTE_PATTERN);
        String startTime = "23:00";
        String endTime = "22:00";;
        System.out.println(checkTimeIsIn(dateTime, startTime, endTime));
    }

    public static boolean checkTimeIsIn(Date date, String beginDate, String endDate) {
        String day = DateUtil.format(date, DatePattern.NORM_DATE_PATTERN);
        Date startTime =
                DateUtil.parse(day + " " + beginDate, DatePattern.NORM_DATETIME_MINUTE_PATTERN);
        Date endTime =
                DateUtil.parse(day + " " + endDate, DatePattern.NORM_DATETIME_MINUTE_PATTERN)
                        .offset(DateField.SECOND, 59);
        if (startTime.after(endTime)) {
            return !DateUtil.isIn(date, endTime, startTime);
        }
        return DateUtil.isIn(date, startTime, endTime);
    }


    @Test
    public void TestHuToolTimeBetween(){
        DateTime time1 = DateUtil.parse("2021-10-01 00:00:00", DatePattern.NORM_DATETIME_PATTERN);
        DateTime time2 = DateUtil.parse("2021-10-29 00:00:00", DatePattern.NORM_DATETIME_PATTERN);
        System.out.println(DateUtil.between(time1, time2, DateUnit.DAY) >= 28); //true

        Long l = 100L;
        String r = "100";
        System.out.println(l.equals(r));  // false
        System.out.println(r.equals(l));  // false
        System.out.println(String.valueOf(l).equals(r)); // true
    }


    @Test
    public void TestHuToolTimeBetween2(){
        DateTime time1 = DateUtil.parse("2021-10-02 12:17:41", DatePattern.NORM_DATETIME_PATTERN);
        DateTime time2 = DateUtil.parse("2021-10-02 12:13:09", DatePattern.NORM_DATETIME_PATTERN);
        long serious = DateUtil.between(time1, time2, DateUnit.MINUTE);
        System.out.println(serious);

    }

}
