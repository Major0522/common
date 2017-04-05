package com.seagen.ecc.utils;

import java.util.Date;

public class DateUtilsTest {

	public static void main(String[] args) throws Exception {
		System.out.println(DateUtils.todayForZeroTime());
		System.out.println(DateUtils.todayForMidnight());
		
		System.out.println("-----------------");
        
		Date today = DateUtils.todayForMidnight();
        Date  beforeYesterday = DateUtils.dayForZeroTime(DateUtils.addDate(today, -2));
        System.out.println(today);
        System.out.println(beforeYesterday);
        System.out.println("---");
        System.out.println(DateUtils.addDate(today, -2));
        
		System.out.println("-----------------");

		String theDay = "2016-10-02";
		String nextDay = DateUtils.getNextDayStr(theDay);
        System.out.println("theDay = " + theDay);
        System.out.println("nextDay = " + nextDay);
		
		System.out.println("-----------------");

		theDay = "2016-1-2";
		nextDay = DateUtils.getNextDayStr(theDay);
        System.out.println("theDay = " + theDay);
        System.out.println("nextDay = " + nextDay);
		
		System.out.println("-----------------");
		
		String theMonth = "2016-10";
		String nextMonth = DateUtils.getNextMonthStr(theMonth);
        System.out.println("theMonth = " + theMonth);
        System.out.println("nextMonth = " + nextMonth);
		
		System.out.println("-----------------");

		theMonth = "2016-1";
		nextMonth = DateUtils.getNextMonthStr(theMonth);
        System.out.println("theMonth = " + theMonth);
        System.out.println("nextMonth = " + nextMonth);
		
		System.out.println("-----------------");
	}
	
}
