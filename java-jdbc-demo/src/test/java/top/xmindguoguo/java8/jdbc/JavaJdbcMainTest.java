package top.xmindguoguo.java8.jdbc;

import org.junit.Test;

import java.sql.*;

/**
 * @ClassName: JavaJdbcMainTest
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/8 0:11
 * @Version: v1.0
 */
public class JavaJdbcMainTest {

    @Test
    public void testALL(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        // MySQL的JDBC连接语句
        // URL编写格式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
        String url = "jdbc:mysql://localhost:3306/java-jdbc-demo?user=root&password=root";
        // 数据库执行的语句
        // 查询语句
        String cmd = "select * from test;";
        try {
            Class.forName("com.mysql.jdbc.Driver"); // 加载驱动
            conn = DriverManager.getConnection(url); // 获取数据库连接
            stmt = conn.createStatement(); // 创建执行环境
//            stmt.execute(sql); // 执行SQL语句
            // 读取数据
            rs = stmt.executeQuery(cmd); // 执行查询语句，返回结果数据集
            rs.last(); // 将光标移到结果数据集的最后一行，用来下面查询共有多少行记录
            System.out.println("共有" + rs.getRow() + "行记录：");
            rs.beforeFirst(); // 将光标移到结果数据集的开头
            while (rs.next()) { // 循环读取结果数据集中的所有记录
                String rowString = "";
                for (int i = 1; i < 4; i++) {
                    rowString += rs.getString(i);
                }
                System.err.println(rowString);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动异常");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("数据库异常");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close(); // 关闭结果数据集
                if (stmt != null)
                    stmt.close(); // 关闭执行环境
                if (conn != null)
                    conn.close(); // 关闭数据库连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public  void  testSelect(){
        
    }
}