package org.apache.commons.dbutils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.lang.ArrayUtils;

public class DBUtilsTest {
    @org.junit.Test
    public void test() {

    }

    @org.junit.Test
    public void testQuery() {
        // 创建连接
        Connection conn = DbUtil.getConnection();
        // 创建SQL执行工具
        QueryRunner queryRunner = new QueryRunner();
        List<Person> list = null;
        try {
            // 执行SQL查询，并获取结果
            list = queryRunner.query(conn, "select * from news", new BeanListHandler<>(Person.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (list != null) {
            // 输出查询结果
            for (Person person : list) {
                System.out.println(ArrayUtils.toString(person));
            }
        }
        // 关闭数据库连接
        DbUtils.closeQuietly(conn);
    }

}
