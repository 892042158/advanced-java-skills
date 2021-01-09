package top.xmindguoguo.java8.solrjdbc.model;

import lombok.Data;

/**
 * 链接数据库的model 属性
 * 
 * @ClassName DataBaseModel
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2019年2月21日 下午2:56:37
 *
 */
@Data
public class DataBaseModel {
    // id
    private Long id;
    // 数据库的名称
    private String dbName;
    // 数据库的类型 ，例如是mysql 还是oracle
    private String type;
    // 链接数据库的URL
    private String dbUrl;
    // 使用的驱动名称
    private String dbDriver;
    // 用户名称
    private String dbUserName;
    // 密码
    private String dbPassword;
}
