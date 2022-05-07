package xmindguoguo.okhttp;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2022/5/8 0:50
 * @Version: v1.0
 */
@Data
public class WenMingModel {
    /**
     * {
     * "a":"【sss】未终思南",
     * "m":5016806,
     * "n":"天问",
     * "r":14056299,
     * "s":13567279,
     * "t":"2022-05-07 05:06:37",
     * "aa":67240,
     * "op":353,
     * "ps":405519,
     * "rp":412,
     * "aid":2038,
     * "pid":"E36BC79C"
     * }
     *         // 测试学生
     *         ExportExcel<WenMingModel> ex = new ExportExcel<>();
     *         String[] headers = {"领主id", "领主名称", "联盟名称", "繁荣度","领主评分",
     *                 "联盟活跃","战功","攻占据点","夺回据点","占领积分"};
     */
    @ExcelProperty(value = "联盟名称",index = 3)
    private String a; //联盟名称
    @ExcelProperty(value ="战功",index = 7)
    private long m; //战功
    @ExcelProperty(value ="领主名称",index = 2)
    private String n; // 领主名称
    @ExcelProperty(value ="繁荣度",index = 4)
    private long r; //繁荣度
    @ExcelProperty(value ="领主评分",index = 5)
    private long s; //领主评分
    @ExcelProperty(value ="最新数据统计时间",index = 0)
    private String t;
    @ExcelProperty(value ="联盟活跃",index = 6)
    private long aa; //联盟活跃
    @ExcelProperty(value ="攻占据点",index = 8)
    private long op; //攻占据点
    @ExcelProperty(value ="占领积分",index = 10)
    private long ps; //占领积分
    @ExcelProperty(value ="夺回据点",index = 9)
    private long rp; //夺回据点
    @ExcelIgnore
    private int aid; //
    @ExcelProperty(value ="领主id",index = 1)
    private String pid; //领主id
    @ExcelProperty(value ="综合评分",index = 11)
    private long score; //领主id
}
