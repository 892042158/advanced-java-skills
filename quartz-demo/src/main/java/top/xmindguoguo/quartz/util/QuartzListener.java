package top.xmindguoguo.quartz.util;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import top.xmindguoguo.common.utils.model.ClassUtil;

import java.util.Date;

public class QuartzListener implements JobListener {
//    protected static final CreditBaseNewDao MYSQL_BASE_DAO = new CreditBaseNewDao();
    @Override
    public String getName() {
        return new Date().getTime() + "QuartzListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        String jobName = context.getJobDetail().getKey().getName();
        String jobGroupName = context.getJobDetail().getKey().getGroup();
        String triggerName = context.getTrigger().getKey().getName();
        String triggerGroupName = context.getTrigger().getKey().getGroup();
        // 一次性任务，执行完之后需要移除
        Long job_id = ClassUtil.obj2long(context.getJobDetail().getJobDataMap().get("JOBID"));
        Long trans_id = ClassUtil.obj2long(context.getJobDetail().getJobDataMap().get("TRANSID"));
        Long task_id = ClassUtil.obj2long(context.getJobDetail().getJobDataMap().get("TASKID"));

//        if (trans_id != 0L) {
//            QueryModel queryModel = new QueryModel();
//            queryModel.addEq("trans_id", trans_id);
//            queryModel.addEq("trans_status", 1);
//            KTransModel model = MYSQL_BASE_DAO.getOne(KTransModel.class, queryModel);
//            if (model != null) {
//                removeTransMonitor(model.getCreateby(), model.getTransId());
//                model.setTransStatus(2);
//                MYSQL_BASE_DAO.update(model, "trans_id");
//            }
//        }
//        if (job_id != 0L) {
//            QueryModel queryModel = new QueryModel();
//            queryModel.addEq("job_id", job_id);
//            queryModel.addEq("job_status", 1);
//            KJobModel model = MYSQL_BASE_DAO.getOne(KJobModel.class, queryModel);
//            if (model != null) {
//                removeJobMonitor(model.getCreateby(), model.getJobId());
//                model.setJobStatus(2);
//                MYSQL_BASE_DAO.update(model, "job_id");
//            }
//        }
//
//        if (task_id != 0L) {
//            SystemTimetaskModel model = MYSQL_BASE_DAO.findById(SystemTimetaskModel.class, task_id);
//            if (model != null) {
//                model.setIsstart(0);
//                MYSQL_BASE_DAO.update(model);
//            }
//        }
        QuartzManager.removeJob(jobName, jobGroupName, triggerName, triggerGroupName);
    }

    private void removeJobMonitor(Long userId, Long jobId) {
//        QueryModel monitorQuery = new QueryModel();
//        monitorQuery.addEq("createby", userId);
//        monitorQuery.addEq("monitor_job", jobId);
//        KJobMonitorModel templateOne = MYSQL_BASE_DAO.getOne(KJobMonitorModel.class, monitorQuery);
//        templateOne.setMonitorStatus(3);
//        StringBuilder runStatusBuilder = new StringBuilder();
//        runStatusBuilder.append(templateOne.getRunStatus()).append(new Date().getTime());
//        templateOne.setRunStatus(runStatusBuilder.toString());
//        MYSQL_BASE_DAO.update(templateOne, "monitor_id");
    }

    /**
     * @Title removeMonitor
     * @Description 移除监控
     * @param userId
     *            用户ID
     * @param transId
     *            转换ID
     * @return void
     */
    private void removeTransMonitor(Long userId, Long transId) {
//        QueryModel monitorQuery = new QueryModel();
//        monitorQuery.addEq("createby", userId);
//        monitorQuery.addEq("monitor_trans", transId);
//        KTransMonitorModel templateOne = MYSQL_BASE_DAO.getOne(KTransMonitorModel.class, monitorQuery);
//        templateOne.setMonitorStatus(3);
//        StringBuilder runStatusBuilder = new StringBuilder();
//        runStatusBuilder.append(templateOne.getRunStatus()).append(new Date().getTime());
//        templateOne.setRunStatus(runStatusBuilder.toString());
//        MYSQL_BASE_DAO.update(templateOne, "monitor_id");
    }
}
