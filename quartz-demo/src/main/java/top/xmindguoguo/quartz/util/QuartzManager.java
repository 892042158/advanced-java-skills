package top.xmindguoguo.quartz.util;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

@Slf4j
public class QuartzManager {

    private static SchedulerFactory schedulerFactory = null;

    private static SchedulerFactory getSchedulerFactory() {
        if (schedulerFactory == null) {
            schedulerFactory = new StdSchedulerFactory();
        }
        return schedulerFactory;
    }

    /**
     * @Description: 添加一个定时任务
     * 
     * @param jobName
     *            任务名
     * @param jobGroupName
     *            任务组名
     * @param triggerName
     *            触发器名
     * @param triggerGroupName
     *            触发器组名
     * @param jobClass
     *            任务
     * @param cron
     *            时间设置，参考quartz说明文档
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, String jobClass,
            String cron, boolean start) {
        try {
            Scheduler sched = getSchedulerFactory().getScheduler();
            // 任务名，任务组，任务执行类
            Class classObject = null;

            List<Map<String, Class<?>>> scheduledlist = ScanScheduledUtil.loadScheduledModel();
            for (int i = 0; i < scheduledlist.size(); i++) {
                Map<String, Class<?>> map = scheduledlist.get(i);
                for (String strid : map.keySet()) {
                    if (strid.equals(jobClass)) {
                        classObject = map.get(strid);
                    }
                }

            }
            if (classObject == null)
                return;
            JobDetail jobDetail = JobBuilder.newJob(classObject).withIdentity(jobName, jobGroupName).build();
            // 触发器
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            // 触发器名,触发器组
            triggerBuilder.withIdentity(triggerName, triggerGroupName);
            triggerBuilder.startNow();
            // 触发器时间设定
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
            // 创建Trigger对象
            CronTrigger trigger = (CronTrigger) triggerBuilder.build();

            // 调度容器设置JobDetail和Trigger
            sched.scheduleJob(jobDetail, trigger);

            // 启动
            if (start) {
                sched.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description: 添加一个定时任务
     * 
     * @param jobName
     *            任务名
     * @param jobGroupName
     *            任务组名
     * @param triggerName
     *            触发器名
     * @param triggerGroupName
     *            触发器组名
     * @param jobClass
     *            任务
     * @param cron
     *            时间设置，参考quartz说明文档
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, String jobClass,
            String cron) {
        try {
            Scheduler sched = getSchedulerFactory().getScheduler();
            // 任务名，任务组，任务执行类
            Class classObject = null;
            List<Map<String, Class<?>>> scheduledlist = ScanScheduledUtil.loadScheduledModel();
            for (int i = 0; i < scheduledlist.size(); i++) {
                Map<String, Class<?>> map = scheduledlist.get(i);
                for (String strid : map.keySet()) {
                    if (strid.equals(jobClass)) {
                        classObject = map.get(strid);
                    }
                }

            }
            if (classObject == null)
                return;
            JobDetail jobDetail = JobBuilder.newJob(classObject).withIdentity(jobName, jobGroupName).build();

            // 触发器
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            // 触发器名,触发器组
            triggerBuilder.withIdentity(triggerName, triggerGroupName);
            triggerBuilder.startNow();
            // 触发器时间设定
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
            // 创建Trigger对象
            CronTrigger trigger = (CronTrigger) triggerBuilder.build();

            // 调度容器设置JobDetail和Trigger
            sched.scheduleJob(jobDetail, trigger);

            // 启动
            if (!sched.isShutdown()) {
                sched.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class classObject,
            String cron, ScheduleJobParam params) {
        try {
            Scheduler sched = getSchedulerFactory().getScheduler();
//            sched.clear();   这个方法是清除所有触发器和工作
            // 任务名，任务组，任务执行类
            if (classObject == null)
                return;
            JobDetail jobDetail = JobBuilder.newJob(classObject).withIdentity(jobName, jobGroupName).build();
            jobDetail.getJobDataMap().put("ScheduleJobParam", params);

            // 触发器
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            // 触发器名,触发器组
            triggerBuilder.withIdentity(triggerName, triggerGroupName);
            triggerBuilder.startNow();
            // 触发器时间设定
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
            // 创建Trigger对象
            CronTrigger trigger = (CronTrigger) triggerBuilder.build();

            // 调度容器设置JobDetail和Trigger
            sched.scheduleJob(jobDetail, trigger);

            // 启动
            if (!sched.isShutdown()) {
                sched.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class classObject,
            String cron, Map<String, Object> parameter) {
        try {
            Scheduler sched = getSchedulerFactory().getScheduler();
            // 任务名，任务组，任务执行类
            if (classObject == null)
                return;
            JobDetail jobDetail = JobBuilder.newJob(classObject).withIdentity(jobName, jobGroupName).build();
            // 添加任务执行的参数
            parameter.forEach((k, v) -> {
                jobDetail.getJobDataMap().put(k, v);
            });
            // 触发器
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            // 触发器名,触发器组
            triggerBuilder.withIdentity(triggerName, triggerGroupName);
            triggerBuilder.startNow();
            // 触发器时间设定
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
            // 创建Trigger对象
            CronTrigger trigger = (CronTrigger) triggerBuilder.build();

            // 调度容器设置JobDetail和Trigger
            sched.scheduleJob(jobDetail, trigger);

            // 启动
            if (!sched.isShutdown()) {
                sched.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void addOnceJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class classObject,
            Map<String, Object> parameter) {
        try {
            Scheduler sched = getSchedulerFactory().getScheduler();
            Date runTime = evenMinuteDate(new Date());
            JobDetail jobDetail = newJob(classObject).withIdentity(jobName, jobGroupName).build();
            // 添加任务执行的参数
            parameter.forEach((k, v) -> {
                jobDetail.getJobDataMap().put(k, v);
            });
            Trigger trigger = newTrigger().withIdentity(triggerName, triggerGroupName).startAt(runTime).build();
            sched.scheduleJob(jobDetail, trigger);
            sched.getListenerManager().addJobListener(new QuartzListener(), KeyMatcher.keyEquals(new JobKey(jobName, jobGroupName)));
            // 启动
            if (!sched.isShutdown()) {
                sched.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description: 修改一个任务的触发时间
     * 
     * @param jobName
     * @param jobGroupName
     * @param triggerName
     *            触发器名
     * @param triggerGroupName
     *            触发器组名
     * @param cron
     *            时间设置，参考quartz说明文档
     */
    public static void modifyJobTime(String jobName, String jobGroupName, String triggerName, String triggerGroupName, String cron) {
        try {
            Scheduler sched = getSchedulerFactory().getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerKey);
            if (trigger == null) {
                return;
            }

            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(cron)) {
                /** 方式一 ：调用 rescheduleJob 开始 */
                // 触发器
                TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
                // 触发器名,触发器组
                triggerBuilder.withIdentity(triggerName, triggerGroupName);
                triggerBuilder.startNow();
                // 触发器时间设定
                triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
                // 创建Trigger对象
                trigger = (CronTrigger) triggerBuilder.build();
                // 方式一 ：修改一个任务的触发时间
                sched.rescheduleJob(triggerKey, trigger);
                /** 方式一 ：调用 rescheduleJob 结束 */

                /** 方式二：先删除，然后在创建一个新的Job */
                // JobDetail jobDetail = sched.getJobDetail(JobKey.jobKey(jobName, jobGroupName));
                // Class<? extends Job> jobClass = jobDetail.getJobClass();
                // removeJob(jobName, jobGroupName, triggerName, triggerGroupName);
                // addJob(jobName, jobGroupName, triggerName, triggerGroupName, jobClass, cron);
                /** 方式二 ：先删除，然后在创建一个新的Job */
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description: 暂停一个任务
     * 
     * @param jobName
     * @param jobGroupName
     * @param triggerName
     * @param triggerGroupName
     */
    public static void pauseJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
        try {
            Scheduler sched = getSchedulerFactory().getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            sched.pauseTrigger(triggerKey);// 停止触发器
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void startJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, String jobClass,
            String cron) {
        try {
            removeJob(jobName, jobGroupName, triggerName, triggerGroupName);
            addJob(jobName, jobGroupName, triggerName, triggerGroupName, jobClass, cron);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void startJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class<?> jobClass,
            String cron, ScheduleJobParam params) {
        try {
//             JobDetail jobDetail = sched.getJobDetail(JobKey.jobKey(jobName, jobGroupName));
//             Class<? extends Job> jobClass = jobDetail.getJobClass();
            removeJob(jobName, jobGroupName, triggerName, triggerGroupName);
            addJob(jobName, jobGroupName, triggerName, triggerGroupName, jobClass, cron, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description: 移除一个任务
     * 
     * @param jobName
     * @param jobGroupName
     * @param triggerName
     * @param triggerGroupName
     */
    public static void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
        try {
            Scheduler sched = getSchedulerFactory().getScheduler();

            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);

            sched.pauseTrigger(triggerKey);// 停止触发器
            sched.unscheduleJob(triggerKey);// 移除触发器
            sched.deleteJob(JobKey.jobKey(jobName, jobGroupName));// 删除任务
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description:启动所有定时任务
     */
    public static void startJobs() {
        try {
            Scheduler sched = getSchedulerFactory().getScheduler();
            sched.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description:关闭所有定时任务
     */
    public static void shutdownJobs() {
        try {
            Scheduler sched = getSchedulerFactory().getScheduler();
            if (!sched.isShutdown()) {
                sched.shutdown();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
