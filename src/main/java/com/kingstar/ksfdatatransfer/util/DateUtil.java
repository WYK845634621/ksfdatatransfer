package com.kingstar.ksfdatatransfer.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2020/5/14 9:51
 */
@Slf4j
public class DateUtil {

    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String HHMM = "HH:mm";
    public static final String YYYYMMDD_HMS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYYMMDDHMS = "yyyyMMddHHmmss";

    /**
     * 根据milli毫秒以后的时间
     *
     * @param milli 毫秒数差值
     * @return
     */
    public static Date getBeforeDayDate(long milli) {
        return new Date(System.currentTimeMillis() + milli);
    }

    /**
     * 获取下一个工作日期默认间隔1天
     *
     * @param beforeDate 上次执行日期
     * @param time       字符串类型 时:分 13:20
     * @return
     */
    public static Date getNextDayWorkDate(Date beforeDate, String time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(beforeDate.getTime() + 1000 * 60 * 60 * 24);
        String[] timeSplit = time.split(":");
        // 时
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeSplit[0]));
        // 分
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeSplit[1]));
        // 秒
        calendar.set(Calendar.SECOND, 0);
        // 毫秒
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    public static String getPastDate(int past, String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        // 时
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        // 分
        calendar.set(Calendar.MINUTE, 0);
        // 秒
        calendar.set(Calendar.SECOND, 0);
        // 毫秒
        calendar.set(Calendar.MILLISECOND, 0);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String result = format.format(today);
        return result;
    }

    /**
     * @param @param  sdate
     * @param @param  bdate
     * @param @return    设定文件 
     * @throws
     * @Description: TODO(计算两个日期 【 日期类型 】 之间的时间距离) 
     */
    public static Map<String, Long> timesBetween(Date sdate, Date bdate) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long hour = 0;
        long min = 0;
        long sec = 0;
        long diff = 0;
        try {
            sdate = df.parse(df.format(sdate));
            bdate = df.parse(df.format(bdate));
            long stime = sdate.getTime();
            long btime = bdate.getTime();
            if (stime > btime) {
                diff = stime - btime;
            } else {
                diff = btime - stime;
            }
            hour = diff / (60 * 60 * 1000);
            min = diff / (60 * 1000) - hour * 60;
            sec = diff / 1000 - hour * 60 * 60 - min * 60;
        } catch (ParseException e) {
            log.error("timesBetween", e);
        }

        Map<String, Long> timeMap = new HashMap<>();
        timeMap.put("Hour", hour);
        timeMap.put("Min", min);
        timeMap.put("Sec", sec);
        return timeMap;

    }

    public static void main(String[] args) throws ParseException {//随机生成一位整数
        List<Date> timesBetweenDates = getTimesBetweenTime("2018-01-01 00:00:00", "2019-01-03 23:59:59", 2);
//        int random = (int) (Math.random()*9+1);
//        String valueOf = String.valueOf(random);
//        //生成uuid的hashCode值
//        int hashCode = UUID.randomUUID().toString().hashCode();
//        //可能为负数
//        if(hashCode<0){
//            hashCode = -hashCode;
//        }
//        String value = valueOf + String.format("%015d", hashCode);
//        System.out.println(value);
    }

    /**
     * 获取date指定  时:分  的时间
     * 如果 time 为空或格式不正确 则直接返回date
     *
     * @param date 修改前时间
     * @param time 字符串类型 时:分 13:20
     * @return
     */
    public static Date getDateSetHourMinute(Date date, String time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        if (StringUtils.isEmpty(time)) {
            return date;
        }
        String[] timeSplit = time.split(":");
        if (timeSplit.length != 2) {
            return date;
        }
        // 时
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeSplit[0]));
        // 分
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeSplit[1]));
        // 秒
        calendar.set(Calendar.SECOND, 0);
        // 毫秒
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }


    public static String dateToString(Date date, String format) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date parse(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException("时间解析错误 " + date + ":" + format);
        }
    }

    /**
     * 获取date当日0点整点
     *
     * @param date 上次执行日期
     * @return
     */
    public static Date getDayStartDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        // 时
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        // 分
        calendar.set(Calendar.MINUTE, 0);
        // 秒
        calendar.set(Calendar.SECOND, 0);
        // 毫秒
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getBeforeDate(Integer hour) {
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        c.setTime(new Date());
        c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) - hour);
        return c.getTime();
    }

    /**
     * 获取date当日最大时间 到秒
     */
    public static Date getDayEndDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        // 时
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        // 分
        calendar.set(Calendar.MINUTE, 59);
        // 秒
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 功能描述
     *
     * @param [startTime, endTime, type]  type 1 以小时为间隔， 2 :以天为间隔
     * @return java.util.List<java.lang.String>
     * @author
     * @date 2019/10/12
     */
    public static List<Date> getTimesBetweenTime(String startTime, String endTime, Integer type) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtil.YYYYMMDD_HMS);
        Date start = dateFormat.parse(startTime);
        Date end = dateFormat.parse(endTime);
        // 如果时间传反了
        long time1 = start.getTime();
        long time2 = end.getTime();
        Date midTime = new Date();
        if (time1 > time2) {
            midTime = start;
            start = end;
            end = midTime;
        }
        List<Date> result = new ArrayList<Date>();
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(start);
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(end);
        while (startCal.before(endCal)) {
            result.add(startCal.getTime());
            if (1 == type) {
                startCal.add(Calendar.HOUR_OF_DAY, 1);
            } else if (2 == type) {
                startCal.add(Calendar.DAY_OF_YEAR, 1);
            }
        }

        return result;
    }

    /**
     * 获取时间间隔十分表格式
     *
     * @param start
     * @param end
     * @return
     */
    public static String getHsm(Date start, Date end) {
        if (end == null || start == null || start.getTime() > end.getTime()) {
            return null;
        }
        long l = (end.getTime() - start.getTime()) / 1000;
        long h = l / 3600;
        long m = (l - h * 3600) / 60;
        long s = (l - h * 3600 - 60 * m);
        StringBuffer buffer = new StringBuffer();
        if (h > 0L) {
            buffer.append(h).append("h ");
        }
        if (m > 0L || h > 0) {
            buffer.append(m).append("m ");
        }
        buffer.append(s).append("s");
        return buffer.toString();
    }
}
