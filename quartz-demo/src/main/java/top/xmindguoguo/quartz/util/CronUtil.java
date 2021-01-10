package top.xmindguoguo.quartz.util;

import org.apache.commons.lang.StringUtils;
import org.quartz.TriggerUtils;
import org.quartz.impl.triggers.CronTriggerImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CronUtil {
    /**
     * 校验cron表达式是否正确
     * 
     * @Title checkCron
     * @author 吕凯
     * @date 2018年2月22日 下午3:43:54
     * @param cron
     * @return boolean
     */
    public static boolean checkCron(String cron) {
        CronTriggerImpl cronTriggerImpl = new CronTriggerImpl();
        try {
            cronTriggerImpl.setCronExpression(cron); // 这里写要准备猜测的cron表达式
            TriggerUtils.computeFireTimes(cronTriggerImpl, null, 1);// 这个是重点，一行代码搞定~~
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public static List<String> getExcuteTime(String cron) {
        return getExcuteTime(cron, 20);
    }

    /**
     * 
     * @desc 计算表达式近20次时间
     * @auth josnow
     * @date 2017年5月31日 下午12:16:25
     * @param cron
     * @return
     * @throws ParseException
     */
    @SuppressWarnings("unchecked")
    public static List<String> getExcuteTime(String cron, Integer size) {
        if (StringUtils.isBlank(cron)) {
            return Collections.EMPTY_LIST;
        }
        CronTriggerImpl cronTriggerImpl = new CronTriggerImpl();
        try {
            cronTriggerImpl.setCronExpression(cron);
        } catch (ParseException e) {
            return Collections.EMPTY_LIST;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 2);// 把统计的区间段设置为从现在到2年后的今天（主要是为了方法通用考虑，如那些1个月跑一次的任务，如果时间段设置的较短就不足20条)
//        List<Date> dates = TriggerUtils.computeFireTimesBetween(cronTriggerImpl, null, now, calendar.getTime());// 这个是重点，一行代码搞定~~
        List<Date> dates = TriggerUtils.computeFireTimes(cronTriggerImpl, null, size);// 这个是重点，一行代码搞定~~
        List<String> list = new ArrayList<>(size == null ? 20 : size);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < dates.size(); i++) {
            list.add(dateFormat.format(dates.get(i)));
        }
        return list;
    }

    public static void main(String[] args) {
//          getExcuteTime("0 0 0 ? * 1L", 10);
        List<String> dates = getExcuteTime("0 0 0 ? * 1-2", 10);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < dates.size(); i++) {
            System.out.println(dateFormat.format(dates.get(i)));
        }
//          getExcuteTime("0 0 * * * ? 2018-2019", 10);
//        getExcuteTime("? * * * * ?", 10);
    }
}
