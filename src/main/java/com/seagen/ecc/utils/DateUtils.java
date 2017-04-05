package com.seagen.ecc.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public final static String DEFAULT_DATETIME = "yyyyMMddHHmmss";

    public final static String DEFAULT_DATETIME_FMT = "yyyy-MM-dd HH:mm:ss";

    public final static String DEFAULT_DATE_FMT = "yyyy-MM-dd";

    public static Date addDate(Date date, int num) {
        final Calendar cal = Calendar.getInstance();

        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, num);

        return cal.getTime();
    }

    public static Date addHour(Date date, int num) {
        final Calendar cal = Calendar.getInstance();

        cal.setTime(date);
        cal.add(Calendar.HOUR, num);

        return cal.getTime();
    }

    public static Date addMonth(Date date, int num) {
        final Calendar cal = Calendar.getInstance();

        cal.setTime(date);
        cal.add(Calendar.MONTH, num);

        return cal.getTime();
    }

    public static Date addSecond(Date date, int num) {
        final Calendar cal = Calendar.getInstance();

        cal.setTime(date);
        cal.add(Calendar.SECOND, num);

        return cal.getTime();
    }

    public static Long datetimeToLong() {
        // return Long.valueOf(new SimpleDateFormat(DEFAULT_DATETIME).format(new Date()));
        return datetimeToLong(new Date());
    }

    public static Long datetimeToLong(Date date) {
        return Long.valueOf(new SimpleDateFormat(DEFAULT_DATETIME).format(date));
    }

    public static String datetimeToString(Date date) {
        return datetimeToString(DEFAULT_DATETIME_FMT, date);
    }

    public static String datetimeToString(String fmt, Date date) {
        return new SimpleDateFormat(fmt).format(date);
    }

    public static String dateToString(Date date) {
        return datetimeToString(DEFAULT_DATE_FMT, date);
    }

    public static String endDateH(String time) {
        if (time != null) {
            time = time + " 23:59:59";
        }
        return time;
    }

    public static String format(Date time, String pattern) {
        final SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(time);
    }

    public static CharSequence format(Date time, String pattern, Locale local) {
        final SimpleDateFormat sdf = new SimpleDateFormat(pattern, local);
        return sdf.format(time);
    }

    /**
     * 得到今天的日期
     * 
     * @param formart
     * @return
     */
    public static String getCurrentDate(String formart) {
        return getDateTimeString(Calendar.getInstance(), formart);
    }

    /**
     * 得到当前日期的字符串格式
     * 
     * @param format
     *            日期格式,例如: "yyyyMMddHHmmss"
     * @return format格式的日期
     */
    public static String getDate() {
        final Date date = new Date();
        final DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        return df.format(date);
    }

    public static Date getDate(Integer hour, Integer minute, Integer second) {
        final Calendar cal = Calendar.getInstance();

        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);

        return cal.getTime();
    }

    /**
     * 取得给定字符串描述的日期对象，描述模式采用pattern指定的格式.
     * 
     * @param dateStr
     *            日期描述 从给定字符串的开始分析文本，以生成一个日期。该方法不使用给定字符串的整个文本。 有关日期分析的更多信息，请参阅 parse(String, ParsePosition) 方法。一个 String，应从其开始处进行分析
     * @param pattern
     *            日期模式
     * @return 给定字符串描述的日期对象。
     */
    public static Date getDateFromString(String dateStr, String pattern) {
        final SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date resDate = null;
        try {
            resDate = sdf.parse(dateStr);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return resDate;
    }

    public static Date getDateMidnight(Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    /**
     * 得到当前日期的字符串格式
     * 
     * @return "yyyy-MM-dd HH:mm:ss" 格式的日期
     */
    public static String getDateTimeStr() {
        return datetimeToString(new Date());
    }

    /**
     * 将日历转换成给定格式的日期字符串
     * 
     * @param c
     * @param formart
     * @return
     */
    public static String getDateTimeString(Calendar c, String formart) {
        final SimpleDateFormat sdf = new SimpleDateFormat(formart);
        return sdf.format(c.getTime());
    }

    public static Date getDateZeroTime(Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    // 当前时间与参数传进来的时间的相差间隔 单位为秒
    public static Long getInterval(Date date) {
        final Calendar cal = Calendar.getInstance();

        final Long interval = cal.getTime().getTime() - date.getTime();

        return interval / 1000;
    }

    public static int getIntervalDays(Date startDay, Date endDay) {
        // 确保startDay在endDay之前
        if (startDay.after(endDay)) {
            final Date cal = startDay;
            startDay = endDay;
            endDay = cal;
        }

        startDay = getDateZeroTime(startDay);
        endDay = getDateZeroTime(endDay);

        final long intrval = startDay.getTime() - endDay.getTime();
        // 根据毫秒数计算间隔天数
        return (int) (intrval / (1000 * 60 * 60 * 24));
    }

    /**
     * 获取两个日期间隔的天数，当前日期必须小于结束日期
     * 
     * @param currentDay
     * @param endDay
     * @return
     */
    public static int getIntervalDaysNu(Date currentDay, Date endDay) {
        if (currentDay.after(endDay)) {
            return -1;
        }
        currentDay = getDateZeroTime(currentDay);
        endDay = getDateZeroTime(endDay);

        final long intrval = endDay.getTime() - currentDay.getTime();
        // 根据毫秒数计算间隔天数
        return (int) (intrval / (1000 * 60 * 60 * 24));
    }

    /**
     * 获取某月的最后一天，返回yyyy-MM-dd
     */
    public static String getLastDayOfMonth(int year, int month) {
        final Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        final int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final String lastDayOfMonth = sdf.format(cal.getTime());
        return lastDayOfMonth;
    }

    /**
     * 根据获取一个唯一的序列号,组成=System.currentTimeMillis()+六位纳秒级的数字字符串
     */
    public static long getSerialNo() {
        final String naonTime = String.valueOf(System.nanoTime());
        final String suffix = naonTime.substring(naonTime.length() - 6, naonTime.length());
        return Long.parseLong(System.currentTimeMillis() + suffix);

    }

    public static int getWeekNameOrd(String weekDay) {
        if ("SUNDAY".equals(weekDay.toUpperCase())) {

            return Calendar.SUNDAY;
        } else if ("MONDAY".equals(weekDay.toUpperCase())) {

            return Calendar.MONDAY;
        } else if ("TUESDAY".equals(weekDay.toUpperCase())) {

            return Calendar.TUESDAY;
        } else if ("THURSDAY".equals(weekDay.toUpperCase())) {

            return Calendar.THURSDAY;
        } else if ("WEDNESDAY".equals(weekDay.toUpperCase())) {

            return Calendar.WEDNESDAY;
        } else if ("FRIDAY".equals(weekDay.toUpperCase())) {

            return Calendar.FRIDAY;
        } else if ("SATURDAY".equals(weekDay.toUpperCase())) {

            return Calendar.SATURDAY;
        } else {
            return Calendar.SUNDAY;
        }

    }

    /**
     * 判断一个日期是否在开始日期和结束日期之间。
     * 
     * @param srcDate
     *            目标日期 yyyy/MM/dd 或者 yyyy-MM-dd
     * @param startDate
     *            开始日期 yyyy/MM/dd 或者 yyyy-MM-dd
     * @param endDate
     *            结束日期 yyyy/MM/dd 或者 yyyy-MM-dd
     * @return 大于等于开始日期小于等于结束日期，那么返回true，否则返回false
     */
    public static boolean isInStartEnd(Date srcDate, Date startDate, Date endDate) {
        if (startDate.compareTo(srcDate) <= 0 && endDate.compareTo(srcDate) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断一个日期是否在开始日期和结束日期之间。
     * 
     * @param srcDate
     *            目标日期 yyyy/MM/dd 或者 yyyy-MM-dd
     * @param startDate
     *            开始日期 yyyy/MM/dd 或者 yyyy-MM-dd
     * @param endDate
     *            结束日期 yyyy/MM/dd 或者 yyyy-MM-dd
     * @return 大于等于开始日期小于等于结束日期，那么返回true，否则返回false
     */
    public static boolean isInStartEnd(String srcDate, String startDate, String endDate) {
        if (startDate.compareTo(srcDate) <= 0 && endDate.compareTo(srcDate) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(datetimeToLong());
        System.out.println(DateUtils.getSerialNo());
        System.out.println(Long.MAX_VALUE);
        Date date = new Date();
        date = DateUtils.addDate(date, -23);
        Date first = DateUtils.getFirstDayofMonth(date);
        System.out.println(first.before(date));
        System.out.println(date);
        System.out.println(first);
        System.out.println(DateUtils.date2descr(DateUtils.addSecond(new Date(), -3600*24*30)));
    }

    public static String parseCommonlyDateStr(String dateTimeStr) {
        if (null == dateTimeStr || dateTimeStr.isEmpty()) {
            return "-";
        }
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final DateFormat df = new SimpleDateFormat("MM月dd日 HH时mm分");
        Date date = null;
        try {
            date = sdf.parse(dateTimeStr);
        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return df.format(date);
    }

    public static Date parseDate(String dateStr) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * @Title: parseDate
     * @Description: String转换成date
     * @return Date
     * @param dateStr
     * @param pattern
     * @return
     */
    public static Date parseDate(String dateStr, String pattern) {
        final SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String parseDateHourStr(String dateTimeStr) {
        if (null == dateTimeStr || dateTimeStr.isEmpty()) {
            return "-";
        }
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final DateFormat df = new SimpleDateFormat("MM月dd日 HH时");
        Date date = null;
        try {
            date = sdf.parse(dateTimeStr);
        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return df.format(date);
    }

    /**
     * 获取DateUtils.getSerialNo()中产生的序列号产生的时间
     */
    public static long serialNo2Date(long serialNo) {
        final String s = String.valueOf(serialNo);
        return Long.parseLong(s.substring(0, s.length() - 6));

    }

    public static String startDateH(String time) {

        if (time != null) {
            time = time + " 00:00:00";
        }
        return time;
    }

    public static String toDateH(Date time) {
        final DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format1.format(time);
    }

    public static Date getLastDayofMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    public static boolean isLastDayofMonth(Date date) {
        String day = datetimeToString(DEFAULT_DATE_FMT, date);
        String lastDay = DateUtils.datetimeToString(DEFAULT_DATE_FMT, getLastDayofMonth(date));
        return day.equals(lastDay);
    }

    public static Date getFirstDayofMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    public static boolean isFirstDayofMonth(Date date) {
        String day = datetimeToString(DEFAULT_DATE_FMT, date);
        String firstDay = DateUtils.datetimeToString(DEFAULT_DATE_FMT, getFirstDayofMonth(date));
        return day.equals(firstDay);
    }

    public static String date2descr(Date date) {
        String format="yy-MM-dd HH:mm";
        Date dnow = new Date();
        if (dnow.before(date)) {
            return DateUtils.datetimeToString(format,date);
        }
        long d = date.getTime() / 1000;
        long now = dnow.getTime() / 1000;
        long se = now - d;
        if (se < 60) {
            return se + "秒前";
        } else if (se < 60 * 60) {
            return se / 60 + "分钟前";
        } else if (se < 60 * 60 * 24) {
            return se / 60 / 60 + "小时前";
        } else if (se < 60 * 60 * 24 * 30) {
            return se / 60 / 60 / 24 + "天前";
        } else {
            return DateUtils.datetimeToString(format,date);
        }
    }

	public static Date formatDefaultDate(Date date) {
		return getDateFromString(datetimeToString(date), DEFAULT_DATE_FMT);
	}
	
	/**
	 * 获取当天日期，时间设置为00:00:00.
	 * 
	 * @return
	 */
	public static Date todayForZeroTime() {
        final Calendar cal = Calendar.getInstance();

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
	}

	/**
	 * 获取当天日期，时间设置为23:59:59.
	 * 
	 * @return
	 */
	public static Date todayForMidnight() {
        final Calendar cal = Calendar.getInstance();

        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);

        return cal.getTime();
	}

	/**
	 * 获取某天日期，时间设置为00:00:00.
	 * 
	 * @return
	 */
	public static Date dayForZeroTime(Date day) {
        final Calendar cal = Calendar.getInstance();
        
        cal.setTime(day);
        
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
	}

	/**
	 * 获取当天日期，时间设置为23:59:59.
	 * 
	 * @return
	 */
	public static Date dayForMidnight(Date day) {
        final Calendar cal = Calendar.getInstance();
        
        cal.setTime(day);
        
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);

        return cal.getTime();
	}
	
	/**
	 * 输出yyyy-MM-dd格式的字符串日期的下一日的字符串（yyyy-MM-dd格式）。
	 *  
	 * @return 下一日的字符串日期（yyyy-MM-dd格式）。
	 */
	public static String getNextDayStr(String theDay) {
		if (theDay == null) {
			return null;
		}
			
		Date theTime = DateUtils.parseDate(theDay, "yyyy-MM-dd");
		
		return getNextDayStr(theTime);
	}

	public static String getNextDayStr(Date theDay) {
		if (theDay == null) {
			return null;
		}
			
		Date nextDay = DateUtils.addDate(theDay, 1);
		
		return DateUtils.dateToString(nextDay);
	}
	
	/**
	 * 输出yyyy-MM格式的字符串月份的下一月的字符串（yyyy-MM格式）。
	 *  
	 * @return 下一月的字符串日期（yyyy-MM格式）。
	 */
	public static String getNextMonthStr(String theMonth) {
		if (theMonth == null) {
			return null;
		}

		String theFirstDay = theMonth + "-01";
		Date loginTime = DateUtils.parseDate(theFirstDay, "yyyy-MM-dd");
		
		if (loginTime == null) {
			return null;
		}

		Date nextMonthDay = DateUtils.addMonth(loginTime, 1);
		String nextFirstDay = DateUtils.dateToString(nextMonthDay);
		
		return nextFirstDay.substring(0, 7);
	}

}
