package top.xmindguoguo.java8.jdbc;

/**
 * @ClassName: JacksonMain
 * @author: 于国帅
 * @Description:
 * @Date: 2020/12/20 1:34
 * @Version: v1.0
 */
public class JavaJdbcMain {
    /**
     * 写一下扩展历程
     * 一 jdbc的配置
     * 1.基础的jdbc
     *  觉得老是重复写这些Connection 感觉比较繁琐，所以封装了 工具类
     * 2.然后复制来复制去老是改配置链接数据库 所以配置与读取分离
     * 3.然后链接老是打开又关闭 浪费性能 所以有了数据库连接池
     *
     * 二 jdbc的操作数据库
     * 1.写的繁琐，查询sql，麻烦于是有了面向对象思想
     * 2.又为了能够重复复用所以有了
     *    springjdbc的JdbcTemplate
     *    apache 的 commons-dbutil
     * 3.再为了基于这种思想有了各种orm框架
     *   mybatis
     *   hibernate
     *   等等
     *
     */

}
