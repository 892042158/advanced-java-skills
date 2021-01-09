package top.xmindguoguo.java8.jdbc.ext;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @ClassName: JdbcUtils
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/9 0:43
 * @Version: v1.0
 */
@Slf4j
public class JdbcUtils {
    private JdbcUtils() {

    }

    private static DataSource dataSource;
    private static Connection connection;

    public static void init(String path) {
        dataSource = readDataSource(path);
    }

    public static void destroy() {
        closeConnection(connection);
    }
    public static Connection getConnection() {
        if (connection == null) {
            openConnection();
        }
        return connection;
    }

    /**
     * 打开数据库连接
     */
    private static void openConnection() {
        try {
            Class.forName(dataSource.getDriver()); // 加载驱动
        } catch (ClassNotFoundException e) {
            log.error("init jdbc.Driver error:", e);
        }
        try {
            connection = DriverManager.getConnection(dataSource.getUrl(), dataSource.getUser(), dataSource.getPassword()); // 获取数据库连接
        } catch (SQLException e) {
            log.error("getConnection error:", e);
        }
    }

    /**
     * 读取数据库配置
     *
     * @param path
     * @return
     */
    private static DataSource readDataSource(String path) {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            log.error(" properties.load error", e);
        }
        DataSource dataSource = new DataSource();
        dataSource.setUrl(properties.getProperty("jdbc.url"));
        dataSource.setDriver(properties.getProperty("jdbc.driver"));
        dataSource.setUser(properties.getProperty("jdbc.user"));
        dataSource.setPassword(properties.getProperty("jdbc.password"));
        return dataSource;
    }

    /**
     * Close the given JDBC Connection and ignore any thrown exception.
     * This is useful for typical finally blocks in manual JDBC code.
     *
     * @param con the JDBC Connection to close (may be {@code null})
     */
    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                log.debug("Could not close JDBC Connection", ex);
            } catch (Throwable ex) {
                // We don't trust the JDBC driver: It might throw RuntimeException or Error.
                log.debug("Unexpected exception on closing JDBC Connection", ex);
            }
        }
    }

    /**
     * Close the given JDBC Statement and ignore any thrown exception.
     * This is useful for typical finally blocks in manual JDBC code.
     *
     * @param stmt the JDBC Statement to close (may be {@code null})
     */
    public static void closeStatement(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                log.trace("Could not close JDBC Statement", ex);
            } catch (Throwable ex) {
                // We don't trust the JDBC driver: It might throw RuntimeException or Error.
                log.trace("Unexpected exception on closing JDBC Statement", ex);
            }
        }
    }

    /**
     * Close the given JDBC ResultSet and ignore any thrown exception.
     * This is useful for typical finally blocks in manual JDBC code.
     *
     * @param rs the JDBC ResultSet to close (may be {@code null})
     */
    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                log.trace("Could not close JDBC ResultSet", ex);
            } catch (Throwable ex) {
                // We don't trust the JDBC driver: It might throw RuntimeException or Error.
                log.trace("Unexpected exception on closing JDBC ResultSet", ex);
            }
        }
    }

}
