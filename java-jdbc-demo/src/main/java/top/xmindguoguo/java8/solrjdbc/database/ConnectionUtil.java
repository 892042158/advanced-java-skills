package top.xmindguoguo.java8.solrjdbc.database;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

import java.sql.*;

/**
 * 数据库连接工具类 主要是创建连接
 *
 * @author JueYue
 * @date 2014年12月21日
 */
public class ConnectionUtil {
    private static Connection connection;
    private static String DB_DRIVER;
    private static String DB_URL;
    private static String DB_USER_NAME;
    private static String DB_PASSWORD;

    private static ThreadLocal<Connection> connectionTl = new ThreadLocal<>();

    private ConnectionUtil() {

    }

    private static ConnectionUtil instance;

    public static Statement createStatement() {
        try {
            /*      DatabaseMetaData dbMeta = connectionTl.get().getMetaData(); 
            //System.out.println("1111111111===="+dbMeta.supportsColumnAliasing()); */
            return connectionTl.get().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (Exception e) {
            throw new RuntimeException("创建 Statement 发生异常", e);
        }
    }

    public static Connection createConnection() {
        try {
            return connectionTl.get();
        } catch (Exception e) {
            throw new RuntimeException("创建 Statement 发生异常", e);
        }
    }

    public static Statement createThisStatement() {
        if (instance == null) {
            initThis();
        }
        try {
            return connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (Exception e) {
            throw new RuntimeException("创建 Statement 发生异常", e);
        }
    }

    private static void initThis() {
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException("创建 Connection 发生异常", e);
        }
    }

    public static void init(String deiver, String url, String username, String passwd) {
        try {
            // System.out.println(System.currentTimeMillis());
            Class.forName(deiver);
            // System.out.println(System.currentTimeMillis());
            connectionTl.set(DriverManager.getConnection(url, username, passwd));
            // System.out.println(System.currentTimeMillis());
        } catch (Exception e) {
            throw new RuntimeException("创建 Connection 发生异常", e);
        }
    }

    public static void checkConnection(String deiver, String url, String username, String passwd) {
        try {
            Driver driver = (Driver) Class.forName(deiver).newInstance();
            boolean urlflag = driver.acceptsURL(url);
            if (!urlflag) {
                throw new RuntimeException("请检查数据库地址!");
            }
            DriverManager.getConnection(url, username, passwd);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("请检查数据库类型!", e);
        } catch (CommunicationsException e) {
            throw new RuntimeException("请检查数据库地址!", e);
        } catch (SQLException e) {
            throw new RuntimeException("请检查用户名或密码,是否正确!", e);
        } catch (Exception e) {
            throw new RuntimeException("请检查驱动类型与数据库地址是否对应!", e);
        }
    }

    public static void close() {
        try {
            if (!connectionTl.get().isClosed())
                connectionTl.get().close();
        } catch (Exception e) {
            throw new RuntimeException("关闭 Connection 发生异常", e);
        }
    }

    public static void closeThis() {
        try {
            if (!connection.isClosed())
                connection.close();
        } catch (Exception e) {
            throw new RuntimeException("关闭 Connection 发生异常", e);
        }
    }

    public static void setDB_DRIVER(String dB_DRIVER) {
        DB_DRIVER = dB_DRIVER;
    }

    public static void setDB_URL(String dB_URL) {
        DB_URL = dB_URL;
    }

    public static void setDB_USER_NAME(String dB_USER_NAME) {
        DB_USER_NAME = dB_USER_NAME;
    }

    public static void setDB_PASSWORD(String dB_PASSWORD) {
        DB_PASSWORD = dB_PASSWORD;
    }

}
