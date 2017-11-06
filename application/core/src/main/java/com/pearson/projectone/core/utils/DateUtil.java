package com.pearson.projectone.core.utils;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import com.pearson.projectone.core.support.data.DayDTO;
/**
 * Created by uphilji on 4/17/17.
 */
public class DateUtil {


    /**
     * This method returns the current date
     *
     * @return
     */
    public static Date getDate(){
        return new Date();
    }

    /**
     * Reconstruct the Date given a year , month and dayOfMonth.
     * If the time zone is present, the time is aligned to the timezone as well.
     *
     * @param year
     * @param month
     * @param dayOfMonth
     * @param timeZone
     * @return
     */
    public static Date getDate(int year, int month, int dayOfMonth, TimeZone timeZone){
        GregorianCalendar  calendar = new GregorianCalendar(year,month,dayOfMonth);
        if (timeZone != null) {
            calendar.setTimeZone(timeZone);
        }
        return calendar.getTime();
    }


    public static DayDTO getDayDTO(int year, int month, int dayOfMonth, TimeZone timeZone){
        DayDTO  dayDTO = null;
        if(month >1 && dayOfMonth >0 && dayOfMonth <=31){
            dayDTO = new DayDTO();
            dayDTO.setDay(dayOfMonth);
            dayDTO.setMonth(month);
        }
        return dayDTO;
    }

    /**
     *
     * @param date
     * @return
     */
    public static DayDTO getDayDTO(Date date){
        return new DayDTO(date);
    }
}
