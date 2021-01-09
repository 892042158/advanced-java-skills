package top.xmindguoguo.java8.solrjdbc;

import lombok.Data;
import top.xmindguoguo.java8.solrjdbc.database.ConnectionUtil;
import top.xmindguoguo.java8.solrjdbc.database.JdbcUtil;
import top.xmindguoguo.java8.solrjdbc.model.DataBaseModel;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 封装了 根据sql 封装的链接池
 * 
 * @ClassName JdbcDataSource
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2019年2月21日 下午3:01:36
 *
 */
//@Slf4j
public class JdbcDataSource {
    private DataBaseModel databaseModel; // 链接数据的信息

    public JdbcDataSource(DataBaseModel databaseModel) {
        this.databaseModel = databaseModel;
    }

    public static void main(String[] args) {
        DataBaseModel jdbcModel = new DataBaseModel();
        jdbcModel.setDbDriver("com.mysql.jdbc.Driver");
        jdbcModel.setDbUrl(
                "jdbc:mysql://127.0.0.1:3306/springboot-boot?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true&failOverReadOnly=false");
        jdbcModel.setDbUserName("root");
        jdbcModel.setDbPassword("root");
        JdbcDataSource jdbcDataSource = new JdbcDataSource(jdbcModel);
        String sql = "select * from t_system_menu";
        Iterator<Map<String, Object>> iterator = jdbcDataSource.getResultSetIterator(sql);
        int i = 0;
        while (iterator.hasNext()) {
            System.err.println(iterator.next());
            i++;
        }
        System.err.println("总共+" + i);
    }

    // 支持查询
    public Iterator<Map<String, Object>> getResultSetIterator(String querySql) {
        try {
            // 返回迭代器 // 初始化 Statement // 执行查询
            return new ResultSetIterator(getConnection(), querySql);
        } catch (SQLException e) {
            return null;
        }
    }

    protected Connection getConnection() {
        // 初始化数据源
        ConnectionUtil.init(databaseModel.getDbDriver(), databaseModel.getDbUrl(), databaseModel.getDbUserName(),
                databaseModel.getDbPassword());
        // 初始化连接 Connection
        Connection connection = ConnectionUtil.createConnection(); // 返回对应的链接
        // 如果使用连接池 ，那么把链接放到连接池里面等待使用 选择关闭就可以
        return connection;
    }

}

/**
 * 封装大数据 Iterator 能够实现对应的数据源获取
 * 
 * @获取的model Map<String, Object> key 列名称，value 列数据
 * @ClassName ResultSetIterator
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2019年2月21日 下午3:02:48
 *
 */
@Data
class ResultSetIterator implements Iterator<Map<String, Object>> {
    private ResultSet resultSet; // 查询出来的结果集
    private List<String> fieldNameList; // 需要查询的字段的集合

    public ResultSetIterator(Connection connection, String querySql) throws SQLException {
        // 初始化 Statement 防止又一次查询把之前的结果集关闭
        Statement statement = JdbcUtil.createStatement(connection, 500, 0);
        // 执行查询
        this.resultSet = JdbcUtil.executeStatement(statement, querySql);
        // 设置字段
        this.fieldNameList = JdbcUtil.getFieldNameList(this.resultSet.getMetaData());
    }

    @Override
    public boolean hasNext() {
        try {
            return resultSet.next();
        } catch (SQLException e) {
            // 关闭链接
            return false;
        }
    }

    /**
     * 返回当前结果集的一条
     * 
     * @Title next
     * @author 于国帅
     * @date 2019年2月21日 下午4:09:05
     * @return
     * @see Iterator#next()
     */
    @Override
    public Map<String, Object> next() {
        Map<String, Object> rowMap = new HashMap<>();
        try {
            for (String fieldName : this.fieldNameList) {
                // Use underlying database's type information except for BigDecimal and BigInteger
                // which cannot be serialized by JavaBin/XML. See SOLR-6165
                Object value;
                value = this.resultSet.getObject(fieldName);
                if (value instanceof BigDecimal || value instanceof BigInteger) {
                    rowMap.put(fieldName, value.toString());
                } else {
                    rowMap.put(fieldName, value);
                }
            }
        } catch (SQLException e) {
            return rowMap;
        }
        return rowMap;
    }

}
