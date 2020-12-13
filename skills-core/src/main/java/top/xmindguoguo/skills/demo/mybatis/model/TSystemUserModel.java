package top.xmindguoguo.skills.demo.mybatis.model;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: TSystemUser
 * @author: 于国帅
 * @Description:
 * @Date: 2020/11/28 15:52
 * @Version: v1.0
 */
@Data
public class TSystemUserModel implements Serializable {
    private static final long serialVersionUID = 4427199980616355080L;

    /**
     * 主键id
     */
    private Long id;


    /**
     * 头像
     */
    private String avatar;

    /**
     * 账号
     */

    private String account;

    /**
     * 密码
     */

    private String password;

    /**
     * md5密码盐
     */
    private String salt;

    /**
     * 名字
     */
    private String name;

    /**
     * 生日
     */
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String birthday;

    /**
     * 性别（1：男 2：女）
     */
    private Integer sex;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 部门id
     */
    private Long deptId;

    /**
     * 状态状态(0：启用 2：冻结 -1：删除）
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 旧密码
     */
    private String oldPassword;
}
