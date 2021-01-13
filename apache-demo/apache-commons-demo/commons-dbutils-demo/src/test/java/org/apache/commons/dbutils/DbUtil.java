
package org.apache.commons.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by N3verL4nd on 2017/6/8.
 */

class DbUtil {
    private static String url = "jdbc:mysql:///news?useUnicode=true&characterEncoding=UTF-8&useSSL=true";
    private static String username = "root";
    private static String password = "root";
    static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                conn = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
}

//public class DBUtilsTest {
//    public static void testQuery() {
//        // 创建连接
//        Connection conn = DbUtil.getConnection();
//        // 创建SQL执行工具
//        QueryRunner queryRunner = new QueryRunner();
//        List<Person> list = null;
//        try {
//            // 执行SQL查询，并获取结果
//            list = queryRunner.query(conn, "select * from persons", new BeanListHandler<>(Person.class));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        if (list != null) {
//            // 输出查询结果
//            list.forEach(System.out::println);
//        }
//        // 关闭数据库连接
//        DbUtils.closeQuietly(conn);
//    }
//
//    public static void testUpdate() {
//        // 创建连接
//        Connection conn = DbUtil.getConnection();
//        // 创建SQL执行工具
//        QueryRunner queryRunner = new QueryRunner();
//        int rows = 0;
//        try {
//            // 执行SQL插入
//            rows = queryRunner.update(conn, "INSERT INTO persons(name, age) VALUES(?, ?)", "阡陌", 24);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        System.out.println("成功插入" + rows + "条数据！");
//        // 关闭数据库连接
//        DbUtils.closeQuietly(conn);
//    }
//
//    public static void main(String[] args) {
//        testUpdate();
//        testQuery();
//
//    }
//}