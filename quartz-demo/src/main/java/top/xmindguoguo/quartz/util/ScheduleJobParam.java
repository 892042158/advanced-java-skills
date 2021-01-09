package top.xmindguoguo.quartz.util;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class ScheduleJobParam implements Serializable {
    private static final long serialVersionUID = -6929621612070076964L;
    private Long jobId;
    private String jobName;
    private Date createTime;
    private String jobCron;
    private Date jobTime;
    private String jobGroup;
    private String jobDesc;
    private String jobClass;
}
