package com.tx.scheduling;

import com.tx.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
public class CountScheduling {

    @Autowired
    private StatisticsDailyService service;
    @Scheduled(cron = "0 0 12 * * ? ")
    public void  countScheduling(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(calendar.DATE,-1);
        String format = simpleDateFormat.format(calendar.getTime());
        service.countstatistics(format);
    }
//@Scheduled(cron = "* * * * * ? ")
//public static void main(String[] args) {
//    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//    Calendar gregorianCalendar = new GregorianCalendar();
//    gregorianCalendar.setTime(new Date());
//    gregorianCalendar.add(gregorianCalendar.DATE,-1);
//    String format = simpleDateFormat.format(gregorianCalendar.getTime());
//    System.out.println(format);
//}
}
