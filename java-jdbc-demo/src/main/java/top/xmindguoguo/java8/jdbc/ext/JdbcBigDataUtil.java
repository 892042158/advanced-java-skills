package top.xmindguoguo.java8.jdbc.ext;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 操作大数据 jdbc 工具类
 * 
 * @ClassName JdbcUtil
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2019年2月21日 下午4:37:47
 *
 */
public class JdbcBigDataUtil {
    /**
     * 获取当前结果集的所有字段名称
     * 
     * @Title getFieldNameList
     * @author 于国帅
     * @date 2019年2月21日 下午4:14:18
     * @param metaData
     * @return
     * @throws SQLException
     *             List<String>
     */
    public static List<String> getFieldNameList(ResultSetMetaData metaData) {
        List<String> colNameList = new ArrayList<>();
        try {
            int count = metaData.getColumnCount() + 1;
            for (int i = 1; i < count; i++) {
                colNameList.add(metaData.getColumnLabel(i));
            }
        } catch (SQLException e) {
            return colNameList;
        }
        return colNameList;
    }

    /**
     * 获取 只能够向下移动的 Statement
     * 
     * @Title createStatement
     * @author 于国帅
     * @date 2019年2月21日 下午4:38:07
     * @param connection
     * @param batchSize
     * @param maxRows
     * @return
     * @throws SQLException
     *             Statement
     */
    public static Statement createStatement(final Connection connection, final int batchSize, final int maxRows) throws SQLException {
        // TYPE_FORWARD_ONLY 而默认的TYPE_FORWARD_ONLY参数只允许结果集的游标向下移动。 在从ResultSet（结果集）中读取记录的时，对于访问过的记录就自动释放了内存。
        // ResultSet.CONCUR_READ_ONLY 就是类似只读 属性，不可仪更改的啊！不能用结果集更新数据。
        Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        statement.setFetchSize(batchSize); // 设置从数据库取得多行的行数大小
        statement.setMaxRows(maxRows); // 将此 Statement 对象生成的所有 ResultSet 对象可以包含的最大行数限制设置为给定数
        return statement;
    }

    public static ResultSet executeStatement(Statement statement, String query) throws SQLException {
        boolean resultSetAvailable = statement.execute(query); // 如果是查询的话返回true，如果是更新或插入的话就返回false了；
        while (!resultSetAvailable && statement.getUpdateCount() != -1) {
            resultSetAvailable = statement.getMoreResults(); // Statement提供了一个getMoreResults()的方法，该方法能将当前Statement "指针" 移动到下一个结果集.
        }
        if (resultSetAvailable) {
            return statement.getResultSet();
        }
        return null;
    }

}
