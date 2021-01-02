package top.xmindguoguo.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
@Slf4j
public final class CmUtil {
    private static long pkId;
    private static Lock lock;

    static {
        /*  pkId = ClassUtil.obj2long(ConfigModel.MARK.getProperties("pkId"));*/
        lock = new ReentrantLock();
    }

    // 10w 数据 就会存在问题了
    public static synchronized long getPkId() {
        lock.lock();

        long arg2;
        try {
            long lTmp = System.currentTimeMillis();
            if (pkId < lTmp) {
                pkId = lTmp;
            } else {
                ++pkId;
            }

            /*    ConfigModel.MARK.writeProperty("pkId", String.valueOf(pkId));*/
            arg2 = pkId;
        } finally {
            lock.unlock();
        }

        return arg2;
    }

    public static synchronized long getPkId(String YYYY_MM_DD) {
        if (YYYY_MM_DD == null) {
            return getPkId();
        } else {
            long lTmp = System.currentTimeMillis();
            if (getYYYY_MM_DD(new Timestamp(lTmp)).equals(YYYY_MM_DD)) {
                return getPkId();
            } else {
                if (pkId < lTmp) {
                    pkId = lTmp;
                } else {
                    ++pkId;
                }

                return (pkId + 28800000L) % 86400000L + getTimeStamp(YYYY_MM_DD).getTime();
            }
        }
    }

    public static String getYYYY_MM_DD(Timestamp timestamp) {
        if (timestamp == null) {
            return "";
        } else {
            GregorianCalendar g = new GregorianCalendar();
            g.setTime(timestamp);
            int year = g.get(1);
            int month = g.get(2) + 1;
            int day = g.get(5);
            return year + "-" + (month < 10 ? "0" + month : String.valueOf(month)) + "-" + (day < 10 ? "0" + day : String.valueOf(day));
        }
    }

    public static String getPreRqByDays(String rqStart, int days) {
        if (rqStart != null && !"".equals(rqStart)) {
            String rqStr = "";

            try {
                SimpleDateFormat ex = new SimpleDateFormat("yyyy-MM-dd");
                long timeLong = ex.parse(rqStart).getTime();
                boolean temp = false;
                if (days <= 24) {
                    rqStr = ex.format(new Date(timeLong - (long) (86400000 * days)));
                } else {
                    int temp1 = days - 24;
                    rqStr = getPreRqByDays(rqStart, 24);
                    rqStr = getPreRqByDays(rqStr, temp1);
                }
            } catch (Exception arg6) {
                arg6.printStackTrace();
            }

            return rqStr;
        } else {
            return null;
        }
    }

    public static Timestamp getTimeStamp(String YYYY_MM_DD) {
        int year = 1900;
        int month = 0;
        int day = 1;

        try {
            year = Integer.parseInt(YYYY_MM_DD.substring(0, 4));
        } catch (Exception arg6) {
            log.info(arg6.getMessage());
        }

        try {
            month = Integer.parseInt(YYYY_MM_DD.substring(5, 7)) - 1;
        } catch (Exception arg5) {
            log.info(arg5.getMessage());
        }

        try {
            day = Integer.parseInt(YYYY_MM_DD.substring(8, 10));
        } catch (Exception arg4) {
            log.info(arg4.getMessage());
        }

        GregorianCalendar g = new GregorianCalendar(year, month, day);
        return new Timestamp(g.getTime().getTime());
    }

    public static String getToday() {
        GregorianCalendar g = new GregorianCalendar();
        Timestamp today = new Timestamp(g.getTime().getTime());
        return today.toString().substring(0, 10);
    }

}
