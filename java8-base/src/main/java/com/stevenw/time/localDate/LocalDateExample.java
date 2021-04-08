package com.stevenw.time.localDate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author stevenw
 * @date 2019/7/26
 */
public class LocalDateExample {
    public static void main(String[] args) throws ParseException {
//        LocalDateExample localDateExample = new LocalDateExample();
//        localDateExample.baseUse();
//        Calendar calendar = Calendar.getInstance(Locale.JAPAN);
//        System.out.println(calendar.getCalendarType());
        /*SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String [] aids =   TimeZone.getAvailableIDs();
        for (String a : aids) {
            System.out.println(a);
        }
        calendar.setTime(simpleDateFormat.parse("2019-11-27"));
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY);
        System.out.println(calendar.toInstant());*/
    }
    public void baseUse(){
        //根据年月日获取LocalDate
        LocalDate localDate = LocalDate.of(2019,11,22);
        LocalDate today = LocalDate.now();

        //2019
        int year = localDate.getYear();
        //可以通过ChronoField枚举类型来获取
        year = localDate.get(ChronoField.YEAR);
        //7
        int month = localDate.getMonthValue();
        //26
        int day = localDate.getDayOfMonth();
        //查看一个月有多少天
        int days = localDate.lengthOfMonth();
        //修改日，年月也类似
        localDate.withDayOfMonth(10);
        localDate.with(ChronoField.DAY_OF_MONTH,10);
        //加日,年月只需替换ChronoUnit
        localDate.plus(5, ChronoUnit.DAYS);
        //减日,年月只需替换ChronoUnit
        localDate.minus(5,ChronoUnit.DAYS);
        System.err.println(localDate.getDayOfWeek().getValue());

    }
}
