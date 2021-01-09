package top.xmindguoguo.quartz;

import common.utils.pinyin.PinyinUtil;
import org.apache.commons.lang.RandomStringUtils;
import study.test.demo.quartz.util.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

public class TestCronTask {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {

        /**
         * 测试同一个cron表达式的任务是否能够一起执行
         * 
         */
        String cronExpression = "0 0/1 * * * ? "; // 每分钟执行一次

        SystemTimetaskModel timetask = new SystemTimetaskModel(); // WebUtil.getModel(request, SystemTimetaskModel.class, true);
        // ==== 页面上设置的
        String jobClassName = "t1ask" + RandomStringUtils.randomAlphanumeric(8);
        timetask.setJobClass(jobClassName);
        timetask.setTaskName("任务名称");
        timetask.setTaskdescribe("用户的描述");
        timetask.setCronExpression(cronExpression); // 每分钟执行一次
        // =====页面上设置的

        timetask.setId(System.currentTimeMillis());
        timetask.setTaskNo(PinyinUtil.getPinYin(timetask.getTaskName()).toUpperCase());
        timetask.setTriggerName(timetask.getTaskName() + "触发器名称");
        timetask.setTriggerNo(PinyinUtil.getPinYin(timetask.getTriggerName()).toUpperCase() + "_TRIGGER");
        timetask.setCreateby(1L);
        timetask.setCreatetime(new Date());
        timetask.setCronExpression(cronExpression);
//        timetask.setYtQuartzId(ytQuartzId);

        // 开始添加任务
        String jobName = timetask.getTaskName();
        String jobGroupName = timetask.getTaskNo();
        String triggerName = timetask.getTriggerName();
        String triggerGroupName = timetask.getTriggerNo();
        Class<?> jobClass = ExchangeBasicJob.class;
        ScheduleJobParam params = new ScheduleJobParam();
        params.setJobClass(timetask.getJobClass());
        QuartzManager.startJob(jobName, jobGroupName, triggerName, triggerGroupName, jobClass, timetask.getCronExpression(), params);

        // 创建第二个任务
        SystemTimetaskModel timetask2 = new SystemTimetaskModel();
//        BeanUtils.copyProperties(timetask2, timetask);  
        // ==== 页面上设置的
        String jobClassName2 = "t2ask" + RandomStringUtils.randomAlphanumeric(8);
        timetask2.setJobClass(jobClassName2);
        timetask2.setTaskName("任务名称2");
        timetask2.setTaskdescribe("用户的描述2");
        timetask2.setCronExpression(cronExpression); // 每分钟执行一次
        // =====页面上设置的

        timetask2.setId(System.currentTimeMillis());
        timetask2.setTaskNo(PinyinUtil.getPinYin(timetask2.getTaskName()).toUpperCase());
        timetask2.setTriggerName(timetask2.getTaskName() + "触发器名称");
        timetask2.setTriggerNo(PinyinUtil.getPinYin(timetask2.getTriggerName()).toUpperCase() + "_TRIGGER");
        timetask2.setCreateby(1L);
        timetask2.setCreatetime(new Date());
        timetask2.setCronExpression(cronExpression);
//        timetask.setYtQuartzId(ytQuartzId);
        jobName = timetask2.getTaskName();
        jobGroupName = timetask2.getTaskNo();
        triggerName = timetask2.getTriggerName();
        triggerGroupName = timetask2.getTriggerNo();
        jobClass = ExchangeBasicJob2.class;
        params = new ScheduleJobParam();
        params.setJobClass(timetask2.getJobClass());
        QuartzManager.startJob(jobName, jobGroupName, triggerName, triggerGroupName, jobClass, timetask.getCronExpression(), params);

    }

    public void testAddjob() {
        String cronExpression = "0 0/1 * * * ? "; // 每分钟执行一次

        SystemTimetaskModel timetask = new SystemTimetaskModel(); // WebUtil.getModel(request, SystemTimetaskModel.class, true);
        // ==== 页面上设置的
        String jobClassName = "task" + RandomStringUtils.randomAlphanumeric(8);
        timetask.setJobClass(jobClassName);
        timetask.setTaskName("任务名称");
        timetask.setTaskdescribe("用户的描述");
        timetask.setCronExpression(cronExpression); // 每分钟执行一次
        // =====页面上设置的

        timetask.setId(System.currentTimeMillis());
        timetask.setTaskNo(PinyinUtil.getPinYin(timetask.getTaskName()).toUpperCase());
        timetask.setTriggerName(timetask.getTaskName() + "触发器名称");
        timetask.setTriggerNo(PinyinUtil.getPinYin(timetask.getTriggerName()).toUpperCase() + "_TRIGGER");
        timetask.setCreateby(1L);
        timetask.setCreatetime(new Date());
        timetask.setCronExpression(cronExpression);
//        timetask.setYtQuartzId(ytQuartzId);

        // 开始添加任务
        String jobName = timetask.getTaskName();
        String jobGroupName = timetask.getTaskNo();
        String triggerName = timetask.getTriggerName();
        String triggerGroupName = timetask.getTriggerNo();
        Class<?> jobClass = ExchangeBasicJob.class;
        ScheduleJobParam params = new ScheduleJobParam();
        params.setJobClass(timetask.getJobClass());
        QuartzManager.startJob(jobName, jobGroupName, triggerName, triggerGroupName, jobClass, timetask.getCronExpression(), params);

    }
}
