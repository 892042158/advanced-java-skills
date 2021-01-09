package top.xmindguoguo.quartz.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@ScheduledName(id = "exchangeContentJob")
@Slf4j
public class ExchangeBasicJob extends BaseJob {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // ScheduleJob任务运行时具体参数，可自定义
        ScheduleJobParam scheduleJob = (ScheduleJobParam) context.getMergedJobDataMap().get("ScheduleJobParam");
        if (scheduleJob != null && StringUtils.isNotBlank(scheduleJob.getJobClass())) {
//            Long start = System.currentTimeMillis();
            String taskid = scheduleJob.getJobClass();
            log.warn("执行任务开始::" + taskid);
//            ExchangeService.exchange(taskid);
//            Long end = System.currentTimeMillis();
//            log.warn("执行任务结束::" + taskid);
//            log.warn("耗时：=====" + (end - start));
        } else {
            log.warn("执行任务::" + scheduleJob);
        }
    }

}
