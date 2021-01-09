package top.xmindguoguo.quartz.util;

import lombok.Data;

import java.util.Date;

/**
 * ClassName: SystemTimetaskModel
 *
 * @author 刘金浩
 * @Date 2018-01-29 11:32
 */
@Data
//@TableName("system_timetask")
public class SystemTimetaskModel {

    /**
     * Id
     */

    private Long id;

    /**
     * Createby
     */

    private Long createby;

    /**
     * Createtime
     */

    private Date createtime;

    /**
     * Taskid
     */

    private String taskName;
    private String taskNo;

    private String triggerName;
    private String triggerNo;

    private String jobClass;

    /**
     * CronExpression
     */
//    @ColumnName("CRON_EXPRESSION")
    private String cronExpression;

    /**
     * Taskdescribe
     */

    private String taskdescribe;

    /**
     * Iseffect
     */

    private Integer iseffect;

    /**
     * Isstart
     */

    private Integer isstart;
    private Long ytQuartzId; // yt定时器执行策略外键
    /**
     * Updateby
     */

    private Long updateby;

    /**
     * Updatetime
     */

    private Date updatetime;

}