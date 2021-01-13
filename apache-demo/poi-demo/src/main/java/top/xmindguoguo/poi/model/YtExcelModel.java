package top.xmindguoguo.poi.model;

import lombok.Data;

import java.util.Date;

/**
 * ClassName: YtExcelModel
 * 
 * @author 于国帅
 * @Date 2018-03-27 16:58
 */
@Data
//@TableName("yt_excel")
public class YtExcelModel {

    /**
     * id
     * 
     */

    private Long id;

    /**
     * titleRow 标题所在的行
     * 
     */

    private Integer titleRow;
    /**
     * 备注
     * 
     */

    private String memo;

    /**
     * 上传的文件的保存位置
     * 
     */

    private String fileurl;

    /**
     * 临时表名称
     * 
     */

    private String temptablename;

    /**
     * 数据源id
     * 
     */

    private Long datasetid;

    /**
     * 删除状态
     * 
     */

    private Integer state;

    /**
     * 标识数据是否导入成功 0 未导入 1导入成功 2失败
     * 
     */
    private Integer importFlag;
    /**
     * 数据导入失败的excel错误文件 存在的url
     * 
     */
    private String errorFileUrl;
    /**
     * 文件类型
     * 
     */
    private String fileType;
    /**
     * xml文件节点名称
     * 
     */
    private String nodeEleName;
    /**
     * 创建时间
     * 
     */
    private Date createtime;

    /**
     * 更新时间
     * 
     */

    private Date updatetime;

    /**
     * 创建人id
     * 
     */

    private Long createby;

    /**
     * 修改人id
     * 
     */

    private Long modifyby;

    public Integer getTitleRow() {
        if (this.titleRow == null) {
            this.titleRow = 0;
        }
        return this.titleRow > 65535 ? 65535 : this.titleRow;
    }

}